package com.zijida.ridergroup.ui.util;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 14-4-1.
 */
public class customFont {
    private static final String FONT_FORMAT = "fonts/xxx.ttf";
    public static void setFont(ViewGroup root,Activity activity,String fontname)
    {
        if(activity == null) return;

        if(root==null)
        {
            ViewGroup sys_content = (ViewGroup) activity.findViewById(android.R.id.content);
            if(sys_content == null || sys_content.getChildCount()<=0) return;
            root = (ViewGroup)sys_content.getChildAt(0);

            if(root==null || !(root instanceof ViewGroup))
            {
                return;
            }

            if(root==null) return;
        }


        String fontpath = FONT_FORMAT.replace("xxx",fontname);
        Typeface tf = Typeface.createFromAsset(activity.getAssets(),fontpath);

        for(int i=0; i<root.getChildCount();i++)
        {
            View v = root.getChildAt(i);
            if(v instanceof ViewGroup) setFont((ViewGroup)v,activity,fontname);
            else if(v instanceof TextView) ((TextView)v).setTypeface(tf);
            else if(v instanceof Button)   ((Button)v).setTypeface(tf);
            else if(v instanceof EditText) ((EditText)v).setTypeface(tf);
        }
    }

    public static void setFont(Activity activity,int view_id,String fontname)
    {
        View v = activity.findViewById(view_id);
        if(v==null) return;

        String fontpath = FONT_FORMAT.replace("xxx",fontname);
        Typeface tf = Typeface.createFromAsset(activity.getAssets(),fontpath);

        if(v instanceof TextView) ((TextView)v).setTypeface(tf);
        else if(v instanceof Button)   ((Button)v).setTypeface(tf);
        else if(v instanceof EditText) ((EditText)v).setTypeface(tf);
    }


    public static void setFont(Activity activity,View view,String fontname)
    {
        if(view==null) return;

        String fontpath = FONT_FORMAT.replace("xxx",fontname);
        Typeface tf = Typeface.createFromAsset(activity.getAssets(),fontpath);

        if(view instanceof TextView) ((TextView)view).setTypeface(tf);
        else if(view instanceof Button)   ((Button)view).setTypeface(tf);
        else if(view instanceof EditText) ((EditText)view).setTypeface(tf);
    }
}
