$(".submit").on("click", function () {
    $("#registerForm").submit(function () {
        // alert(checkUserName())
        // alert(checkEmail())
        // alert(checkCode())
        return checkUserName() && checkEmail() && checkCode();
    })
})
$("#username").on("blur", function () {
    checkUserName();
})
$("#password").on("blur", function () {
    checkPassWord();
})
$("#email").on("blur", function () {
    checkEmail();
})
$("#check").on("blur", function () {
    checkCode();
})

function checkUserName() {
    //前台验证用户名是否符合要求
    let pattern = /^\w{5,7}$/;
    let res = false;
    if (!pattern.test($("#username").val())) {
        $(".message").html("该用户名不合法,请输入5,7位包含字母或数字");
        $("#username").css({"border": "1px solid red"});
        // $("#username").focus();
    } else {
        $.ajax({
            url: "registerController",
            type: "GET",
            async: false,
            data: {"username": $("#username").val(), "method": "checkUserName"},
            dataType: "json",
            success: function (data) {
                // alert(data.flag);
                if (data.flag) {
                    // alert(data.message);
                    $(".message").html("");
                    $("#username").css({"border": "1px solid green"});
                    res = true;
                } else {
                    // alert(data.message)
                    $("#username").css({"border": "1px solid red"});
                    $(".message").html(data.message);
                }
            }
        });
    }
    return res;
}

function checkPassWord() {
    let passPattern = /^\w{3,5}$/;
    if (!passPattern.test($("#password").val())) {
        $(".message").html("该密码不合法,请输入3,5位包含字母或数字");
        $("#password").css({"border": "1px solid red"});
    } else {
        $(".message").html("");
        $("#password").css({"border": "1px solid green"});
        return true;
    }
    return false;
}

function checkEmail() {
    let pattern = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    let res=false;
    if (!pattern.test($("#email").val())) {
        $(".message").html("该邮箱不合法,请新输入");
        $("#email").css({"border": "1px solid red"});
    } else {
        $.ajax({
            url: "registerController",
            type: "GET",
            async: false,
            data: {"email": $("#email").val(), "method": "checkEmail"},
            dataType: "json",
            success: function (data) {
                // alert(data);
                if (data.flag) {
                    // alert(data.message);
                    $(".message").html("");
                    $("#email").css({"border": "1px solid green"});
                    res = true;
                } else {
                    // alert(data.message)
                    $(".message").html(data.message);
                    $("#email").css({"border": "1px solid red"});
                }
            }
        })
    }
    return res;
}

function checkCode() {
    let res = false;
    if ($("#check").val().length == 4) {
        // alert("checkcode equal 4")
        $.ajax({
            url: "checkCodeController",
            type: "GET",
            async: false,
            dataType: "json",
            data: {"code": $("#check").val(), "method": "checkCode"},
            success: function (data) {
                // alert(data.flag);
                if (data.flag) {
                    $(".message").html("");
                    $("#check").css({"border": "1px solid green"});
                    res = true;
                } else {
                    res = false;
                }
            }
        })
    }else {
        $(".message").html("验证码错误")
        $("#check").css({"border": "1px solid red"});
        res=false;
    }
    return res;
}
