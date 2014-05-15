package com.zijida.ridergroup.ui.viewModules;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.zijida.ridergroup.ui.Interfaces.IViewModuleListener;

/**
 * Created by SHENJUN on 14-4-30.
 * Create in RiderGroup
 * 辅助类：为布局某一模块动态引入内容。
 */
public class ViewModuleHelper implements View.OnKeyListener {
    protected Context context;
    protected View root;
    protected ViewGroup root_group;
    protected IViewModuleListener imListener;     // 交互上层提供的回调方法，辅助完成ViewModule自身之外的操作。
    private Animation viewShowAnimation,viewHideAnimation;
    public int layout_resource_id = 0;
    private boolean support_key_message = true;


    protected Animation.AnimationListener animationHideListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            clear();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
    public ViewModuleHelper(Context c)
    {
        this.context = c;
        this.root = null;
        this.root_group = null;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent)
    {
        if(!support_key_message) return false;

        if(KeyEvent.ACTION_DOWN == keyEvent.getAction() && i == KeyEvent.KEYCODE_BACK)
        {
            stop();
            return true;
        }
        return false;
    }

    public void setViewModuleListener(IViewModuleListener listener) { imListener = listener; }
    public void setSupportKeyMessage(boolean v) { support_key_message = v; }

    /**
     * 加载layout资源到指定layout控件下
     * @param layout_id         布局XML文件中承载本类的layout的ID值
     * @return                   初始化成功返回true,否则返回false.
     */
    public boolean load_to(int layout_id)
    {
        if(context == null) return false;
        if(root_group == null) {
            root_group = (ViewGroup) ((Activity) context).findViewById(layout_id);
            if (root_group != null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                if (layout_resource_id != 0) {
                    root = inflater.inflate(layout_resource_id, root_group);
                }
            }
        }
        return (root != null);
    }

    /**
     *
     */
    public void  clear()
    {
        if(root_group != null)
        {
            root_group.removeAllViews();
            root_group = null;
            root = null;
        }
    }

    public void setViewShowAnimation(int anim_id,Animation.AnimationListener listener)
    {
        viewShowAnimation = AnimationUtils.loadAnimation(context,anim_id);
        viewShowAnimation.setAnimationListener(listener);
    }

    public void setViewHideAnimation(int anim_id,Animation.AnimationListener listener)
    {
        viewHideAnimation = AnimationUtils.loadAnimation(context,anim_id);
        viewHideAnimation.setAnimationListener(listener);
    }

    /**
     *
     * @param visiable
     */
    public void  setLayoutVisiable(boolean visiable)
    {
        if(root_group != null)
        {
            int v = visiable?View.VISIBLE:View.GONE;
            if(root_group.getVisibility() != v)
            {
                root_group.setVisibility(v);

                if(visiable && viewShowAnimation!=null)
                {
                    root_group.startAnimation(viewShowAnimation);
                }
                else if(!visiable && viewHideAnimation!=null)
                {
                    root_group.startAnimation(viewHideAnimation);
                }
                else {
                    if (!visiable) {
                        clear();
                    }
                }
            }
        }
    }

    /**
     *
     * @param enable
     */
    public void  setViewEnable(int view_id,boolean enable)
    {
        if(root != null)
        {
            View v = root.findViewById(view_id);
            if(v != null && v.isEnabled() != enable)
            {
                v.setEnabled(enable);
            }
        }
    }

    /**
     *
     * @param view_id
     * @param text
     */
    public void  setViewText(int view_id,String text)
    {
        if(text==null) return;

        if(root != null)
        {
            View v = root.findViewById(view_id);
            if(v != null && v instanceof TextView)
            {
                ((TextView)v).setText(text);
            }
        }
    }

    /**
     *
     * @param view_id
     */
    public CharSequence getViewText(int view_id)
    {
        if(root != null)
        {
            View v = root.findViewById(view_id);
            if(v != null && v instanceof TextView)
            {
                return ((TextView)v).getText();
            }
        }
        return null;
    }

    /**
     *
     * @param view_id
     * @param listener
     */
    public void registClickListener(int view_id,View.OnClickListener listener)
    {
        View v = root.findViewById(view_id);
        if(v != null)
        {
            v.setOnClickListener(listener);
        }
    }

    /**
     * 加载启动
     * 子类重载方法
     */
    public void start()
    {
        setLayoutVisiable(true);
    }

    /**
     * 卸载
     * 子类重载方法
     */
    public void stop()
    {
        setLayoutVisiable(false);
    }


}
