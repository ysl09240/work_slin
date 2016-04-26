package com.slin.test;

/**
 * @author SongLin.Yang
 * @data 2016-04-25 16:02
 */
public class SyncStack {
    private int index=0;
    WoTou[] arrWT =  new WoTou[6];

    public synchronized void push(WoTou wt){
        while(index == arrWT.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        arrWT[index] = wt;
        index ++;
        System.out.println("生产了："+wt);

    }

    public synchronized void pop(){
        while (index == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        index--;
        System.out.println("消费了："+arrWT[index]);
    }

}
