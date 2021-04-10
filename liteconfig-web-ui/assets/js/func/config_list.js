$(function () {

    var current_page = $("#menu > li > ul > li > a[href='usermanage.html']");

    //用户权限管理 激活
    current_page.parent().addClass("active");

    //用户中心 展开
    current_page.parent().parent().addClass("in");

    //用户中心 激活
    current_page.parent().parent().parent().addClass("active");


    $("#title_name").append("<li><a href='configcollection.html'>全部配置集</a></li>")
    $("#title_name").append("<li><span>详情</span></li>")

    var cid = window.location.search.substr(1).match("(^|&)cid=([^&]*)(&|$)");
    console.log(cid);
    if (cid === null || cid.length < 3 || cid[2] === ""
    ) {
        return
    }
    list_configs(cid[2])

});

function save_config(collectionId, configId) {

    username = $.cookie('username');
    accountId = parseInt($.cookie('accountId'));
    formdata = $("#cfg_fm_" + configId).serializeArray();
    data = {
        'username': username,
        'accountId': accountId,
        'id': configId,
        'collectionId': collectionId,
        'cfgName': formdata[0]['value'],
        'cfgKey': formdata[1]['value'],
        'cfgValue': formdata[2]['value']
    };

    console.log(data)

    $.ajax({
        url: 'http://localhost:8301/config/update?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(queryParam), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("获取配置信息错误。")
        },
        statusCode: {}
    })
}

function list_configs(collectionId) {

    queryParam = {
        "collectionIds": [collectionId],
        "nameLike": "",
        "keyLike": "",
        "sort": 1
    };
    var token = "";
    $.ajax({
        url: 'http://localhost:8301/config/list?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(queryParam), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("获取配置信息错误。")
        },
        statusCode: {
            200: function (data) {
                $("#config_list").empty();
                console.log(data["data"]["confs"]);
                var cfglistitem = "";
                $(data["data"]["confs"]).each(function (i, val) {
                    // cfglistitem = val['cfgKey'];
                    cfglistitem = "<div class='col-lg-6 mt-5'>" +
                        "                    <div class='card'>" +
                        "                        <div class='card-body'>" +
                        "                            <h4 class='header-title'><span class='badge badge-pill badge-info'>" + (i + 1) + "</span>&nbsp;&nbsp;" + val['cfgName'] + "</h4>" +
                        "                            <p><b>KEY:</b> " + val['cfgKey'] + "</p>" +
                        "                            <p><b>VALUE:</b> " + ((val['cfgValue'] === null) ? val['cfgValue'] : val['cfgValue'].substr(0, 40)) + " ...</p>" +
                        "                            <p><b>修改时间:</b> " + new Date(parseInt(val['updateTime']) * 1000).toLocaleString().replace(/:\d{1,2}$/, ' ') + "</p>" +
                        "                            <p><b>修改者:</b> " + val['updateUsername'] + "</p>" +
                        "                            <!-- Large modal -->" +
                        "                            <button type='button' class='btn btn-primary btn-flat btn-lg' data-toggle='modal'" +
                        "                                    data-target='.fm-data-" + val['id'] + "'>修改" +
                        "                            </button>" +
                        "" +
                        "" +
                        "                            <div class='modal fade fm-data-" + val['id'] + "'>" +
                        "                                <div class='modal-dialog modal-lg'>" +
                        "                                    <div class='modal-content'>" +
                        "                                        <div class='modal-header'>" +
                        "                                            <h5 class='modal-title'>详细信息</h5>" +
                        "                                            <button type='button' class='close' data-dismiss='modal'>" +
                        "                                                <span>&times;</span></button>" +
                        "                                        </div>" +
                        "                                        <div class='modal-body'>" +
                        "" +
                        "                                        <form class='needs-validation' id='cfg_fm_" + val['id'] + "' novalidate=''>" +
                        "                                            <div class='form-row'>" +
                        "                                                <div class='col-md-4 mb-3 disabled'>" +
                        "                                                    <label for='cfg_id'>配置id</label>" +
                        "                                                    <input disabled type='text' class='form-control' name='id' placeholder='' value='" + val['id'] + "' required=''>" +
                        "                                                </div>" +
                        "                                                <div class='col-md-4 mb-3'>" +
                        "                                                    <label for='validationCustom02'>配置名</label>" +
                        "                                                    <input type='text' class='form-control' name='cfgName' placeholder='配置名' value='" + val['cfgName'] + "' required=''>" +
                        "                                                    <div class='valid-feedback'>" +
                        "                                                        Looks good!" +
                        "                                                    </div>" +
                        "                                                </div>" +
                        "                                                <div class='col-md-4 mb-3'>" +
                        "                                                    <label for='cfg_cfgKey'>Key</label>" +
                        "                                                    <input type='text' class='form-control' name='cfgKey' placeholder='配置key' value='" + val['cfgKey'] + "' required=''>" +
                        "                                                    <div class='valid-feedback'>" +
                        "                                                        Looks good!" +
                        "                                                    </div>" +
                        "                                                </div>" +
                        "                                                <div class='col-md-4 mb-3'>" +
                        "                                                    <label for='cfg_cfgValue'>Value</label>" +
                        "                                                    <textarea class='form-control' cols='20' name='cfgValue' placeholder='配置值' required=''>" + val['cfgValue'] + "</textarea>" +
                        "                                                    <div class='valid-feedback'>" +
                        "                                                        Looks good!" +
                        "                                                    </div>" +
                        "                                                </div>" +
                        "                                            </div>" +
                        "                                        </form>" +
                        "                                        <div class='modal-footer'>" +
                        "                                            <button type='button' class='btn btn-secondary' data-dismiss='modal'>Close</button>" +
                        "                                            <button type='button'  class='btn btn-primary' onclick='save_config(" + collectionId + ", " + val['id'] + ")'>Save changes</button>" +
                        "                                        </div>" +
                        "" +
                        "                                        </div>" +

                        "</div></div></div></div></div></div>";
                    // console.log(cfglistitem);
                    $("#config_list").append(cfglistitem);
                });


            }
        }

    });


}