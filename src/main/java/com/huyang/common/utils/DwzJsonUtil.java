package com.huyang.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.huyang.web.Constants;

public class DwzJsonUtil {

    private static final String DATA_PROMPT = "data";

    private static final String SUCCESS = "操作成功";

    /**
     * 服务端执行错误状态码
     */
    private static final String FAIL_CODE = "300";
    
    private static final String FAIL_SERVICECODE="500";
    /**
     * 服务端执行成功状态码
     */
    private static final String SUCCESS_CODE = "200";
    
    private static final String MESSAGE_PROMPT = "message";
    
    private static final String STATUS_CODE_PROMPT = "statusCode";
    
    private static final String URL_PROMPT = "href";
    
    public static Map<String,Object> getDataResult(Object data){
        HashMap<String, Object> result=new HashMap<String, Object>();
        result.put(STATUS_CODE_PROMPT, SUCCESS_CODE);
        result.put(MESSAGE_PROMPT, SUCCESS);
        result.put(DATA_PROMPT, data);
        return result;
    }
    
    public static ModelAndView getDataResultView(Object data){
        HashMap<String, Object> result=new HashMap<String, Object>();
        result.put(STATUS_CODE_PROMPT, SUCCESS_CODE);
        result.put(MESSAGE_PROMPT, SUCCESS);
        result.put(DATA_PROMPT, data);
        return new ModelAndView(Constants.JSON_VIEW, Constants.JSON_ROOT, result);
    }
    
    public static ModelAndView getSuccessModelAndView(){
        Map<String, String> result=new HashMap<String, String>();
        result.put(STATUS_CODE_PROMPT, SUCCESS_CODE);
        result.put(MESSAGE_PROMPT, SUCCESS);
        return new ModelAndView(Constants.JSON_VIEW, Constants.JSON_ROOT, result);
    }
    
    /**
     * 返回失败消息
     * @param msg
     * @return
     */
    public static ModelAndView getFailModelAndView(String msg) {
        Map<String, String> result=new HashMap<String, String>();
        result.put(STATUS_CODE_PROMPT, FAIL_CODE);
        result.put(MESSAGE_PROMPT, msg);
        return new ModelAndView(Constants.JSON_VIEW, Constants.JSON_ROOT, result);
    }
    
    public static String getFailString(String msg) {
        Map<String, String> result=new HashMap<String, String>();
        result.put(STATUS_CODE_PROMPT, FAIL_CODE);
        result.put(MESSAGE_PROMPT, msg);
        return JSON.toJSONString(result);
    }
    
    /**
     * 返回失败消息和重定向url
     * @param msg
     * @param url
     * @return
     */
    public static ModelAndView getFailModelAndView(String msg,String url) {
        Map<String, String> result=new HashMap<String, String>();
        result.put(STATUS_CODE_PROMPT, FAIL_CODE);
        result.put(MESSAGE_PROMPT, msg);
        result.put(URL_PROMPT, url);
        return new ModelAndView(Constants.JSON_VIEW, Constants.JSON_ROOT, result);
    }

    public static Map<String, String> getOkStatusMsg(String msg) {
        Map<String, String> res=new HashMap<String, String>();
        res.put(STATUS_CODE_PROMPT, SUCCESS_CODE);
        if(null != msg && msg.length() > 0) {
            res.put(MESSAGE_PROMPT, msg);
        }
        return res;
    }

    public static Map<String, String> getLoginOkStatusMsg(String msg) {
        Map<String, String> res=new HashMap<String, String>();
        res.put(STATUS_CODE_PROMPT, SUCCESS_CODE);
        if(null != msg && msg.length() > 0) {
            res.put(MESSAGE_PROMPT, msg);
        }
        res.put("isLogin", "yes");
        return res;
    }

    public static Map<String, String> getRedictStatusMsg(String msg, String url) {
        Map<String, String> res=new HashMap<String, String>();
        res.put(STATUS_CODE_PROMPT, "100");
        if(null != msg && msg.length() > 0) {
            res.put(MESSAGE_PROMPT, msg);
        }
        res.put("url", url);
        return res;
    }

    public static Map<String, String> getErrorStatusMsg(String msg) {
        Map<String, String> res=new HashMap<String, String>();
        res.put(STATUS_CODE_PROMPT, FAIL_CODE);
        res.put(MESSAGE_PROMPT, msg);
        return res;
    }
    
    /**
     * 服务器返回500错误
     * @param msg
     * @return
     */
    public static Map<String,String> getServiceErrorStatusMsg(String msg){
    	 Map<String, String> res=new HashMap<String, String>();
         res.put(STATUS_CODE_PROMPT, FAIL_SERVICECODE);
         res.put(MESSAGE_PROMPT, msg);
         return res;
    }

    public static Map<String, Object> getErrorStatusObject(String msg) {
        Map<String, Object> res=new HashMap<String, Object>();
        res.put("statusCode", "300");
        res.put("message", msg);
        return res;
    }

    public static String getSessionTimeOutMsg() {
        return "{\"statusCode\":\"301\", \"message\":\"会话超时，请重新登录!\"}";
    }

}
