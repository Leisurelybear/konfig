$(function () {

    var current_page = $("#menu > li > ul > li > a[href='groupmanage.html']");

    //用户权限管理 激活
    current_page.parent().addClass("active");

    //用户中心 展开
    current_page.parent().parent().addClass("in");

    //用户中心 激活
    current_page.parent().parent().parent().addClass("active");


    $("#title_name").append("<span>用户组管理</span></li>")

    list_groups(1, 5, "");

    $("#grouplist_search").click(function () {
        list_groups(1, 5, $("#groupsearch_text").val());
    })

    $("#btn_group_add").click(function () {
        if ($("#group_name").val() == null || $("#group_name").val() == "") {
            Notiflix.Notify.Failure("组名不能为空")
            return
        }
        createGroup($("#group_name").val());
    })
});

function createGroup(groupName) {
    data = {
        "groupName": groupName,
    };

    $.ajax({
        url: AUTH_HOST + '/group/create?token=' + $.cookie('token'),//接口地址
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
                    Notiflix.Notify.Success("创建成功")
                    list_groups(1, 5, "");
                } else {
                    Notiflix.Notify.Failure("创建失败：" + data["message"])
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

function del(groupId) {
    console.log("删除", groupId)

    $.ajax({
        url: AUTH_HOST + '/group/delete/' + groupId + '?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: null, //传输的数据
        // contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        // dataType: 'text json', //相反
        async: true,
        error: function (response) {
            Notiflix.Notify.Failure("网络错误")
        },
        statusCode: {
            200: function (data) {
                // $("#tb_groupmanager_list").empty();
                if (data["code"] === 200) {
                    Notiflix.Notify.Success("操作成功");
                    list_groups(1, 5, "");

                    removeTags("div", "modal-backdrop fade show")
                    // modal-backdrop fade show
                } else {
                    Notiflix.Notify.Failure(data["message"])

                }
            }
        }
    })

}

function groupuserdetail(groupId) {
    currentGroupId = groupId;
    $.ajax({
        url: "assets/html/usergroup_detail.html?groupId=" + groupId,
        cache: true,
        async: false,
        success: function (html) {
            $('#modal-body-' + groupId).html(html);
        }
    });

    // $('#modal-body-' + groupId).load('assets/html/usergroup_detail.html', {'groupId': groupId});
}

//展示用户列表
function list_groups(pageNum, pageSize, nameLike) {
    console.log("list_groups: ", pageNum, pageSize, nameLike)
    if (nameLike === "") {
        pageSize = 5
    }
    data = {
        "groupNameLike": nameLike,
        "pageNumber": pageNum,
        "pageSize": pageSize,
        "sort": -1
    };

    $.ajax({
        url: AUTH_HOST + '/group/list?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(data), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("获取用户信息错误")
        },
        statusCode: {
            200: function (data) {
                $("#tb_groupmanager_list").empty();
                if (data["code"] === 200) {
                    groupList = data['data']['groupList'];

                    $(groupList).each(function (i, val) {
                        row = "<tr>" +
                            "     <th scope='row'>" + (i + ((data['data']['pageNum'] - 1) * data['data']['pageSize']) + 1) + "</th>" +
                            "     <td>" + val.groupName + "</td>" +
                            "     <td>" + val.id + "</td>" +
                            "     <td>" + val.rootAccountUsername + "</td>" +
                            "     <td>" +
                            "           <input class='btn btn-info' type='button' value='编辑' onclick='groupuserdetail(" + val.id + ")' data-toggle='modal' data-target='#config_add_form'>" +
                            "                <div class='modal fade' id='config_add_form' style='display: none;' aria-hidden='true'>" +
                            "                    <div class='modal-dialog modal-dialog-centered' role='document'>" +
                            "                        <div class='modal-content'>" +
                            "                            <div class='modal-header'>" +
                            "                                <h5 class='modal-title'>编辑</h5>" +
                            "                                <button type='button' class='close' data-dismiss='modal'><span>×</span></button>" +
                            "                            </div>" +
                            "                            <div class='modal-body' id='modal-body-" + val.id + "'>" +
                            "" +
                            "                            </div>" +
                            "                            <div class='modal-footer'>" +
                            "                                <button type='button' class='btn btn-secondary' data-dismiss='modal'>关闭</button>" +
                            "                            </div>" +
                            "                        </div>" +
                            "                    </div>" +
                            "                </div>" +
                            "" +
                            "           <input class='btn btn-danger' type='button' value='删除' data-toggle='modal' data-target='#group_del_" + val.id + "'>" +
                            "                <div class='modal fade' id='group_del_" + val.id + "' style='display: none;' aria-hidden='true'>" +
                            "                    <div class='modal-dialog modal-dialog-centered' role='document'>" +
                            "                        <div class='modal-content'>" +
                            "                            <div class='modal-header'>" +
                            "                                <h5 class='modal-title'>确认删除？</h5>" +
                            "                                <button type='button' class='close' data-dismiss='modal'><span>×</span></button>" +
                            "                            </div>" +
                            "                            <div class='modal-body'>" +
                            "                                    确认删除？" +
                            "                            </div>" +
                            "                            <div class='modal-footer'>" +
                            "                                <button type='button' class='btn btn-danger' data-dismiss='modal'  onclick='del(" + val.id + ")' >确认删除</button>" +
                            "                                <button type='button' class='btn btn-secondary' data-dismiss='modal'>取消</button>" +
                            "                            </div>" +
                            "                        </div>" +
                            "                    </div>" +
                            "                </div>" +
                            "     </td>" +
                            "</tr>";
                        $("#tb_groupmanager_list").append(row)
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
                            "    <button onclick='list_groups(" + (pageNum - 1) + ", " + pageSize + ", \"\", \"\")' aria-controls='dataTable' data-dt-idx='0' tabindex='0'" +
                            "       class='page-link'>Previous</button>" +
                            "</li>"
                    }

                    //中间的数字
                    for (let i = 1; i < (data['data']["count"] / pageSize) + 1; i++) {
                        if (i === pageNum) {
                            pgs += "<li class='paginate_button page-item active'>" +
                                "    <button onclick='list_groups(" + i + ", " + pageSize + ", \"\", \"\")'" +
                                "       aria-controls='dataTable'" +
                                "       data-dt-idx='1' tabindex='0'" +
                                "       class='page-link'>" + i + "</button>" +
                                "</li>";
                        } else {
                            pgs += "<li class='paginate_button page-item'>" +
                                "    <button onclick='list_groups(" + i + ", " + pageSize + ", \"\", \"\")'" +
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
                            "    <button onclick='list_groups(" + (pageNum + 1) + ", " + pageSize + ", \"\", \"\")'" +
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