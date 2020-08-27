package com.leonard.lock.chapter4;

import java.util.concurrent.locks.ReentrantLock;

/**
 * DESC: synchronize和lock比较
 * 
 *
 * @author JiWei.Chen
 * @date 2020/08/26
 */
public class Content {

    public static final ReentrantLock lock = new ReentrantLock();

    public static ReentrantLock getLock() {
        return lock;
    }

    public static void main(String[] args) {
        Content content1 = new Content();
        Content content2 = new Content();
        System.out.println(content1.lock.toString());
        System.out.println(content2.lock.toString());
    }
}
