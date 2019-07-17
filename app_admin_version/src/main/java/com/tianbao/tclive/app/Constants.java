package com.tianbao.tclive.app;

import com.tianbao.tclive.common.BuildConfig;
import com.tianbao.tclive.common.app.TBApplicationProxy;
import com.tianbao.tclive.common.util.rxTool.RxFileTool;

import java.io.File;

/**
 * @author: Caoy
 * @created on: 2019/7/4 9:47
 * @description:
 */
public interface Constants {

    /**
     * ================= PATH ====================
     **/

    interface Path {
        /**
         * 公司名 文件
         */
        String NAME_COMPANY = BuildConfig.FilePath + File.separator + "TBLiveData" + File.separator + "TBLiveUdi";

        /**
         * SD卡缓存路径 总文件夹
         */
        String PATH_SDCARD = RxFileTool.getRootPath().getAbsolutePath() + File.separator + NAME_COMPANY;
        /**
         * 应用总本地缓存路径
         */
        public static String PATH_DATA = TBApplicationProxy.getInstanceApplication().getCacheDir().getAbsolutePath() + File.separator + NAME_COMPANY;

        /**
         * 网络缓存路径 (不分用户)
         */
        public static String PATH_NET_CACHE = PATH_DATA + File.separator + "NetCache";

    }

    interface Key {
        String FILE_PROVIDER_AUTHORITY = TBApplicationProxy.getInstanceApplication().getPackageName() + ".fileprovider";
    }
    /**
     * ================= Intent ====================
     **/
    interface Intent {
        String IT_LOGIN_BEAN = "it_login_bean";
    }

}
