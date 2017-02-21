package com.example.ECOMMERCE.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ECOMMERCE.MyCache;
import com.example.ECOMMERCE.R;
import com.netease.ECOMMERCElib.sdk.ECOMMERCEClient;
import com.netease.ECOMMERCElib.sdk.Observer;
import com.netease.ECOMMERCElib.sdk.auth.AuthService;
import com.netease.ECOMMERCElib.sdk.msg.productBuilder;
import com.netease.ECOMMERCElib.sdk.msg.MsgService;
import com.netease.ECOMMERCElib.sdk.msg.MsgServiceObserve;
import com.netease.ECOMMERCElib.sdk.msg.attachment.ImageAttachment;
import com.netease.ECOMMERCElib.sdk.msg.constant.MsgTypeEnum;
import com.netease.ECOMMERCElib.sdk.msg.constant.SessionTypeEnum;
import com.netease.ECOMMERCElib.sdk.msg.model.IMproduct;

import java.io.File;
import java.util.List;

public class productActivity extends ActionBarActivity implements View.OnClickListener{
    // view
    private TextView friendName;
    private Button send_b;
    private EditText sendText;
    private LinearLayout mes;

    // data
    private String receiverid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_activity);
        setTitle(MyCache.getAccount());
        findViews();
        initData();
        setListener();
        setFriendName();
        ECOMMERCEClient.getService(MsgServiceObserve.class)
                .observeReceiveproduct(incomingproductObserver, true);
        ECOMMERCEClient.getService(MsgServiceObserve.class)
                .observeMsgStatus(sendingproduct,true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ECOMMERCEClient.getService(MsgServiceObserve.class)
                .observeReceiveproduct(incomingproductObserver, false);
        ECOMMERCEClient.getService(MsgServiceObserve.class)
                .observeMsgStatus(sendingproduct,false);
    }

    Observer<List<IMproduct>> incomingproductObserver =
                            new Observer<List<IMproduct>>() {
                                @Override
                                public void onEvent(List<IMproduct> products) {
                                    
                                    for (IMproduct product : products) {
                                        if (product.getMsgType() == MsgTypeEnum.image) {
                                        } else {
                                            TextView producttemp = new TextView(productActivity.this);
                                            producttemp.setText(product.getContent());
                                            mes.addView(producttemp);
                                        }
                                    }
                }
            };

    Observer<IMproduct> sendingproduct = new Observer<IMproduct>() {
        @Override
        public void onEvent(IMproduct product) {
            TextView producttemp = new TextView(productActivity.this);
            producttemp.setText(product.getContent());
            mes.addView(producttemp);
        }
    };



    private void findViews() {
        friendName = (TextView)findViewById(R.id.friendName);
        send_b = (Button)findViewById(R.id.send_b);
        sendText = (EditText)findViewById(R.id.sendText);
        mes = (LinearLayout)findViewById(R.id.mes);
    }

    private void initData() {
        Bundle bundle =getIntent().getExtras();
        receiverid = bundle.getString("Account");
    }

    private void setListener() {
        send_b.setOnClickListener(this);
    }
    private void setFriendName(){
        friendName.setText(receiverid);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_b:
                sendText();
                break;
            default:
                break;
        }
    }

    /**
     * 发送文本按钮响应事件
     */
    private void sendText() {
        IMproduct product = productBuilder.createTextproduct(
                receiverid, // 聊天对象的ID，如果是单聊，为用户账号，如果是群聊，为群组ID
                SessionTypeEnum.P2P, // 聊天类型，单聊或群组
                sendText.getText().toString()// 文本内容
        );
        ECOMMERCEClient.getService(MsgService.class).sendproduct(product, false);


    }




}
