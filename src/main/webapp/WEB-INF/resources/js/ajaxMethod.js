
$(function () {
    initAjaxForm();
    initExportForm();
    initAjaxButton();
    initComboBox();
    initPagination();
    initValidForm();
    backRefreshButton();
    initTdLengthHandler();
});

function backReload(url){
    window.location.href=url;
}
function back(){
    $(".backButton").click();
}

//重新加载当前页面 针对火狐弹出 ：“为了显示此页面，Firefox 必须发送将重复此前动作的数据（例如搜索或者下订单）。”
function reloadPostedPage(){
    var $paginateForm = $(".paginateForm");
    if($paginateForm && $paginateForm.length != 0) {
        $paginateForm.submit();
    }else if(typeof(param) != "undefined"){//如果页面上存在该变量则为post表单提交后的页面
        var href = window.location.href;
        var formString = "<form action='"+href+"' method='post' id='reloadForm'></form>";
        var inputStrings = [];
        for(var i = 0; i < param.length; i++){
            if(param[i].key.indexOf("back_") == 0) {
                inputStrings.push("<input type='hidden' value='" + param[i].value + "' name='" + param[i].key + "'/>");
            }
        }
        var $form = $(formString);
        $(inputStrings.join("")).appendTo($form);
        $form.appendTo("body");
        $form.submit();
    }else {
        window.location.reload(true);
    }
}

function initAjaxForm() {
    if($.fn.Validform) {
        $(".myAjaxForm").each(function(i){
            createValidForm.call(this);
        });
    }
}

function createValidForm() {
    var isConfirmed = false;
    var afterSuccess = $(this).data("after_success");
    var text = $(this).data("text");

    var btn = $(this).data("btn_submit");
    var tipObj = $(this).data("tip");

    if (typeof(btn) == "undefined" || btn == "") {
        btn = ".submitForm";
    }

    var currForm = $(this).Validform({
        btnSubmit: btn,
        tipSweep: true,
        ajaxPost: true,
        datatype: {
            "needCheck": function (gets, obj, curform, regxp) {
                var data = obj.data();
                var need = data.min,
                    numselected = curform.find("input[name='" + obj.attr("name") + "']:checked").length;
                return numselected >= need ? true : "请至少选择" + need + "项" + data.errmsg + "！";
            },
            "tinyint": function (gets, obj, curform, regxp) {
                var val = obj.val();
                if (val > 127 || val < -128) {
                    return "请填写-128到127之间的数字！"
                }
            },
            "timeAfter": function (gets, obj, curform, regxp) {
                var after = obj.data("after");
                var afterDate = Date.parse(obj.val());
                var beforeDate = Date.parse($(after).val());
                if (afterDate <= beforeDate) {
                    var errormsg = obj.attr("errormsg");
                    if (errormsg && errormsg != "") {
                        return false;
                    }
                    return "开始结束时间先后次序不正确！";
                }
            },
            "gt0": function (gets, obj, curform, regxp) {
                var val = obj.val();
                if (val <= 0) {
                    return "请填写大于0的数字！"
                }
            },
            "percent":function(gets,obj,curform,regxp){
            	var regex = new RegExp("^[0-9]{1,3}(.[0-9]{1,3})?$");
            	if(regex.test(gets)) return true;
            	else return "请正确填写100分比！";
            },
            "num":function(gets,obj,curform,regxp){
            	var regex = /^[0-9]+(.[0-9]*)?$/;
            	if(regex.test(gets)) return true;
            	else return "请填写正确的数字";
            }

        },
        beforeSubmit: function (curform) {
            if (text && text != "") {
                if (!isConfirmed) {
                    myTipBox({title: "确定？", content: text, cancelBtn: true, confirmBtn: true}, function () {
                        isConfirmed = true;
                        currForm.submitForm(false);
                    });
                    return false;
                } else {
                    isConfirmed = false;
                    return true;
                }
            }
            return true;
        },
        callback: function (data) {
            if (data.statusCode == "200") {
                myTipBox({title: "成功", content: data.message, cancelBtn: false, confirmBtn: true}, function () {
                    if (typeof(afterSuccess) != "undefined" && afterSuccess != "") {
                        eval(afterSuccess + "();");
                    }
                });
            }
            else {
                if (data.statusCode == "301") {
                    // myTipBox({title: "登录超时", content: data.message, cancelBtn: false, confirmBtn: true},function(){
                    //     location.href = data.href;
                    // });
                    location.href = data.href;
                } else {
                    myTipBox({title: "失败", content: data.message, cancelBtn: false, confirmBtn: true});
                }
            }
        },
        tiptype: function (msg, o, cssctl) {
            if (o.type == 1) {
                if(typeof(tipObj) != "undefined" && tipObj!= ""){
                    $(tipObj).text(msg)
                }else {
                    myTipBox({content: "正在提交数据...", cancelBtn: false, confirmBtn: false});
                }
            } else if (o.type == 3) {
                if(typeof(tipObj) != "undefined" && tipObj!= ""){
                    $(tipObj).text(msg)
                }else {
                    myTipBox({title: "字段缺失", content: msg, cancelBtn: false, confirmBtn: true});
                }
            }else{
                if(typeof(tipObj) != "undefined" && tipObj!= ""){
                    $(tipObj).text(msg)
                }
            }
        }
    });
    return currForm;
}

