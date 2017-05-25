package com.huyang.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.huyang.common.utils.IdsResult;
import com.huyang.dao.mapper.UserMapper;
import com.huyang.dao.po.Exam;
import com.huyang.dao.po.User;
import com.huyang.service.impl.ExamAdminServiceImpl;
import com.mysql.fabric.xmlrpc.base.Data;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class MessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

	@Autowired
	private UserMapper userMapper;
	
	public static IdsResult sendMessage(Exam exam, User user) {
		String host = "http://sms.market.alicloudapi.com";
		String path = "/singleSendSms";
		String method = "GET";
		String appcode = "af9a96549dea405e877e93a96a86e3ff";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
		// 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		// 组装短信通知参数
		Map<String, String> param = new HashMap<>();
		param.put("name", user.getName());
		param.put("examId", exam.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		param.put("date", sdf.format(exam.getStartTime()));
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		param.put("time", sdf1.format(exam.getStartTime()));
		param.put("examName", exam.getName());
		param.put("date", sdf.format(exam.getStartTime()));
		String paramString = JSON.toJSONString(param);
		querys.put("ParamString", paramString );
		querys.put("RecNum", user.getTel());
		querys.put("SignName", "监考系统");
		querys.put("TemplateCode", "SMS_66880223");

		try {
			/**
			 * 重要提示如下: HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/
			 * src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/
			 * pom.xml
			 */
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			String params = EntityUtils.toString(response.getEntity());
			//HttpParams params = response.getParams();
			String result = params.toString();
			Map<String, Boolean> resultMap = JSON.parseObject(result, Map.class);
			if (!resultMap.get("success")) {
				logger.warn("用户名为：" + user.getName() + "发送短信失败！");
				return IdsResult.build(400, "发送短信失败！");
			}
			return IdsResult.build(200, "发送短信成功！");
			//System.out.println(response.toString());
			// 获取response的body
			// System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("用户名为：" + user.getName() + "发送短信失败！");
			return IdsResult.build(400, "发送短信失败！");
		}
	}
	
	/*public static IdsResult sendMessage(Exam exam, User user) {
		TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23864235",
				"6562ffe70c95552068aaa587ec621d7f");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType("normal");
		req.setSmsFreeSignName("监考系统");
		req.setSmsParamString(
				"{name:'张三',examId:'56045710302202',date:'2017/05/01',time:'17:55',examName:'操作系统',date:'2017/05/01'}");
		req.setRecNum("18109076230");
		req.setSmsTemplateCode("SMS_68085171");
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		// 组装短信通知参数
		Map<String, String> param = new HashMap<>();
		param.put("name", user.getName());
		param.put("examId", exam.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		param.put("date", sdf.format(exam.getStartTime()));
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		param.put("time", sdf1.format(exam.getStartTime()));
		param.put("examName", exam.getName());
		param.put("date", sdf.format(exam.getStartTime()));
		String paramString = JSON.toJSONString(param);
		rsp.setParams(param);
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			logger.warn("姓名："+user.getName()+"; 电话：" + user.getTel()+"; 短信发送异常");
		}
		
		System.out.println(rsp.getBody());
		return IdsResult.build(200, "发送短信成功！");
	}*/
	
	/*public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		System.out.println(simpleDateFormat.format(new Date()));
	}*/
}
