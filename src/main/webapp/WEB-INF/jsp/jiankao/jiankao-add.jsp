<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<jsp:include page="/WEB-INF/jsp/common/_header.jsp">
	<jsp:param name="title" value="监考添加" />
</jsp:include>
<body>
	<article class="page-container">
		<form action="${basePath}/jiankao/jiankao-admin.html?act=add"
			method="post" class="form form-horizontal" id="form-exam-add">
			<input id="professionId" name="professionId" hidden="true" value="${profession.professionId }"/>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>学院：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${college.collegeName }" placeholder="" id="college" name="collegeName"/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>专业：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${profession.professionName }" placeholder="" id="profession" name="professionName"/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>年级：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box radius"  style="max-width: 600px;"> 
						<select class="select" name="grade" id="grade" onchange="getClass()">
							<option value="">请选择年级</option>
							<c:forEach items="${gradeList }" var="grade">
								<option value="${grade.grade }">${grade.grade }级</option>
							</c:forEach>
						</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>班级：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box radius"  style="max-width: 600px;"> 
						<select class="select" name="className" id="className" onchange="getCourses()">
							<option value="">请选择班级</option>
						</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>课程：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box" style="max-width: 600px;"> 
						<select class="select" name="coursesId" id="coursesId" onchange="getCourseDetail()">
								<option value="" selected>请选择课程</option>
						</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>课程号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="" id="courseId" name="courseId">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>课序号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder="" id="lessonNumber" name="lessonNumber">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>监考人员：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="" placeholder=""
						id="username" name="username"> <input type="text"
						class="input-text" value="" placeholder="" id="username"
						name="username">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red"></span>监考人员要求：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<textarea name="beizhu" cols="" rows="" class="textarea"
						placeholder="说点什么...最少输入10个字符"
						onKeyUp="$.Huitextarealength(this,100)"></textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/100
					</p>
				</div>
			</div>

			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit"
						value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<jsp:include page="/WEB-INF/jsp/common/_footer.jsp" />
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${resourcesPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript"
		src="${resourcesPath}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
	<script type="text/javascript"
		src="${resourcesPath}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
	<script type="text/javascript"
		src="${resourcesPath}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.skin-minimal input').iCheck({
				checkboxClass : 'icheckbox-blue',
				radioClass : 'iradio-blue',
				increaseArea : '20%'
			});

			$("#form-exam-add").ajaxForm(function(data) {
				var json = $.parseJSON(data);
				if (json.status == 200) {
					layer.alert(json.data);

				} else {
					layer.alert(json.data);
				}
			});
			
			/* $("#form-exam-add").submit(function(){
			       $.ajax({
			           url:"${basePath}/jiankao/jiankao-admin.html?act=add",
			           data:$('#form-exam-add').serialize(),
			           dataType:"json",
			           error:function(data){
			               alert(data);
			           },
			           success:function(data){
			               if(data.status == 200) {
			               	$("#page-container").close;
			               	layer.open({
			               		type: 1,
			               		area: ['300px','200px'],
			               		fix: false, //不固定
			               		maxmin: true,
			               		shade:0.4,
			               		title: '查看信息',
			               		content: '<div>添加成功！</div>'
			               	});
			               } else {
			               	
			               }
			           }
			       });
			   });    */

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
		
		function getClass() {
			var g = $("#grade").val();
			var p = $("#professionId").val();
			var class1 = {
				grade:g,
				professionId:p
			};
			var select = $("#className").html("");
			var html;
			select.append("<option value="+">请选择班级</option>");
			$.ajax({
				type : 'POST',
				url : '${basePath}/jiankao/jiankao-list.html?act=getClass',
				dataType : 'json',
				data : class1,
				success : function(data) {
					$.each(data, function(i, n){
						select.append("<option value=" + n.classId + ">" + n.className + "</option>");
					});
				},
				error : function(data) {
					console.log(data.msg);
				},
			});
		}
		
		function getCourses() {
			var g = $("#grade").val();
			var p = $("#professionId").val();
			var c = $("#className option:selected").val();
			var class1 = {
				grade:g,
				professionId:p,
				classId:c
			};
			var select = $("#coursesId").html("");
			var html;
			select.append("<option value="+">请选择课程</option>");
			$.ajax({
				type : 'POST',
				url : '${basePath}/jiankao/jiankao-list.html?act=getCourses',
				dataType : 'json',
				data : class1,
				success : function(data) {
					$.each(data, function(i, n){
						select.append("<option value=" + n.courseId + ">" + n.courseName + "</option>");
					});
				},
				error : function(data) {
					console.log(data.msg);
				},
			});
		}
		
		function getCourseDetail() {
			var g = $("#grade").val();
			var p = $("#professionId").val();
			var c = $("#className option:selected").val();
			var coursesId = $("#coursesId option:selected").val();
			var params = {
				grade:g,
				professionId:p,
				classId:c,
				courseId:coursesId
			};
			var courseId = $("#collegeId").html("");
			courseId.append("<option value="+">请选择课程号</option>");
			var lessonNumber = $("#lessonNumber").html("");
			lessonNumber.append("<option value="+">请选择课序号</option>");
			$.ajax({
				type : 'POST',
				url : '${basePath}/jiankao/jiankao-list.html?act=getCourseDetail',
				dataType : 'json',
				data : params,
				success : function(data) {
					$("#courseId").val(data.courseId);
					$("#lessonNumber").val(data.lessonNumber);
				},
				error : function(data) {
					console.log(data.msg);
				},
			});
		}
	</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>