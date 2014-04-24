package com.zijida.ridergroup.ui.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

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
        ((Activity)context).startActivityForResult(intent,INVOKE_CAMERA);
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
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//设置输出格式
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

    //// 将tmpPhotoUri指向的文件，按参数要求压缩存储到指定路径
    public void savePhoto(int type,int w,int h)
    {
        Toast.makeText(context,"savePhoto from "+tempPhotoUri().getPath(), Toast.LENGTH_LONG).show();

        Bitmap bitmap = ImageUtils.scale_compress(tempPhotoUri().getPath(),w,h);
        if(bitmap != null)
        {
            Toast.makeText(context,"savePhoto to "+CacheUtils.get_cache_route(type), Toast.LENGTH_LONG).show();
            ImageUtils.saveTo(CacheUtils.get_cache_route(type),bitmap);
        }
        else
        {
            Toast.makeText(context," FAILED!!! ImageUtils.scale_compress "+tempPhotoUri().getPath(), Toast.LENGTH_LONG).show();
        }
    }

    //// 将tmpPhotoUri指向的文件，按VIEW尺寸压缩存储到指定路径
    public void savePhoto(int type,View view)
    {
        savePhoto(type,view.getWidth(),view.getHeight());
    }

    public void savePhoto(int type,int viewId)
    {
        View view = ((Activity)context).findViewById(viewId);
        if(view != null)
        {
             savePhoto(type,view);
        }
    }

    //// 指定目录下的图片，显示到指定VIEW
    public void showPhoto(int type, View view)
    {
        if(view==null)
        {
            Toast.makeText(context,"照片不能显示：错误的VIEW ID.", Toast.LENGTH_SHORT).show();
            return;
        }

        Bitmap bmp = getPicture(type,view);
        if(bmp==null)
        {
            Toast.makeText(context,"照片不能显示：没有找到图片.", Toast.LENGTH_SHORT).show();
            return;
        }
        ((ImageButton)view).setImageBitmap(bmp);
        ((ImageButton)view).invalidate();
    }

    private Bitmap getPicture(int type,View view) {
        // Get the dimensions of the View

        int targetW = view.getBackground().getIntrinsicWidth();
        int targetH = view.getBackground().getIntrinsicHeight();

        return ImageUtils.scale_compress(CacheUtils.get_cache_route(type),targetW,targetH);
    }
}
