package com.sagiller.learn.func.web.categorylayout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.sagiller.learn.R;
import com.sagiller.learn.model.webpage.WebpageCategory;
import com.sagiller.learn.utils.SimpleAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class WebpageCategoryLayoutAdapter extends SimpleAdapter<List<WebpageCategory>> {

  static class ViewHolder {

    @Bind(R.id.name) TextView text;
    @Bind(R.id.icon) ImageView icon;

    public ViewHolder(View v) {
      ButterKnife.bind(this, v);
      icon.setColorFilter(icon.getResources().getColor(R.color.secondary_text));
    }
  }

  public WebpageCategoryLayoutAdapter(Context context) {
    super(context);
  }

  @Override public View newView(int type, ViewGroup parent) {
    View view = inflater.inflate(R.layout.list_webpage_category_layout_item, parent, false);
    view.setTag(new ViewHolder(view));
    return view;
  }

  @Override public void bindView(int position, int type, View view) {
    ViewHolder vh = (ViewHolder) view.getTag();
    WebpageCategory category = items.get(position);

    vh.text.setText(category.getName());
  }
}
