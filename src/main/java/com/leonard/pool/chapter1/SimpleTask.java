package com.leonard.pool.chapter1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESC: 简单任务
 *
 * @author JiWei.Chen
 * @date 2020/08/17
 */
public class SimpleTask implements Runnable {

    private AtomicInteger atomicInteger;

    public SimpleTask(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }

    @Override
    public void run() {
        atomicInteger.incrementAndGet();
        System.out.println(Thread.currentThread().getName());
    }
}
