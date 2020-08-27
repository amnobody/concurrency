package com.leonard.lock.chapter3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DESC: synchronized背后的monitor锁
 *
 * 通过javap -verbose xxx.class 反汇编查询synchronized作用
 * 1.synchronized修饰对象
 *  可以看到有monitor enter 两个monitor exit
 * 线程执行三种情况：
 *  - 遇到monitor锁计数为0 则持有锁并将计数器+1
 *  - 遇到monitor锁计数非0 如果获取锁的线程就是持有monitor锁的线程 则计数器继续递增
 *  - 遇到monitor锁计数非0 如果获取锁的线程是其他线程 则阻塞等待monitor释放
 *
 * 2.synchronized修饰方法
 * acc_synchronize标志 如果线程发现修饰标志则会尝试获取monitor锁。其他情况类似synchronized修饰对象
 *
 * @author JiWei.Chen
 * @date 2020/08/26
 */
public class Content {

    /**
     * 含义：
     * 1.公平锁指的是按照线程请求的顺序，来分配锁；
     * 2.非公平锁指的是不完全按照请求的顺序，在一定情况下，可以允许插队。
     * 但需要注意这里的非公平并不是指完全的随机，不是说线程可以任意插队，而是仅仅“在合适的时机”插队
     * <p>
     * 为什么要有非公平锁：
     * 常理推断公平锁更加公平，为什么需要非公平锁。
     * <p>
     * 优劣对比：
     * 主要体现在两个指标
     * 1.系统吞吐量
     * 2.是否会造成线程饥饿(非公平锁下，一直有线程抢占插队，导致排队线程没有机会执行)
     * <p>
     * 使用场景：
     * ReentrantLock默认使用非公平锁
     */

    public static class Job implements Runnable {

        private ReentrantLock lock;

        public Job(ReentrantLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            //第一次获取锁 然后释放 然后重新获取情况
            lock.lock();
            try {
                System.out.println(String.format(Thread.currentThread().getName() + "______do something ..."));
                Thread.sleep(10000);
            }catch (Exception e){

            } finally {
                lock.unlock();
            }

            //第2次获取锁 然后释放 然后重新获取情况
            lock.lock();
            try {
                System.out.println(String.format(Thread.currentThread().getName() + "______do something >>>"));
                Thread.sleep((long)Math.random() * 10000);
            } catch (Exception e){

            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Job(lock));
            thread.setName("t" + i);
            list.add(thread);
        }

        for (int i = 0; i < list.size(); i++) {
            list.get(i).start();
            Thread.sleep(100);
        }
    }
}
