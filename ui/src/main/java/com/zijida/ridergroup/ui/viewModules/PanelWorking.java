package com.zijida.ridergroup.ui.viewModules;

import android.content.Context;
import android.view.View;

import com.zijida.ridergroup.ui.R;

/**
 * Created by SHENJUN on 14-5-6.
 * Create in RiderGroup
 */
public class PanelWorking extends ViewModuleHelper {
    public static final int layout_res = R.layout.merge_buttons;
    public boolean isResume = false;

    public PanelWorking(Context c) {
        super(c);
    }

    public boolean load(int layout_id,int res_id)
    {
        if(super.load(layout_id,res_id))
        {
            setViewEnable(R.id.button_pause,false);
            setViewEnable(R.id.button_done,false);
            setViewEnable(R.id.button_lock,true);
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
            v.setBackgroundResource(isResume?R.drawable.drawitem_resume:R.drawable.drawitem_pause);
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
