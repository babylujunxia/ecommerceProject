package com.example.ecommerce;

import android.app.Application;
import android.os.Environment;

import com.example.ecommerce.common.ScreenUtil;
import com.example.ecommerce.main.LoginActivity;
import com.netease.ecommercelib.sdk.ecommerceClient;
import com.netease.ecommercelib.sdk.SDKOptions;
import com.netease.ecommercelib.sdk.StatusBarNotificationConfig;
import com.netease.ecommercelib.sdk.auth.LoginInfo;

public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        // ... your codes
        ecommerceClient.init(this, loginInfo(), options());
        // ... your codes
    }


    private SDKOptions options() {
        SDKOptions options = new SDKOptions();

        StatusBarNotificationConfig config = new StatusBarNotificationConfig();
        config.notificationEntrance = LoginActivity.class;
        config.notificationSmallIconId = R.drawable.ic_stat_notify_msg;
        options.statusBarNotificationConfig = config;
        String sdkPath = Environment.getExternalStorageDirectory() + "/" + getPackageName() + "/ecommerce";
        options.sdkStorageRootPath = sdkPath;


        options.preloadAttach = true;


        options.thumbnailSize = ScreenUtil.getScreenMin() / 2;

        return options;
    }

    // 如果已经存在用户登录信息，返回LoginInfo，否则返回null即可
    private LoginInfo loginInfo() {
        return null;
    }
}
