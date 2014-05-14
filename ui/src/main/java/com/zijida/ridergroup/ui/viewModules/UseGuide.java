package com.zijida.ridergroup.ui.viewModules;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.zijida.ridergroup.ui.Adapters.GuideAdapter;
import com.zijida.ridergroup.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHENJUN on 14-5-5.
 * Create in RiderGroup
 */
public class UseGuide extends ViewModuleHelper {
    public static final int layout_res = R.layout.client_guide;
    private ViewPager pager;
    private List<View> viewList;
    private int scrollstate = 0;
    private boolean scroll_limit = false;
    private int curpagenumber;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.button_next:
                {
                    if(curpagenumber<viewList.size()-1)
                    {
                        pager.setCurrentItem(curpagenumber+1,true);
                        pager.getAdapter().notifyDataSetChanged();
                    }
                    else
                    {
                        setLayoutVisiable(false);
                    }
                }
                break;

                case R.id.menu_button_close:
                {
                    setLayoutVisiable(false);
                }
                break;
            }
        }
    };

    private ViewPager.OnPageChangeListener pageChangeListener =
            new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
                {
                    if(position == viewList.size()-1)
                    {
                        if(scrollstate == 1 && positionOffsetPixels == 0)
                        {
                            if(!scroll_limit)
                            {
                                scroll_limit = true;

                                /// 退出此Activity
                                stop();
                            }
                        }
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    curpagenumber = position;
                        View v = root.findViewById(R.id.button_next);
                        if(v != null)
                        {
                            if(position == viewList.size()-1)
                            {
                                v.setBackgroundResource(R.drawable.button_finish);
                            }
                            else
                            {
                                v.setBackgroundResource(R.drawable.button_next);
                            }
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    scrollstate = state;
                }
            };


    public UseGuide(Context c) {
        super(c);
    }

    public boolean load(int layout_id,int res_id)
    {
        if(super.load(layout_id,res_id))
        {
            setLayoutVisiable(true);
            return true;
        }
        else
        {
        }
        return false;
    }

    @Override
    public boolean OnKeyPress(View view, int i, KeyEvent keyEvent)
    {
        if(KeyEvent.ACTION_DOWN == keyEvent.getAction() && i == KeyEvent.KEYCODE_BACK)
        {
            stop();
            setLayoutVisiable(false);
            return true;
        }
        return false;
    }

    @Override
    public void start()
    {

        LayoutInflater mInflater = ((Activity)context).getLayoutInflater();
        if(mInflater == null)
        {
            return;
        }

        viewList = new ArrayList<View>();
        viewList.add(mInflater.inflate(R.layout.client_guide_1, null));
        viewList.add(mInflater.inflate(R.layout.client_guide_2, null));
        viewList.add(mInflater.inflate(R.layout.client_guide_3, null));
        viewList.add(mInflater.inflate(R.layout.client_guide_4, null));

        pager = (ViewPager)root.findViewById(R.id.viewpager_client);
        if(pager != null)
        {
            pager.setAdapter(new GuideAdapter(viewList));
            pager.setOnPageChangeListener(pageChangeListener);
            pager.setCurrentItem(0);

            registClickListener(R.id.button_next,clickListener);
            registClickListener(R.id.menu_button_close,clickListener);
        }
    }

    @Override
    public void stop()
    {
        viewList.clear();
        setLayoutVisiable(false);
        clear();
    }

}
