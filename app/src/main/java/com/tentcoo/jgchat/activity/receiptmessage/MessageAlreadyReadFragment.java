package com.tentcoo.jgchat.activity.receiptmessage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tentcoo.jgchat.R;
import com.tentcoo.jgchat.activity.FriendInfoActivity;
import com.tentcoo.jgchat.activity.GroupNotFriendActivity;
import com.tentcoo.jgchat.application.JGApplication;
import com.tentcoo.jgchat.pickerimage.fragment.BaseFragment;

import cn.jpush.im.android.api.model.UserInfo;

/**
 * Created by ${chenyn} on 2017/9/5.
 */

@SuppressLint("ValidFragment")
public class MessageAlreadyReadFragment extends BaseFragment {
    private Activity mContext;
    private View mRootView;
    private ListView mReceipt_alreadyRead;
    private AlreadyReadAdapter mAdapter;
    private long mGroupId;

    public MessageAlreadyReadFragment(long groupIdForReceipt) {
        this.mGroupId = groupIdForReceipt;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();

        initView();
        initListViewClick();
    }

    private void initView() {
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.fragment_receipt_already_read,
                (ViewGroup) mContext.findViewById(R.id.main_view), false);
        mReceipt_alreadyRead = (ListView) mRootView.findViewById(R.id.receipt_alreadyRead);
        mAdapter = new AlreadyReadAdapter(this);
        mReceipt_alreadyRead.setAdapter(mAdapter);
    }

    private void initListViewClick() {
        mReceipt_alreadyRead.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserInfo userInfo = (UserInfo) parent.getItemAtPosition(position);
                Intent intent = new Intent();
                if (userInfo.isFriend()) {
                    intent.setClass(mContext, FriendInfoActivity.class);
                }else {
                    intent.setClass(mContext, GroupNotFriendActivity.class);
                }
                intent.putExtra(JGApplication.TARGET_ID, userInfo.getUserName());
                intent.putExtra(JGApplication.TARGET_APP_KEY, userInfo.getAppKey());
                intent.putExtra(JGApplication.GROUP_ID, mGroupId);
                startActivity(intent);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup p = (ViewGroup) mRootView.getParent();
        if (p != null) {
            p.removeAllViewsInLayout();
        }
        return mRootView;
    }
}
