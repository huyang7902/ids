package com.huyang.web;

import com.huyang.web.view.JSONView;

public interface Constants {

    String JSON_ROOT="JSON_ROOT";

    JSONView JSON_VIEW=new JSONView();

    /**
     * com.fx.rh.to.connect.RoleTO#level 角色层级最低的级别
     */
    int LOWEST_ROLE_LEVEL = 2;

    /**
     * 用户session缓存key
     */
    String IDS_USER_TOKEN = "IDS_USER_TOKEN";
    
    /**
     * 管理员菜单缓存key
     */
    String IDS_ADMIN_MENU = "IDS_ADMIN_MENU";
    /**
     * 普通教师菜单缓存key
     */
    String IDS_USER_MENU = "IDS_USER_MENU";
    
    /**
     * redis中缓存的时间
     */
    int expire = 60 * 15;



    /**
     * 打包客户端上传绝对路径
     */
    String PACK_CLIENT_UPLOAD_ABSOLUTE_PATH = "/usr/local/nfs/yueyou/tool/rh/";

    /**
     * 打包客户端下载域名前缀
     */
    String PACK_CLIENT_DOWNLOAD_URL_PREFIX = "http://tool.yueeyou.com/rh/";

    /**
     * 保存的打包客户端前缀
     */
    String PACK_CLIENT_UPLOAD_NAME_PREFIX = "yueyou-pack-client-";

    String KEY_STORE_URL_PREFIX = "http://keystore.d.cn/keystore/"; // keystore文件链接前缀  之前是 http://img.d.cn/cp_admin/keystore/

    String KEY_STORE_ABSOLUTE_PATH = "/usr/local/nfs_security/yueyou/rh/cp/keystore/"; // keystore文件存储的绝对路径
    
    String DOWNLOAD_CONTRACT="channelcontract.html?act=down&fileName=";
    
    String CONTRACT_UPLOAD_FILE_PATH="/usr/local/nfs_security/yueyou/rh/channel/contract/";

    String SECRET_KEY=".d.cn.2017";

    int HTTP_OK_STATUS = 200;

    String UPLOAD_FILE = "/common/uploadFile.html";

    String DELETE_FILE = "/common/deleteFile.html";

    /**
     * 游戏公告图片上传路径
     */
    String IMG_UPLOAD_FILE_PATH="/usr/local/nfs/yueyou/img/rh/app/notice/";

    /**
     * 游戏公告下载图片的URL前缀
     */
    String IMG_DOWNLODA_URL_PREFIX="http://img.yueeyou.com/rh/app/notice/";



    String DOWNLOAD_KEY_STORE = "http://pack.yueeyou.com/sys/downloadKeyStore.html?fileName="; // 下载签名文件

    String DOWNLOAD_RH_SDK = "http://pack.yueeyou.com/sys/downloadRhSdk.html?fileName="; // 下载SDK文件

    String RH_SDK_FILE_PATH = "/usr/local/nfs_security/yueyou/rh/channel/channel_res/"; //融合SDK保存路径

    String KEYSTORE_FILR_PATH = "/usr/local/nfs_security/yueyou/rh/cp/keystore/"; //keystore文件保存路径

    String APPICON_FILE_PATH = "/usr/local/nfs/yueyou/img/rh/app/icon/";//游戏icon保存路径

    String DOWNLOAD_OEM_KEY_STORE = "oemsdk/Cp.html?act=down&fileName="; // 下载签名文件

    
    /**
     * 清除缓存key
     */
    String CLEAN_CACHE_PUBLICK_KEY = "_clean_sdk_cache_666_CodE";
    String CLEAN_CACHE_SECURITY_KEY = "ASj%osoueq0918*@Kns";
    String CHARSET_CODE="UTF-8";
    
    String RH_SDK_URL = "http://rhsdk.yueeyou.com";
    
    //返回参数
    String MSG_CODE="msg_code";
    String MSG_DESC="msg_desc";
    

}
