$(function () {
    $("#checkcode").on("blur", function () {
        checkCode();
    })

    function checkCode() {
        let res = false;
        if ($("#checkcode").val().length == 4) {
            $.ajax({
                url: "checkCodeController",
                type: "GET",
                async: false,
                dataType: "json",
                data: {"code": $("#checkcode").val(), "method": "checkCode"},
                success: function (data) {
                    // alert($("#checkcode").val());
                    // alert(data.code)
                    if (data.code == "011") {
                        $("#errorMsg").html("");
                        $("#checkcode").css({"border": "1px solid green"});
                        res = true;
                    } else {
                        res = false;
                        $("#checkcode").css({"border": "1px solid red"});
                    }
                }
            })
        } else {
            $("#errorMsg").html("验证码错误")
            $("#checkcode").css({"border": "1px solid red"});
            res = false;
        }
        return res;
    }
    // $("#login").prop("disabled",true);
    // if (checkCode()) {
    //     $("#login").prop("disabled",false);
    // }
    $("#login").on("click", function () {
        $.post("loginController", {
            "username": $("#username").val(),
            "password": $("#password").val(),
            "method": "login"
        }, function (data) {
            // alert(data.code)
            if (data.code == "005"&&checkCode()) {
                location.href = "index.html";
            } else {
                $("#errorMsg").html(data.message);
            }
        }, "json")
    });

})