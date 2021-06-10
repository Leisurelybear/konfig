$(function () {

    var current_page = $("#menu > li > ul > li > a[href='configcollection.html']");

    //用户权限管理 激活
    current_page.parent().addClass("active");

    //用户中心 展开
    current_page.parent().parent().addClass("in");

    //用户中心 激活
    current_page.parent().parent().parent().addClass("active");


    $("#title_name").append("<li><a href='configcollection.html'>全部配置集</a></li>")
    $("#title_name").append("<li><span>详情</span></li>")

    //取url中的集合cid
    var cid = window.location.search.substr(1).match("(^|&)cid=([^&]*)(&|$)");
    console.log(cid);
    if (cid === null || cid.length < 3 || cid[2] === ""
    ) {
        return
    }

    //展示当前cid的集合配置列表
    list_configs(cid[2]);

    //添加配置
    $("#btn_config_add").click(function () {
        let configName = $("#config_name").val();
        let configKey = $("#config_key").val();
        let configValue = $("#config_value").val();
        add_config(configName, configKey, configValue, cid[2])
    });

    $("#btn_change_status").click(function () {

        $.ajax({
            url: SERVICE_HOST + '/cfg_coll/change_status/' + cid[2] + '/?token=' + $.cookie('token'),//接口地址
            type: 'post',//请求方式
            data: "", //传输的数据
            contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
            dataType: 'text json', //相反
            error: function (response) {
                Notiflix.Notify.Failure("网络错误")
            },
            statusCode: {
                200: function (data) {
                    if (data['code'] != 200) {
                        Notiflix.Notify.Failure("操作失败： " + data["message"])
                        return
                    }
                    Notiflix.Notify.Success("操作成功：" + data["message"])
                    list_configs(cid[2])
                }
            }
        })

    })

    //权限管理
    $("#btn_cfg_permission").click(function () {
        let cfgCollectionId = cid[2];
        listCfgPermission(cfgCollectionId, 1, 5);
    });

    $("#userlist_search").click(function () {
        let cfgCollectionId = cid[2];
        fillPermissionUserSearch(cfgCollectionId);

    })
});

function fillPermissionUserSearch(cfgCollectionId) {
    queryName = $("#usersearch_text").val();
    if (queryName === "") {
        return
    }
    $("#user_list").empty()

    data = {
        "name": queryName
    };


    //查用户
    $.ajax({
        url: AUTH_HOST + '/admin/list_by_name?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: queryName, //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("网络错误")
        },
        statusCode: {
            200: function (data) {
                if (data['code'] !== 200) {
                    Notiflix.Notify.Failure("操作失败：" + data['message']);
                } else {
                    console.log(data['data'])

                    $(data['data']).each(function (i, val) {
                        str = "<tr>\n" +
                            " <td>用户</td>\n" +
                            " <td>[" + val.id + "]\t" + val.username + "</td>\n" +
                            " <td><i class=\"ti-plus\" onclick='addPermission(" + cfgCollectionId + ", 0, " + val.id + ")'></i></td>\n" +
                            " </tr>"
                        $("#user_list").append(str)
                    })
                }
            }
        }
    })

    //查用户组
    $.ajax({
        url: AUTH_HOST + '/group/list_by_name?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: queryName, //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("网络错误")
        },
        statusCode: {
            200: function (data) {
                if (data['code'] !== 200) {
                    Notiflix.Notify.Failure("操作失败：" + data['message']);
                } else {
                    console.log(data['data'])
                    $(data['data']).each(function (i, val) {

                        str = "<tr>\n" +
                            " <td>用户组</td>\n" +
                            " <td>[" + val.id + "]\t" + val.groupName + "</td>\n" +
                            " <td><i class=\"ti-plus\" onclick='addPermission(" + cfgCollectionId + ", 1, " + val.id + ")'></i></td>\n" +
                            " </tr>"
                        $("#user_list").append(str)
                    })
                }
            }
        }
    })
}

