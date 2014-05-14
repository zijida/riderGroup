package com.zijida.ridergroup.ui.viewModules;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    protected IViewModuleListener imListener;     //

    public ViewModuleHelper(Context c)
    {
        this.context = c;
        this.root = null;
        this.root_group = null;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return OnKeyPress(view,i,keyEvent);
    }

    /**
     * 响应按键消息
     * @param view
     * @param i
     * @param keyEvent
     * @return
     */
    public boolean OnKeyPress(View view, int i, KeyEvent keyEvent)
    {
        return false;
    }

    public void setViewModuleListener(IViewModuleListener listener) { imListener = listener; }

    /**
     * 加载layout资源到指定layout控件下
     * @param layout_id         布局XML文件中承载本类的layout的ID值
     * @param layout_res_id    本类要完成的layout资源文件ID
     * @return                   初始化成功返回true,否则返回false.
     */
    public boolean load(int layout_id,int layout_res_id)
    {
        if(context == null) return false;

        root_group = (ViewGroup)((Activity)context).findViewById(layout_id);
        if(root_group != null)
        {
            root_group.removeAllViews();

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            if(inflater != null)
            {
                root = inflater.inflate(layout_res_id,root_group);
                if(root != null)
                {
                    return true;
                }
            }
        }
        return false;
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
    }

    /**
     * 卸载
     * 子类重载方法
     */
    public void stop()
    {
    }


}
