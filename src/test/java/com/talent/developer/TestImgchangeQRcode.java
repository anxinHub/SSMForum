package com.talent.developer;

import com.ax.base.ImgChange;
import org.junit.Test;

public class TestImgchangeQRcode {
    @Test
    public void testQRcodeRead(){
        System.out.println("hell");
        ImgChange imgChange = new ImgChange();
        System.out.println("hell--"+imgChange.extractImages());
    }
}
