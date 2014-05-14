package com.zijida.ridergroup.ui.viewModules;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.zijida.ridergroup.ui.R;
import com.zijida.ridergroup.ui.util.customFont;

/**
 * Created by SHENJUN on 14-4-30.
 * Create in RiderGroup
 * 倒计时Module
 * 引用layout资源：count_down.xml
 * 动效： 放大、淡入 －－>  缩小、淡出
 */
public class CountDown extends ViewModuleHelper
{
    public static final int layout_res = R.layout.count_down;
    private int count = 5;
    private Animation animation;

    private CountDownTimer cdt = new CountDownTimer(6000,1000) {
        @Override
        public void onTick(long l) {
            if(count>0)
            {
                //start_animation();
                assignCountNumber();
                count = count -1;
            }
            else
            {
                cdt.cancel();
                setLayoutVisiable(false);
                notifyMessionComplete();
            }
        }

        @Override
        public void onFinish() {
            setLayoutVisiable(false);
            notifyMessionComplete();
        }
    };

    public void notifyMessionComplete()
    {
        if(imListener != null)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("id",layout_res);
            imListener.onModuleComplete(bundle);
        }
    }

    public CountDown(Context c) {
        super(c);
    }

    public boolean load(int layout_id,int res_id)
    {
        if(super.load(layout_id,res_id))
        {
            customFont.setFont((Activity)context,R.id.text_count, "HandelGothicEF-Bold");
            setLayoutVisiable(true);
            loadAnimation();
            return true;
        }
        else
        {
        }
        return false;
    }

    @Override
    public void start()
    {
        cdt.start();
    }

    @Override
    public void stop()
    {
        cdt.cancel();
    }

    @Override
    public boolean OnKeyPress(View view, int i, KeyEvent keyEvent)
    {
        if(KeyEvent.ACTION_DOWN == keyEvent.getAction() && i == KeyEvent.KEYCODE_BACK)
        {
            stop();
            setLayoutVisiable(false);
            return true;
        }
        return false;
    }

    private void start_animation()
    {
        TextView v = (TextView)root.findViewById(R.id.text_count);
        if(v != null)
        {
            v.startAnimation(animation);
        }
    }

    private void stop_animation()
    {
        TextView v = (TextView)root.findViewById(R.id.text_count);
        if(v != null)
        {
            v.startAnimation(animation);
        }
    }

    private void assignCountNumber()
    {
        TextView v = (TextView)root.findViewById(R.id.text_count);
        if(v != null)
        {
            v.setText(String.valueOf(count));
        }
    }

    private Animation loadAnimation()
    {
        if(animation==null)
        {
            animation = AnimationUtils.loadAnimation(context,R.anim.count_down);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation _animation) {
                    Log.d("zijida","onAnimationStart");
                    //setViewVisible(true);
                }

                @Override
                public void onAnimationEnd(Animation _animation) {
                    //setViewVisible(false);
                    Log.d("zijida","onAnimationEnd");
                }

                @Override
                public void onAnimationRepeat(Animation _animation) {
                    Log.d("zijida","onAnimationRepeat");
                }
            });
        }
        return animation;
    }
}
