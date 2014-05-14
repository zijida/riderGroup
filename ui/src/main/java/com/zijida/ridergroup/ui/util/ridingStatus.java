package com.zijida.ridergroup.ui.util;

import com.zijida.ridergroup.ui.Interfaces.IRidingStatusListener;

/**
 * Created by SHENJUN on 2014/5/8 0008.
 * Create in RiderGroup
 */
public class ridingStatus {
    public static final int IDLE = 0;
    public static final int STOP = 1;
    public static final int PAUSE = 2;
    public static final int RUN = 3;

    private int mStatus = IDLE;
    private IRidingStatusListener mListener;

    public ridingStatus(IRidingStatusListener listener)
    {
        mListener = listener;
    }

    public void setValue(int status)
    {
        if(mStatus != status)
        {
            mStatus = status;
            if(mListener != null)
            {
                mListener.onStatusChanged(status);
            }
        }
    }

    public int getValue() { return mStatus; }
}
