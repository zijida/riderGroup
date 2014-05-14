package com.zijida.ridergroup.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zijida.ridergroup.ui.Interfaces.IThirdLoginListener;
import com.zijida.ridergroup.ui.util.applicationSettings;
import com.zijida.ridergroup.ui.util.tencentSsoHelper;
import com.zijida.ridergroup.ui.util.weiboSsoHelper;

public class LogonSelector extends plusActivity implements IThirdLoginListener
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
                        weibo.setComplementListener(LogonSelector.this);
                    }
                    weibo.login();
                }
                break;

                case R.id.id_login_qq:
                {
                    if(tencent==null)
                    {
                        tencent = new tencentSsoHelper(LogonSelector.this);
                        tencent.setComplementListener(LogonSelector.this);
                    }
                    tencent.login();
                }
                break;

                case R.id.id_login_direct:
                {
                    jump2Activity(ContextMain.class);
                }
                break;

                case R.id.id_goto_login:
                {
                    jump2Activity(Login.class);
                }
                break;

                case R.id.id_goto_regist:
                {
                    jump2Activity(Regist.class);
                }
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logon_selector);

        registClickListener2(R.id.id_login_qq,clickListener);
        registClickListener2(R.id.id_login_weibo,clickListener);
        registClickListener2(R.id.id_login_direct,clickListener);
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

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
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

    /**
     *
     * @param bundle 定义如下：
     *               key = "id",value = applicationSettings类定义的USER_XX。
     *               key = "User" value = 微博返回的User变量。
     */
    @Override
    public void onLoginSuccessed(Bundle bundle) {
        if(bundle==null) return;

        switch (bundle.getInt("id",0))
        {
            case applicationSettings.USER_QQ:
            {
                applicationSettings as = new applicationSettings(this);
                as.setUserComeFrome(applicationSettings.USER_QQ);
                jump2Activity(ContextMain.class);
            }
            break;

            case applicationSettings.USER_WB:
            {

            }
            break;
        }
    }
}
