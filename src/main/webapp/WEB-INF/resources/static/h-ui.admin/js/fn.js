function getDataUrl(files, callBack) {
    var arr = [], reader, filesLength = files.length;
    filesLength === 1 && (function () {
        reader = new FileReader();
        reader.onload = function (event) {
            //arr.push(event.target.result);
            callBack(event.target.result);
            return false;
        };
        reader.readAsDataURL(files[0])
    })();
    filesLength > 1 && (function () {
        for (var i = 0, j = filesLength; i < j; i += 1) {
            reader = new FileReader();
            reader.onload = function (event) {
                /*$("body").append("<img src='"+event.target.result+"' />")*/
                //arr.push(event.target.result);
                callBack(event.target.result);
            };
            reader.readAsDataURL(files[i]);
        }
    })()
}

function checkLength(which,lengths) {
    var maxChars = lengths||150;
    if (which.value.length > maxChars)
        which.value = which.value.substring(0, maxChars);
    var curr = maxChars - which.value.length;
    document.getElementById("currentLength").innerHTML = curr.toString();
}

function checkAll(that, callBack) {
    var that = that;
    $(that).parent().find("input[type='checkbox']").each(function (a, b) {
        b.checked = that.checked;
        if (callBack && typeof(callBack) === "function") {
            callBack(b, that.checked);
        }
    })
}
function modaldemo(str) {
    var $obj = str ? $(str) : $("#modal-demo");
    $obj.modal("show");
    return $obj;
}

function post(that) {
    $(that).parents("form").submit();
}
function formPost(that) {
    event.preventDefault();
    var $old = $("input[name='oldPw']"),
        $newPw = $("input[name='newPw']"),
        $newPw2 = $("input[name='newPw2']"),
        $errorTip = $("#errorTip");
    $.ajax({
        type: "get",
        url: that.action,
        async: true,
        data: {
            old: $old.val(),
            newPw: $newPw.val(),
            newPw2: $newPw2.val()
        }
    }).done(function (res) {
        if (res.code !== 200) {
            $errorTip.text(res.tip);
        } else {
            window.location.href = window.location.href;
        }
    });

}

function tipBox(option, fn) {
    var htmlArr = [], option = option || {}, $html;
    if ($("#tipBox").length > 0) {
        $("#tipBox").remove();
    }
    htmlArr.push('<div id="tipBox" class="modal"><div class="modal-dialog"><div class="modal-content radius"><div class="modal-header">');
    if(option.isCheckbox&&option.isCheckbox.openTitle&&option.isCheckbox.closeTitle){
        if(option.checkState === true){
            htmlArr.push('<h3 class="modal-title">' + option.isCheckbox.openTitle + '</h3>');
        }else{
            htmlArr.push('<h3 class="modal-title">' + option.isCheckbox.closeTitle + '</h3>');
        }

    }else{
        htmlArr.push('<h3 class="modal-title">' + option.title + '</h3>');
    }

    /*htmlArr.push('<a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();">×</a></div><div class="modal-body">');*/
    htmlArr.push('</div><div class="modal-body">');
    if(option.isCheckbox&&!option.useResContent){
        if(option.checkState === true){
            htmlArr.push(option.isCheckbox.openContent);
        }else{
            htmlArr.push(option.isCheckbox.closeContent);
        }
    }else{
        htmlArr.push(option.content);//提示内容，支持html
    }

    htmlArr.push('</div><div class="modal-footer">');
    /*if(fn&&typeof(fn) === "function"){
     htmlArr.push('<button data-dismiss="modal" aria-hidden="true" onclick="fn()" class="btn btn-primary">确定</button>');
     }else{*/
    htmlArr.push('<button id="tipAffirm" data-dismiss="modal" aria-hidden="true" class="btn btn-primary">确定</button>');
    /*}*/
    if (option.cancelBtn) {
        htmlArr.push('<button id="cancelBtn" data-dismiss="modal" aria-hidden="true" class="btn">关闭</button>');
        /*$(document).one("click","#tipAffirm",function(){
         $(option.selectString).parents("form").submit();
         });*/
        $(document).off("click", "#cancelBtn").on("click", "#cancelBtn", function () {
            option.callBackCancel(option.$obj);
        })
    }
    /*$(document).one("click","#tipAffirm",function(){
     if(fn&&typeof(fn) === "function"){
     fn()
     }
     });*/
    if (fn && typeof(fn) === "function") {
        $(document).off("click", "#tipAffirm").on("click", "#tipAffirm", fn)
    }
    if (option.nosubmit) {
        $(document).off("click", "#tipAffirm");
    }
    htmlArr.push('</div></div></div></div>');
    $html = $(htmlArr.join(""));
    $("body").append($html);
    $html.modal("show");
    return $html;
}

