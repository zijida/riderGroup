package com.zijida.ridergroup.ui.middlefunc;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 14-3-28.
 */
public class middleToast {
    public static void message(Context context,String msg)
    {
        Toast toast = Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        toast.show();
    }
}
