$.ajax({
    url: "assets/html/header.html",
    cache: true,
    async: false,
    success: function (html) {
        $("#head-area").html(html);
    }
});