function dataSerialization($data, isFiles) {
    //$data:要序列化的form表单jquery对象;
    //isFiles:是否需要文件的表单
    //dataSerialization 数据序列化
    if (isFiles) {
        var formdata = new FormData();
        $data.find("input,select,textarea").each(function (index, el) {
            if (el.name) {
                if (el.type === "checkbox") {
                    if (el.checked) {
                        formdata.append(el.name, true);
                    } else {
                        formdata.append(el.name, false);
                    }
                } else if (el.type === "radio") {
                    if (el.checked) {
                        formdata.append(el.name, el.value);
                    }
                } else if (el.type === "file") {
                    formdata.append(el.name, el.files[0]);
                } else {
                    formdata.append(el.name, el.value);
                }
            }
        });
        return formdata;
    } else {
        return $data.find("input,select,textarea").map(function (i, el) {
            if (el.name) {
                if (el.type === "checkbox") {
                    if (el.checked) {
                        return {name: el.name, value: true};
                        //arr.push({name:el.name,value:true})
                    } else {
                        return {name: el.name, value: false};
                    }
                } else if (el.type === "radio") {
                    if (el.checked) {
                        return {name: el.name, value: el.value};
                    }
                } else {
                    return {name: el.name, value: el.value};
                }
            }
        })

    }
}

/*selectString //提交按钮的CSS选择器的字符串  type：String
 check //是否需要校验数据 type：boolean
 checkTit //校验标题 type：String
 checkCon//校验提示内容 type：String
 content//弹框内容 type：String
 success//提交成功后弹框提示文字 type：String
 dynamicTit //动态传入提示框的title，值为提交按钮的data-text type：boolean
 title//弹框的标题  type：String
 cancelBtn//数据提交前是否需要提示  type：boolean
 hasFileObj//是否有文件流，有文件上传会走formdata type：boolean
 callBack//成功后回调 type：function
 callBackCancel//取消后回调 type：function
 aSingleObj//表单内单个状态提交且提交地址使用data-url保存 
 fail //提交数据失败
 postFormStr //提交指定范围内数据 （css选择器）
 callBackFail //失败后回调 type：function
 type：boolean 一般默认值都是false
 */

