package com.zijida.ridergroup.ui.viewModules;

import android.app.Activity;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zijida.ridergroup.ui.Interfaces.IViewGestureListener;

/**
 * Created by SHENJUN on 2014/5/13 0013.
 * Create in RiderGroup
 */
public class GestureView
{
    private GestureDetector gd;
    private Context context;
    private IViewGestureListener m_listener;

    public TextView valueView = null;
    public TextView unionView = null;
    public ImageView mainProp = null;
    public ImageView secondProp = null;

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent)
        {
            if(gd != null)
            {
                return gd.onTouchEvent(motionEvent);
            }
            else return false;
        }
    };

    private GestureDetector.OnGestureListener listener = new GestureDetector.OnGestureListener() {
        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if(m_listener != null)
            {
                m_listener.OnViewPress(GestureView.this,motionEvent);
                return true;
            }
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
            if(m_listener != null)
            {
                return m_listener.OnViewScroll(GestureView.this,motionEvent,motionEvent2,v,v2);
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            if(m_listener != null)
            {
                m_listener.OnViewLongPress(GestureView.this, motionEvent);
            }
        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
            if(m_listener != null)
            {
                return m_listener.OnViewFling(GestureView.this, motionEvent, motionEvent2, v, v2);
            }
            return false;
        }
    };

    public GestureView(Context context,int value_id,int union_id,int main_prop_id,int second_prop_id)
    {
        this.context = context;
        if(value_id != 0)
        {
            valueView = (TextView)((Activity)context).findViewById(value_id);
            if(valueView != null)
            {
                gd = new GestureDetector(context,listener);
                valueView.setOnTouchListener(onTouchListener);
            }
        }

        if(union_id != 0)
        {
            unionView = (TextView)((Activity)context).findViewById(union_id);
        }

        if(main_prop_id != 0)
        {
            mainProp = (ImageView)((Activity)context).findViewById(main_prop_id);
        }

        if(second_prop_id != 0)
        {
            secondProp = (ImageView)((Activity)context).findViewById(second_prop_id);
            secondProp.setEnabled(false);
        }
    }

    public void setGestureListener(IViewGestureListener listener)
    {
        m_listener =  listener;
    }
}
