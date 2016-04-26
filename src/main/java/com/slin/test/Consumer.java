package com.slin.test;

/**
 * @author SongLin.Yang
 * @data 2016-04-26 09:12
 */
public class Consumer implements Runnable {

    SyncStack ss = null;
    Consumer(SyncStack ss){
        this.ss = ss;
    }
    public void run() {
        for (int i = 0; i < 20; i++) {
            ss.pop();

            try {
                Thread.sleep((int) (Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
