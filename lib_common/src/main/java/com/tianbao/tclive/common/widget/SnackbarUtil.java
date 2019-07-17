package com.tianbao.tclive.common.widget;

import android.os.Build;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.tianbao.tclive.common.R;


/**
 * Created by codeest on 16/9/3.
 */

public class SnackbarUtil {


//    public static void config(Context context, Snackbar snack) {
//        addMargins(snack);
//        setRoundBordersBg(context, snack);
//        ViewCompat.setElevation(snack.getView(), 6f);
//    }
//
//    private static void addMargins(Snackbar snack) {
//        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) snack.getView().getLayoutParams();
//        params.setMargins(12, 12, 12, 12);
//        snack.getView().setLayoutParams(params);
//    }
//
//    private static void setRoundBordersBg(Context context, Snackbar snackbar) {
//        snackbar.getView().setBackground(context.getResources().getDrawable(R.drawable.bg_snackbar));
//    }


    public static void show(View view, String msg) {
        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
        //状态栏的flag影响了snackbar的高度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            View viewc = snackbar.getView();
//            int height = viewc.getHeight() - StatusBarUtils.getStatusBarHeight(view.getContext());
//            ViewGroup.LayoutParams layoutParams = viewc.getLayoutParams();
//            layoutParams.height = height;
//            viewc.setLayoutParams(layoutParams);
            TextView tvSnackbarText = (TextView) viewc.findViewById(android.support.design.R.id.snackbar_text);
            tvSnackbarText.setBackgroundColor(view.getResources().getColor(R.color.colorAccent));
            viewc.getBackground().setAlpha(0);
        }
        snackbar.show();
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

//    /**
//     * 向Snackbar中添加view
//     * @param snackbar
//     * @param layoutId
//     * @param index 新加布局在Snackbar中的位置
//     */
//    public static void SnackbarAddView( Snackbar snackbar,int layoutId,int index) {
//
//        View snackbarview = snackbar.getView();//获取snackbar的View(其实就是SnackbarLayout)
//
//        Snackbar.SnackbarLayout snackbarLayout=(Snackbar.SnackbarLayout)snackbarview;//将获取的View转换成SnackbarLayout
//        snackbarLayout.layout(100,100,100,100);
//
//        View add_view = LayoutInflater.from(snackbarview.getContext()).inflate(layoutId,null);//加载布局文件新建View
//
//        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);//设置新建布局参数
//
//        p.gravity= Gravity.CENTER_VERTICAL;//设置新建布局在Snackbar内垂直居中显示
//
//        snackbarLayout.addView(add_view,index,p);//将新建布局添加进snackbarLayout相应位置
//
//    }

}
