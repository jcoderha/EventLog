package com.example.asus.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.WindowManager;

import java.util.Date;

/**
 * Created by Halit on 01.01.2005.
 */
public class Util {

    public static final String[] timeFormats = {
            "yyyy",
            "MM",
            "dd",
            "HH",
            "mm",
            "ss",
    };

    public static String getSmartTime(Date date, String timeTexts[], String now){
        long time  = date.getTime();
        int currentTime = 0;
        for (int i = 0; i < timeFormats.length; i++) {
            currentTime = timeFormat(timeFormats[i], time);
            if( currentTime - timeFormat(timeFormats[i], 0) != 0){
                return String.valueOf(currentTime) + " " + timeTexts[i].trim();
            }
        }
        return now;
    }

    private static int timeFormat(String format, long time){
        return Integer.parseInt((String)DateFormat.format(format, time));
    }

    public static Drawable resize(Context context, Drawable drawable, int x, int y){
        Bitmap b = ((BitmapDrawable)drawable).getBitmap();
        Bitmap r = Bitmap.createScaledBitmap(b, x, y, false);
        return new BitmapDrawable(context.getResources(), r);
    }


    public static int getActionBarHeight(Context context){
        TypedValue tv = new TypedValue();
        if(context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)){
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }else{
            return 0;
        }
    }

    public static Point getScreenSize(Context c){
        WindowManager windowManager = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        Point p = new Point();
        windowManager.getDefaultDisplay().getSize(p);
        return p;
    }

    public static boolean isPortrait(Context c){
        int orientation = c.getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            return true;
        }else{
            return false;
        }
    }

    /**
     * get height of status bar
     *
     * @return height of status bar, if default method does not work, return 25
     */
    public static int getStatusBarHeight(Context context) {
        int barHeight = 25;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            barHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return barHeight;
    }

}
