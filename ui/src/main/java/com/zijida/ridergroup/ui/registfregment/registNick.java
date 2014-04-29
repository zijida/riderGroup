package com.zijida.ridergroup.ui.registfregment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.zijida.ridergroup.ui.R;
import com.zijida.ridergroup.ui.util.customFont;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link registNick.onFragmentListener} interface
 * to handle interaction events.
 * Use the {@link registNick#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class registNick extends registBase {
    private ImageButton buttonNext;

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.button_next:
                {
                    if(m_listener!=null)
                    {
                        String nickname = get_editText(R.id.edit_nickname);
                        if(nickname == null || nickname.isEmpty())
                        {
                            Toast.makeText(getActivity(), "昵称不可为空", Toast.LENGTH_LONG).show();
                            return;
                        }
                        else
                        {
                            m_listener.getUserToken().set_userName(nickname);
                        }

                        Bundle bundle = new Bundle();
                        bundle.putInt("id",R.layout.regist_nick);
                        m_listener.onCommit(bundle);
                    }
                }
                break;

                case R.id.button_back:
                {
                    if(m_listener!=null)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id",R.layout.regist_nick);

                        m_listener.onBack(bundle);
                    }
                }
                break;
            }
        }
    };

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment registNick.
     */
    public static registNick newInstance() {
        registNick fragment = new registNick();
        return fragment;
    }
    public registNick() {
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
            rootView = inflater.inflate(R.layout.regist_nick,container,false);
        }

        registClickListener(R.id.button_next,clickListener);
        registClickListener(R.id.button_back,clickListener);

        //// 自定义字体
        customFont.setFont(getActivity(),rootView.findViewById(R.id.edit_nickname), "HandelGothicEF-Bold");

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        if(m_listener != null)
        {
            set_editText(R.id.edit_nickname,m_listener.getUserToken().get_userName());
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
    public int index() { return 1; }
}