function initTdLengthHandler(){
    $(".tdRender").each(function(){
        var $td = $(this);
        var text = $td.html().replace(/\s/g, "");
        var title = text;
        if(text.length > 20){
            text = text.substr(0,19) + " · · ·";
        }
        $td.text(text);
        $td.attr("title",title);
    });
}

//返回上一页可以让上一页刷新的控件
/*
* 需要在第一页的跳转按钮加上 class ：backRefresh
* 在第二页的返回按钮加上 class ：backButton
* 并在第二页上加上以下代码：
*    <c:if test="${paramValues != null && paramValues.size() != 0}">
         var param = [
             <c:forEach items="${paramValues}" var="pm">
             {"key":"${pm.key}" ,"value":"${pm.value[0]}"},
             </c:forEach>
             {"key":"back_href","value":"${basePath}/customer/forbidUser.html?act=list"} <%--上一页的连接地址--%>
         ];
     </c:if>
*
* */
function backRefreshButton(){
    $(".backRefresh").each(function(){
       $(this).click(function(e){
           var $button = $(this);
           var href = $button.attr("href");
           var formString = "<form action='"+href+"' method='post' id='backRefreshFrom'></form>";
           var $form = $(formString);
           var $paginateForm = $(".paginateForm");
           if(!$paginateForm || $paginateForm.length == 0) {
                window.location.href=href;
               stopDefault(e);
               stopBubble(e);
                return;
           }
           var $inputs = $paginateForm.children("input").clone();
           $inputs.each(function(){
               var name = $(this).attr("name");
               $(this).attr("name", "back_" + name);
           });
           $form.appendTo("body");
           $inputs.appendTo($form);
           $form.submit();
           stopDefault(e);
           stopBubble(e);
       });
    });

    $(".backButton").click(function(){
        goPreListPage();
    });
}

function goPreListPage(){
    if(typeof(param) == "undefined"){
        window.history.go(-1);
        return;
    }
    var href;
    var inputStrings = [];
    for(var i = 0; i < param.length; i++){
        if(param[i].key == "back_href"){
            href = param[i].value;
        }else if(param[i].key.indexOf("back_") != -1){
            param[i].key.indexOf("back_");
            var key =  param[i].key.substr(5);
            var inputString = "<input type='hidden' value='" + param[i].value + "' name='" + key + "'/>";
            inputStrings.push(inputString);
        }
    }
    if(!href){
        window.history.go(-1);
        return;
    }
    var formString = "<form action='"+href+"' method='post' id='backFrom'></form>";
    var $backFrom = $(formString);
    $backFrom.appendTo("body");
    $(inputStrings.join("")).appendTo($backFrom);
    $backFrom.submit();
}


