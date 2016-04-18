package com.sagiller.learn;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.sagiller.learn.constant.Constants;
import com.sagiller.learn.func.base.BaseActivity;
import com.sagiller.learn.func.web.category.WebpageCategoryFragment;
import com.sagiller.learn.func.web.category.WebpageCategoryFragmentBuilder;
import com.sagiller.learn.func.web.webview.WebViewFragment;
import com.sagiller.learn.func.web.webview.OnWebViewTitleChangeListener;
import com.sagiller.learn.func.web.webview.WebViewFragmentBuilder;
import com.sagiller.learn.model.article.Article;

import javax.inject.Inject;

import butterknife.Bind;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,OnWebViewTitleChangeListener {

    @Inject IntentStarter intentStarter;
    @Bind(R.id.toolbar) Toolbar toolbar;
    private MainActivityComponent mainActivityComponent;
    private DrawerLayout drawer;
    private int selectedNavigationItemId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Search action", Snackbar.LENGTH_LONG)
                        .setAction("Search", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                dealDrawerSwitch();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ((TextView)navigationView.getHeaderView(0).findViewById(R.id.text_nav_header_name)).setText("Perry Hu");
        ((TextView)navigationView.getHeaderView(0).findViewById(R.id.text_nav_header_email)).setText("sagiller@163.com");
        navigationView.setNavigationItemSelectedListener(this);
        //load default fragment
        //switchToWebFragment();
    }
    private void dealDrawerSwitch() {
        if (selectedNavigationItemId != 0) {
            if (selectedNavigationItemId == R.id.nav_profile) {
//            ProfileFragment profileFragment = new ProfileFragment();
//            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,profileFragment).commit();
            } else if (selectedNavigationItemId == R.id.nav_gallery) {
                intentStarter.showAuthentication(this);
            } else if (selectedNavigationItemId == R.id.nav_slideshow) {
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                                Pair.create(findViewById(R.id.toolbar),
                                        getString(R.string.shared_toolbar)));

                intentStarter.showArticle(this, new Article(), options.toBundle());
                toolbar.setTitle("test");
            } else if (selectedNavigationItemId == R.id.nav_manage) {
                intentStarter.showWeb(this, Constants.URL_WWW_DEVELOPER_ANDROID_COM,null);
            } else if (selectedNavigationItemId == R.id.nav_share) {
                switchToWebFragment();
            } else if (selectedNavigationItemId == R.id.nav_send) {
                switchToWebpageCategoryFragment();
            }
            selectedNavigationItemId = 0;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        drawer.closeDrawer(GravityCompat.START);
        selectedNavigationItemId = item.getItemId();

        return true;
    }

    void switchToWebFragment() {

        String url = Constants.URL_WWW_GANK_IO;
        WebViewFragment fragment = new WebViewFragmentBuilder(url).build();
        fragment.setWebViewTitleChangeListener(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
        toolbar.setTitle("Webpage");
    }

    void switchToWebpageCategoryFragment() {
        WebpageCategoryFragment fragment = new WebpageCategoryFragmentBuilder(1).build();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
        toolbar.setTitle("Webpage Categories");
    }

    protected void injectDependencies() {
        mainActivityComponent = DaggerMainActivityComponent.create();
        mainActivityComponent.inject(this);
    }

    @Override
    public void webViewTitleChange(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment != null && fragment instanceof WebViewFragment) {
            WebViewFragment webViewFragment = (WebViewFragment) fragment;
            if (webViewFragment.canGoBack()) {
                webViewFragment.goBack();
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }


    }
}
