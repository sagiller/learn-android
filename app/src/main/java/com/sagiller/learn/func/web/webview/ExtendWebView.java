package com.sagiller.learn.func.web.webview;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.sagiller.learn.utils.ToastUtils;

/**
 * Created by sagiller on 16/4/15.
 */
public class ExtendWebView extends WebView {
    private Context mContext;
    private static final int ID_SAVEIMAGE = 1, ID_VIEWIMAGE = 2,ID_SHAREIMAGE = 3, ID_SAVELINK = 4, ID_SHARELINK = 5;
    public static final int PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private EventListener eventListener;

    public ExtendWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public ExtendWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }


    @Override
    protected void onCreateContextMenu(ContextMenu menu) {
        super.onCreateContextMenu(menu);

        HitTestResult result = getHitTestResult();

        MenuItem.OnMenuItemClickListener handler = new MenuItem.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case ID_SAVEIMAGE:
                        checkStoragePermissionThenDownload();
                        break;
                    case ID_SHAREIMAGE:
                        eventListener.shareImageViaIntent(Uri.parse(getHitTestResultExtra()));
                        break;
                    case ID_SHARELINK:
                        eventListener.shareTextViaIntent(getHitTestResultExtra());
                }

                return true;
            }
        };

        if (result.getType() == HitTestResult.IMAGE_TYPE ||
                result.getType() == HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
            menu.setHeaderTitle(result.getExtra());
            menu.add(0, ID_SAVEIMAGE, 0, "Save Image").setOnMenuItemClickListener(handler);
            menu.add(0, ID_VIEWIMAGE, 0, "View Image").setOnMenuItemClickListener(handler);
            menu.add(0, ID_SHAREIMAGE, 0, "Share Image").setOnMenuItemClickListener(handler);

        } else if (result.getType() == HitTestResult.ANCHOR_TYPE ||
                result.getType() == HitTestResult.SRC_ANCHOR_TYPE) {
            menu.setHeaderTitle(result.getExtra());
            menu.add(0, ID_SAVELINK, 0, "Save Link").setOnMenuItemClickListener(handler);
            menu.add(0, ID_SHARELINK, 0, "Share Link").setOnMenuItemClickListener(handler);
        }
    }

    private String getHitTestResultExtra() {
        HitTestResult result = getHitTestResult();
        return result.getExtra();
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }


    /**
     * Must override onRequestPermissionsResult() method in container activity or fragment:
     *
     * @Override public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
     * {
     * super.onRequestPermissionsResult(requestCode, permissions, grantResults);
     * webview.onRequestPermissionsResult(requestCode,permissions, grantResults);
     * }
     */
    private void checkStoragePermissionThenDownload() {
        int permissionCheck = ContextCompat.checkSelfPermission(mContext,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (eventListener != null) {
                eventListener.requestWriteExternalStoragePremissions();
            } else {
                //TODO Throw EventListener is null exception
            }
        } else {
            downloadImage(getHitTestResultExtra());
        }
    }

    private void downloadImage(String url) {
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        DownloadManager.Request r = new DownloadManager.Request(Uri.parse(url));
        r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        r.allowScanningByMediaScanner();
        r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        DownloadManager dm = (DownloadManager) mContext.getSystemService(Activity.DOWNLOAD_SERVICE);
        dm.enqueue(r);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloadImage(getHitTestResultExtra());
                } else {
                    ToastUtils.show(mContext, "you deny permission");
                }
                return;
            }
        }
    }
    public interface EventListener {
        void shareTextViaIntent(String text);
        void shareImageViaIntent(Uri uri);
        void requestWriteExternalStoragePremissions();
    }
}

