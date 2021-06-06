document.write("<script language=javascript src='assets/js/common/util.js'></script>");
document.write("<script language=javascript src='assets/js/vendor/notiflix-aio-2.7.0.min.js'></script>");


$(function () {


    checkToken();

    $("#form_submit").click(function () {
        var login_form = {
            'username': $("#ipt_username").val(),
            'password': $("#ipt_password").val(),
        };

        $.ajax({
            url: AUTH_HOST + '/admin/login',//接口地址
            type: 'post',//请求方式
            data: JSON.stringify(login_form), //传输的数据
            contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
            dataType: 'text json', //相反
            error: function (response) {
                Notiflix.Notify.Failure("网络连接错误！请联系管理员。")
            },
            statusCode: {
                200: function (response) {
                    if (response['code'] === 200) {
                        Notiflix.Notify.Success("登陆成功！正在跳转页面...")
                        var token = response['data']['token'];
                        console.log(token);
                        if (token) {
                            $.ajaxSetup({ //发送请求前触发
                                beforeSend: function (xhr) { //可以设置自定义标头
                                    xhr.setRequestHeader('Authorization', "Bearer " + token);
                                }
                            });
                            sessionStorage.setItem('Authorization', "Bearer " + token);
                            setCookie('token', token, 7);
                            window.location = "index.html"
                        } else {
                            var host_url = window.location.host;
                            window.location.replace("http://" + host_url);
                        }
                        console.log(response)
                    } else {
                        Notiflix.Notify.Failure(response['code'] + " - " + response['message'])
                    }

                }
            }

        })
    })

});


function checkToken() {
    $.ajax({
        url: AUTH_HOST + '/admin/info',
        type: 'get',
        dataType: 'text json',
        beforeSend: function (request) {
            if (getCookie('token') === null) {
                console.log("token失效")
            } else {
                request.setRequestHeader("Authorization", "Bearer " + getCookie('token'));
            }
        },
        error: function () {
            Notiflix.Notify.Failure("网络连接错误！请联系管理员。");
        },
        statusCode: {
            200: function (response) {
                if (response['code'] !== 200) {
                    Notiflix.notify.Failure(response['data']);
                } else {
                    window.location = "index.html"
                }
            },
            404: function () {
                window.location = '404.html';
            }

        }
    })

}