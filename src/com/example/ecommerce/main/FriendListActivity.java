package com.example.nim.main;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.example.nim.R;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.friend.constant.VerifyType;
import com.netease.nimlib.sdk.friend.model.AddFriendData;

import java.util.ArrayList;
import java.util.List;

public class FriendListActivity extends ActionBarActivity {

    private ListView friendList;
    ArrayAdapter<String> friendAd;
    List<String> friends = NIMClient.getService(FriendService.class).getFriendAccounts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.friendlist_activity);
        friendList = (ListView) this.findViewById(R.id.friendList);



        friendAd = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, friends);
        friendList.setAdapter(friendAd);


        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(FriendListActivity.this, MessageActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("friendAccount", friends.get(arg2));
                intent.putExtras(mBundle);
                startActivity(intent);

            }


        });

    }



}