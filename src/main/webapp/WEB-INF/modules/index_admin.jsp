<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>监考教师人员分配系统</title>
</head>
<body>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> <a class="logo navbar-logo f-l mr-10 hidden-xs" href="">监考教师人员分配系统</a> <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="#">H-ui</a> 
			<span class="logo navbar-slogan f-l mr-10 hidden-xs">管理员界面</span> 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul class="cl">
					<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onclick="article_add('添加资讯','article-add.html')"><i class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
							<li><a href="javascript:;" onclick="picture_add('添加资讯','picture-add.html')"><i class="Hui-iconfont">&#xe613;</i> 图片</a></li>
							<li><a href="javascript:;" onclick="product_add('添加资讯','product-add.html')"><i class="Hui-iconfont">&#xe620;</i> 产品</a></li>
							<li><a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
					</ul>
				</li>
			</ul>
		</nav>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<li>超级管理员</li>
					<li class="dropDown dropDown_hover">
						<a href="#" class="dropDown_A">admin <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" onClick="myselfinfo()">个人信息</a></li>
							<li><a href="#">切换账户</a></li>
							<li><a href="#">退出</a></li>
					</ul>
				</li>
					<li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
					<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
							<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
							<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
							<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
							<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
							<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>
</header>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
	<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 计科院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=计科院&major=计算机科学" data-title="计算机" href="javascript:void(0)" >计算机</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=计科院&major=软件工程" data-title="软件工程" href="javascript:void(0)">软件工程</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=计科院&major=网络工程" data-title="网络工程" href="javascript:void(0)">网络工程</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=计科院&major=物联网工程" data-title="物联网工程" href="javascript:void(0)">物联网工程</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=计科院&major=信息管理" data-title="信息管理" href="javascript:void(0)">信息管理</a></li>
			</ul>
		</dd>
	</dl>
	
	<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 材料院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=材料院&major=材料成型" data-title="材料成型" href="javascript:void(0)" >材料成型</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=材料院&major=材料成型" data-title="材料科学" href="javascript:void(0)">材料科学</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=材料院&major=高分子" data-title="高分子" href="javascript:void(0)">高分子</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=材料院&major=新能源" data-title="新能源" href="javascript:void(0)">新能源</a></li>
			</ul>
			<ul>
					<li><a data-href="admin-listcollege=材料院&major=新能源材料" data-title="新能源材料" href="javascript:void(0)">新能源材料</a></li>
			</ul>
		</dd>
	</dl>
	
	<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 地科院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=地科院&major=城市地下" data-title="城市地下" href="javascript:void(0)" >城市地下</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=地科院&major=地理信息" data-title="地理信息" href="javascript:void(0)">地理信息</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=地科院&major=地质学" data-title="地质学" href="javascript:void(0)">地质学</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=地科院&major=勘查与技术" data-title="勘查与技术" href="javascript:void(0)">勘查与技术</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=地科院&major=资源勘查" data-title="资源勘查" href="javascript:void(0)">资源勘查</a></li>
			</ul>
		</dd>
	</dl>
	
		<dl id="menu-article">
			<dt><i class="Hui-iconfont">&#xe616;</i> 电信院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=电信院&major=电器工程" data-title="电器工程" href="javascript:void(0)" >电器工程</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=电信院&major=电子信息" data-title="电子信息" href="javascript:void(0)">电子信息</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=电信院&major=通信工程" data-title="通信工程" href="javascript:void(0)">通信工程</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=电信院&major=自动化" data-title="自动化" href="javascript:void(0)">自动化</a></li>
			</ul>
		</dd>
	</dl>
	
	<dl id="menu-article">
		<dt><i class="Hui-iconfont">&#xe616;</i> 法学院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=法学院&major=法学" data-title="法学" href="javascript:void(0)" >法学</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=法学院&major=电子信息" data-title="社会工作" href="javascript:void(0)">社会工作</a></li>
			</ul>
		</dd>
	</dl>
	
		<dl id="menu-article">
		<dt><i class="Hui-iconfont">&#xe616;</i> 化工院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=化工院&major=安全工程" data-title="安全工程" href="javascript:void(0)" >安全工程</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=化工院&major=化学" data-title="化学" href="javascript:void(0)">化学</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=化工院&major=化学工程" data-title="化学工程" href="javascript:void(0)">化学工程</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=化工院&major=环境工程" data-title="环境工程" href="javascript:void(0)">环境工程</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=化工院&major=应用化学" data-title="应用化学" href="javascript:void(0)">应用化学</a></li>
			</ul>
		</dd>
	</dl>
	
	<dl id="menu-article">
		<dt><i class="Hui-iconfont">&#xe616;</i> 机电院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=机电院&major=测控技术" data-title="测控技术" href="javascript:void(0)" >测控技术</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=机电院&major=工业设计" data-title="工业设计" href="javascript:void(0)">工业设计</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=机电院&major=过程装备" data-title="过程装备" href="javascript:void(0)">过程装备</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=机电院&major=机械电子" data-title="机械电子" href="javascript:void(0)">机械电子</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=机电院&major=机械工程" data-title="机械工程" href="javascript:void(0)">机械工程</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=机电院&major=机械设计" data-title="机械设计" href="javascript:void(0)">机械设计</a></li>
			</ul>
		</dd>
	</dl>
	
	<dl id="menu-article">
		<dt><i class="Hui-iconfont">&#xe616;</i> 经管院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=经管院&major=电子商务" data-title="电子商务" href="javascript:void(0)" >电子商务</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=经管院&major=工商管理" data-title="工商管理" href="javascript:void(0)">工商管理</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=经管院&major=公共事业" data-title="公共事业" href="javascript:void(0)">公共事业</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=经管院&major=国际贸易" data-title="国际贸易" href="javascript:void(0)">国际贸易</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=经管院&major=会计学" data-title="会计学" href="javascript:void(0)">会计学</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=经管院&major=经济学" data-title="经济学" href="javascript:void(0)">经济学</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=经管院&major=市场营销" data-title="市场营销" href="javascript:void(0)">市场营销</a></li>
			</ul>
		</dd>
	</dl>
		
	<dl id="menu-article">
		<dt><i class="Hui-iconfont">&#xe616;</i> 理学院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=理学院&major=电信科技" data-title="电信科技" href="javascript:void(0)" >电信科技</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=理学院&major=信息计算" data-title="信息计算" href="javascript:void(0)">信息计算</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=理学院&major=应用数学" data-title="应用数学" href="javascript:void(0)">应用数学</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=理学院&major=应用物理" data-title="应用物理" href="javascript:void(0)">应用物理</a></li>
			</ul>
		</dd>
	</dl>
		
	<dl id="menu-article">
		<dt><i class="Hui-iconfont">&#xe616;</i> 石工院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=石工院&major=海洋油气" data-title="海洋油气" href="javascript:void(0)" >海洋油气</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=石工院&major=石工" data-title="石工" href="javascript:void(0)">石工</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=石工院&major=石油工程" data-title="石油工程" href="javascript:void(0)">石油工程</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=石工院&major=油储卓越" data-title="油储卓越" href="javascript:void(0)">油储卓越</a></li>
			</ul>
			<ul>
					<li><a data-href="jiankao-list_admin?college=石工院&major=油气储运" data-title="油气储运" href="javascript:void(0)">油气储运</a></li>
			</ul>
		</dd>
	</dl>
		
	<dl id="menu-article">
		<dt><i class="Hui-iconfont">&#xe616;</i> 体育院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=体育院&major=体育教育" data-title="海洋油气" href="javascript:void(0)" >体育教育</a></li>
				</ul>
		</dd>
	</dl>
		
	<dl id="menu-article">
		<dt><i class="Hui-iconfont">&#xe616;</i> 土建院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=土建院&major=测绘工程" data-title="测绘工程" href="javascript:void(0)" >测绘工程</a></li>
				</ul>
				<ul>
					<li><a data-href="jiankao-list_admin?college=土建院&major=城乡规划" data-title="城乡规划" href="javascript:void(0)" >城乡规划</a></li>
				</ul>
				<ul>
					<li><a data-href="jiankao-list_admin?college=土建院&major=工程管理" data-title="工程管理" href="javascript:void(0)" >工程管理</a></li>
				</ul>
				<ul>
					<li><a data-href="jiankao-list_admin?college=土建院&major=建筑能源" data-title="建筑能源" href="javascript:void(0)" >建筑能源</a></li>
				</ul>
				<ul>
					<li><a data-href="jiankao-list_admin?college=土建院&major=土木工程" data-title="土木工程" href="javascript:void(0)" >土木工程</a></li>
				</ul>
		</dd>
	</dl>
		
	<dl id="menu-article">
		<dt><i class="Hui-iconfont">&#xe616;</i> 外语院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=外语院&major=俄语" data-title="俄语" href="javascript:void(0)" >俄语</a></li>
				</ul>
				<ul>
					<li><a data-href="jiankao-list_admin?college=外语院&major=英语" data-title="英语" href="javascript:void(0)" >英语</a></li>
				</ul>
		</dd>
	</dl>
		
	<dl id="menu-article">
		<dt><i class="Hui-iconfont">&#xe616;</i> 艺术院<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="jiankao-list_admin?college=艺术院&major=编导" data-title="编导" href="javascript:void(0)" >编导</a></li>
				</ul>
				<ul>
					<li><a data-href="jiankao-list_admin?college=艺术院&major=表演" data-title="表演" href="javascript:void(0)" >表演</a></li>
				</ul>
				<ul>
					<li><a data-href="jiankao-list_admin?college=艺术院&major=播音" data-title="播音" href="javascript:void(0)" >播音</a></li>
				</ul>
		</dd>
	</dl>
		
</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active">
					<span title="公告首页" data-href="welcome">公告首页</span>
					<em></em></li>
		</ul>
	</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="welcome"></iframe>
	</div>
</div>
</section>

<div class="contextMenu" id="Huiadminmenu">
	<ul>
		<li id="closethis">关闭当前 </li>
		<li id="closeall">关闭全部 </li>
</ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
$(function(){
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
/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['300px','200px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '查看信息',
		content: '<div>管理员信息</div>'
	});
}

/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
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