package com.zijida.ridergroup.ui.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.zijida.ridergroup.ui.R;

/**
 * Created by Administrator on 14-4-16.
 */
public class customMessage {
    private Context context;

    public customMessage(Context context)
    {
        this.context = context;
    }

    public void showMessage(String msg)
    {
        /**监听对话框里面的button点击事件*/
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which)
                {
                    case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
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
        messageDialog flipwarn_dialog = new messageDialog(context);
        // 设置对话框标题
        flipwarn_dialog.setTitle(context.getString(R.string.app_name));
        // 设置对话框消息
        flipwarn_dialog.setMessage(msg);
        // 添加选择按钮并注册监听
        flipwarn_dialog.setButton(AlertDialog.BUTTON_POSITIVE,"确定",listener);
        flipwarn_dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", listener);
        // 显示对话框
        flipwarn_dialog.show();
    }
}
