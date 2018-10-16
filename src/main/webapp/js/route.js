let cid = location.search.split("=")[1];
let pageCount;
// alert(cid)
//加载路线列表
function load() {
    // let curPage = 1;
    console.log("我执行了")
    $.get("routeController", {"method": "queryRouteByCid", "cid": cid, "curPage": curPage}, function (data) {
        pageCount = data.pageCount;
        page(data, "load");
    }, "json")
}

load();



