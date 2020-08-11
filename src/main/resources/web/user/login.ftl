<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>首页</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <#include "../common/common.ftl" />
</head>
<body>
<form id="form_sun" action="check" method="get">
    <input type="text" id="id" name="user_id">
    <input type="password" id="password" name="user_password">
</form>
<button onclick="login_btn()">登录</button>

</body>
<script>
    $(function () {
        // 开始写 jQuery 代码...


    });

    function login_btn() {

        var id = $("#id").val()
        var password = $("#password").val()


        var form = $("#form_sun")
//再次修改input内容
        form.submit();

        // 您还可以为 cookie 添加一个过期时间（以 UTC 或 GMT 时间）。默认情况下，cookie 在浏览器关闭时删除：
        // document.cookie="username=John Doe; expires=Thu, 18 Dec 2043 12:00:00 GMT";
        // 您可以使用 path 参数告诉浏览器 cookie 的路径。默认情况下，cookie 属于当前页面。
        // document.cookie = ";path=/;";
        document.cookie = "userId=" + id + ";path=/;";
        document.cookie = "userPassword=" + password + ";path=/;";
    }
</script>
</html>