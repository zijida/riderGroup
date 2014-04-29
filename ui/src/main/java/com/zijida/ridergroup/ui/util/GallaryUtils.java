package com.zijida.ridergroup.ui.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;

/**
 * Created by SHENJUN on 14-4-22.
 * Create in RiderGroup
 * 为图像应用添加的辅助类
 */
public class GallaryUtils {
    private Context context;
    public final static int INVOKE_CAMERA = 891;
    public final static int INVOKE_PICSTORE = 892;
    public final static int INVOKE_CROP = 893;

    public GallaryUtils(Context context)
    {
        this.context = context;
    }

    public void call()
    {
        CharSequence[] items = {"打开相册选取", "使用相机拍照"};
        new AlertDialog.Builder((Activity)context)
                .setTitle("请您选择图片来源")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if( which == 0 )
                        {
                            pickupPicture();
                        }
                        else
                        {
                            takeAPhoto();
                        }
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {

                    }
                })
                .create().show();
    }

    public void takeAPhoto()
    {
        // 调用在系统注册的拍照Activity
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,tempPhotoUri());
        ((Activity)context).startActivityForResult(intent, INVOKE_CAMERA);
    }

    public void cropPhoto(Uri uri,int outX,int outY)
    {
        if(uri == null){  return;  }

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1); //设置按长和宽的比例裁剪
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outX); //设置输出的大小
        intent.putExtra("outputY", outY);
        intent.putExtra("scale", true); //设置是否允许拉伸
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());//设置输出格式
        ((Activity)context).startActivityForResult(intent, INVOKE_CROP);
    }

    public void pickupPicture()
    {
        // 调用系统图库
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        ((Activity)context).startActivityForResult(Intent.createChooser(intent, null), INVOKE_PICSTORE);
    }

    public Uri tempPhotoUri()
    {
        File file = new File(CacheUtils.getCacheFolder(),"cachephoto.tmp");
        return Uri.fromFile(file);
    }

    //// 指定目录下的图片，显示到指定VIEW
    public void showPhoto(int type, View view)
    {
        if(view==null)  return;
        Bitmap bmp = getPicture(type,view);
        if(bmp!=null)
        {
            ((ImageButton)view).setImageBitmap(bmp);
            ((ImageButton)view).invalidate();
        }
    }

    //// 指定目录下的图片，显示到指定VIEW
    public void showBackgroundPhoto(int type, View view)
    {
        if(view==null)  return;

        Bitmap bmp = getPicture(type,view);
        if(bmp!=null)
        {
            ((ImageButton)view).setBackground(new BitmapDrawable(context.getResources(), bmp));
        }
        else
        {
           // ((ImageButton)view).setBackgroundResource(R.drawable.take_photo);
        }
    }

    private Bitmap getPicture(int type,View view) {
        // Get the dimensions of the View
        int targetW = view.getBackground().getIntrinsicWidth();
        int targetH = view.getBackground().getIntrinsicHeight();
        String pic_path = CacheUtils.get_cache_route(type);
        if(pic_path != null && !pic_path.isEmpty())
        {
            Bitmap bmp = BitmapFactory.decodeFile(pic_path);
            if(bmp==null) return null;

            if(bmp.getWidth()>targetW && bmp.getHeight()>targetH)
            {
                return ImageUtils.scale_compress(bmp,targetW,targetH);
            }
            else return bmp;
        }
        else return null;
    }
}