function submitF(option) {
    var option = option || {};

    $(document).on("click", option.selectString, function (e) {
        var checkObj, $this = $(this);
        var dataUrl = $this.data('url');
        option.$obj = $this;
        option.isCheckbox&&($this.data("status") === undefined?option.checkState=$this.bootstrapSwitch("status"):option.checkState = $this.data("status"));
        /*if (!$this.parents("form").data("has")) {
         $this.parents("form").data("has", true);*/
            option.check && (checkObj = $this.parents("form").validate({ignore:""}));
            option.dynamicTit && (option.title = $this.data("text"));
            $this.parents("form").off("submit").on("submit", function () {
                if (option.check && !checkObj.form()) {
                    tipBox({
                        title: option.checkTit,
                        content: option.checkCon,
                        cancelBtn: option.cancelBtn,
                        nosubmit: true
                    });
                    return false;
                }
                var $this = $(this),
                    data = dataSerialization(option.postFormStr ? option.$obj.parents(option.postFormStr) : $this, option.hasFileObj),
                    sUrl = option.aSingleObj ? dataUrl : $this.attr("action"),
                //sUrl = $this.attr("action"),
                    sType = $this.attr("method") || "GET";
                processData = option.hasFileObj ? false : true;
                contentType = option.hasFileObj ? false : "application/x-www-form-urlencoded";
                $.ajax({
                    type: sType,
                    url: sUrl,
                    async: true,
                    data: data,
                    dataType: "json",
                    processData: processData,
                    contentType: contentType,
                    cache: false
                }).done(function (res) {
                    option.res = res;
                    //console.log(data);
                    if (option.res.statusCode === "200") {
                        tipBox({
                            title: option.title,
                            content: res.message || option.success,
                            cancelBtn: false,
                            useResContent:true,
                            checkState:option.checkState,
                            isCheckbox:option.isCheckbox
                        }, function () {
                            //debugger
                            /*window.location.href = res.href;*/
                            typeof(option.callBack) === "function" && option.callBack(option);

                        });
                    } else {
                        if(option.res.href){
                            parent.location.href = option.res.href;
                        }else{
                            tipBox({
                                title: option.title,
                                content: res.message || option.fail,
                                cancelBtn: false,
                                useResContent:true,
                                checkState:option.checkState,
                                isCheckbox:option.isCheckbox
                            }, function () {
                                //debugger
                                /*window.location.href = res.href;*/

                                typeof(option.callBackFail) === "function" && option.callBackFail(option);

                            });
                        }

                    }

                    //window.location.href = res.href;
                });
                return false;
            });

       /* }*/
        if (option.cancelBtn) {
            /*e.preventDefault()*/
            /*option.$obj = $this;*/
            tipBox(option, function () {
                /*$(option.selectString).parents("form").submit();*/
                option.$obj.parents("form").submit();
            });
            return false;
        }
    })

}
/*
 uploadFile：上传文件按钮的CSS选择器的字符串  type：String
 data-url：上传文件中文件提交url地址data属性；
 data-imgdel：上传文件作为图片时，原型结构中是否有删除图片按钮data属性；
 data-filename：上传文件作为文件时，上传文件原型结构点击上传成功后是否显示文件名；
 data-download：上传文件作为文件时，上传文件原型结构点击上传成功后是否存在下载按钮；
 data-filedel：上传文件作为文件时，上传文件原型结构点击上传成功后是否存在删除按钮；
 data-pvw：需要预览时就在上传文件按钮上添加data-pvw="true";
 */
/*function downLoad(obj) {
    var $obj = $(obj),
        content = $obj.prop("files")[0];
    var downloadUrl = window.URL.createObjectURL(content);
    return (downloadUrl);
}*/
function upload(option) {
    var option = option || "";
    $(document).on("change", option.uploadFile, function () {
        var $this = $(this),
            formdata = new FormData(),
            url = option.dataUrl ? ($this.data('url') + $("#cpId").val()) : $this.data('url'),
            $success = $this.parents().siblings(".success");
        formdata.append($this.data("name"), $this.prop("files")[0]);
        var data = formdata;
        if (option.inputUrl) {
            $this.parent().siblings('p').show();
        }
        $.ajax({
            url: url,
            method: "post",
            data: data,
            dataType: "json",
            processData: false,
            contentType: false,
            cashe: false
        }).done(function (res) {
                if (res.statusCode == "200") {
                    var res = res.data, htmlInput;
                    if ($this.data("req")) {
                        htmlInput = "<input class='required' type='hidden' name=" + $this.data("name") + " value=" + res.url + " />";
                    } else {
                        htmlInput = "<input type='hidden' name=" + $this.data("name") + " value=" + res.url + " />";
                    }
                    if ($this.data("pvw")) {
                        $this.parent().siblings(".img-icon").empty().show();
                        if ($this.data("imgdel")) {
                            $this.parent().siblings(".img-icon").append("<img src='" + res.url + "'class='img-responsive thumbnail' alt='响应式图片'/><a href='javascript:' class='delete' onclick='del(this)' ><i class='Hui-iconfont '>&#xe609</i></a>" + htmlInput + "");
                        } else {
                            $this.parent().siblings(".img-icon").append("<img src=" + res.url + "class='img-responsive thumbnail' alt='响应式图片'/>" + htmlInput + "");
                        }
                    } else {
                        if (option.inputUrl) {
                            $this.parent().siblings('#url').val(res.url);
                            $this.parent().siblings('p').text("上传完成")
                        } else {
                            $this.parent().hide();
                            $this.parent().siblings(".success").empty().show();
                            if ($this.data("filename")) {
                                $success.append("<span class='mr-20 '>" + res.url + "</span>" + htmlInput + "");
                            } else {
                                $success.append("<span class='mr-20 '>已上传</span>" + htmlInput + "");
                            }
                            if ($this.data("download")) {
                                $success.append("<a href=" + res.url + " class='c-primary mr-20' download='dowload'>下载</a>");
                            }
                            if ($this.data("filedel")) {
                                $success.append(" <a title='删除' href='javascript:' onclick='del(this)' class='ml-10' style='text-decoration:none'>删除</a>");
                            }
                        }
                    }
                }
            }
        )
    })
}
/*文件或者图片删除
 data-delurl:点击删除时提交后台的url地址*/
