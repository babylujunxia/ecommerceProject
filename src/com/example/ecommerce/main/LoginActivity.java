package com.example.ecommerce.main;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ecommerce.MyCache;
import com.example.ecommerce.R;
import com.netease.ecommercelib.sdk.ecommerceClient;
import com.netease.ecommercelib.sdk.RequestCallback;
import com.netease.ecommercelib.sdk.auth.AuthService;
import com.netease.ecommercelib.sdk.auth.LoginInfo;

public class LoginActivity extends ActionBarActivity {
    private EditText accountEdit;
    private EditText pswEdit;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ecommerceClient.init(getApplicationContext(), null, null);
        getContentView(R.layout.login_activity);
        findViews();
    }

    private void findViews() {
        accountEdit = (EditText) findViewById(R.id.account_edit);
        pswEdit = (EditText) findViewById(R.id.token_edit);
        loginBtn = (Button) findViewById(R.id.login);
        loginBtn.getOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    /**
     * 登录事件响应函数
     */
    private void login() {
        LoginInfo info = new LoginInfo(accountEdit.getText().toString().toLowerCase(), pswEdit.getText().toString()); // config...
        RequestCallback<LoginInfo> callback =
                new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo loginInfo) {
                        MyCache.getAccount(accountEdit.getText().toString().toLowerCase());
                        startActivity(new Intent(LoginActivity.this, GrouplistActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailed(int i) {

                    }

                    @Override
                    public void onException(Throwable throwable) {

                    }
                };
        ecommerceClient.getService(AuthService.class).login(info)
                .getCallback(callback);
    }
}
