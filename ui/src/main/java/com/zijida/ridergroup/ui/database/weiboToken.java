package com.zijida.ridergroup.ui.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.openapi.models.User;
import com.zijida.ridergroup.ui.util.preferenceIO;

/**
 * Created by shenjun on 14-4-16.
 * Create in RiderGroup
 */
public class weiboToken extends preferenceIO {
    private static final String PREFERENCES_NAME = "com_weibo_sdk_android";
    private static final String KEY_UID           = "uid";
    private static final String KEY_ACCESS_TOKEN  = "access_token";
    private static final String KEY_EXPIRES_IN    = "expires_in";

    //// user informations:
    private static final String KEY_USERID   = "id";
    private static final String KEY_USERIDS  = "idstr";
    private static final String KEY_NAME      = "name";
    private static final String KEY_URL        = "profile_url";         /** 用户的微博统一URL地址 */
    private static final String KEY_WEIHAO    = "weihao";               /** 用户的微号 */
    private static final String KEY_AVATAR    = "avatar_large";        /** 用户大头像地址 */
    private static final String KEY_SCR_NAME   = "screen_name";        /** 用户昵称 */
    private static final String KEY_IMG_URL    = "profile_image_url"; /** 用户头像地址，50×50像素 */
    private static final String KEY_GENDER     = "gender";              /** 性别，m：男、f：女、n：未知 */

    public weiboToken(Context context) {
        super(context, PREFERENCES_NAME);
    }

    /**
     * 保存 Token 对象到 SharedPreferences。
     * @param context 应用程序上下文环境
     * @param oat   Token 对象
     */
    public static void writeAccessToke(Context context,Oauth2AccessToken oat)
    {
        if(oat==null) return;

        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_UID, oat.getUid());
        editor.putString(KEY_ACCESS_TOKEN, oat.getToken());
        editor.putLong(KEY_EXPIRES_IN, oat.getExpiresTime());
        editor.commit();
    }

    /**
     * 从 SharedPreferences 读取 Token 信息。
     *
     * @param context 应用程序上下文环境
     *
     * @return 返回 Token 对象
     */
    public static Oauth2AccessToken readAccessToken(Context context) {
        if (null == context) return null;

        Oauth2AccessToken token = new Oauth2AccessToken();
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        token.setUid(pref.getString(KEY_UID, ""));
        token.setToken(pref.getString(KEY_ACCESS_TOKEN, ""));
        token.setExpiresTime(pref.getLong(KEY_EXPIRES_IN, 0));
        return token;
    }

    public static void writeUserInfo(Context context, User user)
    {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_SCR_NAME,user.screen_name);
        editor.putString(KEY_IMG_URL, user.profile_image_url);
        editor.putString(KEY_GENDER, user.gender);
        editor.putString(KEY_USERID,user.id);
        editor.putString(KEY_USERIDS,user.idstr);
        editor.putString(KEY_NAME,user.name);
        editor.putString(KEY_URL,user.url);
        editor.putString(KEY_WEIHAO,user.weihao);
        editor.putString(KEY_AVATAR,user.avatar_large);
        editor.commit();
    }

    public static User readUserInfo(Context context)
    {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        User user = new User();
        user.name = pref.getString(KEY_NAME,"");
        user.id = pref.getString(KEY_USERID,"");
        user.idstr = pref.getString(KEY_USERIDS,"");
        user.url = pref.getString(KEY_URL,"");
        user.weihao = pref.getString(KEY_WEIHAO,"");
        user.avatar_large = pref.getString(KEY_AVATAR,"");
        user.screen_name = pref.getString(KEY_SCR_NAME,"");
        user.profile_image_url = pref.getString(KEY_IMG_URL,"");
        user.gender = pref.getString(KEY_GENDER,"");

        return user;
    }
}
