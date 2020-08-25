package com.leonard.concurrency.chapter7;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * DESC: 合适的线程数量与cpu之间的关系
 * 影响线程数两个重要因素：
 * 1. 任务的类型；分为任务密集型(需要占用cpu时间多；如加解密等)和IO型(耗时)
 * 2. 线程平均工作时间和线程平均等待时间 thread_num = cpu_num*(1+平均等待/平均耗时)
 * 粗略可以设置在1.5倍的核心数 具体指标可以压测观察cpu负载和jvm线程数
 *
 * 如何根据实际情况合理设置线程数量
 *
 * 线程池线程复用的原理
 *  对线程进行包装 一个worker对应一个thread
 *
 * 线程池结束的方法和判断标志
 *  await
 *  is shutdown
 *  is terminated
 *  shut
 *  shut now
 *
 * 着重分析ThreadPool的execute方法
 * work.runTask()方法 work内部封装的线程不断从工作队列中取出task.然后执行task.run方法 这样就避免了直接使用thread构造runnable(task)
 * 调用start方法创建线程，线程执行完销毁导致浪费系统资源的情况
 *
 * @author JiWei.Chen
 * @date 2020/08/25
 */
public class Content {

    public static void main(String[] args) {

        Thread thread = new Thread(()->{

        });
        ThreadPoolExecutor executor;
    }
}
