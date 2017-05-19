<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<jsp:include page="/WEB-INF/jsp/common/_header.jsp">
	<jsp:param name="title" value="监考添加" />
</jsp:include>
<body>
	<div class="page-container">
		<p class="f-20 text-success">
			欢迎使用监考教师人员分配系统 <span class="f-14"></span>
		</p>


		<!-- <div class="page-container">
			<table class="table table-border table-hover table-bg ">
				<tbody id="gonggao">
					<tr>
						<td class="mt-20"><a href="">计科院：物联网工程 面向对象程序设计期中考试</a></td>
						<td class="mt-5">发布时间：2016-06-01 9:00</td>
					</tr>
					<tr>
						<td class="mt-20"><a href="">计科院：物联网工程 面向对象程序设计期中考试</a></td>
						<td class="mt-5">发布时间：2016-06-01 9:00</td>
					</tr>
					<tr>
						<td class="mt-20"><a href="">计科院：物联网工程 面向对象程序设计期中考试</a></td>
						<td class="mt-5">发布时间：2016-06-01 9:00</td>
					</tr>
					<tr>
						<td class="mt-20"><a href="">计科院：物联网工程 面向对象程序设计期中考试</a></td>
						<td class="mt-5">发布时间：2016-06-01 9:00</td>
					</tr>
				</tbody>
			</table>
			<div id="biuuu_city" style="position: static; float: right; margin-top: 20px"  ></div>
		</div> -->
		


	</div>

	<footer class="footer mt-20" style="position: fixed;bottom: 0px">
		<div class="container">
			<p>
			</p>
		</div>
	</footer>
	<!--_footer 作为公共模版分离出去-->
	<jsp:include page="/WEB-INF/jsp/common/_footer.jsp" />
	<!--/_footer 作为公共模版分离出去-->

	<script type="text/javascript"
		src="${resourcesPath}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="${resourcesPath}/lib/laypage/1.2/laypage.js"></script>
	
	<script type="text/javascript">
	//测试数据
	var data = [
	    ['计科院：物联网工程 面向对象程序设计期中考试','发布时间：2016-06-01 1:00'],
	    ['计科院：物联网工程 面向对象程序设计期中考试','发布时间：2016-06-01 2:00'],
	    ['计科院：物联网工程 面向对象程序设计期中考试','发布时间：2016-06-01 3:00'],
	    ['计科院：物联网工程 面向对象程序设计期中考试','发布时间：2016-06-01 4:00'],
	    ['计科院：物联网工程 面向对象程序设计期中考试','发布时间：2016-06-01 5:00'],
	    ['计科院：物联网工程 面向对象程序设计期中考试','发布时间：2016-06-01 6:00'],
	    ['计科院：物联网工程 面向对象程序设计期中考试','发布时间：2016-06-01 7:00'],
	];

	var nums = 2; //每页出现的数量
	var pages = Math.ceil(data.length/nums); //得到总页数

	var thisDate = function(curr){
	    //此处只是演示，实际场景通常是返回已经当前页已经分组好的数据
	    var str = '', last = curr*nums - 1;
	    last = last >= data.length ? (data.length-1) : last;
	    for(var i = (curr*nums - nums); i <= last; i++){
	        str += '<tr><td class="mt-20"><a href="">'+ data[i][0] +'</a></td><td class="mt-5">'+data[i][1]+'</td></tr>';
	    }
	    return str;
	};

	//调用分页
	laypage({
	    cont: 'biuuu_city',
	    pages: pages,
	    skin: '#808080',
	    jump: function(obj){
	        document.getElementById('gonggao').innerHTML = thisDate(obj.curr);
	        
	    }
	})

</script>
</body>
</html>