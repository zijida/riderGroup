package com.zijida.ridergroup.ui.registfregment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.zijida.ridergroup.ui.R;
import com.zijida.ridergroup.ui.util.CacheUtils;
import com.zijida.ridergroup.ui.util.GallaryUtils;
import com.zijida.ridergroup.ui.util.ImageUtils;

import java.io.File;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link registBike.onFragmentListener} interface
 * to handle interaction events.
 * Use the {@link registBike#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class registBike extends registBase {
    private ImageButton buttonNext;
    private GallaryUtils gu;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.button_next:
                {
                    if(m_listener!=null)
                    {
                        /// 判断是否完成
                        String filename = CacheUtils.get_cache_route(CacheUtils.PATH_BIKE_IMAGE_CACHE);
                        File f = new File(filename);
                        if(!f.exists())
                        {
                            Toast.makeText(getActivity(), "请为爱车拍个照吧", Toast.LENGTH_LONG).show();
                            return;
                        }
                        else
                        {
                            m_listener.getUserToken().set_bikeImg(filename);
                        }

                        Bundle bundle = new Bundle();
                        bundle.putInt("id",R.layout.regist_bike);

                        m_listener.onCommit(bundle);
                    }
                }
                break;

                case R.id.button_back:
                {
                    if(m_listener!=null)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id",R.layout.regist_bike);

                        m_listener.onBack(bundle);
                    }
                }
                break;

                case R.id.button_flip:
                {
                    quest_flip();
                }
                break;

                case R.id.button_bikephoto:
                {
                    gu.call();
                }
                break;
            }
        }
    };

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment registBike.
     */
    public static registBike newInstance() {
        registBike fragment = new registBike();
        return fragment;
    }
    public registBike() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(rootView==null)
        {
            rootView = inflater.inflate(R.layout.regist_bike,container,false);
        }

        registClickListener(R.id.button_next,clickListener);
        registClickListener(R.id.button_back,clickListener);
        registClickListener(R.id.button_flip,clickListener);
        registClickListener(R.id.button_bikephoto,clickListener);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        gu = new GallaryUtils(getActivity());
        View img_view = rootView.findViewById(R.id.button_bikephoto);
        if(img_view != null)
        {
            gu.showPhoto(CacheUtils.PATH_BIKE_IMAGE_CACHE,img_view);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try
        {
            m_listener = (onFragmentListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement onFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        m_listener = null;
    }

    @Override
    public int index() { return 3; }


    @Override
    public void onCallActivityDone(int requestCode, int resultCode, Intent data)
    {
        View view = rootView.findViewById(R.id.button_bikephoto);
        if(view == null) return;

        switch(requestCode)
        {
            case GallaryUtils.INVOKE_CAMERA:
            {
                gu.cropPhoto(gu.tempPhotoUri(),view.getBackground().getIntrinsicWidth(),view.getBackground().getIntrinsicHeight());
            }
            break;

            case GallaryUtils.INVOKE_PICSTORE:
            {
                /// 来源：系统图片目录
                Uri uri = data.getData();
                gu.cropPhoto(uri,view.getBackground().getIntrinsicWidth(),view.getBackground().getIntrinsicHeight());
            }
            break;

            case GallaryUtils.INVOKE_CROP:
            {
                //从intent中获取图片
                final Bitmap headBitmap = (Bitmap) data.getExtras().get("data");
                if(headBitmap == null){  return;  }

                ImageUtils.saveBitmap(CacheUtils.get_cache_route(CacheUtils.PATH_BIKE_IMAGE_CACHE),headBitmap);
                gu.showPhoto(CacheUtils.PATH_BIKE_IMAGE_CACHE,view);
            }
            break;

        }
    }
}
