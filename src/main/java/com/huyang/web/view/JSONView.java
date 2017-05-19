package com.huyang.web.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.alibaba.fastjson.JSON;
import com.huyang.web.Constants;


public class JSONView extends AbstractView implements Constants {

    private static final String jsonContentType="application/json; charset=UTF-8";

    /**
     * 该View对应的输出类型
     */
    public String getContentType() {
        return jsonContentType;
    }

    /**
     * 输出JSON数据
     * @param response
     * @param message JSON字符串
     */
    public static void writeJSONData(HttpServletResponse response, String message) {
        response.setContentType(jsonContentType);
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=null;
        try {
            out=response.getWriter();
            out.println(message);
            out.flush();
        } catch(IOException e) {
        } finally {
            if(out != null) {
                out.close();
                out=null;
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object res=model.get(JSON_ROOT);
        String jsonStr= JSON.toJSONString(res,true);
        writeJSONData(response, jsonStr);
        model=null;
        Enumeration names=request.getAttributeNames();
        if(null != names) {
            while(names.hasMoreElements()) {
                String name=(String)names.nextElement();
                request.setAttribute(name, null);
            }
        }
    }

}
