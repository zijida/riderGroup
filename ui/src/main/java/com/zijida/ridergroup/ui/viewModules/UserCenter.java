package com.zijida.ridergroup.ui.viewModules;

import android.content.Context;
import android.view.View;

import com.zijida.ridergroup.ui.R;

/**
 * Created by SHENJUN on 2014/5/15 0015.
 * Create in RiderGroup
 */
public class UserCenter extends ViewModuleHelper {

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_hide_menu: {
                    stop();
                }
                break;
            }
        }
    };

    public UserCenter(Context c) {
        super(c);
        layout_resource_id = R.layout.user_center;
    }

    @Override
    public boolean load_to(int layout_id)
    {
        if(super.load_to(layout_id))
        {
            setViewShowAnimation(R.anim.right_in,null);
            setViewHideAnimation(R.anim.right_out,animationHideListener);

            return true;
        }
        return false;
    }


}
