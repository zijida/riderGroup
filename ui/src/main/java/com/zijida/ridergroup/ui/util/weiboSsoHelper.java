package com.zijida.ridergroup.ui.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.models.User;
import com.zijida.ridergroup.ui.Interfaces.IThirdLoginListener;
import com.zijida.ridergroup.ui.database.weiboToken;

/**
 * Created by shenjun on 14-4-16.
 * Create in RiderGroup
 * App Key：1295800343
 * App Secret：9415e60197d62f935026104f674a7345
 * Call Mode: SSO
 */
public class weiboSsoHelper {
    public static final String APP_ID = "1295800343";
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
    public static final String SCOPE = "email";

    public  Context context;
    private SsoHandler ssoHandler;  // 仅当SDK支持SSO时有效
    private WeiboAuth auth;
    private AuthListener listener;
    private IThirdLoginListener callback_completeListener;

    @SuppressLint("SimpleDateFormat")
    private class AuthListener implements WeiboAuthListener {
        @Override
        public void onComplete(Bundle bundle) {
            Oauth2AccessToken oat = Oauth2AccessToken.parseAccessToken(bundle);
            if(oat.isSessionValid())
            {
                weiboToken.writeAccessToke(context.getApplicationContext(), oat);

                UsersAPI ua = new UsersAPI(oat);
                ua.show(Long.parseLong(oat.getUid()),new RequestListener() {
                    @Override
                    public void onComplete(String s) {
                        if(!TextUtils.isEmpty(s))
                        {
                            User user = User.parse(s);
                            weiboToken.writeUserInfo(context,user);

                            if(callback_completeListener!=null)
                            {
                                Bundle bundle = new Bundle();
                                bundle.putInt("id",applicationSettings.USER_WB);
                                callback_completeListener.onLoginSuccessed(bundle);
                            }
                        }
                        else
                        {
                            Toast.makeText(context,"用户信息未知，请确认用户名密码是否正确。", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onWeiboException(WeiboException e) {
                        Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
            else
            {
                // 以下几种情况，您会收到 Code：
                // 1. 当您未在平台上注册的应用程序的包名与签名时；
                // 2. 当您注册的应用程序包名与签名不正确时；
                // 3. 当您在平台上注册的包名和签名与您当前测试的应用的包名和签名不匹配时。
                String code = bundle.getString("code");
                String message = "微博登录未成功";
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\n返回值: " + code;
                }
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
        }
    };

    public weiboSsoHelper(Context context)
    {
        this.context = context;
        auth = new WeiboAuth(context, APP_ID,REDIRECT_URL,SCOPE);
        ssoHandler = new SsoHandler((Activity) context,auth);
        listener = new AuthListener();
        callback_completeListener = null;
    }

    public void setComplementListener(IThirdLoginListener _listener)
    {
        this.callback_completeListener = _listener;
    }

    public void login()
    {
        ssoHandler.authorize(listener);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(ssoHandler != null)
        {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

}
