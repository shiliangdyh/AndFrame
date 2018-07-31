package com.mirstone.baselib.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mirstone.baselib.R;


public class ToastUtil {
    private static Toast toast;
    private static final int DEFAULT_GRAVITY = Gravity.CENTER;

    public static void show(Context context, String info, int gravity) {
        LinearLayout linearLayout = new LinearLayout(context.getApplicationContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
        linearLayout.setLayoutParams(params);
        linearLayout.setGravity(gravity);

        TextView textView = new TextView(context.getApplicationContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = context.getResources().getDimensionPixelOffset(R.dimen.toast_margin);
        layoutParams.setMargins(margin, 0, margin, 0);
        textView.setBackgroundResource(R.drawable.toast_bg);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(gravity);
        if (gravity == Gravity.BOTTOM){
            linearLayout.setPadding(0,0,0,context.getResources().getDimensionPixelOffset(R.dimen.toast_margin_bottom));
        }
        int padding = context.getResources().getDimensionPixelOffset(R.dimen.toast_padding);
        textView.setPadding(padding, padding, padding, padding);
        textView.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        linearLayout.addView(textView);

//        TextView toast_tv = view.findViewById(com.ruking.frame.library.R.id.toast_tv);
        textView.setText(info);
        textView.getBackground().setAlpha(166);
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(context.getApplicationContext());
        toast.setGravity(gravity, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(linearLayout);
        toast.show();
    }

    public static void show(Context context, int info) {
        show(context, info, DEFAULT_GRAVITY);
    }

    public static void show(Context context, int info, int gravity) {
        show(context, context.getResources().getString(info), gravity);
    }

    public static void show(Context context, String info) {
        show(context, info, DEFAULT_GRAVITY);
    }
}
