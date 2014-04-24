package com.zijida.ridergroup.ui.registfregment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.zijida.ridergroup.ui.R;
import com.zijida.ridergroup.ui.util.messageDialog;
import com.zijida.ridergroup.ui.util.statusMatrix;
import com.zijida.ridergroup.ui.util.userToken;

/**
 * Created by Administrator on 14-3-28.
 *
 */
public abstract class registBase extends Fragment
{
    public int index() { return -1; };

    public interface onFragmentListener
    {
        public void onCommit(Bundle bundle);
        public void onBack(Bundle bundle);
        public void onFlip();
        public userToken getUserToken();
    }

    protected onFragmentListener m_listener;
    protected View rootView;

    public void onCallActivityDone(int requestCode, int resultCode, Intent data) {}
    protected void set_editText(int id, String text)
    {
        if(rootView==null) return;
        if(text == null || text.isEmpty()) return;

        EditText et = (EditText)rootView.findViewById(id);
        if(et != null)
        {
            et.setText(text);
            et.invalidate();
        }
    }

    protected String get_editText(int id)
    {
        if(rootView==null) return null;
        EditText et = (EditText)rootView.findViewById(id);
        if(et != null)
        {
            return et.getText().toString();
        }
        return null;
    }

    protected void registClickListener(int id,View.OnClickListener clickListener)
    {
        if(rootView==null) return;

        View view = rootView.findViewById(id);
        if(view!=null)
        {
            view.setOnClickListener(clickListener);

            if(view.getId() == R.id.button_back ||
                    view.getId() == R.id.button_flip )
            {
                statusMatrix.setButtonStateChangeListener(view, true);
            }
            else
            {
                statusMatrix.setButtonStateChangeListener(view, false);
            }
        }
    }

    protected void quest_flip()
    {
        /**监听对话框里面的button点击事件*/
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which)
                {
                    case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                        if(m_listener!=null) m_listener.onFlip();
                        dialog.dismiss();
                        break;

                    case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                        dialog.dismiss();
                        break;

                    default:
                        break;
                }
            }
        };

        // 创建警示对话框
        messageDialog flipwarn_dialog = new messageDialog(getActivity());
        // 设置对话框标题
        flipwarn_dialog.setTitle(getString(R.string.app_name));
        // 设置对话框消息
        flipwarn_dialog.setMessage(getString(R.string.warning_flipping));
        // 添加选择按钮并注册监听
        flipwarn_dialog.setButton(AlertDialog.BUTTON_POSITIVE,"确定",listener);
        flipwarn_dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", listener);
        // 显示对话框
        flipwarn_dialog.show();
    }
}
