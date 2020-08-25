package com.leonard.concurrency.chapter4;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author ChenJiWei
 * @version V1.0
 * @Description
 * @date 2020/08/22
 */
public class Content {

    /**
     * 1.corePoolSize = maximumPoolSize
     * FixedThreadPool
     */
    public void FixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
    }

    /**
     * 2.create thread as long as tasks coming
     * points:
     * - 60s alive
     * - SynchronousQueue
     * - corePoolSize = 0
     */
    public void CachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    }

    /**
     * 3.schedule
     * - 定时（体现在延迟某一时间后执）
     * - 周期
     * <p>
     * executor.schedule()
     */
    public static void ScheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        /**
         * 1.延迟执行
         */
        scheduledThreadPool.schedule(new PoolTask(), 10, TimeUnit.DAYS);

        /**
         * 2.首次延迟 之后每隔一段时间执行
         */
        scheduledThreadPool.scheduleAtFixedRate(new PoolTask(), 2, 10, TimeUnit.SECONDS);

        /**
         * 3.首次延迟 之后每隔一段时间执行
         */
        scheduledThreadPool.scheduleWithFixedDelay(new PoolTask(), 2, 5, TimeUnit.SECONDS);

        /**
         * <p>重点：</b>
         * 方法2和3区别
         * 在于首次延迟之后周期性任务执行的开始时间节点
         * 方法2：固定的时间频率
         * 方法3：上次任务执行完成之后
         */
    }

    /**
     * 4特点：只会有一个线程去执行任务
     * 如果线程在执行任务过程中发生异常，线程池会创建另外一个线程去执行剩余任务
     * 适合 所有的任务顺序执行
     */
    public static void singleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            singleThreadExecutor.submit(new PoolTask());
        }
    }

    /**
     * 第五种：
     * 同scheduledExecutor 不同点：设置corePoolSize=1
     */
    public void singleThreadScheduledExecutor() {
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * 第六种：适合分裂子任务
     */
    public static void forkJoinPool() {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(new FiTask(30));
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static class FiTask extends RecursiveTask<Integer> {

        int n;

        public FiTask(int n) {
            this.n = n;
        }

        @Override
        protected Integer compute() {
            FiTask f1 = new FiTask(n - 1);
            FiTask f2 = new FiTask(n - 2);
            if (n <= 1) {
                return n;
            }
            f1.fork();
            f2.fork();
            return f1.join() + f2.join();
        }
    }


    public static class PoolTask implements Runnable {
        @Override
        public void run() {
            Random random = new Random();
            int i = random.nextInt(4);
            System.out.println("开始" + LocalTime.now() + "...." + Thread.currentThread().getName() + "..." + i);
            if (i == 0) {
                System.out.println(Thread.currentThread().getName() + "执行任务发生异常");
                throw new RuntimeException(Thread.currentThread().getName() + "执行任务发生异常");
            } else {
                System.out.println("---");
            }
//            try {
//                Thread.sleep(i * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println("结束" + LocalTime.now() + "···" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        Runnable aNew = Content::new;
        System.out.println(aNew.getClass());
        Content content = new Content();
        System.out.println(content.getClass());

        Function<String,Integer> function = Integer::parseInt;
        Integer apply = function.apply("123");
        System.out.println(apply);
        BiFunction<String, Integer, Integer> stringIntegerIntegerBiFunction = Integer::parseInt;
    }

    /**
     * 1 2 3 4 5 6 7
     * 1 1 2 3 5 8 13
     *
     * @param n
     * @return
     */
    public static int fi(int n) {
        if (n == 2 || n == 1) {
            return 1;
        }
        return fi(n - 1) + fi(n - 2);
    }

}
