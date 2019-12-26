<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/12/24 0024
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = request.getContextPath(); %>
<html>
<head>
  <title>dd</title>
</head>
<body>
  <div>
    <input type="file" name="jj"/>
  </div>
  <div id="displayImg">

  </div>
  <div>
    <button id="readRQC">识别上面图片</button>
  </div>
  <div id="displayResult">

  </div>
  <div id="output"></div>
</body>
<script src="js/common/llqrcode.js"></script>
<script src="<%=path%>/js/jquery/jquery-3.3.1.js"></script>
<script>
  var rr = function(imgUrl){
    var uimg = './img/tele.pdf'
    // var uimg = document.getElementById('telePdf').getAttribute('src')
    console.log('entry function'+imgUrl);
    try {
      qrcode.decode(uimg);
    } catch (e) {
      console.log('error'+e);
    };
    qrcode.callback = function (e) {
      document.getElementById('output').innerHTML = '结果：' + e;
      console.log('error'+e);
    }
  }
  var aj = function(){
    $.ajax({
      url: '/SSMForum/goQRCODE',
      data:{"a":1},
      type: "post",
      dataType:"json",
      async:false,
      success: function (data) {
        result=data;
      },
      error:function x(s) {
        var a = 1;
        result = s.response
      }
    })
  }
  document.getElementById('readRQC').addEventListener('click',rr)
  aj()
</script>
</html>
