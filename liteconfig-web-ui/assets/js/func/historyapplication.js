$(function () {

    var current_page = $("#menu > li > ul > li > a[href='historyapplication.html']");

    //用户权限管理 激活
    current_page.parent().addClass("active");

    //用户中心 展开
    current_page.parent().parent().addClass("in");

    //用户中心 激活
    current_page.parent().parent().parent().addClass("active");


    $("#title_name").append("<span>审批记录</span></li>")

    list();
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
                            usernameTbName = "applicant_" + val.id;
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
function list() {


    $.ajax({
        url: SERVICE_HOST + '/audit/history_list?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: null, //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("获取用户信息错误")
        },
        statusCode: {
            200: function (data) {
                $("#tb_undoapplication_list").empty();
                if (data["code"] === 200) {
                    myApplicationList = data['data'];
                    var ids = [];
                    $(myApplicationList).each(function (i, val) {

                        str = "<tr>\n" +
                            "    <th scope=\"row\">" + (i + 1) + "</th>\n" +
                            "     <td>" + val.cfgCollectionId + "</td>\n" +
                            "     <td>" + val.content + "</td>\n" +
                            "     <td name='applicant_" + val.applicantAid + "'>" + val.applicantAid + "</td>\n" +
                            "     <td>" + new Date(parseInt(val.handleTime) * 1000).toLocaleString().replace(/:\d{1,2}$/, ' ') + "</td>\n" +
                            "     <td>" + ((val.status === 1) ? "同意" : "驳回") + "</td>\n" +
                            "  </tr>\n"
                        $("#tb_undoapplication_list").append(str);
                        ids.push(val.applicantAid)
                    });

                    fillUsername(ids);

                } else {
                    Notiflix.Notify.Failure(data["message"])

                }

            }
        }
    });


}

function handle(collectionId, auditId, isApproved) {
    console.log(collectionId, auditId, isApproved);
    data = {
        "auditId": auditId,
        "collectionId": collectionId,
        "isApproved": isApproved
    };
    $.ajax({
        url: SERVICE_HOST + '/audit/handle?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(data), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("获取用户信息错误")
        },
        statusCode: {
            200: function (data) {
                if (data["code"] === 200) {
                    Notiflix.Notify.Success("处理成功！")
                    list()
                } else {
                    Notiflix.Notify.Failure(data["message"])
                }
            }
        }
    })

}