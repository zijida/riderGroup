package com.zijida.ridergroup.ui.util;

/**
 * Created by SHENJUN on 14-4-23.
 * Create in RiderGroup
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {

    // 质量压缩方法
    public static Bitmap quality_compress(Bitmap image,int target_size) {

        if(image==null) return null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;

        do
        {
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG,options,baos);
            options -= 10;
        }
        while (baos.toByteArray().length/1024 > target_size);

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        return  BitmapFactory.decodeStream(isBm, null, null);
    }

    public static Bitmap quality_compress(String imageFile,int target_size)
    {
        if(imageFile==null || imageFile.isEmpty()) return null;

        // Get the dimensions of the bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile);
        if(bitmap == null) return null;

        return quality_compress(bitmap, target_size);
    }

    // 图片按比例大小压缩方法（根据Bitmap图片压缩）
    public static Bitmap scale_compress(Bitmap image,int target_w,int target_h)
    {
        if(image==null) return null;

        // 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
        int options = 100;
        Bitmap bitmap = null;
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
            while(baos.toByteArray().length / 1024 > 1024)
            {
                baos.reset();
                options -= 20;
                image.compress(Bitmap.CompressFormat.JPEG,options,baos);
            }
            if(image!=null)
            {
                if(!image.isRecycled()) image.recycle();
                image = null;
            }

            // Get the dimensions of the bitmap
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            {
                ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
                BitmapFactory.decodeStream(isBm,null, bmOptions);
                isBm.close();
            }
            int photoW = bmOptions.outWidth;
            int photoH = bmOptions.outHeight;

            // Determine how much to scale down the image
            int scaleFactor = Math.min(photoW/target_w, photoH/target_h);

            // Decode the image file into a Bitmap sized to fill the View
            bmOptions.inJustDecodeBounds = false;
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inPurgeable = true;
            bmOptions.inInputShareable = true;

            {
                ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
                bitmap = BitmapFactory.decodeStream(isBm,null, bmOptions);
                isBm.close();
                baos.close();
            }
        }
        catch (IOException e)
        {
        }
        return bitmap;
    }

    // 图片按比例大小压缩方法（根据Bitmap图片压缩）
    public static Bitmap scale_compress(String imageFile,int target_w,int target_h)
    {
        if(imageFile==null || imageFile.isEmpty()) return null;

        // Get the dimensions of the bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile);
        if(bitmap == null) return null;

        return scale_compress(bitmap,target_w,target_h);
    }

    public static void saveTo(String savePath,Bitmap bitmap)
    {
        if(savePath==null || bitmap==null) return;
        if(bitmap.getWidth()==0 || bitmap.getHeight()==0) return;

        File file = new File(savePath);
        try
        {
            FileOutputStream out=new FileOutputStream(file);
            if(bitmap.compress(Bitmap.CompressFormat.PNG, 100, out))
            {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void saveTo(String savePath,String srcPath,int w,int h)
    {
        if(savePath==null || srcPath==null) return;
        if(savePath.length()==0 || srcPath.length()==0) return;

        // Get the dimensions of the bitmap
        Bitmap bitmap = scale_compress(srcPath,w,h);
        if(bitmap != null)
        {
            saveTo(savePath,bitmap);
        }
    }
}