package com.didoumi.www.data.jsonp;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;

@ControllerAdvice(basePackages = "com.usoft.web.controller.jsonp")
//public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
public class JsonpAdvice {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("zhangsan");
        arrayList.add("lisi");
        arrayList.add("lisi3");
        arrayList.add("lisi4");
        arrayList.add("lisi5");
        arrayList.add("lisi6");
        arrayList.add("lisi7");
        arrayList.add("lisi8");
        arrayList.add("lisi9");
        arrayList.add("lisi10");
        arrayList.add("lisi11");
        for (String s: arrayList) {
            System.out.println(s);
        }
    }
}
