package com.zijida.ridergroup.ui.util;

import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 14-3-28.
 * 颜色过滤矩阵方法，定义按钮的按下、弹起状态
 * 颜色过滤矩阵是一个4x5的矩阵，四行分别是红色通道值，绿色通道值，蓝色通道值和alpha通道值。五列分别是对应通道的红色值，绿色值，蓝色值，alpha值和偏移量。
     RGB和Alpha的终值计算方法如下：
     Red通道终值= a[0] * srcR + a[1] * srcG + a[2] * srcB + a[3] * srcA + a[4]
     Green通道终值= a[5] * srcR + a[6] * srcG + a[7] * srcB + a[8] * srcA + a[9]
     Blue通道终值= a[10] * srcR + a[11] * srcG + a[12] * srcB + a[13] * srcA + a[14]
     Alpha通道终值= a[15] * srcR + a[16] * srcG + a[17] * srcB + a[18] * srcA + a[19]
     备注：
     srcR为原图Red通道值，srcG为原图Green通道值，srcB为原图Blue通道值，srcA为原图Alpha通道值。
     每个通道的源值和终值都在0到255的范围内。即使计算结果大于255或小于0，值都将被限制在0到255的范围内。
 */
public class statusMatrix {
    /**
            * 按钮被按下
    */
    private final static float[] BUTTON_PRESSED = new float[] {
            0.7f, 0, 0, 0, 0,
            0, 0.7f, 0, 0, 0,
            0, 0, 0.7f, 0, 0,
            0, 0, 0, 1, 0 };

    /**
     * 按钮恢复原状
     */
    private final static float[] BUTTON_RELEASED = new float[] {
            1, 0, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0 };

    /**
     * 3通道70灰度--> RGB(162,205,57)
     */
    private final static float[] BUTTON_COLORED = new float[] {
            2.314f, 0, 0, 0, 0,
            0, 2.92f, 0, 0, 0,
            0, 0, 0.666f, 0, 0,
            0, 0, 0, 1, 0 };

    private static final View.OnTouchListener touchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            if(v==null || event==null) return false;

            Drawable d = v.getBackground();
            if(d==null) return false;

            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                d.setColorFilter(new ColorMatrixColorFilter(BUTTON_PRESSED));
                if(Build.VERSION.SDK_INT< Build.VERSION_CODES.JELLY_BEAN)
                {
                    v.setBackgroundDrawable(d);
                }
                else v.setBackground(d);
            }
            else if(event.getAction() == MotionEvent.ACTION_UP)
            {
                d.setColorFilter(new ColorMatrixColorFilter(BUTTON_RELEASED));
                if(Build.VERSION.SDK_INT< Build.VERSION_CODES.JELLY_BEAN)
                {
                    v.setBackgroundDrawable(d);
                }
                else v.setBackground(d);
            }
            return false;
        }
    };

    private static final View.OnTouchListener touchListener_colored = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            if(v==null || event==null) return false;

            Drawable d = v.getBackground();
            if(d==null) return false;

            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                d.setColorFilter(new ColorMatrixColorFilter(BUTTON_COLORED));
                if(Build.VERSION.SDK_INT< Build.VERSION_CODES.JELLY_BEAN)
                {
                    v.setBackgroundDrawable(d);
                }
                else v.setBackground(d);
            }
            else if(event.getAction() == MotionEvent.ACTION_UP)
            {
                d.setColorFilter(new ColorMatrixColorFilter(BUTTON_RELEASED));
                if(Build.VERSION.SDK_INT< Build.VERSION_CODES.JELLY_BEAN)
                {
                    v.setBackgroundDrawable(d);
                }
                else v.setBackground(d);
            }
            return false;
        }
    };

    public static void setButtonStateChangeListener(View v,boolean colored) {
        v.setOnTouchListener(colored?touchListener_colored:touchListener);
    }
}
