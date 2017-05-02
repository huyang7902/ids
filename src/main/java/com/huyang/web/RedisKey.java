package com.huyang.web;

/**
 * 类/接口注释
 *
 * @author haiqing.shi@downjoy.com
 * @DATE 2017-02-14 11:10
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
