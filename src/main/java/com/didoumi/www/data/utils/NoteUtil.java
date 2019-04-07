package com.didoumi.www.data.utils;

import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class NoteUtil {

    //随机数发生器Id
    public static String getId(){
        return new Random().nextInt(100000) +""+System.currentTimeMillis();//获得毫秒数加随机数
    }

    //获取session中的值
    public static Object getSessionForKey(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

}
