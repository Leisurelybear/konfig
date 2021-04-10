$.ajax({
    url: "assets/html/sidebar.html",
    cache: true,
    async: false,
    success: function (html) {
        $("#sidebar-menu").html(html);
    }
});

