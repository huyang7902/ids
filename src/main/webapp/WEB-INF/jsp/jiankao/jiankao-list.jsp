<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<jsp:include page="/WEB-INF/jsp/common/_header.jsp">
    <jsp:param name="title" value="监考列表"/>
</jsp:include>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
		${college.collegeName } <span class="c-gray en">&gt;</span> ${profession.professionName } <a
			class="btn btn-success radius r"
			style="line-height: 1.6em; margin-top: 3px"
			href="javascript:location.replace(location.href);" title="刷新"><i
			class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="page-container">
		<div class="text-c">
			<input id="professionId" name="professionId" hidden="true" value="${profession.professionId }"/>
			选择年级： <select class="input-text" style="width: 130px;" name="grade" id="grade" onchange="getClass()">
				<option value="">---选择年级---</option>
				<!-- 循环遍历班级 -->
				<c:forEach items="${gradeList }" var="grade">
					<option value="${grade.grade }">${grade.grade }级</option>
				</c:forEach>
			</select> 选择班级： <select class="input-text"
				style="max-width: 150px;" name="classId" id="className">
				<option value="">---选择班级---</option>
			</select> 日期范围： <input type="text"
				onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')}' })"
				id="startTime" class="input-text Wdate" style="width: 120px;">
			- <input type="text"
				onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}'})"
				id="endTime" class="input-text Wdate" style="width: 120px;">
			<input type="text" class="input-text" style="width: 250px"
				placeholder="输入课程名称" id="course" name="">
			<button type="submit" class="btn btn-success" onclick="search()"
				id="" name="">
				<i class="Hui-iconfont">&#xe665;</i> 搜索
			</button>
		</div>

		<c:if test="${loginUser.accessRoleLevele == 3 }" >
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l"><a href="javascript:;" onclick="datadel()"
					class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
						批量删除</a> <a href="javascript:;"
					onclick="jiankao_add('添加用户','jiankao-list.html?act=add&collegeId=${college.collegeId}&professionId=${profession.professionId }','','500')"
					class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
						添加监考</a></span>
				</span>
			</div>
		</c:if>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th width="110">ID</th>
						<th width="100">班级名</th>
						<th width="150">课程名</th>
						<th width="70">监考时间</th>
						<th width="70">选择截止时间</th>
						<th width="90">监考地点</th>
						<th width="100">监考人员要求</th>
						<th width="70">是否已启用</th>
						<th width="150">选择教师</th>
						<c:if test="${loginUser.accessRoleLevele == 3 }">
						<th width="75">操作</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${examList.list }" var="exam">
						<tr class="text-c">
							<td><input type="checkbox" value="1" name=""></td>
							<td>${exam.id }</td>
							<td>${exam.className }</td>
							<td>${exam.name }</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
									value="${exam.startTime }" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
									value="${exam.deadTime }" /></td>
							<td>${exam.classRoom }</td>
							<td>${exam.remark }</td>
							<td class="td-status"><c:choose>
									<c:when test="${exam.status == 1}">
										<span class="label label-success radius">已启用</span>
									</c:when>
									<c:when test="${exam.status == 0}">
										<span class="label label-success radius">已结束</span>
									</c:when>
								</c:choose></td>
							<td class="td-manage">
							<c:forEach items="${exam.peopleList }"
									var="people">
									<input class="btn btn-success radius size-MINI" type="button"
										onclick="choose(1)" value="${people }"> &nbsp; &nbsp; 
							</c:forEach>
							</td>
							<c:if test="${loginUser.accessRoleLevele == 3}">
								<td class="td-manage"><a title="编辑" href="javascript:;"
									onclick="jiankao_edit('编辑','jiankao-add_admin','4','','510')"
									class="ml-5" style="text-decoration: none"><i
										class="Hui-iconfont">&#xe6df;</i></a> <a title="删除"
									href="javascript:;" onclick="jiankao_del(this,'1')" class="ml-5"
									style="text-decoration: none"><i class="Hui-iconfont">&#xe6e2;</i></a>
								</td>
						</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="dataTables_wrapper cl pt-20">
			<span class="f-l pt-10">共计${examList.total}条记录</span>
			<form action="${basePath }/jiankao/jiankao-list.html?act=list"
				class="paginateForm" method="post">
				<input type="hidden" name="pageNum" value="${examList.pageNum}" />
				<input type="hidden" name="college" value="${param.college}" />
				<input type="hidden" name="profession" value="${param.profession}" /> 
				<input type="hidden" name="grade" value="${param.grade}" />
				<input type="hidden" name="classId" value="${param.classId}" />
				<input type="hidden" name="startTime" value="${param.startTime}" />
				<input type="hidden" name="endTime" value="${param.endTime}" />

				<div class="dataTables_paginate paginate"
					data-page_num="${examList.pageNum}"
					data-page_size="${examList.pageSize}" data-total="${examList.total}">
				</div>
			</form>
		</div>
	</div>
	<!--_footer 作为公共模版分离出去-->
