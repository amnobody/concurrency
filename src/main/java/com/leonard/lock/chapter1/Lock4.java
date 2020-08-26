package com.leonard.lock.chapter1;

/**
 * @author ChenJiWei
 * @version V1.0
 * @Description 公平锁 非公平锁
 * @date 2020/08/26
 */
public class Lock4 {

    /**
     *公平锁和非公平锁。
     *
     * 1.公平锁的公平的含义在于如果线程现在拿不到这把锁，那么线程就都会进入等待，开始排队，在等待队列里等待时间长的线程会优先拿到这把锁，有先来先得的意思。
     * 2.非公平锁就不那么“完美”了，它会在一定情况下，忽略掉已经在排队的线程，发生插队现象。
     *
     */

    public static void main(String[] args) {

    }

}
