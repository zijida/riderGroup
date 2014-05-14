package com.zijida.ridergroup.ui.Interfaces;

import android.view.MotionEvent;

import com.zijida.ridergroup.ui.viewModules.GestureView;

/**
 * Created by SHENJUN on 2014/5/13 0013.
 * Create in RiderGroup
 */
public interface IViewGestureListener
{
    public boolean OnViewFling(GestureView view,MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2);
    public boolean OnViewScroll(GestureView view,MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2);
    public void OnViewPress(GestureView view,MotionEvent motionEvent);
    public void OnViewLongPress(GestureView view,MotionEvent motionEvent);
}
