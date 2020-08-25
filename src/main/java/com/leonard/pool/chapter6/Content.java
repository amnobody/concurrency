package com.leonard.pool.chapter6;

import java.util.concurrent.SynchronousQueue;

/**
 * DESC: 为什么需要手动创建线程池
 *
 * 分析Executors创建线程池缺点，就应该从阻塞队列入手
 * LinkedBlockingQueue 和  DelayWorkQueue
 *  同样在线程池中近乎无界队列 可能导致无限添加任务，触发OOM
 * SynchronousQueue
 *  转发任务，本身并没有问题。Cached会因无限制创建线程，导致资源耗尽
 *
 * @author JiWei.Chen
 * @date 2020/08/25
 */
public class Content {


    public static void main(String[] args) {
        SynchronousQueue queue = new SynchronousQueue();
    }
}