function del(obj) {
    //debugger
    var $obj = $(obj),
        $parent = $obj.parent(),
        delUrl = $parent.data("delurl"),
        $input = $obj.siblings("input"),
        name = $input.prop("name"),
        data = (name, $input.val(""));

    function clearField() {
        if (($.trim($obj.text()) !== "下载") && $parent.siblings(".btn-upload").find("input").data("pvw")) {
            $parent.prev().find(".upload").val("");
            $parent.hide();
            if ($parent.siblings(".btn-upload").find("input").data("req")) {
                $parent.html("<input type='hidden' class='required' name='" + name + "'>")
            }
        } else {
            $parent.next().show().find(".upload").val("");
            $parent.hide();
        }
    }

    if ($parent.data("delurl") == "") {
        clearField();
    } else {
        $.ajax({
            url: delUrl,
            method: 'post',
            data: data,
            dataType: 'json'
        }).done(function (res) {
            if (res.statusCode == "200") {
                clearField();
            }
        });
    }
}

//游戏、渠道、区服输入收缩相关资讯查询
function query_ajax(obj, url) {
    var $obj = $(obj);
    if ($.trim($obj.val()) !== "") {
        $obj.addClass('ac_loading');
        $.ajax({
            url: url,
            dataType: "json",
            data: {
                data: $obj.data('type'),
                name: $obj.val()
            }
        }).done(function (data) {
            $obj.removeClass('ac_loading');
            $obj.siblings(".search_result").show();
            if (data !== "") {
                var json = data.data;
                $obj.siblings(".search_result").find("a").each(function (i, v) {
                    $(v).parent().show();
                    $(v).text(json[i].name);
                });
            } else {
                $obj.val("暂时搜索不到数据");
            }
        })
    } else {
        $obj.removeClass('ac_loading');
    }
}

function choose(a) {
    $(a).parents(".search_result").prev().val($(a).text());
    $(a).parents(".search_result").hide();
    $(a).parents(".search_result").prev().focus()
}

function randomWord(randomFlag, min, max, selectStr, hiddenInput) {
    var str = "",
        range = min,
        arr = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

    // 随机产生
    if (randomFlag) {
        range = Math.round(Math.random() * (max - min)) + min;
    }
    for (var i = 0; i < range; i++) {
        pos = Math.round(Math.random() * (arr.length - 1));
        str += arr[pos];
    }
    $(selectStr).text(str);
    $(hiddenInput).val(str);
}