<jsp:include page="/WEB-INF/jsp/common/_footer.jsp"/>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="${resourcesPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<%-- 	<script type="text/javascript" src="${resourcesPath}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${resourcesPath}/lib/laypage/1.2/laypage.js"></script> --%>
	<script type="text/javascript">
	

	/*-----------------------------------------------------------------*/
		$(function() {
			$('.table-sort').dataTable({
				"aaSorting" : [ [ 1, "desc" ] ],//默认第几个排序
				"bStateSave" : true,//状态保存
				"aoColumnDefs" : [
				//{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
				{
					"orderable" : false,
					"aTargets" : [ 0, 8 ]
				} // 制定列不参与排序
				]
			});

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
			select.append("<option value="+">---选择班级---</option>");
			$.ajax({
				type : 'POST',
				url : '${basePath}/jiankao/jiankao-list.html?act=getClass',
				dataType : 'json',
				data : class1,
				success : function(data) {
					$.each(data, function(i, n){
						select.append("<option value=" + n.classId + ">" + n.className + "</option>");
					});
					//select.append(html);
					
							},
				error : function(data) {
					console.log(data.msg);
				},
			});
		}
	
		/*用户-添加*/
		function jiankao_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
		/*用户-查看*/
		function jiankao_show(title, url, id, w, h) {
			layer_show(title, url, w, h);
		}
		/*用户-停用*/
		function jiankao_stop(obj, id) {
			layer
					.confirm(
							'确认要停用吗？',
							function(index) {
								$
										.ajax({
											type : 'POST',
											url : '',
											dataType : 'json',
											success : function(data) {
												$(obj)
														.parents("tr")
														.find(".td-manage")
														.prepend(
																'<a style="text-decoration:none" onClick="jiankao_start(this,id)" href="javascript:;" title="启用">_$tag___________________&#xe6e1;_$ta_$ta');
												$(obj)
														.parents("tr")
														.find(".td-status")
														.html(
																'_$tag____________________________________已停用_$tag__');
												$(obj).remove();
												layer.msg('已停用!', {
													icon : 5,
													time : 1000
												});
											},
											error : function(data) {
												console.log(data.msg);
											},
										});
							});
		}

		/*用户-启用*/
		function jiankao_start(obj, id) {
			layer
					.confirm(
							'确认要启用吗？',
							function(index) {
								$
										.ajax({
											type : 'POST',
											url : '',
											dataType : 'json',
											success : function(data) {
												$(obj)
														.parents("tr")
														.find(".td-manage")
														.prepend(
																'<a style="text-decoration:none" onClick="jiankao_stop(this,id)" href="javascript:;" title="停用">_$tag___________________&#xe631;_$ta_$ta');
												$(obj)
														.parents("tr")
														.find(".td-status")
														.html(
																'_$tag____________________________________已启用_$tag__');
												$(obj).remove();
												layer.msg('已启用!', {
													icon : 6,
													time : 1000
												});
											},
											error : function(data) {
												console.log(data.msg);
											},
										});
							});
		}
		/*用户-编辑*/
		function jiankao_edit(title, url, id, w, h) {
			layer_show(title, url, w, h);
		}
		/*密码-修改*/
		function change_password(title, url, id, w, h) {
			layer_show(title, url, w, h);
		}
		/*用户-删除*/
		function jiankao_del(obj, id) {
			layer.confirm('确认要删除吗？', function(index) {
				$.ajax({
					type : 'POST',
					url : '',
					dataType : 'json',
					success : function(data) {
						$(obj).parents("tr").remove();
						layer.msg('已删除!', {
							icon : 1,
							time : 1000
						});
					},
					error : function(data) {
						console.log(data.msg);
					},
				});
			});
		}
	</script>
</body>
</html>