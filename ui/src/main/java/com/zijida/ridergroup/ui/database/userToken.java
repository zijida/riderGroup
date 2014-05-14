package com.zijida.ridergroup.ui.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.zijida.ridergroup.ui.util.preferenceIO;

/**
 * Created by SHENJUN on 14-4-22.
 * Create in RiderGroup
 */
public class userToken extends preferenceIO {
    private final static String PREFERENCES_NAME = "current_user";
    private static final String KEY_USERNAME     = "nickname";
    private static final String KEY_EMAIL        = "email";
    private static final String KEY_PASSWORD     = "password";
    private static final String KEY_HEADIMAGE    = "head_image";
    private static final String KEY_BIKEIMAGE    = "head_image";
    private static final String KEY_GENDER       = "gender";
    private static final String KEY_AGE           = "age";

    public userToken(Context context) {
        super(context, PREFERENCES_NAME);
    }

    public int get_age() { return get_integer(KEY_AGE); }
    public String get_userName() { return get_value(KEY_USERNAME); }
    public String get_email() { return get_value(KEY_EMAIL); }
    public String get_password() { return get_value(KEY_PASSWORD); }
    public String get_headImg() { return get_value(KEY_HEADIMAGE); }
    public String get_bikeImg() { return get_value(KEY_BIKEIMAGE); }
    public String get_gender() { return get_value(KEY_GENDER); }

    public void set_age(int age) { set_integer(KEY_AGE,age);}
    public void set_userName(String value) { set_value(KEY_USERNAME,value); }
    public void set_email(String value) { set_value(KEY_EMAIL,value); }
    public void set_password(String value) { set_value(KEY_PASSWORD,value); }
    public void set_headImg(String value) { set_value(KEY_HEADIMAGE,value); }
    public void set_bikeImg(String value) { set_value(KEY_BIKEIMAGE,value); }
    public void set_gender(String value) { set_value(KEY_GENDER,value); }

    public static void write(Context context, UserInformation ui)
    {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(KEY_USERNAME,ui.nickName);
        editor.putString(KEY_EMAIL, ui.email);
        editor.putString(KEY_PASSWORD,ui.password);
        editor.putString(KEY_HEADIMAGE,ui.emoteImg);
        editor.putString(KEY_BIKEIMAGE,ui.bikeImg);
        editor.putString(KEY_GENDER,ui.gender);
        editor.putString(KEY_AGE, ui.age);
        editor.commit();
    }

    public static UserInformation read(Context context)
    {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        UserInformation ui = new UserInformation();
        ui.nickName = pref.getString(KEY_USERNAME, "");
        ui.email = pref.getString(KEY_EMAIL,"");
        ui.password = pref.getString(KEY_PASSWORD,"");
        ui.emoteImg = pref.getString(KEY_HEADIMAGE,"");
        ui.bikeImg = pref.getString(KEY_BIKEIMAGE,"");
        ui.gender = pref.getString(KEY_GENDER,"");
        ui.age = pref.getString(KEY_AGE,"");

        return ui;
    }
}
