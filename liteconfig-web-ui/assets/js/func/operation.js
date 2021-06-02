$(function () {

    var current_page = $("#menu > li > ul > li > a[href='operation.html']");

    //用户权限管理 激活
    current_page.parent().addClass("active");

    //用户中心 展开
    current_page.parent().parent().addClass("in");

    //用户中心 激活
    current_page.parent().parent().parent().addClass("active");


    $("#title_name").append("<span>操作日志</span></li>")

    list(1, 5, "", "");

})


//展示日志
function list(pageNum, pageSize, nameLike, emailLike) {
    console.log("list_users: ", pageNum, pageSize, nameLike, emailLike)
    if (nameLike === "") {
        pageSize = 5
    }
    data = {
        "type": nameLike,
        "pageNumber": pageNum,
        "pageSize": pageSize,
    };
    $.ajax({
        url: 'http://' + document.domain + ':8301/log/list?token=' + $.cookie('token'),//接口地址
        type: 'post',//请求方式
        data: JSON.stringify(data), //传输的数据
        contentType: 'application/json', //前端（html）传给后端（java Web程序）的数据类型
        dataType: 'text json', //相反
        error: function (response) {
            Notiflix.Notify.Failure("网络错误")
        },
        statusCode: {
            200: function (data) {
                console.log(data)
                $("#tb_log_list").empty();
                if (data["code"] === 200) {
                    opLogList = data['data']['opLogList'];

                    $(opLogList).each(function (i, log) {
                        row = "<tr>\n" +
                            "   <th scope=\"row\">" + (i + ((data['data']['pageNumber'] - 1) * data['data']['pageSize']) + 1) + "</th>" +
                            "   <td>" + log['opType'] + "</td>" +
                            "   <td>" + log['log'] + "</td>" +
                            "   <td>" + log['dataAfter'] + "</td>" +
                            "   <td>" + log['updateUsername'] + "</td>" +
                            "   <td>" + timeStamp2String(log.updateTime) + "</td>" +
                            "</tr>"
                        $("#tb_log_list").append(row)
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
                            "    <button onclick='list(" + (pageNum - 1) + ", " + pageSize + ", \"\", \"\")' aria-controls='dataTable' data-dt-idx='0' tabindex='0'" +
                            "       class='page-link'>Previous</button>" +
                            "</li>"
                    }

                    //中间的数字
                    for (let i = 1; i < (data['data']["count"] / pageSize) + 1; i++) {
                        if (i === pageNum) {
                            pgs += "<li class='paginate_button page-item active'>" +
                                "    <button onclick='list(" + i + ", " + pageSize + ", \"\", \"\")'" +
                                "       aria-controls='dataTable'" +
                                "       data-dt-idx='1' tabindex='0'" +
                                "       class='page-link'>" + i + "</button>" +
                                "</li>";
                        } else {
                            pgs += "<li class='paginate_button page-item'>" +
                                "    <button onclick='list(" + i + ", " + pageSize + ", \"\", \"\")'" +
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
                            "    <button onclick='list(" + (pageNum + 1) + ", " + pageSize + ", \"\", \"\")'" +
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