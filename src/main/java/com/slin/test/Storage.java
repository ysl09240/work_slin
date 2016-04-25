package com.slin.test;

import java.util.LinkedList;
import java.util.Observable;

/**
 * 仓库类Storage实现缓冲区
 * @author SongLin.Yang
 * @data 2016-04-22 11:13
 */
public class Storage {

    //创建仓库最大存储量
    private final int MAX_SIZE=100;

    //创建仓库载体
    private LinkedList<Object> list = new LinkedList<Object>();

    //生产num个产品
    public void produce(int num){

        //同步代码段
        synchronized (list){
            //如果仓库剩余容量
            while(list.size()+num > MAX_SIZE){


            }
        }

    }

}
