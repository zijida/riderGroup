package com.zijida.ridergroup.ui.viewModules;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.zijida.ridergroup.ui.R;

/**
 * Created by SHENJUN on 2014/5/14 0014.
 * Create in RiderGroup
 */
public class MainMenu extends ViewModuleHelper
{
    public static final int MSG_MENU_USER_CENTER = 40;
    public static final int MSG_MENU_SETTINGS = 42;

    private View.OnClickListener clickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            Bundle b = new Bundle();
            switch (view.getId())
            {
                case R.id.button_hide_menu:
                {
                    stop();
                }
                break;

                case R.id.panel_my_center:
                {
                    b.putInt("message_id",MSG_MENU_USER_CENTER);
                    imListener.onMessage(b);
                }
                break;

                case R.id.panel_around_people:
                {
                }
                break;

                case R.id.panel_settings:
                {
                    b.putInt("message_id",MSG_MENU_SETTINGS);
                    imListener.onMessage(b);
                }
                break;
            }
        }
    };

    public MainMenu(Context c) {
        super(c);
        layout_resource_id = R.layout.context_menu;
    }

    @Override
    public boolean load_to(int layout_id)
    {
        if(super.load_to(layout_id))
        {
            setViewShowAnimation(R.anim.right_in,null);
            setViewHideAnimation(R.anim.right_out,animationHideListener);

            registClickListener(R.id.button_hide_menu,clickListener);
            registClickListener(R.id.panel_my_center,clickListener);
            registClickListener(R.id.panel_around_people,clickListener);
            registClickListener(R.id.panel_settings,clickListener);

            return true;
        }
        return false;
    }
}
