package com.zijida.ridergroup.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zijida.ridergroup.ui.middlefunc.middleToast;
import com.zijida.ridergroup.ui.util.customFont;

public class Login extends plusActivity {

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.button_back:
                {
                    Intent intent = new Intent(Login.this,LogonSelector.class);
                    intent.putExtra("is_back",1);
                    Login.this.startActivity(intent);
                    Login.this.finish();
                }
                break;

                case R.id.button_enter:
                    middleToast.message(getApplicationContext(),"验证登录，正在开发...");
                break;

                case R.id.button_user_protocal:
                    middleToast.message(getApplicationContext(),"用户协议，正在开发...");
                break;

                case R.id.button_regist:
                {
                    Intent intent = new Intent(Login.this,Regist.class);
                    Login.this.startActivity(intent);
                    Login.this.finish();
                }
                break;

                case R.id.button_forgetpassword:
                    middleToast.message(getApplicationContext(),"忘记密码，正在开发...");
                break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        registClickListener(R.id.button_back,clickListener);
        registClickListener(R.id.button_enter,clickListener);
        registClickListener(R.id.button_user_protocal,clickListener);
        registClickListener(R.id.button_regist,clickListener);
        registClickListener(R.id.button_forgetpassword,clickListener);

        customFont.setFont(null, this, "HandelGothicEF-Bold");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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

}
