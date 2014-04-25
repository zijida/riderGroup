package com.zijida.ridergroup.ui.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shenjun on 14-4-16.
 * Create in ${PROJECT_NAME}
 */
public class preferenceIO {

    private SharedPreferences spf;
    public Context context;

    public preferenceIO(Context context,String PREFERENCES_NAME)
    {
        this.context = context;
        spf = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public String get_value(String key)
    {
        if(spf==null) return null;
        return spf.getString(key,null);
    }

    public boolean get_boolean(String key,boolean defVal)
    {
        if(spf==null) return defVal;
        return spf.getBoolean(key, defVal);
    }

    public long get_long(String key)
    {
        if(spf==null) return -1;
        return spf.getLong(key, -1);
    }

    public int get_integer(String key)
    {
        if(spf==null) return -1;
        return spf.getInt(key, -1);
    }

    public void set_value(String key,String value)
    {
        if(spf==null) return;
        if(value==null ||value.length()==0) return;

        SharedPreferences.Editor editor = spf.edit();
        if(editor!=null)
        {
            editor.putString(key, value);
            editor.commit();
        }
    }

    public void set_boolean(String key,boolean value)
    {
        if(spf==null) return;

        SharedPreferences.Editor editor = spf.edit();
        if(editor!=null)
        {
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public void set_long(String key,long value)
    {
        if(spf==null) return;

        SharedPreferences.Editor editor = spf.edit();
        if(editor!=null)
        {
            editor.putLong(key, value);
            editor.commit();
        }
    } 
    public void set_integer(String key,int value)
    {
        if(spf==null) return;

        SharedPreferences.Editor editor = spf.edit();
        if(editor!=null)
        {
            editor.putInt(key, value);
            editor.commit();
        }
    }

    public void clear()
    {
        if(spf==null) return;

        SharedPreferences.Editor editor = spf.edit();
        editor.clear();
        editor.commit();
    }

}
