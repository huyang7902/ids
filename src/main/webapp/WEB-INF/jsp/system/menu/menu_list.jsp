<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<jsp:include page="/WEB-INF/jsp/common/_header.jsp">
    <jsp:param name="title" value="菜单管理"/>
</jsp:include>
<body>
<div class="main">
    <form action="${basePath}/system/menu.html?act=del" class="myAjaxForm" data-after_success="refresh" data-text="确定删除这些菜单吗？">
        <div class="yx-manage-head bg-header cl pl-20 pr-20 pt-10 pb-10">
            <!-&#45;&#45;-->
            <span class="f-l pt-5">菜单管理</span>
            <div class="f-r mr-30">
                <span class="c-error pt-20 errorTip"></span>
                <a class="btn btn-primary radius mr-20 del submitForm" href="javascript:" data-text="删除">删除</a>
                <a class="btn btn-primary radius" href="javascript:" onclick="modal2()">添加</a>
            </div>
        </div>
        <div class="pt-20 mr-20 ml-20 pb-20 box-shadow" style="max-width: 1400px;">
            <c:forEach items="${menus}" var="root">
                <c:forEach items="${root.children}" var="parent">
                    <div class="row cl mt-10">
                        <div class="formControls col-xs-6 col-sm-4 col-xs-offset-1 col-sm-offset-1">
                            <input type="checkbox" class="mt-5 ml-10 pcheck" onclick="al(this)"  name="treecheckbox" data-min="1" data-errmsg="菜单"
                                   value="${parent.id}" datatype="needCheck" nullmsg="请选择菜单删除！">
                            <a class="Hui-iconfont ml-10 back  icon-open">&#xe600;</a>
                            <label class="ml-10 sct" onclick="openMenuEditor(${parent.id});" style="cursor: pointer">${parent.name}</label>
                            <div class="second ml-50 pl-30" style="display: none;">

                                <c:forEach items="${parent.children}" var="child">
                                    <div class="row cl mt-10">
                                        <input type="checkbox" class="mt-5 ml-10 game-admin" onclick="cp(this);" name="treecheckbox" value="${child.id}">
                                        <label class="ml-10" onclick="openMenuEditor(${child.id});" style="cursor: pointer">${child.name}</label>
                                    </div>
                                </c:forEach>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:forEach>
        </div>
    </form>
</div>
<div id="modal2" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <form class="form form-horizontal myAjaxForm" action="${basePath}/system/menu.html?act=save" data-after_success="refresh">
                <div class=" pl-20  clearfix bg-header ">
                    <h4 class="f-l f-14">添加</h4>
                    <a class="f-r mr-20 mt-10 back" data-dismiss="modal" aria-hidden="true"><i class="Hui-iconfont">&#xe6a6;</i></a>
                </div>
                <div class="pt-20 pl-20 pd-20">
                    <div class="row cl mt-10">
                        <label style="width: 100px;" class="form-label col-xs-3 col-sm-3 lh-30">*名称：</label>
                        <div class="formControls col-xs-5 col-sm-6 ml-10">
                            <input type="text" class="input-text radius required" datatype="*" nullmsg="菜单名不能为空！" id="name" name="name"/>
                        </div>
                    </div>
                    <div class="row cl mt-10">
                        <input id="parentId" name="parentId" type="hidden"/>
                        <label style="width: 100px;" class="form-label col-xs-3 col-sm-3 lh-30">*父节点：</label>
                        <div class="formControls col-xs-5 col-sm-9 ml-10">
                            <input type="text" readonly id="parentName" datatype="*" nullmsg="没有选择父节点！"
                                   onclick="openChooseMenu();" class="input-text radius" style="width:60%;">
                            <a href="javascript:void(0)" onclick="openChooseMenu();" class="mr-10"><i class="Hui-iconfont">&#xe709;</i></a>
                        </div>
                    </div>
                    <div class="row cl mt-10">
                        <label style="width: 100px;" class="form-label col-xs-3 col-sm-3 lh-30">*排序：</label>
                        <div class="formControls col-xs-5 col-sm-6 ml-10">
                            <input type="text" class="input-text radius" id="sortOrderNo" datatype="*,n,tinyint" nullmsg="排序不能为空！" errormsg="排序必须是数字！" name="sortOrderNo">
                        </div>
                    </div>
                    <div class="row cl mt-10">
                        <label style="width: 100px;" class="form-label col-xs-3 col-sm-3 lh-30">*可见性：</label>
                        <div class="formControls col-xs-5 col-sm-6 ml-10">
                            <span class="select-box" style="width:100%;">
                                <select id="accessRoleLevel" name="accessRoleLevel" class="select"
                                        datatype="*,n" nullmsg="没有选择可见性" errormsg="可见性必须是数字！" style="height: 20px;">
                                  <option value="0">0</option>
                                  <option value="1">1</option>
                                  <option value="2" >2</option>
                                  <option value="3" >3</option>
                                </select>
                            </span><br>
                            <span style="color: #7e7e80">
                                可见性分为0,1,2三级，逐级降低；<br>
                                0级：普通用户可用；<br>
                                1级：1级及以上角色可用（未使用）；<br>
                                2级：全部角色可用（未使用）<br>
                                3级：仅超级管理员可用<br>
                            </span>
                        </div>
                    </div>
                    <div class="row cl mt-10">
                        <label style="width: 100px;" class="form-label col-xs-3 col-sm-3 lh-30">链接地址：</label>
                        <div class="formControls col-xs-5 col-sm-6 ml-10">
                            <input type="text" class="input-text radius" id="url" name="url">
                        </div>
                    </div>
                    <div class="row cl mt-10">
                        <label style="width: 100px;" class="form-label col-xs-3 col-sm-3 lh-30">链接说明：</label>
                        <div class="formControls col-xs-5 col-sm-6 ml-10">
                            <input type="text" class="input-text radius" id="urlText" name="remark">
                        </div>
                    </div>
                    <p class="c-error pt-20 errorTip"></p>
                </div>
                <div class="modal-footer" style="text-align: center">
                    <input type="submit" class="btn btn-primary submit_add submitForm" id="" name="" value="确定">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>


