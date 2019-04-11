package com.didoumi.www.data.utils;


import com.didoumi.www.data.entity.CommonFiled;
import com.didoumi.www.data.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
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

    //设置创建人，创建时间，更新人，更新时间
    public static void setCreteAndUpdateAndTime(CommonFiled commonFiled) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        User user = (User)request.getSession().getAttribute(Constant.USER.getName());
        commonFiled.setCreateUser(user.getUsername());
        commonFiled.setUpdateUser(user.getUsername());

        setUpdateAndTime(commonFiled);
    }

    //设置更新人、更新时间
    public static void setUpdateAndTime(CommonFiled commonFiled) {
        LocalDateTime localDateTime = LocalDateTime.now();
        commonFiled.setCreateTime(localDateTime);
        commonFiled.setUpdateTime(localDateTime);
    }

}
