$(function () {
    $.get("header.html", function (data) {
        $("#header").html(data);
        $(".search_input").val(localStorage.getItem("searchWord"));
    });
    $.get("footer.html", function (data) {
        $("#footer").html(data);
    });
    //登录成功显示登录信息
    $.get("loginController", {"method": "initUser"}, function (data) {
        // alert(data)
        let message;
        if (data == null) {
            message = "<a href='login.html'>点击登录</a>"
        } else {
            message = "欢迎回来!-" + data.username;
        }
        $("#welcome").html(message);
    }, "json")
    //获取导航条数据
    let cid = location.search.split("=")[1];
    $.get("categoryController", {"method": "initCategory"}, function (data) {
        let str = "";

        console.log(data);
        for (let i = 0; i < data.length; i++) {
            if (cid == (i + 1)) {
                str += "<li class='nav-active'><a href='route_list.html?cid=" + data[i].cid + "'>" + data[i].cname + "</a></li>"
            } else {
                str += "<li><a href='route_list.html?cid=" + data[i].cid + "'>" + data[i].cname + "</a></li>"
            }
        }
        $(".nav").html($(".nav").html() + str);

    }, "json")


    // $(document).on("click","#test",function () {
    //     alert("123143214214")
    // })

});