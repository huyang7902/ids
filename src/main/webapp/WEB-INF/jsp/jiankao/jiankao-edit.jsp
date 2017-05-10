<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<jsp:include page="/WEB-INF/jsp/common/_header.jsp">
	<jsp:param name="title" value="监考添加" />
</jsp:include>
<body>
	<article class="page-container">
		<form action="${basePath}/jiankao/jiankao-admin.html?act=update"
			method="post" class="form form-horizontal" id="form-exam-edit">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>监考编号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${exam.id }" placeholder="" id="id" name="id" readonly="true"/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>学院：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${college.collegeName }" placeholder="" id="college" name="collegeName" readonly="true"/>
					<input type="text" class="input-text" value="${college.collegeId }" placeholder="" id="college" name="collegeId" hidden="true"/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>专业：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${profession.professionName }" placeholder="" id="profession" name="professionName" readonly="true"/>
					<input id="proId" name="proId" hidden="true" value="${profession.professionId }"/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>年级：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${exam.grade }" placeholder="" id="grade" name="grade" readonly="true"/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>班级：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${class1.className }" placeholder="" id="className" name="className" readonly="true"/>
					<input type="text" class="input-text" value="${class1.classId }" placeholder="" id="classId" name="classId" hidden="true"/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>课程：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${exam.name }" placeholder="" id="name" name="name" readonly="true"/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>任课教师：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${teacher.name }" 
					  placeholder="" id="teacherName" name="teacherName"  readonly="true">
					 <input type="text" class="input-text" value="${teacher.uid }" placeholder="" id="teacherId" name="teacherId" hidden="true"/>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>课程号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${exam.courseId }" 
					 datatype="n" nullmsg="课程号不能为空" placeholder="" id="courseId" name="courseId"  readonly="true">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">
				<span class="c-red">*</span>课序号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${exam.lessonNumber }"
					datatype="n" nullmsg="课序号不能为空"  placeholder="" id="lessonNumber" name="lessonNumber" readonly="true">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"> <span
					class="c-red">*</span>考试教室：
				</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" value="${exam.classRoom }" datatype="*"
						nullmsg="考试教室不能为空" placeholder="" id="classRoom"
						name="classRoom" >
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>监考人员：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<span class="select-box radius"  style="max-width: 600px;"> 
						<select class="select" name="peopleNum" id="peopleNum" datatype="n" nullmsg="请择监考人数">
							<option value="${exam.peopleNum }">${exam.peopleNum }人</option>
							<option value="">请择监考人数</option>
							<option value="1">1人</option>
							<option value="2">2人</option>
							<option value="3">3人</option>
							<option value="4">4人</option>
						</select>
					</span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>选择教师：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<c:forEach items="${peopleList }" var="people" >
						<input type="text" class="input-text" value="${people }" 
						 placeholder="" id="peopleName[]"
						name="peopleName" >
					</c:forEach>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>考试时间：</label>
				<div class="formControls col-xs-8 col-sm-9" style="max-width: 600px;">
					<input type="text" datatype="*" nullmsg="请择监考考试时间" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${exam.startTime }" />"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d+1}'})"
						id="startTime" name="startTime" class="input-text Wdate" style="width: 150px;">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red">*</span>监考教师选择截止时间：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" datatype="*" nullmsg="请择监考教师选择截止时间" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${exam.deadTime }" />"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-{%d+1}'})"
						id="deadTime" name="deadTime" class="input-text Wdate" style="width: 150px;">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3"><span
					class="c-red"></span>监考人员要求：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<textarea name=remark cols="" rows="" class="textarea"  placeholder="说点什么..." onKeyUp="$.Huitextarealength(this,100)">${exam.remark }</textarea>
					<p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>
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

			
       
			
			//$.post("${basePath}/jiankao/jiankao-admin.html?act=add", $("#form-exam-add").serialize());
			
			/* $("#form-exam-add").submit(function(){
			       $.ajax({
			    	   type : 'POST',
			           url:"${basePath}/jiankao/jiankao-admin.html?act=add",
			           data:{
			        	   collegeId: $("#collegeId").val()
			           },
			           dataType:"json",
			           error:function(data){
			               alert(data);
			           },
			           success:function(data){
			               if(data.status == 200) {
			            	   layer.alert(json.data);
			               	}
			           },
			       }); 
			   });
 */
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
		
		$("#form-exam-edit").Validform();
		$("#form-exam-edit").ajaxForm(function(data) {
			var json = $.parseJSON(data);
			if (json.status == 200) {
				layer.alert(json.msg);

			} else {
				layer.alert(json.msg);
			}
		});
		

	</script>
	<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>