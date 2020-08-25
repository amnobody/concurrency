package com.leonard.concurrency.chapter5;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * DESC: 线程池常用的阻塞队列
 * 常见线程池的阻塞队列
 * FixedThreadPool LinkedBlockingQueue
 * SingleThreadPool
 *
 * CachedThreadPool SynchronousQueue(因为一旦有任务被提交就直接转发给线程或者创建新线程来执行，而不需要另外保存它们)
 *  队列不会存储任务 所以线程池会无限制创建线程
 *
 * ScheduledThreadPool DelayedWorkQueue
 * SingledScheduledThreadPool
 * @author JiWei.Chen
 * @date 2020/08/25
 */
public class Content {

    public static void main(String[] args) {
        DelayQueue queue;
        ScheduledThreadPoolExecutor executor;
    }
}
