package com.slin.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by e6gps on 2017/6/19.
 */
@Component
public class SpringContextUtils {

    private static ApplicationContext applicationContext;
    static{
        if(null == applicationContext){
            applicationContext =  new ClassPathXmlApplicationContext("classpath*:ApplicationContext.xml");
        }
    }

    public static <T> T getBean(String name) throws BeansException {
        return (T)applicationContext.getBean(name);
    }
}
