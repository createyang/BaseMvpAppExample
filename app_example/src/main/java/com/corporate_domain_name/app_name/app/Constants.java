package com.corporate_domain_name.app_name.app;

import com.corporate_domain_name.app_name.common.BuildConfig;
import com.corporate_domain_name.app_name.common.app.TBApplicationProxy;
import com.corporate_domain_name.app_name.common.util.rxTool.RxFileTool;

import java.io.File;

/**
 * @author: Caoy
 * @created on: 2019/7/4 9:47
 * @description:
 */
public interface Constants {

    public static int SDKAPPID = 110;

    /****** 华为离线推送参数start ******/
    // 在腾讯云控制台上传第三方推送证书后分配的证书ID
    public static final long HW_PUSH_BUZID = 5220;
    // 华为开发者联盟给应用分配的应用APPID
    public static final String HW_PUSH_APPID = "100642285"; // 见清单文件
    /****** 华为离线推送参数end ******/

    /****** 小米离线推送参数start ******/
    // 在腾讯云控制台上传第三方推送证书后分配的证书ID
    public static final long XM_PUSH_BUZID = 5218;
    // 小米开放平台分配的应用APPID及APPKEY
    public static final String XM_PUSH_APPID = "2882303761517953663";
    public static final String XM_PUSH_APPKEY = "5421795368663";
    /****** 小米离线推送参数end ******/

    /****** 魅族离线推送参数start ******/
    // 在腾讯云控制台上传第三方推送证书后分配的证书ID
    public static final long MZ_PUSH_BUZID = 5223;
    // 魅族开放平台分配的应用APPID及APPKEY
    public static final String MZ_PUSH_APPID = "118863";
    public static final String MZ_PUSH_APPKEY = "d9c7628144e541c1a6446983531467c8";
    /****** 魅族离线推送参数end ******/

    /****** vivo离线推送参数start ******/
    public static final long VIVO_PUSH_BUZID = 5224;
    // vivo开放平台分配的应用APPID及APPKEY
    public static final String VIVO_PUSH_APPID = "11178"; // 见清单文件
    public static final String VIVO_PUSH_APPKEY = "a90685ff-ebad-4df3-a265-3d4bb8e3a389"; // 见清单文件
    /****** vivo离线推送参数end ******/




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
