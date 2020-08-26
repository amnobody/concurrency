package com.leonard.lock.chapter1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ChenJiWei
 * @version V1.0
 * @Description 可重入和非可重入
 * @date 2020/08/26
 */
public class Lock2 {

    /**
     * 可重入锁指的是线程当前已经持有这把锁了，能在不释放这把锁的情况下，再次获取这把锁。
     * 同理，不可重入锁指的是虽然线程当前持有了这把锁，但是如果想再次获取这把锁，也必须要先释放锁后才能再次尝试获取。
     *
     * 对于可重入锁而言，最典型的就是 ReentrantLock，正如它的名字一样，reentrant 的意思就是可重入，它也是 Lock 接口最主要的一个实现类。
     */
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
