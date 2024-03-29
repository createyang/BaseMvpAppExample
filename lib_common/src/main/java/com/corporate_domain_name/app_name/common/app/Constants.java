package com.corporate_domain_name.app_name.common.app;

import com.corporate_domain_name.app_name.common.BuildConfig;
import com.corporate_domain_name.app_name.common.util.rxTool.RxFileTool;

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
        String APP_ID = "123456";
        String APP_KEY = "123456";
    }

}
