package com.zijida.ridergroup.ui.viewModules;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.zijida.ridergroup.ui.R;

/**
 * Created by SHENJUN on 14-5-6.
 * Create in RiderGroup
 */
public class PanelStart extends ViewModuleHelper
{
    public static final int MSG_KEYPRESS_START = 0;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_start:
                {
                    if(imListener!=null)
                    {
                        Bundle b = new Bundle();
                        b.putInt("message_id",MSG_KEYPRESS_START);
                        imListener.onMessage(b);
                    }
                }
                break;
            }
        }
    };

    public PanelStart(Context c) {
        super(c);
        layout_resource_id = R.layout.merge_start;
    }

    @Override
    public boolean load_to(int layout_id)
    {
        if(super.load_to(layout_id))
        {
            setSupportKeyMessage(false);
            registClickListener(R.id.button_start,clickListener);
            return true;
        }
        return false;
    }

}
