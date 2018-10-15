let cid = location.search.split("=")[1];
let curPage = 1;
let pageCount;

// alert(cid)
function load() {

    $.get("routeController", {"method": "queryRouteByCid", "cid": cid, "curPage": curPage}, function (data) {
        pageCount = data.pageCount;
        let str = "";
        for (let i of data.dataList) {
            // alert(data.length);
            str += "<li>\n" +
                "                        <div class=\"img\"><img src=\"" + i.rimage + "\" alt=\"\"></div>\n" +
                "                        <div class=\"text1\">\n" +
                "                            <p>" + i.rname + "</p>\n" +
                "                            <br/>\n" +
                "                            <p>" + i.routeIntroduce + "</p>\n" +
                "                        </div>\n" +
                "                        <div class=\"price\">\n" +
                "                            <p class=\"price_num\">\n" +
                "                                <span>&yen;</span>\n" +
                "                                <span>" + i.price + "</span>\n" +
                "                                <span>起</span>\n" +
                "                            </p>\n" +
                "                            <p><a href=\"route_detail.html?rid="+i.rid+"\">查看详情</a></p>\n" +
                "                        </div>\n" +
                "                    </li>"
        }
        $("#routeList").html(str);
        console.log("当前页为" + data.curPage)
        console.log("数据条数" + data.totalCount)
        console.log("总共页数" + data.pageCount);
        $("#pageCount").html(data.pageCount);
        $("#totalCount").html(data.totalCount);
        //设置分页
        let pagination = "";
        let str2 = "";
        let start = data.curPage - 5;
        let end = data.curPage + 4;
        if (start < 1) {
            start = 1;
            end = 10;
        }
        if (end > data.pageCount) {
            end = data.pageCount;
            start=data.pageCount-9;
        }
        if (data.pageCount<10){
            start=1;
            end=data.pageCount;
        }
        for (let i = start; i <= end; i++) {
            if (data.curPage == i) {
                str2 += "<li class='curPage'><a href=\"#\">" + i + "</a></li>"
            } else {
                // debugger
                str2 += "<li><a href=\"javascript:changeCurPage(" + i + ")\">" + i + "</a></li>"
            }
        }
        pagination += " <ul>\n" +
            "                        <li><a href=\"index.html\">首页</a></li>\n" +
            "                        <li class=\"threeword\" id='prev'><a href=\"javascript:changeCurPage('prev')\">上一页</a></li>\n" +
            str2 +
            "                        <li class=\"threeword\" id='next'><a href=\"javascript:changeCurPage('next');\">下一页</a></li>\n" +
            "                        <li class=\"threeword\"><a href=\"javascript:changeCurPage('last');\">末页</a></li>\n" +
            "                    </ul>"
        $(".pageNum").html(pagination);

    }, "json")
}

load();
$(function () {
    if (curPage == 1) {
        $("#prev a").removeAttr("href");
    }
    if (curPage == pageCount) {
        $("#next a").removeAttr("href");
    }

})

function changeCurPage(page) {
    if (page == "prev") {
        curPage--;
    } else if (page == "next") {
        curPage++;
    } else if (page == "last") {
        curPage = pageCount;
    } else {
        curPage = page;
    }
    // window.location.reload();
    // window.scrollTo(0,0)
    load();
    $.scrollTo("#header",1000);
}