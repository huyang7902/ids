<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<jsp:include page="/WEB-INF/jsp/common/_header.jsp">
	<jsp:param name="title" value="教师监考人员分配系统" />
</jsp:include>
<body>
	<div id="userInfo" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form action="${basePath}/login/saveInfo.html" method="post"
			class="userInfo" data-after_success="refresh">

			<div class="modal-dialog">
				<div class="modal-content radius">
					<div class="modal-header">
						<h3 class="modal-title">个人信息</h3>
						<a class="close" data-dismiss="modal" aria-hidden="true"
							href="javascript:void();">×</a>
					</div>
					<div class="modal-body">
						<div class="pl-10 pt-10 cl">
							<span class="pr-20 f-l">姓名：</span> 
							<span class="size-L f-l">${user.name} </span>
						</div>
						<div class="pl-10 pt-10 cl">
							<span class="pr-20 f-l">工号：</span> 
							<span class="size-L f-l"> ${user.uid} </span>
						</div>
						<div class="pl-10 pt-10 cl">
							<span class="pr-20 pt-10 f-l">手机：</span> <span class="size-L f-l">
								<input name="tel" value="${user.tel}" type="text" datatype="m"
								nullmsg="请输入手机号码！" placeholder=""
								class="input-text radius size-L">

							</span>
						</div>
						<div class="pl-10 pt-10 cl">
							<span class="pr-20 pt-10 f-l">邮箱：</span> <span class="size-L f-l">
								<input name="email" value="${user.email}" type="text"
								datatype="e" nullmsg="请输入邮箱！" placeholder=""
								class="input-text radius size-L">

							</span>
						</div>
						<p class="c-error pt-20 errorTip"></p>
					</div>
					<div style="text-align: center;" class="modal-footer">
						<button type="submit" class="btn btn-primary submitForm">确定</button>
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<div id="changePassword" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<form action="${basePath}/login/resetPass.html" id="resetPass" method="post">
			<div class="modal-dialog">
				<div class="modal-content radius">
					<div class="modal-header">
						<h3 class="modal-title">修改密码</h3>
					</div>
					<div class="modal-body">
						<div class="pl-10 pt-10 cl">
							<span class="size-L f-l"> <input name="originPass"
								value="" type="password" placeholder="原密码" datatype="*"
								nullmsg="请输入原密码！" class="input-text radius size-L">

							</span>
						</div>
						<div class="pl-10 pt-10 cl">
							<span class="size-L f-l"> <input name="newPass" value=""
								type="password" placeholder="新密码" datatype="*"
								nullmsg="请输入新密码！" errormsg="密码必须包含大小写英文字母及数字，且6-16位字符！"
								class="input-text radius size-L">

							</span>
						</div>
						<div class="pl-10 pt-10 cl">
							<span class="size-L f-l"> <input name="newPassConfirm"
								value="" type="password" placeholder="再次输入新密码" datatype="*"
								recheck="newPass" nullmsg="请再输入一次新密码！" errormsg="您两次输入的新密码不一致！"
								class="input-text radius size-L">
							</span>
						</div>
						<p class="c-error pt-20 errorTip"></p>
					</div>
					<div style="text-align: center;" class="modal-footer">
						<button type="submit" class="btn btn-primary submitForm">确定</button>
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
					</div>
				</div>
			</div>
		</form>
	</div>
	<header class="navbar-wrapper">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl">
				<a class="logo navbar-logo f-l mr-10 hidden-xs"
					href="/aboutHui.shtml">监考教师人员分配系统</a> <a
					class="logo navbar-logo-m f-l mr-10 visible-xs"
					href="/aboutHui.shtml">H-ui</a> <span
					class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0</span> <a
					aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs"
					href="javascript:;">&#xe667;</a>
				<nav id="Hui-userbar"
					class="nav navbar-nav navbar-userbar hidden-xs">
					<ul class="cl">
						<li>${user.des }</li>
						<li class="dropDown dropDown_hover"><a href="#"
							class="dropDown_A">${user.name }<i class="Hui-iconfont">&#xe6d5;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a onclick="openUserInfo()" href="#">个人信息</a></li>
								<li><a onclick="openChangePassword()" href="#">修改密码</a></li>
								<li><a href="${basePath}/login/logout.html">退出</a></li>
							</ul></li>
						<li id="Hui-msg"><a href="#" title="消息"><span
								class="badge badge-danger">1</span><i class="Hui-iconfont"
								style="font-size: 18px">&#xe68a;</i></a></li>
						<li id="Hui-skin" class="dropDown right dropDown_hover"><a
							href="javascript:;" class="dropDown_A" title="换肤"><i
								class="Hui-iconfont" style="font-size: 18px">&#xe62a;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" data-val="default"
									title="默认（黑色）">默认（黑色）</a></li>
								<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
								<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
								<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
								<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
								<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<aside class="Hui-aside">
		<div class="menu_dropdown bk_2">

			<c:forEach items="${menus}" var="root">
				<c:forEach items="${root.children }" var="parent">
					<dl id="menu-${parent.id}">
						<dt>
							<i class="Hui-iconfont">&#xe6c0;</i> ${parent.name}<i
								class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
						</dt>
						<dd>
							<c:forEach items="${parent.children}" var="child">
								<ul>
									<li><a id="menu-a-${parent.id}"
										data-href="${basePath}${child.url}&college=${parent.name}&profession=${child.name}"
										data-title="${child.name }" href="javascript:void(0)">${child.name }</a>
									</li>
								</ul>

							</c:forEach>

						</dd>
					</dl>

				</c:forEach>
			</c:forEach>

		</div>
	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="我的桌面" data-href="welcome">我的桌面</span>
						<em></em>
					</li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display: none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0" src="welcome"></iframe>
			</div>
		</div>
	</section>

	<div class="contextMenu" id="Huiadminmenu">
		<ul>
			<li id="closethis">关闭当前</li>
			<li id="closeall">关闭全部</li>
		</ul>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<jsp:include page="/WEB-INF/jsp/common/_footer.jsp" />
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${resourcesPath}/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript">
		$(function() {
			/*$("#min_title_list li").contextMenu('Huiadminmenu', {
				bindings: {
					'closethis': function(t) {
						console.log(t);
						if(t.find("i")){
							t.find("i").trigger("click");
						}		
					},
					'closeall': function(t) {
						alert('Trigger was '+t.id+'\nAction was Email');
					},
				}
			});*/
		});
		
		/*修改用户信息*/
		var name = "${user.name}";
		var uid = "${user.uid}";
		var tel = "${user.tel}";
		var email = "${user.email}";
		function openUserInfo() {
			$("input[name='name']").val(name);
			$("input[name='uid']").val(uid);
			$("input[name='tel']").val(tel);
			$("input[name='email']").val(email);
			$(".errorTip").text("");
			$('#userInfo').modal("show");
		}
		$(".userInfo").Validform();
		$(".userInfo").ajaxForm(function(data) {
			var json = $.parseJSON(data);
			if (json.status == 200) {
				layer.alert("修改成功！请刷新页面再次查看");
				openUserInfo();

			} else {
				layer.alert(json.msg);
			}
		});

		/*修改密码*/
		function openChangePassword() {
			$("#originPass").val("");
			$("#newPass").val("");
			$("#newPassConfirm").val("");
			$(".errorTip").text("");
			$('#changePassword').modal("show");
		}
		$("#resetPass").Validform();
		$("#resetPass").ajaxForm(function(data) {
			var json = $.parseJSON(data);
			if (json.status == 200) {
				layer.alert("修改密码成功！");
			} else {
				layer.alert(json.msg);
			}
		});
		
		
		function urlDec(id) {
			var url = $("#" + id).attr("data-href");
			alert(url);
			var ss = encodeURI(url);

			alert(ss);
			$("#" + id).attr("data-href", encodeURI(url));
		}

		
		
		/*个人信息*/
		function myselfinfo() {
			layer.open({
				type : 1,
				area : [ '300px', '200px' ],
				fix : false, //不固定
				maxmin : true,
				shade : 0.4,
				title : '查看信息',
				content : '<div>管理员信息</div>'
			});
		}

		/*资讯-添加*/
		function article_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*图片-添加*/
		function picture_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*产品-添加*/
		function product_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*用户-添加*/
		function member_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
	</script>

	<!--此乃百度统计代码，请自行删除-->
	<script>
		var _hmt = _hmt || [];
		(function() {
			var hm = document.createElement("script");
			hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
			var s = document.getElementsByTagName("script")[0];
			s.parentNode.insertBefore(hm, s);
		})();
	</script>
	<!--/此乃百度统计代码，请自行删除-->
</body>
</html>