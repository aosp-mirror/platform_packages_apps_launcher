/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.launcher;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Adapter showing the types of items that can be added to a {@link Workspace}.
 */
public class AddAdapter extends BaseAdapter {
    
    private final Launcher mLauncher;
    private final LayoutInflater mInflater;
    
    private final ArrayList<ListItem> mItems = new ArrayList<ListItem>();
    
    public static final int ITEM_APPLICATION = 0;
    public static final int ITEM_SHORTCUT = 1;
    public static final int ITEM_SEARCH = 2;
    public static final int ITEM_APPWIDGET = 3;
    public static final int ITEM_LIVE_FOLDER = 4;
    public static final int ITEM_FOLDER = 5;
    public static final int ITEM_WALLPAPER = 6;
    
    /**
     * Specific item in our list.
     */
    public class ListItem {
        public final CharSequence text;
        public final Drawable image;
        public final int actionTag;
        
        public ListItem(Resources res, int textResourceId, int imageResourceId, int actionTag) {
            text = res.getString(textResourceId);
            if (imageResourceId != -1) {
                image = res.getDrawable(imageResourceId);
            } else {
                image = null;
            }
            this.actionTag = actionTag;
        }
    }
    
    public AddAdapter(Launcher launcher) {
        super();
        
        mLauncher = launcher;
        mInflater = (LayoutInflater) mLauncher.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        // Create default actions
        Resources res = launcher.getResources();
        
        mItems.add(new ListItem(res, R.string.group_applications,
                R.drawable.ic_launcher_application, ITEM_APPLICATION));
        
        mItems.add(new ListItem(res, R.string.group_shortcuts,
                R.drawable.ic_launcher_empty, ITEM_SHORTCUT));
        
        mItems.add(new ListItem(res, R.string.group_search,
                R.drawable.ic_search_widget, ITEM_SEARCH));
        
        mItems.add(new ListItem(res, R.string.group_widgets,
                R.drawable.ic_launcher_appwidget, ITEM_APPWIDGET));
        
        mItems.add(new ListItem(res, R.string.group_live_folders,
                R.drawable.ic_launcher_empty, ITEM_LIVE_FOLDER));
        
        mItems.add(new ListItem(res, R.string.group_folder,
                R.drawable.ic_launcher_folder, ITEM_FOLDER));
        
        mItems.add(new ListItem(res, R.string.group_wallpapers,
                R.drawable.ic_launcher_gallery, ITEM_WALLPAPER));

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem item = (ListItem) getItem(position);
        
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.add_list_item, parent, false);
        }
        
        TextView textView = (TextView) convertView;
        textView.setTag(item);
        textView.setText(item.text);
        textView.setCompoundDrawablesWithIntrinsicBounds(item.image, null, null, null);
        
        return convertView;
    }

    public int getCount() {
        return mItems.size();
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
    
}
