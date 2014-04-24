package com.zijida.ridergroup.ui.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by shenjun on 14-4-15.
 * Create in ${PROJECT_NAME}
 */

public class tencentSsoHelper {
    public static final String APP_ID = "101062409";
    public static final String SCOPE = "get_simple_userinfo";
    public Tencent tencent;
    public Context context;
    public UserInfo userinfo;
    public tencentToken token;

    public tencentSsoHelper(Context context)
    {
        this.context = context;
        tencent = Tencent.createInstance(APP_ID,context.getApplicationContext());
        userinfo = new UserInfo(context,tencent.getQQToken());
        token = new tencentToken(context.getApplicationContext());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(tencent != null)
        {
            tencent.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void login()
    {
        if(tencent == null) return;
        /// 预免重复登录
        pre_relogin();

        if(!tencent.isSessionValid())
        {
            tencent.login((Activity)context,SCOPE,new baseUiListener() {
                @Override
                public void onComplete(Object o)
                {
                    if(o==null) return;
                    save_token((JSONObject)o);
                    get_userInfo();
                }
            });
        }
        else
        {
            //tencent.logout(context);
        }
    }


    public void logout()
    {
        if(tencent.isSessionValid()) {
            tencent.logout(context);
        }
    }

    public void shared_2_QQ()
    {
        if(tencent==null || !tencent.isSessionValid()) return;

        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "新闻的标题");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY,  "新闻的摘要");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL,  "http://www.qq.com/news/1.html");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME,  "测试应用");
        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        tencent.shareToQQ((Activity)context,params,new baseUiListener());
    }

    //*****************************************************************************************************************//

    private class baseUiListener implements IUiListener
    {
        @Override
        public void onComplete(Object o) {

        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(context, uiError.errorMessage, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
        }
    };

    private void get_userInfo()
    {
        userinfo.getUserInfo(new baseUiListener() {
            @Override
            public void onComplete(Object o)
            {
                save_userinfo((JSONObject)o);
            }
        });
    }
    private void save_token(JSONObject o)
    {
        try
        {
            int ret = o.getInt("ret");
            if(ret != 0)
            {
                Toast.makeText(context,o.getString("msg"), Toast.LENGTH_SHORT).show();
                return;
            }

            token.set_sessionTime(String.valueOf(System.currentTimeMillis()));
            token.set_open_id(o.getString(tencentToken.KEY_OPENID));
            token.set_access_token(o.getString(tencentToken.KEY_ACCESS_TOKEN));
            token.set_expires_in(o.getString(tencentToken.KEY_EXPIRES_IN));
        }
        catch (JSONException je)
        {
            Toast.makeText(context, je.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void save_userinfo(JSONObject o)
    {
        try
        {
            int ret = o.getInt("ret");
            if(ret != 0)
            {
                Toast.makeText(context,o.getString("msg"), Toast.LENGTH_SHORT).show();
                return;
            }

            token.set_nickname(o.getString(tencentToken.KEY_NICK_NAME));
            token.set_figure_space(o.getString(tencentToken.KEY_FIGURE_URL_SPACE));
            token.set_figure_qq(o.getString(tencentToken.KEY_FIGURE_URL_QQ));
            token.set_gender(o.getString(tencentToken.KEY_GENDER));
        }
        catch (JSONException je)
        {
            Toast.makeText(context, je.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void pre_relogin()
    {
        if(token==null) return;
        if(token.get_open_id() == null) return;

        long sstime = token.get_integer(tencentToken.KEY_LASTSESSIONTIME);
        long expire = token.get_integer(tencentToken.KEY_EXPIRES_IN);
        if(sstime<0 || expire<0) return;

        sstime = (System.currentTimeMillis()-sstime)/1000;

        if(sstime>expire) return;  // token期限失效判断

        tencent.setOpenId(token.get_open_id());
        tencent.setAccessToken(token.get_access_token(),token.get_expires_in());
    }
}
