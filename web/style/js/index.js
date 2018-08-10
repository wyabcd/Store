$(function() {
    var stuList = getStuList();
    //聚焦失焦input
    $('input').eq(0).focus(function () {
        if ($(this).val().length == 0) {
            $(this).parent().next("div").text("支持中文，字母，数字，'-'，'_'的多种组合");
        }
    })
    $('input').eq(1).focus(function () {
        if ($(this).val().length == 0) {
            $(this).parent().next("div").text("建议使用字母、数字和符号两种以上的组合，6-20个字符");
        }
    })
    $('input').eq(2).focus(function () {
        if ($(this).val().length == 0) {
            $(this).parent().next("div").text("请再次输入密码");
        }
    })
    $('input').eq(3).focus(function () {
        if ($(this).val().length == 0) {
            $(this).parent().next("div").text("输入昵称");
        }
    })
    $('input').eq(4).focus(function () {
        if ($(this).val().length == 0) {
            $(this).parent().next("div").text("请使用正确的邮箱格式");
        }
    })
    $('input').eq(5).focus(function () {
        if ($(this).val().length == 0) {
            $(this).parent().next().next("div").text("看不清？点击图片更换验证码");
        }
    })
    //input各种判断
    //用户名：
    $('input').eq(0).blur(function () {
        if ($(this).val().length == 0) {
            $(this).parent().next("div").text("");
            $(this).parent().next("div").css("color", '#ccc');
        } else if ($(this).val().length > 0 && $(this).val().length < 4) {
            $(this).parent().next("div").text("长度只能在4-20个字符之间");
            $(this).parent().next("div").css("color", 'red');
        } else if ($(this).val().length >= 4 && !isNaN($(this).val())) {
            $(this).parent().next("div").text("用户名不能为纯数字");
            $(this).parent().next("div").css("color", 'red');
        } else {
            for (var m = 0; m < stuList.length; m++) {
                if ($(this).val() == stuList[m].name) {
                    $(this).parent().next("div").text("该用户名已被注册");
                    $(this).parent().next("div").css("color", 'red');
                    return;
                }
            }
            $(this).parent().next("div").text("");
        }
    })
    //密码
    $('input').eq(1).blur(function () {
        if ($(this).val().length == 0) {
            $(this).parent().next("div").text("");
            $(this).parent().next("div").css("color", '#ccc');
        } else if ($(this).val().length > 0 && $(this).val().length < 6) {
            $(this).parent().next("div").text("长度只能在6-20个字符之间");
            $(this).parent().next("div").css("color", 'red');
        } else {
            $(this).parent().next("div").text("");
        }
    })
//	确认密码
    $('input').eq(2).blur(function () {
        if ($(this).val().length == 0) {
            $(this).parent().next("div").text("");
            $(this).parent().next("div").css("color", '#ccc');
        } else if ($(this).val() != $('input').eq(1).val()) {
            $(this).parent().next("div").text("两次密码不匹配");
            $(this).parent().next("div").css("color", 'red');
        } else {
            $(this).parent().next("div").text("");
        }
    })
//邮箱
    $('input').eq(4).blur(function () {
        var email = document.getElementsByName("email")[0].value;
        if (email != "" && !/^\w+@\w+(\.\w+)+$/.test(email)) {
            $(this).parent().next("div").text("邮箱格式不正确");
            $(this).parent().next("div").css("color", 'red');
        }
    })
// 	验证码
    $('input').eq(5).blur(function () {
        document.getElementById("valistr_msg").innerHTML = "";
        var valistr = document.getElementsByName("valistr")[0].value;
        if (valistr == null || valistr == "") {
            document.getElementById("valistr_msg").innerHTML = "<font color='red'>" + msg + "</font>";
            return false;
        }
        return true;
    })

//	提交按钮
    $("#submit_btn").click(function (e) {
        for (var j = 0; j < 5; j++) {
            if ($('input').eq(j).val().length == 0) {
                $('input').eq(j).focus();
                if (j == 4) {
                    $('input').eq(j).parent().next().next("div").text("此处不能为空");
                    $('input').eq(j).parent().next().next("div").css("color", 'red');
                    e.preventDefault();
                    return;
                }
                $('input').eq(j).parent().next(".tips").text("此处不能为空");
                $('input').eq(j).parent().next(".tips").css("color", 'red');
                e.preventDefault();
                return;
            }
        }

    })
    //  建立构造函数，构造学生信息模板
    function Student(name,password,tel,id){
        this.name = name;
        this.password = password;
        this.id = id;
    }
//	获取之前所有已经注册的用户集合
    function getStuList(){
        var list = localStorage.getItem('stuList');
        if(list != null){
            return JSON.parse(list);
        }else{
            return new Array();
        }
    }
})

