package com.tentcoo.jgchat.utils.keyboard.interfaces;

import android.view.ViewGroup;

import com.tentcoo.jgchat.utils.keyboard.adpater.EmoticonsAdapter;


public interface EmoticonDisplayListener<T> {

    void onBindView(int position, ViewGroup parent, EmoticonsAdapter.ViewHolder viewHolder, T t, boolean isDelBtn);
}
