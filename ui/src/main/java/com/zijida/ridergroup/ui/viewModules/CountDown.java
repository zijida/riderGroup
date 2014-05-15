package com.zijida.ridergroup.ui.viewModules;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    private int count = 5;
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
                stop();
                notifyMessionComplete();
            }
        }

        @Override
        public void onFinish()
        {
            stop();
            notifyMessionComplete();
        }
    };

    public void notifyMessionComplete()
    {
        if(imListener != null)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("id",layout_resource_id);
            imListener.onModuleComplete(bundle);
        }
    }

    public CountDown(Context c) {
        super(c);
        layout_resource_id = R.layout.count_down;
    }

    private void assignCountNumber()
    {
        TextView v = (TextView)root.findViewById(R.id.text_count);
        if(v != null)
        {
            v.setText(String.valueOf(count));
        }
    }

    @Override
    public boolean load_to(int layout_id)
    {
        if(super.load_to(layout_id))
        {
            customFont.setFont((Activity)context,R.id.text_count, "HandelGothicEF-Bold");
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
        super.start();
        cdt.start();
    }

    @Override
    public void stop()
    {
        cdt.cancel();
        super.stop();
    }


}
