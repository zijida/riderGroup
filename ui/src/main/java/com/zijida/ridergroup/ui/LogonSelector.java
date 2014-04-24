package com.zijida.ridergroup.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zijida.ridergroup.ui.middlefunc.middleToast;
import com.zijida.ridergroup.ui.util.tencentSsoHelper;
import com.zijida.ridergroup.ui.util.weiboSsoHelper;

public class LogonSelector extends plusActivity
{
    private tencentSsoHelper tencent;
    private weiboSsoHelper weibo;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.id_login_weibo:
                {
                    if(weibo==null)
                    {
                        weibo = new weiboSsoHelper(LogonSelector.this);
                    }
                    weibo.login();
                }
                break;

                case R.id.id_login_qq:
                {
                    if(tencent==null)
                    {
                        tencent = new tencentSsoHelper(LogonSelector.this);
                    }
                    tencent.login();
                }
                break;

                case R.id.id_login_direct:
                {
                    middleToast.message(getApplicationContext(),"前往主界面，请准备...");
                    Intent intent = new Intent(LogonSelector.this, ContextMain.class);
                    LogonSelector.this.startActivity(intent);
                    LogonSelector.this.finish();
                }
                break;

                case R.id.id_goto_login:
                {
                    middleToast.message(getApplicationContext(),"前往登录界面，请准备...");
                    Intent intent = new Intent(LogonSelector.this,Login.class);
                    LogonSelector.this.startActivity(intent);
                    LogonSelector.this.finish();
                }
                break;

                case R.id.id_goto_regist:
                {
                    middleToast.message(getApplicationContext(),"前往注册界面，请准备...");
                    Intent intent = new Intent(LogonSelector.this,Regist.class);
                    LogonSelector.this.startActivity(intent);
                    LogonSelector.this.finish();
                }
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logon_selector);

        registClickListener(R.id.id_login_qq,clickListener);
        registClickListener(R.id.id_login_weibo,clickListener);
        registClickListener(R.id.id_login_direct,clickListener);
        registClickListener(R.id.id_goto_login,clickListener);
        registClickListener(R.id.id_goto_regist,clickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logon_selector, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(tencent != null)
        {
            tencent.onActivityResult(requestCode, resultCode, data);
        }
        if(weibo != null)
        {
            weibo.onActivityResult(requestCode, resultCode, data);
        }
    }

}
