$(function () {
    $.get("favoriteController",{"method":"queryMyFavorite"},function (data) {
        console.log(data);
        let str="";
        for (let i of data){
            str+="<div class=\"col-md-3\">\n" +
                "                            <a href=\"route_detail.html?rid="+i.rid+"\">\n" +
                "                                <img src=\""+i.rimage+"\" alt=\"\">\n" +
                "                                <div class=\"has_border\">\n" +
                "                                    <h3>"+i.rname+"</h3>\n" +
                "                                    <div class=\"price\">网付价<em>￥</em><strong>"+i.price+"</strong><em>起</em></div>\n" +
                "                                </div>\n" +
                "                            </a>\n" +
                "                        </div>"
        }
        $("#favorite").html(str);
    },"json")

})