<div id="chooseMenus" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class=" pl-20  clearfix bg-header ">
                <h4 class="f-l f-14">选择父菜单</h4>
                <a class="f-r mr-20 mt-10 back" data-dismiss="modal" aria-hidden="true"><i class="Hui-iconfont">&#xe6a6;</i></a>
            </div>

            <div class="pt-20 mr-20 ml-20 pb-20" style="max-width: 1400px;">
                <c:forEach items="${menus}" var="root">
                        <div class="row cl mt-10">
                            <div class="formControls">
                                <input type="checkbox" class="mt-5 ml-10 "  name="game" style="display: none">
                                <a class="Hui-iconfont ml-10 back  icon-open">&#xe600;</a>
                                <label class="ml-10 sct chooseParent" data-id="${root.id}" data-name="${root.name}"
                                       style="cursor: pointer">${root.name}</label>
                                <div class="second ml-50 pl-30" style="display: none;">

                                    <c:forEach items="${root.children}" var="parent">
                                        <div class="row cl mt-10">
                                            <input type="checkbox" class="mt-5 ml-10 game-admin" name=""  style="display: none">
                                            <label class="ml-10 chooseParent" data-id="${parent.id}" style="cursor: pointer"
                                                   data-name="${parent.name}">${parent.name}</label>
                                        </div>
                                    </c:forEach>

                                </div>
                            </div>
                        </div>
                </c:forEach>
            </div>
            <div class="modal-footer" style="text-align: center">
                <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
            </div>
        </div>
    </div>
</div>


<div id="menuEditor" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content radius">
            <div class=" pl-20  clearfix bg-header ">
                <h4 class="f-l f-14">编辑</h4>
                <a class="f-r mr-20 mt-10 back" data-dismiss="modal" aria-hidden="true"><i class="Hui-iconfont">&#xe6a6;</i></a>
            </div>
            <div class="ajaxContent">

            </div>
        </div>
    </div>
</div>

<!--_footer 作为公共模版分离出去-->
<jsp:include page="/WEB-INF/jsp/common/_footer.jsp"/>
<!--/_footer 作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${resourcesPath}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${resourcesPath}/lib/jquery.validation/1.14.0/additional-methods.js"></script>
<script type="text/javascript" src="${resourcesPath}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${resourcesPath}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${resourcesPath}/static/h-ui.admin/js/fn.js"></script>
<script type="text/javascript">
    function modal2() {
        $("#modal2 input[type!='submit']").val("");
        $(".errorTip").text("");
        $("#modal2").modal("show");
    }

    function openChooseMenu() {
        $("#chooseMenus").modal("show");
    }

    function openMenuEditor(id){
        var loading = "<div class='pt-20 pl-20 pd-20'><div class='row cl mt-10'><label style='width: 100px;' class='form-label col-xs-3 col-sm-3 lh-30'></label>" +
                "<div class='formControls col-xs-5 col-sm-6 ml-10'>加载数据中，请稍后...</div></div></div>";
        var ajaxContent = $("#menuEditor .ajaxContent");
        ajaxContent.html("");
        $(loading).appendTo(ajaxContent);
        $("#menuEditor").modal("show");
        ajaxContent.load("${basePath}/system/menu.html?act=goedit&id="+id,function(){
            initAjaxForm();
        });
    }


    $('.icon-open').on('click', function () {
        var $this = $(this),
                $prev = $this.prev(),
                $next = $this.next().next();
        $next.toggle();
        if ($prev.hasClass('expand')) {
            $prev.removeClass('expand');
            $this.html('&#xe600;');
        } else {
            $prev.addClass('expand');
            $this.html('&#xe6a1;');
        }
    });
    $(function () {
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });

        $(".chooseParent").click(function(){
            var data = $(this).data();
            $("#parentId").val(data.id);
            $("#parentName").val(data.name);
            $("#chooseMenus").modal("hide");
        })

    });
    function al(obj) {
        var $fir_obj = $(obj),
                $sec_obj = $fir_obj.parents(".row").find('.second input');
        var i, k;
        if ($fir_obj.prop("checked")) {
            for (i = 0; i < $sec_obj.length; ++i) {
                $sec_obj.prop('checked', true);
            }
        } else {
            for (k = 0; k < $sec_obj.length; ++k) {
                $sec_obj.prop('checked', false);
            }
        }
    }

    function cp(obj){
        var $this = $(obj) ,$parent = $this.parents(".row").find('.pcheck');
        if(!$this.prop("checked")){
            $parent.removeProp("checked");
        }
    }

    function refresh(){
        location.reload(true);
    }
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>