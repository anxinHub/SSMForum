package com.ax.controller.testController;

import com.ax.base.ImgChange;
import com.ax.dao.UserMapper;
import com.ax.model.FileLib;
import com.ax.model.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("/goQRCODE")
    public void checkPngQRcode(HttpServletResponse resp,HttpServletRequest req,String code){
        System.out.println("entry controller of QRCODE");
        req.getParameter("a");
        try {
            resp.getWriter().print("{a:1}");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        ImgChange imgChange =new ImgChange();
//        imgChange.pdfToImg();
//        imgChange.extractImages();
//        InputStream is =new ;

//        try {
//            ImgChange.cutPNG(new FileInputStream("E:/gitaxforum/SSMForum/src/main/webapp/img/telePng.png"),
//                    new FileOutputStream("E:/gitaxforum/SSMForum/src/main/webapp/img/telePngCut.png"), 0,0,150,150);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


    @RequestMapping("/helloJBDCtest")
    public void testJDBC(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("----------------------------");
        String driverClassName="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost/talent3";
        String username="root";
        String password="an123";
        try {
            Class.forName(driverClassName);
            Connection con = (Connection) DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM `account_manager`";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            //ps.setString(1,"NAME_");
            ResultSet rs = ps.executeQuery();
            Integer columnCount = rs.getMetaData().getColumnCount();
//            String id = rs.getString(1);
           // System.out.println(rs+","+columnCount+","+id);
            /*while (rs.next()) {
                System.out.println(rs.getString("userName"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getInt("age"));
                System.out.println(rs.getDate("birthday"));
            }*/
            rs.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("helloJustMybatis")
//    public void testJustMybatis(HttpServletRequest req, HttpServletResponse res){
    public void testJustMybatis(){
        try {
            String resource = "mybatis-config.xml";
            InputStream is = Resources.getResourceAsStream(resource);

            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = ssf.openSession();

            //返回结果为实体类时，selecLIst只拿到了第一列字段的值
            // (解决：第一次resultType为string，之后改变为实体类User后未编译src/main/java下的XML文件)

            //sqlsession找到mapper.xml下的id,和对应的Dao下的interface无关
            //statemenet填写xml里的namespace，但为了使用动态代理(sqlsession.getMapper())命名为DAO全路径
            //实体类和字段值名字不一样时需要resultMap来映射
            List<User> u = sqlSession.selectList("com.ax.dao.UserMapper.getAllUser");
            List<FileLib> ut = sqlSession.selectList("com.ax.dao.UserMapper.getAllUserTest");
            //一级缓存 session 相同sql和param 不会查询，直接输出
            List<User> sessionCachOne = sqlSession.getMapper(UserMapper.class).getAllUser();

            User user = new User();
            user.setId("11111111111111111111111");
            user.setSex("1");
            user.setUserRole(1);
/*            Integer successRow = sqlSession.insert("com.ax.dao.UserMapper.insertUser",user);
            insert不能自动提交 使用sqlsession.commit()
            [JdbcTransaction]Setting autocommit to false on JDBC Connection[JDBC4Connection]
            if (successRow!=0){
                sqlSession.commit();
            }*/

            //Dao有对应的mapper接口且提供和mapper.xml的id相同的方法,和dao类路径相同的namespace
            //updata会清空一级缓存
            List<User> um = sqlSession.getMapper(UserMapper.class).getAllUser();

            //二级缓存，同一个namespace下
            sqlSession.close();
            SqlSession sqlS = ssf.openSession();
            List<User> sessionCacheTwo = sqlS.getMapper(UserMapper.class).getAllUser();

            System.out.println(u+"000000000000000000"+ut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
