package com.huyang.web;

import com.huyang.web.view.JSONView;

public interface Constants {

	String JSON_ROOT = "JSON_ROOT";

	JSONView JSON_VIEW = new JSONView();

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


	String CHARSET_CODE = "UTF-8";

	// 返回参数
	String MSG_CODE = "msg_code";
	String MSG_DESC = "msg_desc";

	/**
	 * exam监考表常量
	 */
	/**0:表示已经结束*/
	Byte EXAM_STATUS_0 = 0;
	 /**1表示正在进行*/
	Byte EXAM_STATUS_1 = 1;
	/**2表示取消*/
	Byte EXAM_STATUS_2 = 2;
	/**3表示删除*/
	Byte EXAM_STATUS_3 = 3;

}
