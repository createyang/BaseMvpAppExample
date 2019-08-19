package com.corporate_domain_name.live.lib_comlive;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.corporate_domain_name.live.lib_comlive.demo.TCUtils;

import java.security.MessageDigest;

/**
 * Module:   TCGlideCircleTransform
 *
 * Function: Glide图像裁剪
 *
 */
public class TCGlideCircleTransform extends BitmapTransformation {


    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return TCUtils.createCircleImage(toTransform, 0);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }
}
