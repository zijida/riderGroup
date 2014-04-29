package com.zijida.ridergroup.ui.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zijida.ridergroup.ui.R;

/**
 * Created by Administrator on 14-4-8.
 */
public class messageDialog extends Dialog
{
    private TextView title;
    private TextView message;
    private Button positive;
    private Button negative;

    DialogInterface.OnClickListener positiveClickListener, negativeClickListener;


    public messageDialog(Context context)
    {
        super(context,R.style.Dialog);
        create(context);
    }

    public messageDialog(Context context, int theme)
    {
        super(context, theme);
        create(context);
    }

    public void create(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.customdialog,null);
        this.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        title = (TextView)findViewById(R.id.dlg_title);
        message = (TextView)findViewById(R.id.dlg_message);
        positive = (Button)findViewById(R.id.dlg_positiveButton);
        negative = (Button)findViewById(R.id.dlg_negativeButton);

        statusMatrix.setButtonStateChangeListener(positive,statusMatrix.MATRIX_COLOR);
        statusMatrix.setButtonStateChangeListener(negative,statusMatrix.MATRIX_COLOR);

        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positiveClickListener.onClick(messageDialog.this,DialogInterface.BUTTON_POSITIVE);
            }
        });

        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                negativeClickListener.onClick(messageDialog.this,DialogInterface.BUTTON_NEGATIVE);
            }
        });
    }

    public void setTitle(String title_string) { title.setText(title_string); }
    public void setMessage(String message_string) { message.setText(message_string);}
    public void setButton(int whichButton, java.lang.CharSequence text, DialogInterface.OnClickListener listener)
    {
        switch (whichButton)
        {
            case DialogInterface.BUTTON_NEGATIVE:
                negative.setText(text);
                negativeClickListener = listener;
            break;

            case DialogInterface.BUTTON_POSITIVE:
                positive.setText(text);
                positiveClickListener = listener;
            break;
        }
    }
}
