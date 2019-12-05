package com.talent.developer;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestShiro {
    @Test
    public void a() {
        Subject currentUser = SecurityUtils.getSubject();
        System.out.println(currentUser);

    }
}
