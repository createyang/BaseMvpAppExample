package com.corporate_domain_name.app_name.common.widget.dialog.progressing.animation.interpolator;

import android.view.animation.Interpolator;

/**
 * @author vondear
 */
public class Ease {
    public static Interpolator inOut() {
        return PathInterpolatorCompat.create(0.42f, 0f, 0.58f, 1f);
    }
}
