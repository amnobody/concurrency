package com.leonard.pool.chapter1;

import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESC:
 *
 * @author JiWei.Chen
 * @date 2020/08/17
 */
public class PoolThread {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        AtomicInteger atomicInt = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            executorService.submit(new SimpleTask(atomicInt));
        }
        stopWatch.stop();
        System.out.println(String.format("耗时=%ss,累加=%s", stopWatch.getTotalTimeSeconds(), atomicInt.get()));
    }
}
