package com.mirstone.baselib.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * @package: com.zhuku.utils
 * @fileName: ScreenUtil
 * @data: 2018/4/13 10:12
 * @author: ShiLiang
 * @describe: 与屏幕相关操作
 */

public final class ScreenUtil {

    /**
     * 获取屏幕DisplayMetrics
     *
     * @param context 上下文
     * @return DisplayMetrics
     */
    private static DisplayMetrics getMetrics(@NonNull Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /**
     * 获取屏幕宽（PX）
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getDeviceWidth(@NonNull Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    /**
     * 获取屏幕高（PX）
     *
     * @param context 上下文
     * @return 屏幕高度
     */
    public static int getDeviceHeight(@NonNull Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return context.getResources().getDisplayMetrics().heightPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.y;
    }

    /**
     * 获取屏幕密度
     *
     * @param context 上下文
     * @return 屏幕密度
     */
    public static float getDeviceDensity(@NonNull Context context) {
        return getMetrics(context).density;
    }

    /**
     * 获取屏幕dpi
     *
     * @param context 上下文
     * @return 屏幕dpi
     */
    public static int getDeviceDpi(@NonNull Context context) {
        return getMetrics(context).densityDpi;
    }

    /**
     * 获取手机状态栏高度(PX)
     *
     * @param context 上下文
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(@NonNull Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getActionBarSize(@NonNull Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        int actionbarSize = typedArray.getDimensionPixelOffset(0, 0);
        typedArray.recycle();
        return actionbarSize;
    }
}
