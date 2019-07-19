package com.tianbao.live.lib_comlive.demo;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Caoy
 * @created on: 2019/7/16 16:11
 * @description:
 */
public  class TCUtils {

    /**
     * 录制权限检测：存储权限、摄像头权限、录音权限
     * @param activity
     * @return
     */
    public static boolean checkRecordPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(activity,
                        permissions.toArray(new String[0]),
                        100);
                return false;
            }
        }
        return true;
    }
}
