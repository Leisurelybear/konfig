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
    loadCollections(0)

    bind_btn_collection_add()

});

function btn_collection_del(collectionId) {

    $.ajax({

        url: 'http://' + document.domain + ':8301/cfg_coll/del/' + collectionId + '?token=' + $.cookie("token"),//接口地址
        type: 'delete',//请求方式
        // data: JSON.stringify(queryParam), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("删除配置失败。")
        },
        statusCode: {
            200: function (data) {
                if (data["code"] == 200) {
                    loadCollections(0)
                } else {
                    Notiflix.Notify.Failure("删除配置失败。原因：" + data["message"])
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

/**
 * 加载配置集合
 * @param condition 条件：0=全部配置；1=草稿配置；2=我創建的；3=我創建的草稿配置
 */
function loadCollectionsFilter(condition) {

    let tmp = $("#collection_filter").html()

    switch (condition) {
        case 0:
            $("#collection_filter").html("全部配置");
            break;
        case 1:
            $("#collection_filter").html("只看草稿");
            break;
        case 2:
            $("#collection_filter").html("只看我创建");
            break;
        case 3:
            $("#collection_filter").html("只看我创建的草稿");
            break;
    }

    if (tmp === $("#collection_filter").html()) {
        return
    }

    loadCollections(condition);

}


/**
 * 加载配置集合
 * @param condition 条件：0=全部配置；1=草稿配置；2=我創建的；3=我創建的草稿配置
 */
function loadCollections(condition) {
    $("#cfg_collections_container").empty();
    queryParam = {
        "nameLike": "",
        "sort": -1,
        "pageNum": 0,
        "nums": 10,
        "isDraft": false,
        "createUsername": "",
    };

    if (condition === 1 || condition === 3) {
        queryParam["isDraft"] = true
    }
    if (condition === 2 || condition === 3) {
        queryParam["createUsername"] = $.cookie("username");
    }

    $.ajax({

        url: 'http://' + document.domain + ':8301/cfg_coll/list?token=' + $.cookie("token"),//接口地址
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
                        "            <h4 class='title'>" + "<span class='badge badge-secondary'>ID:" + val.id + "</span>&nbsp;" + val.cName + "</h4>\n" +
                        "            <p class='card-text'>" + (val.isDraft === 1 ? "<b class='badge badge-pill badge-secondary'>草稿版本</b>" : "<b class='badge badge-pill badge-success'>线上版本</b>") +
                        " | <b>修改时间：</b>" + timeStamp2String(val.updateTime) +
                        " | <b>创建者：</b>" + val.createUsername + "</p>" +
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

        url: 'http://' + document.domain + ':8301/cfg_coll/add/' + collectionName + '?token=' + $.cookie("token"),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(data), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("网络错误")
        },
        statusCode: {
            200: function (data) {

                if (data["code"] == 200) {
                    Notiflix.Notify.Success("正在跳转创建好的配置集")
                    window.parent.location = "http://" + document.domain + "/konfig/liteconfig-web-ui/config_list.html?cid=" + data["data"]["id"]
                } else {
                    Notiflix.Notify.Failure("操作失败：" + data['message'])

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