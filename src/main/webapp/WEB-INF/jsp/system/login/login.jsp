<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link href="${resourcesPath}/static/h-ui/css/H-ui.min.css"
	rel="stylesheet" type="text/css" />
<link href="${resourcesPath}/static/h-ui.admin/css/H-ui.login.css"
	rel="stylesheet" type="text/css" />
<link href="${resourcesPath}/static/h-ui.admin/css/style.css"
	rel="stylesheet" type="text/css" />
<link href="${resourcesPath}/lib/Hui-iconfont/1.0.8/iconfont.css"
	rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>教师监考人员分配系统</title>
</head>
<body>
	<input type="hidden" id="TenantId" name="TenantId" value="" />
	<div class="header"></div>
	<div class="loginWraper">
		<div id="loginform" class="loginBox">
			<form class="form form-horizontal"
				action="${basePath}/login/doLogin.html" method="post">
				<c:if test="${!empty msg }">
					<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe6dd;</i></label>
					<div class="formControls col-xs-8">
							<strong style="color: red;">${msg }</strong>
					</div>
					</div>
				</c:if>
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
					<div class="formControls col-xs-8">
						<input id="" name="uid" type="text" placeholder="账号" value="${param.uid }"
							class="input-text size-L" datatype="n" nullmsg="账号不能为空" errormsg="账号必须是数字">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
					<div class="formControls col-xs-8">
						<input id="" name="password" type="password" placeholder="密码" value="${param.password }" 
						  class="input-text size-L">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe62c;</i></label>
					<div class="mt-20 skin-minimal">
					  <div class="radio-box formControls">
					    <input type="radio" id="radio-1" name="accessRoleLevele" value="1" checked>
					    <label for="radio-1">普通教师</label>
					  </div>
					  <div class="radio-box">
					    <input type="radio" id="radio-2" name="accessRoleLevele" value="3">
					    <label for="radio-2">管理员</label>
 					 </div>
 				</div>
			</div>
				<!--  <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">
          <img src=""> <a id="kanbuq" href="javascript:;">看不清，换一张</a> </div>
      </div> 
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <label for="online">
            <input type="checkbox" name="online" id="online" value="">
            使我保持登录状态</label>
        </div>
      </div>-->
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<input name="" type="submit" class="btn btn-success radius size-L"
							value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"> <input
							name="" type="reset" class="btn btn-default radius size-L"
							value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="footer">Copyright IDS by HuYang</div>
<script type="text/javascript" src="${resourcesPath}/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${resourcesPath}/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${resourcesPath}/js/Validform_v5.3.2.js"></script>
<script type="text/javascript" src="${resourcesPath}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<!--此乃百度统计代码，请自行删除-->
	<script>
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
		
		$("#loginform").Validform();
	</script>
	<!--/此乃百度统计代码，请自行删除-->
</body>
</html>