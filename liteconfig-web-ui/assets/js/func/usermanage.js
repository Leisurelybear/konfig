$(function () {

    var current_page = $("#menu > li > ul > li > a[href='usermanage.html']");

    //用户权限管理 激活
    current_page.parent().addClass("active");

    //用户中心 展开
    current_page.parent().parent().addClass("in");

    //用户中心 激活
    current_page.parent().parent().parent().addClass("active");


    $("#title_name").append("<span>用户信息管理</span></li>")

    list_users(1, 5, "", "");

    $("#user_name").bind('input propertychange', function () {
        username_status = false;
        console.log($("#user_name").val())
        if ($("#user_name").val() === "") {
            $("#info_username").html("用户名不能为空！")
            $("#info_username").css('color', 'red')
            username_status = false;
            return
        }

        data = {
            "username": $("#user_name").val(),
            "password": "",
            "email": ""
        };

        $.ajax({
            url: AUTH_HOST + '/admin/dup?token=' + $.cookie('token'),//接口地址
            type: 'post',//请求方式
            data: JSON.stringify(data), //传输的数据
            contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
            dataType: 'text json', //相反
            error: function (response) {
                Notiflix.Notify.Failure("网络错误")
            },
            statusCode: {
                200: function (data) {
                    if (data["code"] === 200) {
                        $("#info_username").html("用户名可以使用！")
                        $("#info_username").css('color', 'green')
                        username_status = true;
                    } else {
                        $("#info_username").html("用户名重复！")
                        $("#info_username").css('color', 'red')
                        username_status = false;
                    }
                }
            }
        })
    })


    $("#re-password").bind('input propertychange', function () {
        password_status = false;
        console.log($("#user_name").val())
        if ($("#re-password").val() === "") {
            $("#info_password").html("密码不能为空！")
            $("#info_password").css('color', 'red')
            password_status = false;
            return;
        }
        if ($("#re-password").val() !== $("#password").val()) {
            $("#info_password").html("两次密码不相同！")
            $("#info_password").css('color', 'red')
            password_status = false;
            return
        } else {
            $("#info_password").html("密码可以使用！")
            $("#info_password").css('color', 'green')
            password_status = true;
        }

    })

    //添加用户
    $("#btn_user_add").click(function () {

        if (typeof (password_status) == "undefined" || typeof (username_status) == "undefined" || !password_status || !username_status) {
            Notiflix.Notify.Failure("添加失败，用户名或密码检测未通过！")
            return
        }

        data = {
            "username": $("#user_name").val(),
            "password": $("#password").val(),
            "email": $("#email").val()
        };

        $.ajax({
            url: AUTH_HOST + '/admin/register?token=' + $.cookie('token'),//接口地址
            type: 'post',//请求方式
            data: JSON.stringify(data), //传输的数据
            contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
            dataType: 'text json', //相反
            error: function (response) {
                Notiflix.Notify.Failure("网络错误")
            },
            statusCode: {
                200: function (data) {
                    if (data["code"] === 200) {
                        Notiflix.Notify.Success("添加成功！用户名为：" + $("#user_name").val())
                        username_status = false;
                        password_status = false;
                    } else {
                        Notiflix.Notify.Failure("用户名重复！")
                    }
                }
            }
        })
    })

});


//查询用户信息
function userDetail(user_id) {
    alert(user_id)
}

function updateUserInfo(userid) {

    data = {
        "accountId": userid,
        "password": $("#password-" + userid).val()
    };

    if (data.accountId <= 0){
        return
    }
    if (data.password === ""){
        Notiflix.Notify.Failure("新密码不能为空！")
        return

    }
    if (data.password.length < 6){
        Notiflix.Notify.Failure("新密码长度必须大于6")
        return;
    }

    console.log(data);

    $.ajax({
        url: AUTH_HOST + '/admin/change_passwd?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(data), //传输的数据
        dataType: 'text json', //相反
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        error: function (response) {
            Notiflix.Notify.Failure("网络错误")
        },
        statusCode: {
            200: function (data) {
                if (data["code"] === 200){
                    Notiflix.Notify.Success("操作成功")
                }else {
                    Notiflix.Notify.Failure("操作失败：" + data["message"])

                }
            }
        }
    })

}


