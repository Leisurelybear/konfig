document.write("<script language=javascript src='assets/js/common/util.js'></script>");
document.write("<script language=javascript src='assets/js/vendor/notiflix-aio-2.7.0.min.js'></script>");

$(function () {

    //验证用户个人信息
    getInfo();

});

function timeStamp2String(time){
    time *= 1000
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}


//根据token得到用户信息
function getInfo() {
    $.ajax({
        url: AUTH_HOST + '/admin/info',
        // url: 'http://KONFIG-AUTHENTICATION/admin/info',
        type: 'get',
        dataType: 'text json',
        beforeSend: function (request) {
            if (getCookie('token') === null) {
                Notiflix.Notify.Warning("token失效！请重新登录！");
                window.location = 'login.html';
            } else {
                request.setRequestHeader("Authorization", "Bearer " + getCookie('token'));
            }
        },
        error: function () {
            Notiflix.Notify.Failure("网络连接错误！请联系管理员。");
            window.location = 'login.html';
        },
        statusCode: {
            200: function (response) {
                if (response['code'] !== 200) {
                    window.location = 'login.html';
                    Notiflix.notify.Failure(response['data']);

                } else {

                    $.cookie('username', response['data']['username'], { expires: 30 });
                    $.cookie('accountId', response['data']['accountId'], { expires: 30 });
                    $.cookie('email', response['data']['email'], { expires: 30 });

                    var nickname = response['data']['nickname'];
                    var username = response['data']['username'];
                    var avatar_url = response['data']['imgUrl'];
                    $("#header-username").html(nickname !== null ? nickname : username);
                    if (avatar_url !== null) {
                        $("#header_avatar").attr('src', avatar_url);
                    }
                }
            },
            404: function () {
                window.location = '404.html';
            }

        }
    });


    //获得头像
    // $.ajax({
    //     url: 'https://api.btstu.cn/sjtx/api.php?lx=c1&format=images',
    //     type: 'get',
    //     dataType: 'text json',
    //     error:function(data){
    //         console.log(data)
    //     }
    //
    // })

}