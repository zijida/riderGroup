package com.zijida.ridergroup.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

import com.zijida.ridergroup.ui.util.applicationSettings;

public class Main extends plusActivity {
    private applicationSettings as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startNavigate();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    //// 以下为导航部分

    private static final int GO_LOGIN = 1000;
    private static final int GO_GUIDE = 1001;
    private static final int GO_CONTEXT = 1002;
    /**
     * Handler:跳转到不同界面
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg)
        {
            Intent intent=null;
            switch (msg.what)
            {
                case GO_GUIDE:
                    intent = new Intent(Main.this,Guide.class);
                    break;
                case GO_LOGIN:
                    intent = new Intent(Main.this,LogonSelector.class);
                    break;
                case GO_CONTEXT:
                    intent = new Intent(Main.this,ContextMain.class);
                    break;
            }

            if(intent != null)
            {
                Main.this.startActivity(intent);
                Main.this.finish();
            }
            super.handleMessage(msg);
        }
    };

    private void startNavigate()
    {
        final int delay = 1000;
        Context context = this.getApplicationContext();
        if(as==null)
        {
            as = new applicationSettings(this);
        }

        if(as.isFirstRun())
        {
            // 使用Handler的postDelayed方法，1秒后执行跳转到MainActivity
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, delay);
            as.setFirstRunSign(false);
        }
        else
        {
            if(as.userComeFrom()==-1)
            {
                mHandler.sendEmptyMessageDelayed(GO_LOGIN, delay);
            }
            else
            {
                /// 直接进入
                mHandler.sendEmptyMessageDelayed(GO_CONTEXT, delay);
            }
        }
    }
}