function addPermission(cfgCollectionId, type, id) {
    //type = 0: id为用户id，添加用户权限
    //type = 1；id为用户组ID，添加用户组权限
    console.log(cfgCollectionId, type, id)

    data = {
        "collectionId": cfgCollectionId,
        "accountIds": [],
        "groupIds": [],
    };
    if (type === 0) {
        data.accountIds = [id]
    } else if (type === 1) {
        data.groupIds = [id]
    }

    $.ajax({
        url: SERVICE_HOST + '/cfg_permission/create?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(data), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("网络错误")
        },
        statusCode: {
            200: function (data) {
                if (data['code'] !== 200) {
                    Notiflix.Notify.Failure("操作失败：" + data['message']);
                } else {
                    if (data['data'] === false) {
                        Notiflix.Notify.Failure("操作失败！配置中已有该权限");
                    } else {
                        Notiflix.Notify.Success("操作成功！");
                        listCfgPermission(cfgCollectionId, 1, 5)

                    }

                }
            }
        }
    })

}

function removeRow(id, collectionId) {
    $.ajax({
        url: SERVICE_HOST + '/cfg_permission/remove/' + id + '?token=' + $.cookie('token'),//接口地址
        type: 'delete',//请求方式
        data: null, //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("网络错误")
        },
        statusCode: {
            200: function (data) {
                if (data['code'] !== 200) {
                    Notiflix.Notify.Failure("操作失败：" + data['message']);

                } else {
                    Notiflix.Notify.Success("操作成功！");
                    listCfgPermission(collectionId, 1, 5)
                }

            }
        }
    })
}

function listCfgPermission(cfgCollectionId, pageNumber, pageSize) {
    data = {
        "collectionIds": [cfgCollectionId],
        "accountIds": [],
        "groupsIds": [],
        "pageNumber": pageNumber,
        "pageSize": pageSize
    };


    groupMap = {};
    accountMap = {};

    $.ajax({
        url: SERVICE_HOST + '/cfg_permission/list?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(data), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("网络错误")
        },
        statusCode: {
            200: function (data) {
                if (data['code'] === 200) {
                    console.log(data);
                    $("#cfg_premission_list").empty()
                    $("#cfg_permission_page").empty()

                    $(data['data']['accountList']).each(function (i, val) {
                        accountMap[val.id] = val.username;

                    })
                    $(data['data']['groupList']).each(function (i, val) {
                        groupMap[val.id] = val.groupName;

                    })

                    $(data['data']['permissionList']).each(function (i, val) {
                        tr = "<tr>\n" +
                            "    <th scope=\"row\">" + ((pageNumber * pageSize) + i - pageSize + 1) + "</th>\n" +
                            "    <td>" + (val.type === 0 ? "用户权限" : "用户组权限") + "</td>\n" +
                            "    <td>" + (val.type === 0 ? ("[" + val.accountId + "] " + accountMap[val.accountId]) : ("[" + val.groupId + "] " + groupMap[val.groupId])) + "</td>\n" +
                            // "    <td>" + val.collectionId + "</td>\n" +
                            "    <td><i class=\"ti-trash\" onclick='removeRow(" + val.id + ", " + val.collectionId + ")'>删除</i></td>\n" +
                            "</tr>"
                        $("#cfg_premission_list").append(tr)

                    });


                    //分页
                    pageNums = Math.floor(data['data']['count'] / data['data']['pageSize']) + 1;
                    cfgPermissionPage = "<li class=\"paginate_button page-item previous " + (pageNumber === 1 ? "disabled" : "") + "\" >" +
                        "    <a href=\"#\"  onclick='listCfgPermission(" + cfgCollectionId + ", " + (pageNumber - 1) + ", " + pageSize + ")'  class=\"page-link\">Previous</a>" +
                        "</li>";

                    for (let i = 0; i < pageNums; i++) {
                        cfgPermissionPage += "<li class=\"paginate_button page-item " + ((i + 1) === pageNumber ? "active" : "") + " \">" +
                            "    <a href=\"#\" onclick='listCfgPermission(" + cfgCollectionId + ", " + (i + 1) + ", " + pageSize + ")' class=\"page-link\">" + (i + 1) + "</a>" +
                            "</li>"
                    }
                    console.log(pageNumber, pageNums)
                    cfgPermissionPage += "<li class=\"paginate_button page-item next " + (pageNumber >= pageNums ? "disabled" : "") + "\" \">" +
                        "    <a href=\"#\" onclick='listCfgPermission(" + cfgCollectionId + ", " + (pageNumber + 1) + ", " + pageSize + ")' class=\"page-link\">Next</a>" +
                        "</li>"
                    $("#cfg_permission_page").append(cfgPermissionPage)
                } else {
                    $('#user_search_div').empty()
                }
            }
        }
    })

}

