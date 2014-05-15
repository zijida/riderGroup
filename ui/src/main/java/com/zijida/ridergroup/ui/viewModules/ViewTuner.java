package com.zijida.ridergroup.ui.viewModules;

import android.content.Context;
import android.util.TypedValue;
import android.view.MotionEvent;

import com.zijida.ridergroup.ui.Interfaces.IViewGestureListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHENJUN on 2014/5/13 0013.
 * Create in RiderGroup
 */
public class ViewTuner implements IViewGestureListener {
    private List<GestureView> viewGroup;
    private Context mContext;

    public ViewTuner(Context context)
    {
        mContext = context;
        viewGroup = new ArrayList<GestureView>();
    }

    public void add(GestureView view)
    {
        view.setGestureListener(this);
        viewGroup.add(view);
    }

    @Override
    public boolean OnViewFling(GestureView view, MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2)
    {
        int offsetX = (int)(motionEvent2.getX()-motionEvent.getX());
        if(offsetX>20 || offsetX<-20)
        {
            if(view.secondProp != null)
            {
                view.secondProp.setEnabled(!view.secondProp.isEnabled());
                view.mainProp.setEnabled(!view.mainProp.isEnabled());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean OnViewScroll(GestureView view, MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2)
    {
        return false;
    }

    @Override
    public void OnViewPress(GestureView view, MotionEvent motionEvent)
    {
        for (GestureView vv : viewGroup)
        {
            if (vv.equals(view))
            {
                if (vv.valueView != null)
                {
                    vv.valueView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 60f);
                }
            }
            else
            {
                if (vv.valueView != null)
                {
                    vv.valueView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 28f);
                }
            }
        }
    }

    @Override
    public void OnViewLongPress(GestureView view, MotionEvent motionEvent) {
    }
}
