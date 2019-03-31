package com.didoumi.www.data.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.IOUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class DataController {

    @RequestMapping("/success")
    public String success(Map<String,Object> map) {
//        Map<String,Object> map = new HashMap<>();
        map.put("hello","你好");
        return "index";
    }

    @Value("classpath:static/data/data.json")
    private Resource dataJson;

    @GetMapping("/{dataType}")
    @ResponseBody
    public Object getSeller (@PathVariable("dataType") String dataType) throws IOException {
        String json = new String(IOUtils.readFully(dataJson.getInputStream(), -1,true));
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(json, map.getClass());
        Map<String, Object> response = new HashMap<>();
        response.put("static/data", map.get(dataType));
        response.put("errno", 0);
        return response;
    }

//    public static void main(String[] args) {
//
////        StringBuffer a = new StringBuffer("A");
////        StringBuffer b = new StringBuffer("B");
////        opreation(a, b);
////        System.out.println(a+","+b);
////        String[] s = new String[10];
////        System.out.println(s.length);
////        System.out.println(s[9]);
////        getSum(10,1);
//
////        int[] arr = {2,1,95,33};
////        maopao(arr, arr.length);
////        Arrays.stream(arr).forEach(value -> System.out.print(value + ","));
//
////        String str1 = new String("abc");
////
////        String str2 = new String("abc");
////
////        System.err.println(str1.equals(str2));
////        System.err.println(str1==str2);
////        StringBuffer sb1 = new StringBuffer("abc");
////        StringBuffer sb2 = new StringBuffer("abc");
////        System.err.println(sb1.equals(sb2));
////        System.err.println(sb1==sb2);
//        //System.out.println(Arrays.stream(arr).findAny());
////        System.out.println(method1());
////        System.out.println(method2());
////        Integer i1 = 127;
////
////        Integer i2 = 127;
////
////        System.err.println(i1 == i2);
////        //System.err.println(i1.equals(i2));
////
////        i1 = 128;
////
////        i2 = 128;
////
////        System.err.println(i1 == i2);//System.err.println(i1.equals(i2));
////        System.err.println(12 - 11.9 );
////        System.err.println("===========================");
////
////        BigInteger one = new BigInteger("1");
////
////        BigInteger two = new BigInteger("2");
////
////        BigInteger three = new BigInteger("3");
////
////        BigInteger sum = new BigInteger("0");
////
////        sum.add(one);
////
////        sum.add(two);
////
////        sum.add(three);
////
////        System.err.println(sum.toString());
////        System.err.println(args.length);
//
//        String str1 = "str1";
//        String str2 = "str2";
//        Thread t1 = new Thread(() -> {
//            try {
//                while (true) {
//                    synchronized(str1) {
//                        System.out.println("线程1尝试获取第二个锁");
//                        Thread.sleep(100);
//                        synchronized (str2){
//                            System.out.println("打印线程1");
//                        }
//                    }
//                }
//            } catch (Exception e) {
//
//            }
//        });
//        Thread t2 = new Thread(() -> {
//            try {
//                while (true) {
//                    synchronized(str2) {
//                        System.out.println("线程1尝试获取第一个锁");
//                        Thread.sleep(100);
//                        synchronized (str1){
//                            System.out.println("打印线程2");
//                        }
//                    }
//                }
//            } catch (Exception e) {
//
//            }
//        });
//        t1.start();
//        t2.start();
//
//    }
//    public static int method1() {
//        int x = 1;
//        try {
//            return x;
//        } finally {
//            x = x + 2;
//        }
//    }
//
//    public static int method2() {
//        int x = 1;
//        try {
//            return x;
//        } finally {
//            x = x + 2;
//            return ++x;
//        }
//    }
//
//    private static void maopao(int[] a, int b) {
//        int n,k = b;
//        boolean flag = true;
//        while(flag) {
//            flag = false;
//            for (int j = 1; j < k; j++) {
//                if (a[j-1] > a[j]) {
//                    int temp;
//                    temp = a[j-1];
//                    a[j-1] = a[j];
//                    a[j] = temp;
//                    flag = true;
//                }
//            }
//            k--;
//        }
//    }
//
//    private static void opreation(StringBuffer a, StringBuffer b) {
//        b.append(a);
//        b = a;
//    }
//    public static int getSum(int day, int start){
//        day --;
//        start = start*2+2;
//        System.out.println(start);
//        if (day > 0){
//            getSum(day,start);
//        }
//        return start;
//    }

}
