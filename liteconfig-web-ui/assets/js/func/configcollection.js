function configure_header(url, headername){

}
$(function () {

    var current_page = $("#menu > li > ul > li > a[href='configcollection.html']");

    //用户权限管理 激活
    current_page.parent().addClass("active");

    //用户中心 展开
    current_page.parent().parent().addClass("in");

    //用户中心 激活
    current_page.parent().parent().parent().addClass("active");

    $("#title_name").append("<li><span>全部配置集</span></li>")


    //加载配置集合
    loadCollections()


});

function loadCollections() {
    $("#cfg_collections_container").empty();
    username = "root";
    queryParam = {
        "nameLike": "",
        "sort": 1,
        "pageNum": 0,
        "nums": 10
    };

    $.ajax({

        url: 'http://localhost:8301/cfg_coll/list/' + username,//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(queryParam), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("获取配置信息错误。")
        },
        statusCode: {
            200: function (data) {
                $(data['data']).each(function (i, val) {

                    str = "<div class=\"col-lg-4 col-md-6 mt-5\">\n" +
                        "      <div class=\"card card-bordered\">\n" +
                        "          <div class=\"card-body\">\n" +
                        "            <h5 class=\"title\">" + val.cName + "</h5>\n" +
                        "            <p class=\"card-text\">" + (val.isDraft === 1 ? "<b class='badge badge-pill badge-secondary'>草稿版本</b>" : "<b class='badge badge-pill badge-success'>线上版本</b>")+" | <b>修改时间：</b>"+new Date(parseInt(val.updateTime) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ')+"</p>\n" +
                        "            <a href='config_list.html?cid=" + val.id + "' id=\"btn_c_" + val.id + "\" class=\"btn btn-primary\" >配置</a>\n" +
                        "          </div>\n" +
                        "       </div>\n" +
                        "  </div>";

                    $("#cfg_collections_container").append(str)
                });


                // console.log(data)
            }
        }
    })

}


$('#btn_c_1').click(function () {
    layer.open({
        type: 2,
        skin: 'layui-layer-lan',
        title: '配置详情',
        fix: false,
        shadeClose: true,
        maxmin: true,
        area: ['700px', '670px'],
        content: 'assets/html/config_detail.html',

    });
})