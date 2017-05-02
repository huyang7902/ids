<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form class="form form-horizontal myAjaxForm" action="${basePath}/system/menu.html?act=save" data-after_success="refresh">
    <input type="hidden" value="${menuTO.id}" name="id">
    <div class="pt-20 pl-20 pd-20">
        <div class="row cl mt-10">
            <label style="width: 100px;" class="form-label col-xs-3 col-sm-3 lh-30">*名称：</label>
            <div class="formControls col-xs-5 col-sm-6 ml-10">
                <input type="text" class="input-text radius required" datatype="*" nullmsg="菜单名不能为空！"
                       value="${menuTO.name}" id="_name" name="name"/>
            </div>
        </div>
        <div class="row cl mt-10">
            <input id="_parentId" name="parentId" type="hidden" value="${requestScope.menuTO.parentId}"/>
            <label style="width: 100px;" class="form-label col-xs-3 col-sm-3 lh-30">*父节点：</label>
            <div class="formControls col-xs-5 col-sm-9 ml-10">
    				<span class="size-L f-l" style="margin-top: 6px;">
                        ${parent.name}
                    </span>
            </div>
        </div>
        <div class="row cl mt-10">
            <label style="width: 100px;" class="form-label col-xs-3 col-sm-3 lh-30">*排序：</label>
            <div class="formControls col-xs-5 col-sm-6 ml-10">
                <input type="text" class="input-text radius" id="_sortOrderNo" datatype="*,n" nullmsg="排序不能为空！" errormsg="排序必须是整数！"
                       value="${requestScope.menuTO.sort}" name="sortOrderNo">
            </div>
        </div>
        <div class="row cl mt-10">
            <label style="width: 100px;" class="form-label col-xs-3 col-sm-3 lh-30">*可见性：</label>
            <input type="hidden" name="accessRoleLevel" value="${menuTO.accessRoleLevel}">
            <div class="formControls col-xs-5 col-sm-6 ml-10">
                <span class="size-L f-l" style="margin-top:6px;">
                    ${menuTO.accessRoleLevel}
                </span>
            </div>
        </div>
        <div class="row cl mt-10">
            <label style="width: 100px;" class="form-label col-xs-3 col-sm-3 lh-30">链接地址：</label>
            <div class="formControls col-xs-5 col-sm-6 ml-10">
                <input type="text" class="input-text radius" id="_url" name="url"
                       value="${requestScope.menuTO.url}">
            </div>
        </div>
        <div class="row cl mt-10">
            <label style="width: 100px;" class="form-label col-xs-3 col-sm-3 lh-30">链接说明：</label>
            <div class="formControls col-xs-5 col-sm-6 ml-10">
                <input type="text" class="input-text radius" id="_urlText" name="remark" value="${requestScope.menuTO.remark}">
            </div>
        </div>
        <p class="c-error pt-20 errorTip"></p>
    </div>
    <div class="modal-footer" style="text-align: center">
        <input type="submit" class="btn btn-primary submit_add submitForm" value="确定">
        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
    </div>
</form>