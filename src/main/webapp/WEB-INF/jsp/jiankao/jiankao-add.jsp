<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<jsp:include page="/WEB-INF/jsp/common/_header.jsp">
    <jsp:param name="title" value="监考添加"/>
</jsp:include>
<body>
<article class="page-container">
	<form action="${basePath}/jiankao/jiankao-admin.html?act=add" method="post" class="form form-horizontal" id="form-exam-add">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>班级：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="username" name="username">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>课程名：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="username" name="username">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>监考时间：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d+1}'})" id="datemin" class="input-text Wdate" style="width: 150px;">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>监考地点：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="username" name="username">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>监考人员：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="username" name="username">
				<input type="text" class="input-text" value="" placeholder="" id="username" name="username">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>监考人员要求：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea name="beizhu" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符" onKeyUp="$.Huitextarealength(this,100)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
				<input class="btn btn-primary radius" type="submit"  value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</article>

	<!--_footer 作为公共模版分离出去-->
<jsp:include page="/WEB-INF/jsp/common/_footer.jsp"/>
	<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${resourcesPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${resourcesPath}/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="${resourcesPath}/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="${resourcesPath}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	

		$("#form-exam-add").ajaxForm( function (data) {
			var json = $.parseJSON(data);
			if(json.status == 200){
				layer.alert('添加成功');
			}else{
				layer.alert('添加失败');
			}
			});
	
	/* $("#form-member-add").validate({
		rules:{
			username:{
				required:true,
				minlength:2,
				maxlength:16
			},
			sex:{
				required:true,
			},
			mobile:{
				required:true,
				isMobile:true,
			},
			email:{
				required:true,
				email:true,
			},
			uploadfile:{
				required:true,
			},
			
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			//$(form).ajaxSubmit();
			var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	}); */
});
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>