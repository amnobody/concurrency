package com.leonard.pool.chapter2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESC: 线程池6个核心参数
 *
 * @author JiWei.Chen
 * @date 2020/08/18
 */
public class ThreadPoolTest {

    private final AtomicInteger poolSize = new AtomicInteger(1);
    private final AtomicInteger threadNum = new AtomicInteger(1);

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10,
                10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
    }
}
