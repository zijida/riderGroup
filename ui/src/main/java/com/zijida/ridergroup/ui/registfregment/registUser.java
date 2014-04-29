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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link registUser.onFragmentListener} interface
 * to handle interaction events.
 * Use the {@link registUser#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class registUser extends registBase {
    private ImageButton buttonNext;

    //判断email格式是否正确
    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId())
            {
                case R.id.button_next:
                {
                    if(m_listener!=null)
                    {
                        String email = get_editText(R.id.edit_mail);
                        if(email == null || email.isEmpty())
                        {
                            Toast.makeText(getActivity(),"邮箱不可为空",Toast.LENGTH_LONG).show();
                            return;
                        }
                        else if(!isEmail(email))
                        {
                            Toast.makeText(getActivity(),"邮箱格式不正确",Toast.LENGTH_LONG).show();
                            return;
                        }
                        else
                        {
                            m_listener.getUserToken().set_email(email);
                        }

                        String password = get_editText(R.id.edit_password);
                        if(password == null || password.isEmpty())
                        {
                            Toast.makeText(getActivity(),"密码不可为空",Toast.LENGTH_LONG).show();
                            return;
                        }
                        else if(password.length()<6)
                        {
                            Toast.makeText(getActivity(),"密码长度不可少于6个字符",Toast.LENGTH_LONG).show();
                            return;
                        }
                        else
                        {
                            m_listener.getUserToken().set_password(password);
                        }

                        /// 完成回调
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", R.layout.regist_user);
                        m_listener.onCommit(bundle);
                    }
                }
                break;

                case R.id.button_back:
                {
                    if(m_listener!=null)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putInt("id",R.layout.regist_user);
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
     * @return A new instance of fragment registUser.
     */
    public static registUser newInstance() {
        registUser fragment = new registUser();
        return fragment;
    }

    public registUser() {
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
            rootView = inflater.inflate(R.layout.regist_user,container,false);
        }

        registClickListener(R.id.button_next,clickListener);
        registClickListener(R.id.button_back,clickListener);

        //// 自定义字体
        customFont.setFont(getActivity(),rootView.findViewById(R.id.edit_mail), "HandelGothicEF-Bold");
        customFont.setFont(getActivity(),rootView.findViewById(R.id.edit_password), "HandelGothicEF-Bold");

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        if(m_listener != null)
        {
            set_editText(R.id.edit_mail,m_listener.getUserToken().get_email());
            set_editText(R.id.edit_password, m_listener.getUserToken().get_password());
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
            throw new ClassCastException(activity.toString() + " must implement OnFragmentUserListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        m_listener = null;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentUserListener {
        public void onUserChanged(String name,String password);
        public void onBack();
    }

    @Override
    public int index() { return 0; }
}
