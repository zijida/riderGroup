package com.zijida.ridergroup.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;

import com.zijida.ridergroup.ui.Interfaces.IRidingStatusListener;
import com.zijida.ridergroup.ui.Interfaces.IViewModuleListener;
import com.zijida.ridergroup.ui.util.customFont;
import com.zijida.ridergroup.ui.util.messageDialog;
import com.zijida.ridergroup.ui.util.ridingStatus;
import com.zijida.ridergroup.ui.viewModules.CountDown;
import com.zijida.ridergroup.ui.viewModules.GestureView;
import com.zijida.ridergroup.ui.viewModules.GradeShare;
import com.zijida.ridergroup.ui.viewModules.MainMenu;
import com.zijida.ridergroup.ui.viewModules.PanelStart;
import com.zijida.ridergroup.ui.viewModules.PanelWorking;
import com.zijida.ridergroup.ui.viewModules.UseGuide;
import com.zijida.ridergroup.ui.viewModules.UserCenter;
import com.zijida.ridergroup.ui.viewModules.UserSettings;
import com.zijida.ridergroup.ui.viewModules.ViewModuleHelper;
import com.zijida.ridergroup.ui.viewModules.ViewTuner;

import java.util.Timer;
import java.util.TimerTask;

public class ContextMain extends plusActivity
        implements IViewModuleListener
        ,IRidingStatusListener
{
    private final int ONTIMER = 1290;

    private ViewModuleHelper ctrl_panel = null;
    private ViewModuleHelper merge_panel = null;
    private ridingStatus status;
    private boolean frame_shine = false;
    private ViewTuner tuner;

    private Timer timer;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg)
        {
            if(msg.what == ONTIMER)
            {
                flash_riding_status();
            }
            else super.handleMessage(msg);
        }
    };

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.button_menu:
                {
                    show_layer_menu();
                }
                break;
            }
        }
    };

    protected void show_layer_sharegrade()
    {
        if(merge_panel != null)
        {
            merge_panel.stop();
            merge_panel = null;
        }

        merge_panel = new GradeShare(this);
        if(merge_panel.load_to(R.id.layout_float_layer))
        {
            merge_panel.setViewModuleListener(ContextMain.this);
            merge_panel.start();
        }
    }

    protected void show_layer_countdown()
    {
        if(merge_panel != null)
        {
            merge_panel.stop();
            merge_panel = null;
        }

        merge_panel = new CountDown(ContextMain.this);
        if(merge_panel.load_to(R.id.layout_float_layer))
        {
            merge_panel.setViewModuleListener(ContextMain.this);
            merge_panel.start();
        }
    }

    protected void show_layer_useguide()
    {
        if(merge_panel != null)
        {
            merge_panel.stop();
            merge_panel = null;
        }

        merge_panel = new UseGuide(ContextMain.this);
        if(merge_panel.load_to(R.id.layout_float_layer))
        {
            merge_panel.start();
        }
    }

    protected void show_layer_menu()
    {
        if(merge_panel != null)
        {
            merge_panel.stop();
            merge_panel = null;
        }

        merge_panel = new MainMenu(ContextMain.this);
        if(merge_panel.load_to(R.id.layout_float_layer))
        {
            merge_panel.setViewModuleListener(ContextMain.this);
            merge_panel.start();
        }
    }

    protected void show_layer_user_center()
    {
        if(merge_panel != null)
        {
            merge_panel.stop();
            merge_panel = null;
        }

        merge_panel = new UserCenter(this);
        if(merge_panel.load_to(R.id.layout_float_layer))
        {
            merge_panel.setViewModuleListener(ContextMain.this);
            merge_panel.start();
        }
    }

    protected void show_layer_user_settings()
    {
        if(merge_panel != null)
        {
            merge_panel.stop();
            merge_panel = null;
        }

        merge_panel = new UserSettings(this);
        if(merge_panel.load_to(R.id.layout_float_layer))
        {
            merge_panel.setViewModuleListener(ContextMain.this);
            merge_panel.start();
        }
    }

    protected void show_layer_working_panel()
    {
        if(ctrl_panel != null)
        {
            ctrl_panel.stop();
            ctrl_panel = null;
        }

        ctrl_panel = new PanelWorking(this);
        if(ctrl_panel.load_to(R.id.layout_button_panel))
        {
            ctrl_panel.setViewModuleListener(ContextMain.this);
            ctrl_panel.start();
        }
    }

    protected void show_layer_start_panel()
    {
        if(ctrl_panel != null)
        {
            ctrl_panel.stop();
            ctrl_panel = null;
        }

        ctrl_panel = new PanelStart(this);
        if(ctrl_panel.load_to(R.id.layout_button_panel))
        {
            ctrl_panel.setViewModuleListener(ContextMain.this);
            ctrl_panel.start();
        }
    }

    protected void show_dialog_grade_share()
    {
        /**监听对话框里面的button点击事件*/
        DialogInterface.OnClickListener dlgOnclicklistener = new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which)
                {
                    case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    {
                        dialog.dismiss();
                        show_layer_sharegrade();
                    }
                    break;

                    case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    {
                        dialog.dismiss();
                    }
                    break;
                }
            }
        };

        // 创建警示对话框
        messageDialog flipwarn_dialog = new messageDialog(ContextMain.this);
        // 设置对话框标题
        flipwarn_dialog.setTitle(getString(R.string.title_complete));
        // 设置对话框消息
        flipwarn_dialog.setMessage(getString(R.string.question_complete));
        // 添加选择按钮并注册监听
        flipwarn_dialog.setButton(AlertDialog.BUTTON_POSITIVE,"确定",dlgOnclicklistener);
        flipwarn_dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", dlgOnclicklistener);
        // 显示对话框
        flipwarn_dialog.show();
    }

    protected void show_riding_status()
    {
        int resid = 0;
        switch (status.getValue())
        {
            case ridingStatus.IDLE:    resid = R.drawable.lamp_idle; break;
            case ridingStatus.STOP:    resid = R.drawable.lamp_stop; break;
            case ridingStatus.RUN:     resid = R.drawable.lamp_run; break;
            case ridingStatus.PAUSE:   resid = R.drawable.lamp_pause; break;
        }

        View v = findViewById(R.id.image_riding_status);
        if(v != null)
        {
            v.setBackgroundResource(resid);
        }
    }

    protected void flash_riding_status()
    {
        int resid = 0;
        switch (status.getValue())
        {
            case ridingStatus.RUN:     resid = frame_shine?R.drawable.lamp_run_2:R.drawable.lamp_run; break;
            case ridingStatus.PAUSE:   resid = frame_shine?R.drawable.lamp_pause_2:R.drawable.lamp_pause; break;
            default: return;
        }

        if(resid == 0) return;

        View v = findViewById(R.id.image_riding_status);
        if(v != null)
        {
            v.setBackgroundResource(resid);
            frame_shine = !frame_shine;
        }
    }

    protected void regist_table_panel_touch_production()
    {
        if(tuner == null)
        {
            tuner = new ViewTuner(this);
            GestureView gv1 = new GestureView(this,R.id.text_speed_value,R.id.text_speed_union,R.id.view_speed_now,R.id.view_speed_avg);
            GestureView gv2 = new GestureView(this,R.id.text_duration_value,R.id.text_duration_union,R.id.view_single_duration,R.id.view_duration);
            GestureView gv3 = new GestureView(this,R.id.text_time_value,0,R.id.view_single_time,0);
            tuner.add(gv1);
            tuner.add(gv2);
            tuner.add(gv3);
        }
    }

    protected void start_timer()
    {
        if(timer == null)
        {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run()
                {
                    Message msg = new Message();
                    msg.what = ONTIMER;
                    handler.sendMessage(msg);
                }
            }, 1000, 1000);
        }
    }

    protected void cancel_timer()
    {
        if(timer != null)
        {
            timer.cancel();
            timer = null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contextmain);

        registClickListener(R.id.button_menu,clickListener);
        status = new ridingStatus(this);
        /// 初始化按钮区layout
        show_layer_start_panel();
        //// 注册主仪表盘的滑动控制
        regist_table_panel_touch_production();

        customFont.setFont(null, this, "HandelGothicEF-Bold");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        { //监控/拦截/屏蔽返回键
            if(merge_panel != null)
            {
                merge_panel.stop();
                merge_panel = null;
                return true;
            }
        }
        else if(keyCode == KeyEvent.KEYCODE_MENU)
        {
            //监控/拦截菜单键
            show_layer_menu();
        }
        else if(keyCode == KeyEvent.KEYCODE_HOME)
        {
            //由于Home键为系统键，此处不能捕获，需要重写onAttachedToWindow()
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * IViewModuleListener方法实现
     * onModuleComplete
     * @param bundle
     */
    @Override
    public void onModuleComplete(Bundle bundle)
    {
        if(bundle==null) return;
        int id = bundle.getInt("id");
        switch (id)
        {
            case R.layout.count_down:
            {
                status.setValue(ridingStatus.RUN);
                show_layer_working_panel();
                start_timer();
            }
            break;

            case R.layout.context_share:
            {
                status.setValue(ridingStatus.STOP);
                cancel_timer();
                show_layer_start_panel();
            }
            break;
        }
    }

    /**
     * IViewModuleListener方法实现
     * onModuleCancel
     * @param bundle
     */
    @Override
    public void onModuleCancel(Bundle bundle) {

    }

    @Override
    public void onMessage(Bundle bundle)
    {
        if(bundle == null) return;
        int msg_id = bundle.getInt("message_id");

        switch (msg_id)
        {
            case PanelStart.MSG_KEYPRESS_START:
            {
                show_layer_countdown();
            }
            break;

            case PanelWorking.MSG_KEYPRESS_DONE:
            {
                show_dialog_grade_share();
            }
            break;

            case PanelWorking.MSG_STATUS_CHANGE:
            {
                status.setValue(bundle.getInt("value"));
            }
            break;

            case MainMenu.MSG_MENU_USER_CENTER:
            {
                show_layer_user_center();
            }
            break;

            case MainMenu.MSG_MENU_SETTINGS:
            {
                show_layer_user_settings();
            }
            break;
        }
    }

    @Override
    public void onStatusChanged(int new_status)
    {
        show_riding_status();
    }
}
