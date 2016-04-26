package com.slin.test;

/**
 * @author SongLin.Yang
 * @data 2016-04-26 09:08
 */
public class Producer implements Runnable {

    SyncStack ss = null;

    Producer(SyncStack ss){
        this.ss = ss;
    }
    public void run() {
        for (int i = 0; i < 20; i++) {
            WoTou wt = new WoTou(i);
            ss.push(wt);
            try {
                Thread.sleep((int) (Math.random()*200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
