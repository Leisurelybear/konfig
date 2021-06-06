const AUTH_HOST = "http://localhost:8021";
const SERVICE_HOST = "http://localhost:8301";


$.ajax({
    url: "assets/html/header.html",
    cache: true,
    async: false,
    success: function (html) {
        $("#head-area").html(html);
    }
});
