/*
 * Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sagiller.learn.func.web.category;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.annotatedadapter.annotation.ViewField;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.sagiller.learn.R;
import com.sagiller.learn.func.base.view.ListAdapter;
import com.sagiller.learn.model.webpage.WebpageCategory;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author Hannes Dorfmann
 */
public class WebpageCategoryAdapter extends ListAdapter<List<WebpageCategory>> implements WebpageCategoryAdapterBinder {

    public interface ItemClickedListener {
        public void onItemClicked(WebpageCategoryAdapterHolders.CategoryViewHolder vh, WebpageCategory category);
    }

    @ViewType(
            layout = R.layout.list_webpage_category_item,
            views = {
                    @ViewField(id = R.id.name, name = "name", type = TextView.class),
                    @ViewField(id = R.id.desc, name = "desc", type = TextView.class),
            })
    public final int category = 0;

    private ItemClickedListener clickListener;
    private Format format = new SimpleDateFormat("dd. MMM", Locale.getDefault());

    public WebpageCategoryAdapter(Context context, ItemClickedListener clickListener) {
        super(context);
        this.clickListener = clickListener;
    }

    @Override
    public void bindViewHolder(final WebpageCategoryAdapterHolders.CategoryViewHolder vh, int position) {
        final WebpageCategory category = items.get(position);
        vh.name.setText(category.getName());
        vh.desc.setText(category.getDesc());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                clickListener.onItemClicked(vh, category);
            }
        });
    }

    /**
     * Finds a mail by his id if displayed in this adapter
     */
    public WebpageCategory findCategory(int id) {
        if (items == null) {
            return null;
        }

        for (WebpageCategory m : items) {
            if (m.getId() == id) {
                return m;
            }
        }

        return null;
    }

    /**
     * Searches for an equal mail (compares mail id) in the adapter.
     *
     * @return A {@link WebpageCategoryInAdapterResult} containing the information if the adapter contains that
     * mail and at which index position. If the adapter doesn't contain this mail, then the result
     * will contain the index position where the mail would be.
     */
    public WebpageCategoryInAdapterResult findCategory(WebpageCategory category) {
        //int indexPosition = Collections.binarySearch(items, category, MailComparator.INSTANCE);
        int indexPosition = 1;
        boolean containsMail = false;
        WebpageCategory found = null;
        if (indexPosition < 0) {
            indexPosition = ~indexPosition;
        } else {
            found = items.get(indexPosition);
            if (found.getId() == category.getId()) {
                containsMail = true;
            } else {
                containsMail = false;
                found = null;
            }
        }

        return new WebpageCategoryInAdapterResult(containsMail, found, indexPosition);
    }

    /**
     * Holds the information if the adapter contains a certain mail and at which index position. If
     * the adapter doesn't contain this mail, then the result will
     * contain the index position where the mail would be.
     */
    public static class WebpageCategoryInAdapterResult {
        boolean found;
        WebpageCategory adapterCategory;
        int index;

        public WebpageCategoryInAdapterResult(boolean found, WebpageCategory adapterCategory, int index) {
            this.found = found;
            this.adapterCategory = adapterCategory;
            this.index = index;
        }

        public boolean isFound() {
            return found;
        }

        public WebpageCategory getAdapterMail() {
            return adapterCategory;
        }

        public int getIndex() {
            return index;
        }
    }
}
