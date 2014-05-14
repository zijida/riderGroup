package com.zijida.ridergroup.ui.viewModules;

import android.content.Context;

import com.zijida.ridergroup.ui.R;

/**
 * Created by SHENJUN on 14-5-6.
 * Create in RiderGroup
 */
public class PanelStart extends ViewModuleHelper
{
    public static final int layout_res = R.layout.merge_start;

    public PanelStart(Context c) {
        super(c);
    }

    public boolean load(int layout_id,int res_id)
    {
        if(super.load(layout_id,res_id))
        {
            return true;
        }
        else
        {
        }
        return false;
    }

}
