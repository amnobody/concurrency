package com.leonard.lock.chapter1;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ChenJiWei
 * @version V1.0
 * @Description 偏向锁 轻量级锁 重量级锁
 * @date 2020/08/26
 */
public class Lock1 {

    /**
     * 第一种分类是偏向锁/轻量级锁/重量级锁，这三种锁特指 synchronized 锁的状态，
     * 通过在对象头中的 mark word 来表明锁的状态。
     *
     * 1.偏向锁
     * 如果自始至终，对于这把锁都不存在竞争，那么其实就没必要上锁，只需要打个标记就行了，这就是偏向锁的思想。
     * 一个对象被初始化后，还没有任何线程来获取它的锁时，那么它就是可偏向的，
     * 当有第一个线程来访问它并尝试获取锁的时候，它就将这个线程记录下来，
     * 以后如果尝试获取锁的线程正是偏向锁的拥有者，就可以直接获得锁，开销很小，性能最好。
     *
     * 2.轻量级锁
     * JVM 开发者发现在很多情况下，synchronized 中的代码是被多个线程交替执行的，而不是同时执行的，
     * 也就是说并不存在实际的竞争，或者是只有短时间的锁竞争，用 CAS 就可以解决，
     * 这种情况下，用完全互斥的重量级锁是没必要的。轻量级锁是指当锁原来是偏向锁的时候，
     * 被另一个线程访问，说明存在竞争，那么偏向锁就会升级为轻量级锁，线程会通过自旋的形式尝试获取锁，而不会陷入阻塞。
     *
     * 3.重量级锁
     * 重量级锁是互斥锁，它是利用操作系统的同步机制实现的，所以开销相对比较大。
     * 当多个线程直接有实际竞争，且锁竞争时间长的时候，轻量级锁不能满足需求，锁就会膨胀为重量级锁。
     * 重量级锁会让其他申请却拿不到锁的线程进入阻塞状态。
     *
     * 锁升级：
     * 无 -> 偏向锁 -> 轻量级 -> 重量级
     */


    /**
     * 题外：cas
     * compare and swap 解决多线程并行情况下使用锁造成性能损耗的一种机制。
     * 实现思想(v a b) v为内存地址 a预期原值 b为新值
     * 如果内存地址的值 跟 预期原值a 匹配 则更新为b
     * 自旋cas(v,a,b)操作 while
     * <p>
     * cas操作必须由处理器，具有原子性，且不允许中断
     * <p>
     * <p>
     * jdk8中cas
     * Unsafe类 sun.misc包下
     * 提供方法：
     * 内存管理、操作对象、累、变量等
     *
     * cas缺点：
     * 1.可能导致A - B - A问题，线程1获取A值并未修改之前，另外线程先从A改为B然后改为A
     * - 添加版本号校验(AtomicStampedReference jdk5之后)
     * 2.多个线程并发情况，自旋可能很长时间不会成功，会增大cpu的开销
     * 3.只能对一个变量进行原子操作(jdk5之后增加 AtomicReference 可以把多个对象放入到一个对象中)
     */

    public static void main(String[] args) {
        Unsafe unsafe = Unsafe.getUnsafe();
        /**
         * param1:要操作的对象
         * param2：要操作对象某个属性的地址偏移值 类似User@5caf905d
         * param3: 期望值
         * param4：新值
         *
         * 如果cas成功 返回oldValue = oldValue + newValue
         * 如果cas失败 自旋至成功为止
         */
        String oldName = "jack";
        String newName = "jack ma";
        unsafe.compareAndSwapObject(new User(), 1878L, oldName, newName);

        AtomicReference atomicReference = new AtomicReference();
    }

    public static class User{
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
