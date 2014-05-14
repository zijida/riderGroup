package com.zijida.ridergroup.ui;

import android.app.Activity;
import android.content.Intent;
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
         /*
         int distance = getIntent().getIntExtra("is_back",0);
         if(distance == 0)
            overridePendingTransition( R.anim.right_in,R.anim.left_out);
         else
             overridePendingTransition( R.anim.left_in,R.anim.right_out);
             */
     }

    protected void registClickListener(int id,View.OnClickListener clickListener)
    {
        if(clickListener == null) return;

        View view = findViewById(id);
        if(view!=null)
        {
            view.setOnClickListener(clickListener);
        }
    }

    protected void registClickListener(View root,int id,View.OnClickListener clickListener)
    {
        if(root == null) return;
        if(clickListener == null) return;

        View view = root.findViewById(id);
        if(view!=null)
        {
            view.setOnClickListener(clickListener);
        }
    }

    protected void registClickListener2(int id,View.OnClickListener clickListener)
    {
        View view = findViewById(id);
        if(view!=null)
        {
            view.setOnClickListener(clickListener);
            statusMatrix.setButtonStateChangeListener(view,statusMatrix.MATRIX_GRAY);
        }
    }

    protected void setDefaultTouchLook(int id,int type)
    {
        View view = findViewById(id);
        if(view!=null)
        {
            statusMatrix.setButtonStateChangeListener(view,type);
        }
    }

    protected void jump2Activity(java.lang.Class cls )
    {
        Intent intent = new Intent(this, cls);
        this.startActivity(intent);
        this.finish();
    }
}