//翻页
function limitInput(o) {
    var value = o.value;
    var min = 1;
    var max = endPage;
    if (parseInt(value) < min || parseInt(value) > max) {
        alert('输入错误');
        o.value = '';
    }
}
$('.jumpPage').on('keypress', function (e) {
    if (e.keyCode === 13) {
        window.location.href = contextRoot + "&page=" + $('.jumpPage').val();
        return false;
    }
});
function flip(obj) {
    var $obj = $(obj), concat = "&", str;
    str = createUrl(argmentsArr) || [];
    currentPage = parseInt(currentPage, 10);
    endPage = parseInt(endPage, 10);
    !contextRoot.indexOf("?") && (concat = "?");
    /* typeof(createUrl(argementsArr)) === "undefind" && (str = []);*/
    if ($.trim($obj.text()) === "下一页") {
        currentPage = currentPage + 1;
        toUrl = contextRoot + concat + "page=" + currentPage + str.join("");
        if (currentPage <= endPage) {
            window.location.href = toUrl;
        } else {
            alert("当前页面已经是尾页了！");
        }
    } else if ($.trim($obj.text()) === "上一页") {
        currentPage = currentPage - 1;
        toUrl = contextRoot + concat + "page=" + currentPage + str.join("");
        if (currentPage > 0) {
            window.location.href = toUrl;
        } else {
            alert("当前页面已经是首页了！")
        }
    } else if ($.trim($obj.text()) === "尾页") {
        window.location.href = contextRoot + concat + "page=" + endPage + str.join("");
    } else if ($.trim($obj.text()) === "首页") {
        window.location.href = contextRoot + concat + "page=1" + str.join("");
    } else if ($.trim($obj.text()) === "跳转") {
        window.location.href = contextRoot + concat + "page=" + $obj.next().val() + str.join("");
    }
    else {
        window.location.href = contextRoot + concat + "page=" + $obj.data("dt-idx") + str.join("");
    }
}

function createUrl(arr) {
    var str = [];
    for (var i = 0, j = arr.length; i < j; i += 1) {
        if (arr[i].val !== "") {
            if (arr[i].name === "appName" || arr[i].name === "roleName" || arr[i].name === "channelName") {
                str.push("&" + arr[i].name + "=" + encodeURI(arr[i].val));
            } else {
                str.push("&" + arr[i].name + "=" + arr[i].val);
            }

        }
    }
    return str;
}
function exportUrl(that) {
    var str = createUrl(exportArr);
    window.location.href = that.href + str.join("");

}
/*收益公式动态变化*/
function profitRule() {
    //var option = option || {};
    $("#canalRate").on("change", function () {
        $("#profit-canalRate").text($(this).val());
    });
    $("#firmInto").on("change", function () {
        $("#profit-firmInto").text("");
        $("#profit-firmInto").text($(this).val());
    });
    /*$("#firmInto2").on("change", function () {
        $("#profit-firmInto").text($(this).val());
    });*/
    $("#invoiceType").on("change", function () {
        var taxRate = $(this).find("option:selected").data("select");
        $("#taxRate").text(taxRate +'%');
        $("#profit-textRate").text(taxRate);
    })
}
function pickDate(obj) {
    var $obj = $(obj),
        $datepick = $("#datepick");
    $datepick.modal("show");
    $datepick.find(".filt").on("click", function () {
        $datepick.modal("hide");
        if ($('#startTime').val() == '' || $('#endTime').val() == '') {
        } else {
            $obj.val($('#startTime').val() + ' — ' + $('#endTime').val());
        }
    })
}
var start = {
    format: 'YYYY-MM-DD hh:mm:ss',
    minDate: "1911-09-19 00:00:00", //设定最小日期为当前日期
    /*  isinitVal: true,
     festival: true,
     ishmsVal: false,*/
    maxDate: '2999-12-31 23:59:59', //最大日期
    choosefun: function (elem, datas) {
        end.minDate = datas; //开始日选好后，重置结束日的最小日期
    }
};
var end = {
    format: 'YYYY-MM-DD hh:mm:ss',
    minDate: "1911-09-19 00:00:00", //设定最小日期为当前日期
    /* ishmsVal: false,*/
    maxDate: '2999-12-31 23:59:59', //最大日期
    choosefun: function (elem, datas) {
        start.maxDate = datas; //将结束日的初始值设定为开始日的最大日期
    }
};


