function configure_header(url, headername) {

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

    bind_btn_collection_add()

});

function btn_collection_del(collectionId) {

    $.ajax({

        url: 'http://localhost:8301/cfg_coll/del/'+ collectionId +'?token=' + $.cookie("token"),//接口地址
        type: 'delete',//请求方式
        // data: JSON.stringify(queryParam), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("删除配置失败。")
        },
        statusCode: {
            200: function (data) {
                if (data["code"] == 200){
                    loadCollections()
                }else {
                    Notiflix.Notify.Failure("删除配置失败。原因："+data["message"])
                }
            }
        }
    })
}

function bind_btn_collection_add() {
    $("#btn_collection_add").click(function () {
        layer.open({
            type: 2,
            skin: 'layui-layer-lan',
            title: '添加配置集',
            fix: false,
            shadeClose: true,
            maxmin: true,
            area: ['400px', '400px'],
            content: 'assets/html/add_collection_detail.html',

        });
    })
}

function loadCollections() {
    $("#cfg_collections_container").empty();
    username = "root";
    queryParam = {
        "nameLike": "",
        "sort": -1,
        "pageNum": 0,
        "nums": 10,
        "isDraft":false
    };

    $.ajax({

        url: 'http://localhost:8301/cfg_coll/list?token=' + $.cookie("token"),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(queryParam), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("获取配置信息错误。")
        },
        statusCode: {
            200: function (data) {
                $("#cfg_collections_container").empty();

                $(data['data']).each(function (i, val) {

                    str = "<div class='col-lg-4 col-md-6 mt-5'>" +
                        "      <div class='card card-bordered'>" +
                        "          <div class='card-body'>" +
                        "            <h4 class='title'>" + val.cName + "</h4>\n" +
                        "            <p class='card-text'>" + (val.isDraft === 1 ? "<b class='badge badge-pill badge-secondary'>草稿版本</b>" : "<b class='badge badge-pill badge-success'>线上版本</b>") +
                        " | <b>修改时间：</b>" + new Date(parseInt(val.updateTime) * 1000).toLocaleString().replace(/:\d{1,2}$/, ' ') +
                        " | <b>创建者：</b>"+val.createUsername+"</p>" +
                        "            <a href='config_list.html?cid=" + val.id + "' id='btn_c_" + val.id + "' class='btn btn-primary' >配置</a>" +
                        "            <input class='btn btn-danger' onclick='btn_collection_del(" + val.id + ")' type='button' value='删除'>" +
                        "          </div>\n" +
                        "       </div>\n" +
                        "  </div>";

                    $("#cfg_collections_container").append(str);
                });


                // console.log(data)
            }
        }
    })

}

//添加集合
function add_collection(collectionName) {
    window.parent.layer.closeAll()
    data = {
        "collectionName": collectionName
    };

    $.ajax({

        url: 'http://localhost:8301/cfg_coll/add/' + collectionName + '?token=' + $.cookie("token"),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(data), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("获取配置信息错误。")
        },
        statusCode: {
            200: function (data) {
                Notiflix.Notify.Success("正在跳转创建好的配置集")

                if (data["code"] == 200) {
                    window.parent.location = "http://localhost:63342/konfig/liteconfig-web-ui/config_list.html?cid=" + data["data"]["id"]
                }
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