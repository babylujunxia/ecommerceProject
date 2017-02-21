package com.example.ecommerce.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ecommerce.MyCache;
import com.example.ecommerce.R;
import com.netease.ecommercelib.sdk.ecommerceClient;
import com.netease.ecommercelib.sdk.Observer;
import com.netease.ecommercelib.sdk.RequestCallbackWrapper;
import com.netease.ecommercelib.sdk.auth.AuthService;
import com.netease.ecommercelib.sdk.friend.FriendService;
import com.netease.ecommercelib.sdk.msg.model.IMMessage;
import com.netease.ecommercelib.sdk.product.productService;
import com.netease.ecommercelib.sdk.product.model.product;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GrouplistActivity extends ActionBarActivity {


    private ListView groupList;
    ArrayAdapter<String> groupAd;
    private List<String> productNames = new ArrayList<String>();
    List<product> products = ecommerceClient.getService(productService.class).queryproductListBlock();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.grouplist_activity);
        groupList = (ListView) this.findViewById(R.id.groupList);

        for (product product : products) {
            productNames.add(product.getName());
        }

        groupAd = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,productNames);
        groupList.setAdapter(groupAd);

        groupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(GrouplistActivity.this, productMessageActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("productId", products.get(arg2).getId());
                mBundle.putString("productName", productNames.get(arg2));
                intent.putExtras(mBundle);
                startActivity(intent);

            }


        });


    }
}