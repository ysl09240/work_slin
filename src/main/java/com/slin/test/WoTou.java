package com.slin.test;

/**
 * @author SongLin.Yang
 * @data 2016-04-25 16:01
 */
public class WoTou {
    private int id;
    WoTou(int id){
        this.id = id;
    }

    @Override
    public String toString() {
        return  "WoTou : " + id;
    }
}
