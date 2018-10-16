let rid = location.search.split("=")[1];
$.get("routeDetailController", {"method": "queryRouteDetailByRid", "rid": rid}, function (data) {
    console.log(data);
    //设置分类
    $("#category").html(data.category.cname);
    $("#category").attr("href", "route_list.html?cid=" + data.cid)
    //设置routeIntroduce
    $("#introduce").html(data.rname);
    //设置
    $(".rname").html("[尾单特卖] " + data.rname);
    $(".routeIntroduce").html(data.routeIntroduce)
    $("#price").html("￥" + data.price + " ")
    //设置商家信息
    $("#sname").html("公司名称: " + data.seller.sname);
    $("#consphone").html("联系电话: " + data.seller.consphone);
    $("#address").html("地址: " + data.seller.address);

    let count = 0;
    $("#favorite").on("click", function () {
        $.get("favoriteController", {"rid": rid, "method": "addFavorite"}, function (data) {
            console.log(data);
            if (data.code == "favorite_success") {
                console.log("收藏成功");
                $("#favorite").attr("disabled", true).addClass("already").html("已收藏");
                $("#favorite").off();

                $("#favorited").html("已收藏" + ++count + "次");

            } else {
                alert("请先登录")
                location.href="login.html";
            }
        }, "json");
    })
    if (data.favorite) {
        $("#favorite").attr("disabled", true).addClass("already").html("已收藏");
        $("#favorite").off();
    }else {

    }
    $("#favorited").html("已收藏" + data.count + "次");


    let strImg = "";
    let str1 = " <dd>\n" +
        "                    <a class=\"up_img up_img_disable\"></a>\n";
    for (let img of data.routeImgList) {
        strImg +=
            "                    <a title=\"\" class=\"little_img cur_img\" data-bigpic=\"" + img.bigPic + "\">\n" +
            "                        <img src=\"" + img.smallPic + "\">\n" +
            "                    </a>\n";
    }
    let str2 = "   <a class=\"down_img down_img_disable\" style=\"margin-bottom: 0;\"></a>\n" +
        "                </dd>"
    strImg = str1 + strImg + str2;
    $(".prosum_left").append(strImg);
}, "json")
