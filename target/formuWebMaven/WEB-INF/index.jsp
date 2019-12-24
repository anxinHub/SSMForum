<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = request.getContextPath(); %>
<html>
<head>
    <meta charset="utf-8">
    <title>WEBvueTest</title>
    <meta http-equiv=X-UA-Compatible content="IE=edge">

    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
</head>
<body>
<div id="hvue">
    <h2>vue test{{site}}</h2>
</div>
</body>
<script src="<%=path%>/js/vue/vue.js"></script>
<script src="./js/vue/vue.js"></script>
<script>
    new Vue({
        el:'#hvue',
        data:{
            site:"第一个vue"
        }
    })
</script>
</html>