//展示用户列表
function list_users(pageNum, pageSize, nameLike, emailLike) {
    console.log("list_users: ", pageNum, pageSize, nameLike, emailLike)
    if (nameLike === "") {
        pageSize = 5
    }
    data = {
        "usernameLike": nameLike,
        "emailLike": emailLike,
        "pageNumber": pageNum,
        "pageSize": pageSize,
        "sort": -1
    };
    $.ajax({
        url: AUTH_HOST + '/admin/queryall?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(data), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("网络错误")
        },
        statusCode: {
            200: function (data) {
                $("#tb_usermanager_list").empty();
                if (data["code"] === 200) {
                    userList = data['data']['userList'];

                    $(userList).each(function (i, user) {
                        row = "<tr>" +
                            "     <th scope='row'>" + (i + ((data['data']['pageNum'] - 1) * data['data']['pageSize']) + 1) + "</th>" +
                            "     <td>" + user.id + "</td>" +
                            "     <td>" + user.username + "</td>" +
                            "     <td>" + user.email + "</td>" +
                            "     <td>" +
                            "           <input class='btn btn-info' type='button' value='编辑' data-toggle='modal' data-target='#config_add_form'>" +
                            "                <div class='modal fade' id='config_add_form' style='display: none;' aria-hidden='true'>" +
                            "                    <div class='modal-dialog modal-dialog-centered' role='document'>" +
                            "                        <div class='modal-content'>" +
                            "                            <div class='modal-header'>" +
                            "                                <h5 class='modal-title'>编辑</h5>" +
                            "                                <button type='button' class='close' data-dismiss='modal'><span>×</span></button>" +
                            "                            </div>" +
                            "                            <div class='modal-body'>" +
                            "                               <form id='user-info-" + user.id + "'>" +
                            "                                <label for='nickname'>用户名</label>" +
                            "                                <input disabled type='text' value='" + user.username + "' class='form-control' name='nickname' placeholder='用户名'>" +
                            "" +
                            "                                <label for='email'>邮箱</label>" +
                            "                                <input disabled type='text' class='form-control' value='" + user.email + "' name='email' placeholder='邮箱'>" +
                            "" +
                            "                                <label for='password-" + user.id + "'>密码</label>" +
                            "                                <input type='text' class='form-control' id='password-" + user.id + "' name='password' placeholder='修改密码'>" +
                            // "                                <textarea class='form-control' cols='20' name='extra' name='password' placeholder='修改密码' required=''></textarea>" +
                            "                               </form>" +
                            "" +
                            "                            </div>" +
                            "                            <div class='modal-footer'>" +
                            "                                <button type='button' class='btn btn-secondary' data-dismiss='modal'>取消</button>" +
                            // "                                <button type='button' class='btn btn-warning' onclick='userDetail(" + user.id + ")' >点击查询</button>" +
                            "                                <button type='button' class='btn btn-primary'  data-dismiss='modal' onclick='updateUserInfo(" + user.id + ")' id='btn_config_add'>保存</button>" +
                            "                            </div>" +
                            "                        </div>" +
                            "                    </div>" +
                            "                </div>" +
                            "     </td>" +
                            "</tr>";
                        $("#tb_usermanager_list").append(row)
                    });

                    //分页
                    pgs = "";
                    if (pageNum === 1) { //是不是第一页
                        pgs += "<li class='paginate_button page-item previous disabled'" +
                            "    id='dataTable_previous'>" +
                            "    <a href='#' aria-controls='dataTable' data-dt-idx='0' tabindex='0'" +
                            "       class='page-link'>Previous</a>" +
                            "</li>"
                    } else {
                        pgs += "<li class='paginate_button page-item previous'" +
                            "    id='dataTable_previous'>" +
                            "    <button onclick='list_users(" + (pageNum - 1) + ", " + pageSize + ", \"\", \"\")' aria-controls='dataTable' data-dt-idx='0' tabindex='0'" +
                            "       class='page-link'>Previous</button>" +
                            "</li>"
                    }

                    //中间的数字
                    for (let i = 1; i < (data['data']["count"] / pageSize) + 1; i++) {
                        if (i === pageNum) {
                            pgs += "<li class='paginate_button page-item active'>" +
                                "    <button onclick='list_users(" + i + ", " + pageSize + ", \"\", \"\")'" +
                                "       aria-controls='dataTable'" +
                                "       data-dt-idx='1' tabindex='0'" +
                                "       class='page-link'>" + i + "</button>" +
                                "</li>";
                        } else {
                            pgs += "<li class='paginate_button page-item'>" +
                                "    <button onclick='list_users(" + i + ", " + pageSize + ", \"\", \"\")'" +
                                "       aria-controls='dataTable'" +
                                "       data-dt-idx='1' tabindex='0'" +
                                "       class='page-link'>" + i + "</button>" +
                                "</li>";
                        }
                    }

                    if (pageNum >= ((data['data']["count"] / pageSize))) {
                        pgs += "<li class='paginate_button page-item next disabled' id='dataTable_next'>" +
                            "    <button " +
                            "       aria-controls='dataTable'" +
                            "       data-dt-idx='3'" +
                            "       tabindex='0'" +
                            "       class='page-link'>Next</button>" +
                            "</li>"
                    } else {
                        pgs += "<li class='paginate_button page-item next' id='dataTable_next'>" +
                            "    <button onclick='list_users(" + (pageNum + 1) + ", " + pageSize + ", \"\", \"\")'" +
                            "       aria-controls='dataTable'" +
                            "       data-dt-idx='3'" +
                            "       tabindex='0'" +
                            "       class='page-link'>Next</button>" +
                            "</li>"
                    }

                    $("#um_pagination").html(pgs)


                } else {
                    Notiflix.Notify.Failure(data["message"])

                }

            }
        }
    });


}