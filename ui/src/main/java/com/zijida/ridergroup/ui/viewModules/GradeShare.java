package com.zijida.ridergroup.ui.viewModules;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.zijida.ridergroup.ui.database.OnesGrade;
import com.zijida.ridergroup.ui.R;
import com.zijida.ridergroup.ui.util.customFont;

/**
 * Created by SHENJUN on 14-5-8.
 * Create in RiderGroup
 */
public class GradeShare extends ViewModuleHelper {

    private View.OnClickListener clickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.button_save:
                {
                    stop();
                    notifyMessionComplete();
                }
                break;

                case R.id.button_no_save:
                {
                    stop();
                    notifyMessionComplete();
                }
                break;

                case R.id.button_share_wx:
                {
                }
                break;

                case R.id.button_share_wb:
                {
                }
                break;

            }
        }
    };

    public GradeShare(Context c) {
        super(c);
        layout_resource_id = R.layout.context_share;
    }

    @Override
    public boolean load_to(int layout_id)
    {
        if(super.load_to(layout_id))
        {
            customFont.setFont((Activity)context,R.id.text_time_range_value,"HandelGothicEF-Bold");
            customFont.setFont((Activity)context,R.id.text_range_total_value,"HandelGothicEF-Bold");
            customFont.setFont((Activity)context,R.id.text_time_total_value,"HandelGothicEF-Bold");
            customFont.setFont((Activity)context,R.id.text_speed_total_value,"HandelGothicEF-Bold");

            registClickListener(R.id.button_share_wb,clickListener);
            registClickListener(R.id.button_share_wx,clickListener);
            registClickListener(R.id.button_save,clickListener);
            registClickListener(R.id.button_no_save,clickListener);
            return true;
        }
        return false;
    }

    public void update(OnesGrade og)
    {
        if(og == null) return;

        setViewText(R.id.text_time_range_value,og.getDatetime());
        setViewText(R.id.text_range_total_value,og.getDistance());
        setViewText(R.id.text_time_total_value,og.getSpendtime());
        setViewText(R.id.text_speed_total_value,og.getSpeed());
    }

    public OnesGrade getvalues()
    {
        OnesGrade og = new OnesGrade();
        og.setDatetime((String)getViewText(R.id.text_time_range_value));
        og.setDistance((String)getViewText(R.id.text_range_total_value));
        og.setSpendtime((String)getViewText(R.id.text_time_total_value));
        og.setSpeed((String)getViewText(R.id.text_speed_total_value));

        return og;
    }

    public void notifyMessionComplete()
    {
        if(imListener != null)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("id",layout_resource_id);
            imListener.onModuleComplete(bundle);
        }
    }

}
