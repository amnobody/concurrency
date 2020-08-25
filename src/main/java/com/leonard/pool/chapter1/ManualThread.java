package com.leonard.pool.chapter1;

import org.springframework.util.StopWatch;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * DESC:
 *
 * @author JiWei.Chen
 * @date 2020/08/17
 */
public class ManualThread {

    public static void main(String[] args) {
        test();
    }

    /**
     * 创建一万个任务执行
     */
    public static void test() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        AtomicInteger atomicInt = new AtomicInteger();
        for (int i = 0; i < 10000; i++) {
            new Thread(new SimpleTask(atomicInt)).start();
        }
        stopWatch.stop();
        System.out.println(String.format("耗时=%ss", stopWatch.getTotalTimeSeconds()));
    }
}