function add_config(configName, key, value, collectionId) {
    console.log(configName, key, value)

    data = {
        'configName': configName,
        'configKey': key,
        'configValue': value,
        'collectionId': collectionId,
    };

    $.ajax({
        url: SERVICE_HOST + '/config/create?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(data), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("网络错误")
        },
        statusCode: {
            200: function (data) {
                if (data['code'] != 200) {
                    Notiflix.Notify.Failure("添加配置失败： " + data["message"])
                    return
                }
                Notiflix.Notify.Success("添加成功！请下拉至底部 ")
                list_configs(collectionId)
            }
        }
    })

}

//删除配置
function delete_cfg(collectionId, configId) {
    data = {
        'collectionId': collectionId,
        'configId': configId,
    };

    console.log(data)

    $.ajax({
        url: SERVICE_HOST + '/config/delete?token=' + $.cookie('token'),//接口地址
        type: 'delete',//请求方式
        data: JSON.stringify(data), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("网络错误。")
        },
        statusCode: {
            200: function (data) {
                if (data['code'] != 200) {
                    Notiflix.Notify.Failure("删除失败： " + data["message"])
                    return
                }

                Notiflix.Notify.Info("删除成功！")
                list_configs(collectionId)
            }
        }
    })
}

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
        url: SERVICE_HOST + '/config/update?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(data), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("网络错误。")
        },
        statusCode: {
            200: function (data) {

                if (data['code'] !== 200) {
                    Notiflix.Notify.Failure("查询配置列表失败： " + data["message"]);

                    return
                }else {
                    list_configs(collectionId);
                    Notiflix.Notify.Success("修改成功！");
                    removeTags("div", "modal-backdrop fade show")
                    // document.getElementsByClassName("modal-backdrop")[0].remove("modal-backdrop")
                }
            }
        }
    })
}

function removeTags(tagName, tagClass) {
    var tagElements = document.getElementsByTagName(tagName);
    for (var m = 0; m < tagElements.length; m++) {
        if (tagElements[m].className == tagClass) {
            tagElements[m].parentNode.removeChild(tagElements[m]);
        }
    }
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
        url: SERVICE_HOST + '/config/list?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(queryParam), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("网络错误")
        },
        statusCode: {
            200: function (data) {

                if (data['code'] !== 200) {
                    Notiflix.Notify.Failure("查询配置列表失败： " + data["message"]);
                    $.delay(3000);
                    window.history.go(-1);
                    return
                }
                $("#btn_config_collection_id").html(data["data"]["collectionId"]);
                $("#btn_config_collection_name").html(data["data"]["collectionName"]);
                $("#btn_config_collection_status").html((data["data"]["isDraft"] === 1) ? "草稿版本" : "线上版本")

                $("#btn_change_status").html((data["data"]["isDraft"] === 1) ? "上线" : "下线");

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
                        "                            <p><b>修改时间:</b> " + timeStamp2String(val.updateTime) +
                        "                            <p><b>修改者:</b> " + val['updateUsername'] + "</p>" +
                        "                            <!-- Large modal -->" +
                        "                            <button type='button' class='btn btn-primary btn-flat btn-lg' data-toggle='modal'" +
                        "                                    data-target='.fm-data-" + val['id'] + "'>修改" +
                        "                            </button>" +
                        "                            <button type='button' onclick='delete_cfg( " + val["collectionId"] + " , " + val['id'] + ")' class='btn btn-danger btn-flat btn-lg'>删除</button>" +
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
                        "                                            <button type='button'  class='btn btn-primary' data-dismiss='modal' onclick='save_config(" + collectionId + ", " + val['id'] + ")'>Save changes</button>" +
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