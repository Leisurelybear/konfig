$(function () {
    var current_page = $("#menu > li > ul > li > a[href='myapplication.html']");

    //用户权限管理 激活
    current_page.parent().addClass("active");

    //用户中心 展开
    current_page.parent().parent().addClass("in");

    //用户中心 激活
    current_page.parent().parent().parent().addClass("active");

    $("#title_name").append("<span>我的申请</span></li>")

    list_users(1, 5, "", "");
});

//通过id获取用户名
function fillUsername(ids) {
    if (ids.length === 0) {
        return
    }
    $.ajax({
        url: AUTH_HOST + '/admin/user/list_by_aids?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(ids), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("获取用户信息错误")
        },
        statusCode: {
            200: function (data) {
                for (let id in data) {
                    if (data["code"] === 200) {
                        $(data['data']).each(function (i, val) {
                            usernameTbName = "handle_" + val.id;
                            console.log($("[name=" + usernameTbName + "]"))
                            $("[name=" + usernameTbName + "]").html(val.username)
                        })
                    }
                }
            }
        }
    })


}

//展示用户列表
function list_users(pageNum, pageSize, nameLike, emailLike) {
    console.log("list_users: ", pageNum, pageSize, nameLike, emailLike)
    data = {
        "usernameLike": nameLike,
        "emailLike": emailLike,
        "pageNumber": pageNum,
        "pageSize": pageSize,
        "sort": 1
    };

    $.ajax({
        url: SERVICE_HOST + '/audit/my_list?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: null, //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("获取用户信息错误")
        },
        statusCode: {
            200: function (data) {
                let ids = []
                $("#tb_myapplication_list").empty();
                if (data["code"] === 200) {
                    myApplicationList = data['data'];
                    $(myApplicationList).each(function (i, val) {
                        status = "";
                        switch (val.status) {
                            case 0:
                                status = "<span style='color: deepskyblue'>待处理</span>";
                                break;
                            case 1:
                                status = "<span style='color: green'>通过</span>";
                                break;
                            case 2:
                                status = "<span style='color: red'>已驳回</span>";
                                break;
                            default:
                                status = "<span style='color: gray'>已撤销</span>";
                        }

                        str = "<tr>\n" +
                            "    <th scope=\"row\">" + (i + 1) + "</th>\n" +
                            "     <td>" + val.cfgCollectionId + "</td>\n" +
                            "     <td>" + status + "</td>\n" +
                            "     <td name='handle_" + val.reviewerAid + "'>" + val.reviewerAid + "</td>\n" +
                            "     <td>" + val.content + "</td>\n" +
                            // "     <td><input type=\"button\" value=\"撤销\" class=\"btn btn-dark\"></td>\n" +
                            "  </tr>\n"
                        $("#tb_myapplication_list").append(str);
                        ids.push(val.reviewerAid)
                    })
                    fillUsername(ids);

                } else {
                    Notiflix.Notify.Failure(data["message"])
                }
            }

        }
    });

}