package com.zijida.ridergroup.ui.registfregment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.zijida.ridergroup.ui.R;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link registGender.onFragmentListener} interface
 * to handle interaction events.
 * Use the {@link registGender#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class registGender extends registBase {
    private static final int MALE = 0;
    private static final int FEMALE = 1;

    private ImageButton buttonNext;
    private int gender = MALE;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.button_next:
                {
                    if(m_listener!=null)
                    {
                        m_listener.getUserToken().set_gender(gender==MALE?"男":"女");

                        Bundle bundle = new Bundle();
                        bundle.putInt("id",R.layout.regist_gender);

                        m_listener.onCommit(bundle);
                    }
                }
                break;

                case R.id.button_back:
                {
                    if(m_listener!=null)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id",R.layout.regist_gender);

                        m_listener.onBack(bundle);
                    }
                }
                break;

                case R.id.button_flip:
                {
                    quest_flip();
                }
                break;

                case R.id.button_male:
                {
                    gender = MALE;
                    exchangeGender();
                }
                break;

                case R.id.button_female:
                {
                    gender = FEMALE;
                    exchangeGender();
                }
                break;
            }
        }
    };

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment registGender.
     */
    public static registGender newInstance() {
        registGender fragment = new registGender();
        return fragment;
    }

    public registGender() {
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
            rootView = inflater.inflate(R.layout.regist_gender,container,false);
        }

        registClickListener(R.id.button_next,clickListener);
        registClickListener(R.id.button_back,clickListener);
        registClickListener(R.id.button_flip,clickListener);
        registClickListener(R.id.button_male,clickListener);
        registClickListener(R.id.button_female,clickListener);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        if(m_listener != null)
        {
            String strGender = m_listener.getUserToken().get_gender();
            if(strGender!=null && !strGender.isEmpty())
            {
                gender = m_listener.getUserToken().get_gender().equals("男")?MALE:FEMALE;
                exchangeGender();
            }
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
    public int index() { return 4; }


    //////////////////////////////////////////////////////////////////////////////////////////////////
    private void set_background(int view_id,int res_id)
    {
        View view = rootView.findViewById(view_id);
        if(view != null)
        {
            view.setBackgroundResource(res_id);
        }
    }

    private void exchangeGender()
    {
        if(gender==MALE)
        {
            set_background(R.id.button_male,R.drawable.male_2);
            set_background(R.id.button_female,R.drawable.female);
        }
        else
        {
            set_background(R.id.button_male,R.drawable.male);
            set_background(R.id.button_female,R.drawable.female_2);
        }
    }
}
