<html>
<body>
<h2>Hello World!11</h2>
</body>
<script>
    var code = 1;
    var xmls = 2;
    $.ajax({
        url: "/DHCHERPBUDG//helloMysql",
        data:{code:code,xMLStr:xmls},
        type: "post",
        dataType:"json",
        async:false,
        success: function (data) {
            //window.location.href="select.html?a=2";
            result=data;
        },
        error:function x(s) {
            debugger
        }
    });
</script>
</html>
