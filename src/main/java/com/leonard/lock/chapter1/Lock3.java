package com.leonard.lock.chapter1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ChenJiWei
 * @version V1.0
 * @Description 共享锁 独占锁
 * @date 2020/08/26
 */
public class Lock3 {


    /**
     * 共享锁指的是我们同一把锁可以被多个线程同时获得，
     *
     * 独占锁指的就是，这把锁只能同时被一个线程获得。
     *
     * 读写锁，就最好地诠释了共享锁和独占锁的理念。读写锁中的读锁，是共享锁，
     * 而写锁是独占锁。读锁可以被同时读，可以同时被多个线程持有，而写锁最多只能同时被一个线程持有。
     * @param args
     */
    public static void main(String[] args) {
        //
        ReentrantReadWriteLock.ReadLock readLock;
    }
}
