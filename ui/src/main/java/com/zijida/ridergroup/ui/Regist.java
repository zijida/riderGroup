package com.zijida.ridergroup.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.zijida.ridergroup.ui.registfregment.registAge;
import com.zijida.ridergroup.ui.registfregment.registBase;
import com.zijida.ridergroup.ui.registfregment.registBike;
import com.zijida.ridergroup.ui.registfregment.registEmote;
import com.zijida.ridergroup.ui.registfregment.registGender;
import com.zijida.ridergroup.ui.registfregment.registNick;
import com.zijida.ridergroup.ui.registfregment.registUser;
import com.zijida.ridergroup.ui.util.userToken;

public class Regist extends FragmentActivity
        implements registBase.onFragmentListener,GestureDetector.OnGestureListener
{
    private int fragindex = -1;
    private int distance = 0;
    private userToken ut;
    private registBase rb;

    private GestureDetector gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ut = new userToken(this);
        setContentView(R.layout.regist);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        showFirstFregment();

        gd = new GestureDetector(this,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.regist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    @Override
    public void onCommit(Bundle bundle) {
        if(bundle == null) return;
        int id = bundle.getInt("id");
        switch (id)
        {
            case R.layout.regist_user:
            break;

            case R.layout.regist_age:
            {
                Intent intent = new Intent(this,ContextMain.class);
                this.startActivity(intent);
                this.finish();
            }
            break;
        }
        flip2NextFregment();
    }

    @Override
    public void onBack(Bundle bundle) {
        if(bundle == null) return;
        int id = bundle.getInt("id");
        switch (id)
        {
            case R.layout.regist_user:
            {
                Intent intent = new Intent(this,Login.class);
                intent.putExtra("is_back",1);
                this.startActivity(intent);
                this.finish();
            }
            break;

            default:  flip2PrevFregment(); break;
        }
    }

    @Override
    public void onFlip()
    {
        Intent intent = new Intent(this,ContextMain.class);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public userToken getUserToken() {
        return ut;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK)
        {
            //result is not correct
            return;
        }
        else if(rb != null)
        {
            rb.onCallActivityDone(requestCode,resultCode,data);
        }
    }

    /**
     * ********************* 以下是触屏手势响应区域  ********************************
    */
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2)
    {
        if(v>200)
        {
            flip2PrevFregment();
            return true;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return gd.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(this,LogonSelector.class);
            intent.putExtra("is_back",1);
            this.startActivity(intent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //// 加载并初始化Fragment
    private registBase createFregment(int index)
    {
        rb = null;
        switch (index)
        {
            case 0: rb = registUser.newInstance();  break;
            case 1: rb = registNick.newInstance();  break;
            case 2: rb = registEmote.newInstance(); break;
            case 3: rb = registBike.newInstance();  break;
            case 4: rb = registGender.newInstance();   break;
            case 5: rb = registAge.newInstance();   break;

        }
        return rb;
    }

    private void showFregment(registBase fragment)
    {
        if(fragment==null) return;

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(distance == 0)
            ft.setCustomAnimations(R.anim.right_in, R.anim.left_out)
                    .replace(R.id.regist_container, fragment)
                    .commit();
        else
            ft.setCustomAnimations(R.anim.left_in, R.anim.right_out)
                    .replace(R.id.regist_container, fragment)
                    .commit();

        fragindex = fragment.index();
    }

    private void showFirstFregment()
    {
        showFregment(createFregment(0));
    }

    private void flip2NextFregment()
    {
        distance = 0;
        showFregment(createFregment(fragindex + 1));
    }

    private void flip2PrevFregment()
    {
        distance = 1;
        showFregment(createFregment(fragindex - 1));
    }

}
