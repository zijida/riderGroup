package com.zijida.ridergroup.ui.viewModules;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.zijida.ridergroup.ui.database.OnesGrade;
import com.zijida.ridergroup.ui.R;
import com.zijida.ridergroup.ui.util.customFont;

/**
 * Created by SHENJUN on 14-5-8.
 * Create in RiderGroup
 */
public class GradeShare extends ViewModuleHelper {
    public static final int layout_res = R.layout.context_share;

    public GradeShare(Context c) {
        super(c);
    }

    public boolean load(int layout_id,int res_id)
    {
        if(super.load(layout_id,res_id))
        {
            customFont.setFont((Activity)context,R.id.text_time_range_value,"HandelGothicEF-Bold");
            customFont.setFont((Activity)context,R.id.text_range_total_value,"HandelGothicEF-Bold");
            customFont.setFont((Activity)context,R.id.text_time_total_value,"HandelGothicEF-Bold");
            customFont.setFont((Activity)context,R.id.text_speed_total_value,"HandelGothicEF-Bold");
            setLayoutVisiable(true);
            return true;
        }
        else
        {
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

    public void notifyMessionComplete()
    {
        if(imListener != null)
        {
            Bundle bundle = new Bundle();
            bundle.putInt("id",layout_res);
            imListener.onModuleComplete(bundle);
        }
    }

}
