package com.zijida.ridergroup.ui.viewModules;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.zijida.ridergroup.ui.R;
import com.zijida.ridergroup.ui.util.ridingStatus;

/**
 * Created by SHENJUN on 14-5-6.
 * Create in RiderGroup
 */
public class PanelWorking extends ViewModuleHelper {
    public boolean isResume = true;
    public static final int MSG_KEYPRESS_DONE = 20;
    public static final int MSG_STATUS_CHANGE = 21;

    private View.OnClickListener clickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Bundle b = new Bundle();
            switch (view.getId())
            {
                case R.id.button_pause:
                {
                    exchage_pause_resume_button();
                    b.putInt("message_id",MSG_STATUS_CHANGE);
                    b.putInt("value", isResume ? ridingStatus.RUN:ridingStatus.PAUSE);
                    imListener.onMessage(b);

                }
                break;

                case R.id.button_done:
                {
                    b.putInt("message_id",MSG_KEYPRESS_DONE);
                    imListener.onMessage(b);
                }
                break;

                case R.id.button_lock:
                {
                    exchange_lock_panel();
                }
                break;
            }
        }
    };

    public PanelWorking(Context c) {
        super(c);
        layout_resource_id = R.layout.merge_buttons;
    }

    @Override
    public boolean load_to(int layout_id)
    {
        if(super.load_to(layout_id))
        {
            setSupportKeyMessage(false);

            setViewEnable(R.id.button_pause,false);
            setViewEnable(R.id.button_done,false);
            setViewEnable(R.id.button_lock,true);

            registClickListener(R.id.button_pause,clickListener);
            registClickListener(R.id.button_done,clickListener);
            registClickListener(R.id.button_lock,clickListener);
            return true;
        }
        else
        {
        }
        return false;
    }

    public void exchage_pause_resume_button()
    {
        View v = root.findViewById(R.id.button_pause);
        if(v != null)
        {
            isResume = !isResume;
            v.setBackgroundResource(isResume?R.drawable.drawitem_pause:R.drawable.drawitem_resume);
        }
    }

    public void exchange_lock_panel()
    {
        View v = root.findViewById(R.id.button_pause);
        if(v==null) return;

        if(v.isEnabled())
        {
            View vl = root.findViewById(R.id.button_lock);
            if(vl != null)
            {
                vl.setBackgroundResource(R.drawable.button_lock_down);
            }
            setViewEnable(R.id.button_pause,false);
            setViewEnable(R.id.button_done,false);
        }
        else
        {
            View vl = root.findViewById(R.id.button_lock);
            if(vl != null)
            {
                vl.setBackgroundResource(R.drawable.button_unlock_up);
            }
            setViewEnable(R.id.button_pause,true);
            setViewEnable(R.id.button_done,true);
        }
    }
}
