package com.zijida.ridergroup.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zijida.ridergroup.ui.middlefunc.middleToast;
import com.zijida.ridergroup.ui.util.customFont;

public class ContextMain extends plusActivity
{
    private LayoutInflater mlayout;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.button_menu:
                    middleToast.message(getApplicationContext(), "菜单启动，正在开发...");
                break;

                case R.id.button_start:
                {
                    loadLayout(R.layout.merge_buttons);
                }
                break;

                case R.id.button_pause:
                {
                    loadLayout(R.layout.merge_start);
                }
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contextmain);

        registClickListener(R.id.button_menu,clickListener);
        /// 初始化按钮区layout
        loadLayout(R.layout.merge_start);
        customFont.setFont(null, this, "HandelGothicEF-Bold");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.context, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadLayout(int layout_id)
    {
        mlayout = LayoutInflater.from(this);
        if(mlayout == null) return;

        ViewGroup layout_panel = (ViewGroup)findViewById(R.id.layout_button_panel);
        if(layout_panel==null) return;

        layout_panel.removeAllViews();
        View root = mlayout.inflate(layout_id,layout_panel);
        if(root != null)
        {
            switch (layout_id)
            {
                case R.layout.merge_start:
                {
                    registClickListener(root, R.id.button_start, clickListener);
                }
                break;

                case R.layout.merge_buttons:
                {
                    registClickListener(root,R.id.button_pause,clickListener);
                    registClickListener(root,R.id.button_done,clickListener);
                    registClickListener(root,R.id.button_lock,clickListener);
                }
                break;
            }
        }

    }
}
