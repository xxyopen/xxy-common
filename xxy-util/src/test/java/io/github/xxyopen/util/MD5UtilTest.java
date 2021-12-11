package io.github.xxyopen.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * MD5Util单元测试
 *
 * @author xxy
 * @version 1.0.0
 * @since 2021/12/11
 */
public class MD5UtilTest {

    @Test
    public void md5UtilTest(){
        String password = "123456";
        String md5Password = MD5Util.MD5New(password);
        System.out.println(md5Password);
        Assert.assertNotEquals("123456",md5Password);
        Assert.assertEquals(md5Password,MD5Util.MD5New(password));
    }
}
