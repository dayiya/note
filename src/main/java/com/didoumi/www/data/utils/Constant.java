package com.didoumi.www.data.utils;

public enum Constant {
    USER("user"),//存放在session中用户的key
    MENU("menus"),//存放在session中目录的key
    FIVEHANDER("500"),//返回的结果状态
    ONE("1"),//笔记的类型，1表示笔记簿
    TWO("2"),//笔记的类型，2表示笔记
    TWOHANDER("200");//返回的结果状态

    private String name;

    Constant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
