<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="lib/layer/2.4/skin/layer.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>公告首页</title>
<style type="text/css">
.gonggao {
	clear: both;
	border-bottom: #dad0bc dashed 1px; padding : 4px;
	height: 25px;
	padding: 4px;
}
</style>
</head>
<body>
	<div class="page-container">
		<p class="f-20 text-success">
			欢迎使用监考教师人员分配系统 <span class="f-14">管理员界面</span>
		</p>


		<div class="page-container">
			<table class="table table-border table-hover table-bg ">
				<tbody id="gonggao">
					<!-- <tr>
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
					</tr> -->
				</tbody>
			</table>
			<div id="biuuu_city" style="position: static; float: right; margin-top: 20px"  ></div>
		</div>
		


	</div>

	<footer class="footer mt-20" style="position: fixed;bottom: 0px">
		<div class="container">
			<p>
				感谢jQuery、layer、laypage、Validform、UEditor、My97DatePicker、iconfont、Datatables、WebUploaded、icheck、highcharts、bootstrap-Switch<br>
				Copyright &copy;2015-2017 H-ui.admin v3.0 All Rights Reserved.<br>
				本后台系统由<a href="http://www.h-ui.net/" target="_blank"
					title="H-ui前端框架">H-ui前端框架</a>提供前端技术支持
			</p>
		</div>
	</footer>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<script type="text/javascript"
		src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
	
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