function stopBubble(e) {
//如果提供了事件对象，则这是一个非IE浏览器
    if ( e && e.stopPropagation )
    //因此它支持W3C的stopPropagation()方法
        e.stopPropagation();
    else
    //否则，我们需要使用IE的方式来取消事件冒泡
        window.event.cancelBubble = true;
}
//阻止浏览器的默认行为
function stopDefault( e ) {
    //阻止默认浏览器动作(W3C)
    if ( e && e.preventDefault )
        e.preventDefault();
    //IE中阻止函数器默认动作的方式
    else
        window.event.returnValue = false;
    return false;
}

function initExportForm() {
    if ($.fn.Validform) {
        $(".exportForm").each(function(i){
            var $exportForm = $(this);
            $exportForm.Validform({
                btnSubmit: ".exportButton",
                tipSweep: true,
                ajaxPost: false,
            });
        });
    }
}

function initValidForm(){
    if ($.fn.Validform) {
        $(".validForm").each(function(){
            $(this).Validform();
        });
    }
}


// data-url : 异步请求地址
// data-text : 确认弹窗提示内容
// data-callback : 服务器返回成功后的回调参数名，如果为空将刷新页面
function initAjaxButton() {
    $(".myAjaxButton").each(function (i) {
        var currBut = $(this);
        var data = currBut.data();
        currBut.click(function () {
            myTipBox({title: "确定？", content: data.text, cancelBtn: true, confirmBtn: true}, function () {
                myTipBox({content: "处理中，请稍后...", cancelBtn: false, confirmBtn: false});
                $.post(data.url, function (json) {
                    if (json.statusCode == "200") {
                        myTipBox({title: "成功", content: json.message, cancelBtn: false, confirmBtn: true}, function () {
                            if (data.callback) {
                                eval(data.callback+"();");
                            }else{
                                reloadPostedPage();
                            }
                        });
                    }else if(json.statusCode == "301"){
                        // myTipBox({title: "登录超时", content: json.message, cancelBtn: false, confirmBtn: true},function(){
                        //     window.location.href = json.href;
                        // });
                        window.location.href = json.href;
                    }else {
                        myTipBox({title: "失败", content: json.message, cancelBtn: false, confirmBtn: true});
                    }
                }, "json");
            });
        });
    });
}

// data-json 存放游戏或渠道json数据的变量
function initComboBox(){
    var maxResultCount = 5;//结果最多显示5条
    var $myComboBox = $(".myComboBox");
    $myComboBox.each(function (i) {
        var comboBox = $(this);
        var data = comboBox.data();
        var json;
        eval("json = "+data.json+";");
        var hidden = comboBox.nextAll(".comboBoxHidden");
        var initVal = hidden.val();
        if(initVal){
            $.each(json, function (n, value) {
                if (value.id == initVal) {
                    comboBox.val(value.name);
                    hidden.data("name",value.name);
                    hidden.data("id",value.id);
                    return;
                }
            });
        }

        comboBox.keyup(function(){
            var val = comboBox.val();
            if(!val || val == ""){
                return;
            }
            var result = [];
            if (json) {
                var searchCnt = 0;
                $.each(json, function (n, value) {
                    if (value.name.toLowerCase().indexOf(val.toLowerCase()) > -1) {
                        result.push(value);
                        searchCnt++;
                    }
                    if (searchCnt == maxResultCount) {
                        return false;
                    }
                });
            }

            var html = [];
            var $resultBox = comboBox.siblings(".search_result");

            html.push("<ul class='pt-10'>");
            if(result.length != 0) {
                $.each(result, function (i) {
                    html.push("<li onclick='chooseComboBox(this)' data-id='" + result[i].id + "' data-name='" + result[i].name + "' class='pl-20'><a href='javascript:' class='back'>" + result[i].name + "</a></li>");
                });
            }else{
                html.push("<li onclick='chooseComboBox(this)' class='pl-20'><a href='javascript:' class='back'>未查询到相关数据</a></li>");
            }
            html.push("</ul>");
            var h = html.join("");
            $resultBox.empty();
            $(h).appendTo($resultBox);

            $resultBox.show();
            $resultBox.find("ul,li,a").show();

        });
    });

    //页面点击后收起comboBox并设置值
    $(document).on("click", function () {
        $(".search_result").hide();
        $(".myComboBox").each(function(i){
            var $comboBox = $(this);
            var $comboBoxHidden = $comboBox.nextAll(".comboBoxHidden");
            if($comboBox.val() && $comboBox.val()!= ""){
                var name = $comboBoxHidden.data("name");
                $comboBox.val(name);
            }else{
                $comboBoxHidden.val("");
                $comboBoxHidden.removeData("id");
                $comboBoxHidden.removeData("name");
            }
        });
    });
}

