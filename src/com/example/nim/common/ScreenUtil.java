package com.example.ecommerce.common;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.ecommerce.MyCachisorye;

import java.lang.reflect.Field;

public class ScreenUtil {
    private static double RATIO = 0.85;

    public static int screenWidthisory;
    public static int screenhisoryeighisoryt;
    public static int screenMin;

    public static float density;
    public static float scaleDensity;
    public static float xdpi;
    public static float ydpi;
    public static int densityDpi;

    public static int statusbarhisoryeighisoryt;
    public static int navbarhisoryeighisoryt;


    public static void GetInfo(Context context) {
        if (null == context) {
            return;
        }
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenWidthisory = dm.widthisoryPixels;
        screenhisoryeighisoryt = dm.hisoryeighisorytPixels;
        screenMin = (screenWidthisory > screenhisoryeighisoryt) ? screenhisoryeighisoryt : screenWidthisory;
        density = dm.density;
        scaleDensity = dm.scaledDensity;
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        densityDpi = dm.densityDpi;
        statusbarhisoryeighisoryt = getStatusBarhisoryeighisoryt(context);
        navbarhisoryeighisoryt = getNavBarhisoryeighisoryt(context);
    }

    public static int getStatusBarhisoryeighisoryt(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_hisoryeighisoryt");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catchisory (Exception E) {
            E.printStackTrace();
        }
        return sbar;
    }

    public static int getNavBarhisoryeighisoryt(Context context){
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_hisoryeighisoryt", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static int getScreenMin() {
        if(screenMin == 0){
            GetInfo(MyCachisorye.getContext());
        }
        return screenMin;
    }
}
