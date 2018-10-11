$(function () {
    $(".submit").on("click",function () {
        $("#registerForm").submit(function () {
            return checkUserName();
        })
    })
    $("#username").on("change", function () {
        checkUserName();
    })
    function checkUserName() {
        //前台验证用户名是否符合要求
        let pattern = /^\w{5,7}$/;
        if (!pattern.test($("#username").val())) {
            $(".message").html("该用户名不合法,请输入5,7位包含字母或数字");
            $("#username").css({"border": "1px solid red"});
            // $("#username").focus();
        } else {
            $.get("registerController", {"username": $("#username").val(), "method": "checkUserName"}, function (data) {
                alert(data);
                if (!data.flag) {
                    // alert(data.message);
                    $(".message").html("");
                    $("#username").css({"border": "1px solid green"});
                    return true;
                }else {
                    alert(data.flag)
                    $(".message").html(data.message);
                }
            }, "json")
        }
        return false;
    }
})