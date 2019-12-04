package com.talent.developer;
import com.ax.controller.testController.TestController;
import com.ax.dao.FileLibMapper;
import com.ax.dao.UserMapper;
import com.ax.dao.impl.UserMapperImpl;
import com.ax.model.FileLib;
import com.ax.model.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import org.activiti.engine.*;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Slf4j
public class TestJava {
    @Test
    public void testJDBC() {
        String driverClassName="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://119.3.189.58/tanlent3";
        String username="develop";
        String password="dev123456";
        try {
            Class.forName(driverClassName);
            Connection con = (Connection) DriverManager.getConnection(url, username, password);
            String sql = "SELECT * FROM `user_info` where user_account=?";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1,"develop");
            ResultSet rs = ps.executeQuery();
            Integer columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i=1;i<=columnCount;i++){
                    Object value = rs.getObject(i);
                    Object column = rs.getMetaData().getColumnName(i);
                    System.out.println("columnName:"+column+"\nvalue:"+value);
                }
            }
            rs.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void justMybatisTest(){
        try {
            String resource = "mybatis-config.xml";
            InputStream is = Resources.getResourceAsStream(resource);

            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = ssf.openSession();
/*
            List<User> uList = sqlSession.selectList("com.ax.dao.UserMapper.getAllUser");
            List<User> cachFirst = sqlSession.getMapper(UserMapper.class).getAllUser();

            User user = new User();
            user.setId("11111111111111111111111");
            user.setSex("1");
            user.setUserRole(1);
            Integer successRow = sqlSession.insert("com.ax.dao.UserMapper.insertUser",user);
            if (successRow!=0){
            //    sqlSession.commit();
            }*/

            List<User> cachFirstTwo = sqlSession.getMapper(UserMapper.class).getAllUser();

            //二级缓存，同一个namespace下
            sqlSession.close();
            SqlSession sqlS = ssf.openSession();
            List<User> cacheSecond = sqlS.getMapper(UserMapper.class).getAllUser();

            System.out.println("over");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSpringMybatis(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        UserMapper ump = (UserMapper) context.getBean("userMapper");
        List<User> ul = ump.getAllUser();

        SqlSession ss = (SqlSession) context.getBean("sqlSessionTemplate");
        UserMapperImpl umi = new UserMapperImpl(ss);
        List<User> userListByImpl = umi.getUserById("11001100101010101001001000001111");

        List<FileLib> fl = ss.getMapper(FileLibMapper.class).getFileLibAll();
        System.out.print(ul);
    }
    @Test
    public void testMybatis(){
        //调用数据库
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        JdbcTemplate jt = (JdbcTemplate) context.getBean("jdbcTemplate");
        jt.update("delete from t_filelibrary where fileid='111'");

    }


}
