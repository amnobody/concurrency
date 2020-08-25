package com.leonard.concurrency.chapter3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * DESC:线程池的四种拒绝策略
 *
 * @author JiWei.Chen
 * @date 2020/08/21
 */
public class Content {

    public void strategies() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,5, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));

        /**
         * 策略1：直接丢弃任务 并抛出异常
         * 优点：告诉提交任务的线程 任务提交失败
         */
        new ThreadPoolExecutor.AbortPolicy();

        /**
         * 策略2：丢弃队列中入队时间最长的任务 然后线程池调用execute执行任务 走线程加入线程池的流程(core queue max reject)
         * 优点：保存最近提交的任务
         * 缺点：可能造成数据丢失
         */
        new ThreadPoolExecutor.DiscardOldestPolicy();

        /**
         * 策略3：直接丢弃任务 无感知√√
         * 缺点：丢失任务数据
         */
        new ThreadPoolExecutor.DiscardPolicy();

        /**
         * 策略4：任务交给调用executor.submit(task)方法的线程去执行
         * 优点：
         *  1.负反馈，谁调用谁负责执行 不会丢失数据
         *  2.提交任务的线程去执行任务；空出时间给线程池执行未完成的任务并且该线程不会再提交任务
         * 缺点：
         *  可能会阻塞调用线程池的线程
       */
        new ThreadPoolExecutor.CallerRunsPolicy();
    }

}