//选择comboBox的事件，将选择的id和name保存
function chooseComboBox(obj){
    var $this = $(obj);
    var data = $this.data();
    var resultBox = $this.parents(".search_result");
    var comboBox = resultBox.prevAll(".myComboBox");
    var hidden = resultBox.prevAll(".comboBoxHidden");
    hidden.val(data.id);
    comboBox.val(data.name);
    hidden.data("name",data.name);
    hidden.data("id",data.id);
    resultBox.hide();
    $this.parents(".search_result").siblings(".myComboBox").focus();
    
    var callbackString = comboBox.data("after_select");
    if(typeof(callbackString) != "undefined" && callbackString != ''){
    	eval(callbackString+"("+data.id+", '"+ data.name +"');");
    }
}

function initPagination(){
    $(".dataTables_wrapper .paginate").each(function(){
        var $pagination = $(this);
        var data = $pagination.data();

        var disableFirst = false,
            disableLast = false,
            disablePre = false,
            disableNext = false,
            pageNum = data.page_num,
            pageSize = data.page_size,
            total = data.total;

        totalPage = Math.ceil(total/pageSize);
        totalPage = totalPage < 1 ? 1 : totalPage;
        pageNum = pageNum > totalPage ? totalPage : pageNum;
        pageNum = pageNum < 1 ? 1 : pageNum;
        disablePre = disableFirst = pageNum == 1;
        disableNext = disableLast = pageNum == totalPage;
        var showPage = [1,-99,pageNum-2,pageNum-1,pageNum,pageNum+1,pageNum+2,-99,totalPage];//分页条的9个位置展示页码，不在页码范围的数组为不显示该位置，-99为“...”

        if(showPage[2] == 2){
            showPage[1] = 0;
        }else if(showPage[2] < 2){
            showPage[1] = showPage[0] = 0;
        }

        if(showPage[6] == totalPage - 1){
            showPage[7] = 0;
        }else if(showPage[6] > totalPage - 1){
            showPage[7] = showPage[8] = 0;
        }

        var pageHtml = [];
        
        pageHtml.push("<a class='btn previous pageFirst pageButton");
        if(disableFirst){
            pageHtml.push(" disabled' ");
        }else{
            pageHtml.push("' ");
        }
        pageHtml.push("aria-controls='DataTables_Table_0' style='margin: 0 0 10px 10px;' data-page_num='"+1+"'>首页</a>");


        pageHtml.push("<a class='btn previous pagePre pageButton");
        if(disablePre){
            pageHtml.push(" disabled' ");
        }else{
            pageHtml.push("' ");
        }
        pageHtml.push("aria-controls='DataTables_Table_0' style='margin: 0 0 10px 10px;' data-page_num='"+(pageNum-1)+"'>上一页</a>");


        for(var i = 0; i < showPage.length; i++){
            if(showPage[i] == -99){
                pageHtml.push("...");
            }else if(showPage[i] <= totalPage && showPage[i] > 0){
                if(showPage[i] == pageNum) {
                    pageHtml.push("<span><a class='btn btn-primary disabled' style='margin: 0 0 10px 10px;' aria-controls='DataTables_Table_0'>"+showPage[i]+"</a></span>");
                }else{
                    pageHtml.push("<span><a class='btn pageSpecial pageButton' style='margin: 0 0 10px 10px;' data-page_num='"+showPage[i]+"' aria-controls='DataTables_Table_0'>"+showPage[i]+"</a></span>");
                }
            }
        }

        pageHtml.push("<a class='btn next pageNext pageButton");
        if(disableNext){
            pageHtml.push(" disabled' ");
        }else{
            pageHtml.push("' ");
        }
        pageHtml.push("aria-controls='DataTables_Table_0' style='margin: 0 0 10px 10px;' data-page_num='"+(pageNum+1)+"' >下一页</a>");

        pageHtml.push("<a class='btn next pageLast pageButton");
        if(disableLast){
            pageHtml.push(" disabled' ");
        }else{
            pageHtml.push("' ");
        }
        pageHtml.push("aria-controls='DataTables_Table_0' style='margin: 0 0 10px 10px;' data-page_num='"+totalPage+"' >尾页</a>");

        pageHtml.push("<a class='pl-5 btn-link pageButton' style='margin: 0 0 0 10px;' id='jumpTo'>跳转</a> <input id='pageInput' style='width: 50px;' style='margin: 0 0 10px 10px;' data-max='"+totalPage+"' class='pl-5 input-text size-MINI pageSelector' type='number'/>");


        $pagination.html("");
        $(pageHtml.join("")).appendTo($pagination);
    });

    $(".dataTables_wrapper .paginateForm .pageButton:not(.disabled)").click(function(){
        var $pageButton = $(this);
        var pageNum;
        if($pageButton.attr("id") == "jumpTo"){
            pageNum = $("#pageInput").val();
            var max = $("#pageInput").data("max");
            pageNum = rightPageNum(pageNum, max);
        }else {
            pageNum = $pageButton.data("page_num");
        }
        if(pageNum){
            $(".dataTables_wrapper .paginateForm input[name='pageNum']").val(pageNum);
            $(".dataTables_wrapper .paginateForm").submit();
        }
    });

    function rightPageNum(value, max) {
        if (value) {
            value = (value > max ? max : value);
            value = (value < 1 ? 1 : value);
            $(".dataTables_wrapper .paginateForm input[name='pageNum']").val(value);
        }
        return value;
    }

    $(".dataTables_wrapper .paginateForm .pageSelector").keydown(function (event) {
        var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
        if (keyCode == 13) {
            var value = $(this).val();
            var max = $(this).data("max");
            rightPageNum(value, max);
        }
    });
}





