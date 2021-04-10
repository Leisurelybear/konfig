$(function () {

    var current_page = $("#menu > li > ul > li > a[href='usermanage.html']");

    //用户权限管理 激活
    current_page.parent().addClass("active");

    //用户中心 展开
    current_page.parent().parent().addClass("in");

    //用户中心 激活
    current_page.parent().parent().parent().addClass("active");


    $("#title_name").append("<span>用户权限管理</span></li>")
});