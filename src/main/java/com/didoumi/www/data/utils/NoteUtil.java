package com.didoumi.www.data.utils;

import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class NoteUtil {

    //随机数发生器Id，生成18位数的id
    public static String getId(){
        String id = new Random().nextInt(100000) + "" +System.currentTimeMillis();//获得毫秒数加随机数
        if (id.length() < 18) {
            int temp = 1;
            for (int i =0; i < (18 - id.length()); i++) {
                temp = temp * 10;
            }
            id += new Random().nextInt(temp - 1) ;
        }
        return id;
    }

    //获取session中的值
    public static Object getSessionForKey(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }
}