function myTipBox(option, fn) {
    var htmlArr = [], option = option || {}, $html;
    if ($("#tipBox").length > 0) {
        $("#tipBox").remove();
    }
    htmlArr.push('<div id="tipBox" class="modal"><div class="modal-dialog"><div class="modal-content radius"><div class="modal-header">');
    if(option.confirmBtn || option.cancelBtn) {
        htmlArr.push('<h3 class="modal-title">' + option.title + '</h3>');
        htmlArr.push('<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"></a></div><div class="modal-body">');
        // htmlArr.push('<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a></div><div class="modal-body">');
    }
    htmlArr.push(option.content);//提示内容，支持html
    htmlArr.push('</div><div class="modal-footer">');
    if(option.confirmBtn) {
        htmlArr.push('<button id="tipAffirm" data-dismiss="modal" aria-hidden="true" class="btn btn-primary">确定</button>');
    }
    if (option.cancelBtn) {
        htmlArr.push('<button id="cancelBtn" data-dismiss="modal" aria-hidden="true" class="btn">关闭</button>');
        if(option.callBackCancel){
            $(document).off("click", "#cancelBtn").on("click", "#cancelBtn", function () {
                option.callBackCancel(option.$obj);
            });
        }else{
            $(document).off("click", "#cancelBtn");
        }
    }
    if (fn && typeof(fn) === "function" && option.confirmBtn) {
        $(document).off("click", "#tipAffirm").on("click", "#tipAffirm", fn)
    }else{
        $(document).off("click", "#tipAffirm");
    }
    htmlArr.push('</div></div></div></div>');
    $html = $(htmlArr.join(""));
    $("body").append($html);
    $html.modal("show");
    return $html;
}
