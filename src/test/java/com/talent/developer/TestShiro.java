package com.talent.developer;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.junit.Test;

public class TestShiro {
    @Test
    public void useIniLogin() {
        System.out.println("hello shiro");
    }
    @Test
    public void useJdbcLogin() {
        System.out.println("hello shiro");
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:cfg/shiro.ini");
        SecurityManager sm = factory.getInstance();
        SecurityUtils.setSecurityManager(sm);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken utoken = new UsernamePasswordToken("javaTom","1111");
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
//        dwsm.setRealm();
        try {
            subject.login(utoken);
            if(subject.isAuthenticated()){
                System.out.println("验证通过");
            }
        }catch (AuthenticationException e){
            e.printStackTrace();
            System.out.println("验证失败");
        }
    }
}
