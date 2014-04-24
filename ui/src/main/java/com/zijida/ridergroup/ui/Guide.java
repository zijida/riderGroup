package com.zijida.ridergroup.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Guide extends Activity implements ViewPager.OnPageChangeListener {

    private List<View> items;
    private View[]  pages;
    private ViewPager  pager;
    private int scrollstate = 0;
    private final int page_size = 3;
    private boolean scroll_limit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        InitViewPager();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** 以下是完成OnPageChangeListener接口方法*/
    /** OnPageChangeListener：onPageScrolled 当前页面被滑动时调用 */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(position==(page_size-1))
        {
            if(scrollstate==1 && positionOffsetPixels==0)
            {
                if(scroll_limit == false) {
                    scroll_limit = true;

                    Intent intent = new Intent(Guide.this, LogonSelector.class);
                    this.startActivity(intent);
                    this.finish();
                }
            }
        }
    }

    /** OnPageChangeListener：onPageSelected  新的页面被选中时调用 */
    @Override
    public void onPageSelected(int position) {
        for (int i=0;i<page_size;i++)
        {
            pages[i].setEnabled(position==i?true:false);
        }
    }

    /** OnPageChangeListener：onPageScrollStateChanged 当滑动状态改变时调用  */
    @Override
    public void onPageScrollStateChanged(int state) {
        scrollstate = state;
    }


    private class guideAdapter extends PagerAdapter
    {
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==(object);
        }

        @Override
        public Object instantiateItem(View collection, int position)
        {
            ((ViewPager)collection).addView(items.get(position));
            return items.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            ((ViewPager)container).removeView(items.get(position));
        }
    }

    /**
     * * 初始化ViewPager
    */
    private void InitViewPager() {
        pager = (ViewPager) findViewById(R.id.viewpager_guide);
        items = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        items.add(mInflater.inflate(R.layout.guide_1, null));
        items.add(mInflater.inflate(R.layout.guide_2, null));
        items.add(mInflater.inflate(R.layout.guide_3, null));
        pager.setAdapter(new guideAdapter());
        pager.setOnPageChangeListener(this);
        pager.setCurrentItem(0);

        pages = new View[page_size];
        pages[0] = findViewById(R.id.guide_1_4_1);
        pages[1] = findViewById(R.id.guide_1_4_2);
        pages[2] = findViewById(R.id.guide_1_4_3);

        pages[1].setEnabled(false);
        pages[2].setEnabled(false);
    }
}
