package com.zijida.ridergroup.ui.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shenjun on 14-4-15.
 * Create in ${PACKAGE_NAME}
 */

public class tencentToken extends preferenceIO {
    public static final String PREFERENCES_NAME = "tencentToken";
    public static final String KEY_OPENID         = "openid";
    public static final String KEY_ACCESS_TOKEN  = "access_token";
    public static final String KEY_EXPIRES_IN    = "expires_in";
    public static final String KEY_NICK_NAME    = "nickname";
    public static final String KEY_FIGURE_URL_SPACE    = "figureurl_2";
    public static final String KEY_FIGURE_URL_QQ    = "figureurl_qq_1";
    public static final String KEY_GENDER    = "gender";
    public static final String KEY_LASTSESSIONTIME    = "session_time";

    public tencentToken(Context context)
    {
        super(context,PREFERENCES_NAME);
    }

    /**
     * 保存 Token 对象到 SharedPreferences。
     * @param context 应用程序上下文环境
     * @param o   Token 对象
     */
    public static void writeAccessToke(Context context,JSONObject o)
    {
        if(o==null) return;

        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        try
        {
            editor.putString(KEY_OPENID,o.getString(KEY_OPENID));
            editor.putString(KEY_ACCESS_TOKEN,o.getString(KEY_ACCESS_TOKEN));
            editor.putString(KEY_EXPIRES_IN,o.getString(KEY_EXPIRES_IN));
        }
        catch (JSONException je)
        {
            Toast.makeText(context, je.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        editor.commit();
    }

    /**
     * 从 SharedPreferences 读取 Token 信息。
     *
     * @param context 应用程序上下文环境
     *
     * @return 返回 Token 对象
     */
    /*
    public static Oauth2AccessToken readAccessToken(Context context) {
        if (null == context) return null;

        Oauth2AccessToken token = new Oauth2AccessToken();
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        token.setUid(pref.getString(KEY_UID, ""));
        token.setToken(pref.getString(KEY_ACCESS_TOKEN, ""));
        token.setExpiresTime(pref.getLong(KEY_EXPIRES_IN, 0));
        return token;
    }
    */


    public static void writeUserInfo(Context context,JSONObject o)
    {
        if(o==null) return;

        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        try
        {
            editor.putString(KEY_NICK_NAME,o.getString(KEY_NICK_NAME));
            editor.putString(KEY_FIGURE_URL_SPACE,o.getString(KEY_FIGURE_URL_SPACE));
            editor.putString(KEY_FIGURE_URL_QQ,o.getString(KEY_FIGURE_URL_QQ));
            editor.putString(KEY_GENDER,o.getString(KEY_GENDER));
            editor.putString(KEY_LASTSESSIONTIME, o.getString(KEY_LASTSESSIONTIME));
        }
        catch (JSONException je)
        {
            Toast.makeText(context, je.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        editor.commit();
    }

    public String get_sessionTime() { return get_value(KEY_LASTSESSIONTIME); }
    public String get_gender()      { return get_value(KEY_GENDER); }
    public String get_figure_qq()   { return get_value(KEY_FIGURE_URL_QQ); }
    public String get_figure_space(){ return get_value(KEY_FIGURE_URL_SPACE); }
    public String get_nickname()    { return get_value(KEY_NICK_NAME); }
    public String get_open_id()     { return get_value(KEY_OPENID); }
    public String get_access_token()    { return get_value(KEY_ACCESS_TOKEN); }
    public String get_expires_in()    { return get_value(KEY_EXPIRES_IN); }

    public void set_sessionTime(String sessionTime)     { set_value(KEY_LASTSESSIONTIME,sessionTime); }
    public void set_gender(String gender)               { set_value(KEY_GENDER,gender); }
    public void set_figure_qq(String figureQQ)          { set_value(KEY_FIGURE_URL_QQ,figureQQ); }
    public void set_figure_space(String figureSpace)    { set_value(KEY_FIGURE_URL_SPACE,figureSpace); }
    public void set_nickname(String nickname)           { set_value(KEY_NICK_NAME,nickname); }
    public void set_open_id(String openID)              { set_value(KEY_OPENID, openID); }
    public void set_access_token(String accessToken)    { set_value(KEY_ACCESS_TOKEN,accessToken); }
    public void set_expires_in(String expiresIn)        { set_value(KEY_EXPIRES_IN,expiresIn); }

}
