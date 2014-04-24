package com.zijida.ridergroup.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.zijida.ridergroup.ui.util.statusMatrix;

/**
 * Created by Administrator on 14-4-3.
 */
public class plusActivity extends Activity
{
     @Override
     protected void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);
         int distance = getIntent().getIntExtra("is_back",0);
         if(distance == 0)
            overridePendingTransition( R.anim.right_in,R.anim.left_out);
         else
             overridePendingTransition( R.anim.left_in,R.anim.right_out);
     }

    protected void registClickListener(int id,View.OnClickListener clickListener)
    {
        View view = findViewById(id);
        if(view!=null)
        {
            view.setOnClickListener(clickListener);
            statusMatrix.setButtonStateChangeListener(view,false);
        }
    }
}
