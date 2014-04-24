package com.zijida.ridergroup.ui.util;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by Administrator on 14-4-14.
 */
public class sideMenu extends RelativeLayout
{
    private Context m_context;
    private Scroller scroller;
    private float m_startX;
    private float m_lastX;

    public sideMenu(Context context)
    {
        super(context);
        m_context = context;
        scroller = new Scroller(context);
    }

    public sideMenu(Context context, android.util.AttributeSet attrs)
    {
        super(context,attrs);
        m_context = context;
        scroller = new Scroller(context);
    }

    public sideMenu(android.content.Context context, android.util.AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);
        m_context = context;
        scroller = new Scroller(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                m_lastX = event.getX();
                return false;

            case MotionEvent.ACTION_MOVE:
                int distance = Math.abs((int)(m_lastX-event.getX()));
                if(distance > 10)
                {
                    return true;
                }
                return false;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
            break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                if (scroller != null && !scroller.isFinished())  { scroller.abortAnimation();  }

                m_startX = event.getX();
            }
            break;

            case MotionEvent.ACTION_MOVE:
            {
                int offsetX = (int)(m_startX-event.getX());
                scrollBy(offsetX,0);
                m_startX = event.getX();
            }
            break;

            case MotionEvent.ACTION_CANCEL:
            break;

            case MotionEvent.ACTION_UP:
            {
                int menu_width = this.getWidth();
                if(m_startX<menu_width/2)
                {

                }
                else
                {

                }
            }
            break;
        }
        return true;
    }

    @Override
    public void computeScroll()
    {
        if(scroller.computeScrollOffset())
        {
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }
}
