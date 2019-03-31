package com.didoumi.www.data.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Token {
    private Token(){}
    private static Token instance = new Token();

    public Token getinstance(){
        return instance;
    }

    //随机数发生器
    public static String getToken(){
        String token = System.currentTimeMillis() + "" + new Random().nextInt();//获得毫秒数加随机数
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(token.getBytes());
            BASE64Encoder base = new BASE64Encoder();
            base.encode(md5);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return token;
    }
}
