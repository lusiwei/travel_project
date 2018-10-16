function searchRoute() {
    let pageCount;
    // console.log(keyword)
    let keyword=$(".search_input").val();
    localStorage.setItem("searchWord",keyword)
    console.log()
    $.get("searchController", {
        "method": "search",
        "searchWord":keyword,
        "curPage": curPage
    }, function (data) {
        console.log(data);
        pageCount = data.pageCount;
        page(data, "searchRoute");
    }, "json")
}
