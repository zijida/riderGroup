package com.zijida.ridergroup.ui.Interfaces;

import android.os.Bundle;

/**
 * Created by SHENJUN on 14-4-25.
 * Create in RiderGroup
 */
public interface IThirdLoginListener {
    /**
     * 定义用于在第三方登录成功后回调的方法
     * @param bundle 定义如下：
     *               key = "id",value = applicationSettings类定义的USER_XX。
     *               key = "User" value = 微博返回的User变量。
     *               key = "Date" value = 腾讯返回的JSONObject变量。
     *
     */
    public void onLoginSuccessed(Bundle bundle);
}
