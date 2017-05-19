package com.huyang.web;

/**
 *  redis
 *
 * @Project ids
 * @package com.huyang.web
 * @author HuYang 790247179@qq.com
 * @date 2017年5月19日 下午8:19:26
 * @version V1.0 Copyright (c) 2017
 */
public interface RedisKey {

    static String PREFIX = "fxrh-sdkmanage:";

    /**
     * 登录用户缓存
     */
    interface USER_SESSION{
        String keyformat = PREFIX + "user_session:%s";
        int expire = 60 * 15;
    }

    /**
     * 登陆验证频次限制缓存
     */
    interface LOGIN_FREQUENCY_LIMIT {
        String keyformat = PREFIX + "frequency_limit_ip_%s";
        int expire = 60;
        /**
         * 限制次数
         */
        int times = 10;
    }

    /**
     * 用户菜单缓存
     */
    interface USER_MENUS{
        String keyformat = PREFIX + "user_menu:%s";
        int expire = 61 * 15;
    }

}
