package com.slin.test;

/**
 * @author SongLin.Yang
 * @data 2016-04-26 09:14
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        SyncStack ss = new SyncStack();
        Producer p = new Producer(ss);
        Consumer c = new Consumer(ss);

        new Thread(p).start();
        new Thread(c).start();
    }
}
