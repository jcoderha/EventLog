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
    public static String getSmartTime(Date date){
        long time  = date.getTime();
        String result = null;
        /*String syear , smonth, sday, shour, sminute, ssecond;

        syear = (String) DateFormat.format("yyyy", time);
        smonth = (String) DateFormat.format("MM", time);
        sday = (String) DateFormat.format("dd", time);
        shour = (String) DateFormat.format("HH", time);
        sminute = (String) DateFormat.format("mm", time);
        ssecond = (String) DateFormat.format("ss", time);*/

        int year = timeFormat("yyyy", time),
                month = timeFormat("MM", time),
                day = timeFormat("dd", time),
                hour = timeFormat("HH", time),
                minute = timeFormat("mm", time),
                second = timeFormat("ss", time);
        /*
        if(syear == null){
            year = 0;
        }else{
            year =  Integer.parseInt(syear);
        }

        if(smonth == null){
            month = 0;
        }else{
            month =  Integer.parseInt(smonth);
        }
        if(sday == null){
            day = 0;
        }else{
            day =  Integer.parseInt(sday);
        }
        if(shour == null){
            hour = 0;
        }else{
            hour =  Integer.parseInt(shour);
        }
        if(sminute == null){
            minute = 0;
        }else{
            minute =  Integer.parseInt(sminute);
        }
        if(ssecond == null){
            second = 0;
        }else{
            second =  Integer.parseInt(ssecond);
        }*/

        if(year - timeFormat("yyyy", 0) == 0){
            if(month - timeFormat("MM", 0) == 0){
                if(day - timeFormat("dd", 0) == 0){
                    if(hour - timeFormat("HH", 0)== 0){
                        if(minute - timeFormat("mm", 0)== 0){
                            if(second - timeFormat("ss", 0) == 0){
                                result = "şimdi";
                            }else{
                                result = String.valueOf(second) + " saniye önce";
                            }
                        }else{
                            result = String.valueOf(minute) + " dakika önce";
                        }
                    }else{
                        result = String.valueOf(hour) + " saat önce";
                    }
                }else{
                    result = String.valueOf(day) + " gün önce";
                }
            }else{
                result = String.valueOf(month) + " ay önce";
            }
        }else{
            result = String.valueOf(year) + " yıl önce";
        }
        return result;
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
