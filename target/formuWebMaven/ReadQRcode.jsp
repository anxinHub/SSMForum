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
  <img src="<%=path%>/img/tele.pdf" id="telePdf">
  <img src="<%=path%>/img/billQR.png" id="billQR">
  <img src="<%=path%>/img/baiduQR.png" id="baiduQR">
  <embed src="<%=path%>/img/tele.pdf" id="embedimgurl">
  <div>
    <button id="readRQC">识别上面图片</button>
  </div>
  <div id="output"></div>
</body>
<script src="js/common/llqrcode.js"></script>
<script>
  var rr = function(imgUrl){
      var uimg = './img/billQRComplete.png'
      var imgurl3 = document.getElementById('billQR').getAttribute('src')
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
  document.getElementById('readRQC').addEventListener('click',rr)
</script>
</html>
