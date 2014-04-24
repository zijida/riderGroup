package com.zijida.ridergroup.ui.util;

import android.content.Context;

/**
 * Created by shenjun on 14-3-28.
 * SharedPreferences 方式存储
 */
public class applicationSettings extends preferenceIO {
    public static final int USER_QQ = 1;    // 用户类型标识：QQ用户
    public static final int USER_WB = 2;    // 用户类型标识：微博用户
    public static final int USER_RG = 0;    // 用户类型标识：骑士团注册用户

    private static final String PREFERENCES_NAME = "app_reference";
    private static final String global_first_run = "fresh_fish";
    private static final String KEY_USERTYPE      = "sources";  /* 用户来源：微博、腾讯、骑士团*/

    public applicationSettings(Context context)
    {
        super(context,PREFERENCES_NAME);
    }

    public boolean isFirstRun() { return get_boolean(global_first_run,true); }
    public int userComeFrom() { return get_integer(KEY_USERTYPE); }

    public void setFirstRunSign(boolean bTrue)  { set_boolean(global_first_run,bTrue); }
    public void setUserComeFrome(int type)       { set_integer(KEY_USERTYPE,type);}
}
