<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String path = request.getContextPath(); %>
<html>
  <head>
    <meta charset="utf-8">
    <title>vueTest</title>
    <meta http-equiv=X-UA-Compatible content="IE=edge">

    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
  </head>
  <style>
    .active{
      color:red;
    }
  </style>
  <body>
    <div id="hvue">
      <%--直接使用mustache{{}}--%>
      <h2>vue test{{site[0]}}</h2>
      <%--标签遍历数组--%>
      <div v-for="item in site">{{item}}</div>
      <div>{{count}}</div>
      <%--按钮事件直接添加--%>
      <button v-on:click="count++">+</button>
      <%--语法糖、方法函数--%>
      <button @click="increment">-</button>
      <button v-on:click="sub">--</button>
      <img v-bind:src="imgurl" alt="tu">
      <div>
        <%--可以有两个class，会合并--%>
        <div class="edit" :class="act"> 语法糖直接变色</div>
        <%--绑定对象、数组，改变对象key的value的Boolean值，false时不绑定该样式--%>
        <%--key为样式，不能为data的值--%>
        <div v-bind:class="{active:chgColor}">按钮变色l</div>
        <button v-on:click="changeColor">变色</button>
      </div>
    </div>
  </body>


  <script src="js/vue/vue.js"></script>
    <script v-bind:src="vueurl"></script>
    <script>
      var vv = new Vue({
        el: '#hvue',
        data: {
          site: ["第一个vue", "好好工作"],
          count: 2,
          imgurl:'img/muwu.jpg',
          vueurl:'js/vue/vue.js',
          chgColor:false,
          act:"active"
        },
        methods:{
          increment:function(){
            console.log("-被执行");
            this.count++;
          },
          sub:function(){
            console.log("-被执行");
            this.count--;
          },
          changeColor:function () {
            this.chgColor=!this.chgColor;
          }
        }
      })
  </script>
</html>
