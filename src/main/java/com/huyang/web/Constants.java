package com.huyang.web;

import com.huyang.web.view.JSONView;

public interface Constants {

    String JSON_ROOT="JSON_ROOT";

    JSONView JSON_VIEW=new JSONView();


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

    int HTTP_OK_STATUS = 200;


    /**
     * 清除缓存key
     */
    String CLEAN_CACHE_PUBLICK_KEY = "_clean_sdk_cache_666_CodE";
    String CLEAN_CACHE_SECURITY_KEY = "ASj%osoueq0918*@Kns";
    
    String CHARSET_CODE="UTF-8";
    
    
    //返回参数
    String MSG_CODE="msg_code";
    String MSG_DESC="msg_desc";
    

}
