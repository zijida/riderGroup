package com.zijida.ridergroup.ui.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by SHENJUN on 14-4-23.
 * Create in RiderGroup
 * 定义、存取APP缓存目录结构
 * －RiderGroup
 *   -cache
 *    -user
 *    -
 */
public class CacheUtils {
    public static final int PATH_HEAD_IMAGE_CACHE = 1;
    public static final int PATH_BIKE_IMAGE_CACHE = 2;
    public static final int PATH_LIVE_RIDING_CACHE = 3;

    public static final String ROOT_DIR = "RiderGroup";
    public static final String CACHE_DIR = "cache";
    public static final String HEAD_IMAGE_NAME = "head.cache";
    public static final String BIKE_IMAGE_NAME = "bike.cache";
    public static final String LIVE_RIDING_RECORD = "live_riding.cache";

    public static String get_cache_route(int route_id)
    {
        switch (route_id)
        {
            case PATH_BIKE_IMAGE_CACHE:
            {
                return getCacheFolder()+BIKE_IMAGE_NAME;
            }

            case PATH_HEAD_IMAGE_CACHE:
            {
                return getCacheFolder()+HEAD_IMAGE_NAME;
            }
            case PATH_LIVE_RIDING_CACHE:
            {
                return getCacheFolder()+LIVE_RIDING_RECORD;
            }
        }
        return null;
    }

    public static String getCacheFolder()
    {
        String route = Environment.getExternalStorageDirectory().getAbsolutePath();
        route += File.separator;
        route += ROOT_DIR;
        route += File.separator;
        route += CACHE_DIR;
        route += File.separator;
        File f = new File(route);
        if(!f.exists()) { f.mkdirs(); }
        return route;
    }
}
