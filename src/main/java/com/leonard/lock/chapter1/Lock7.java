package com.leonard.lock.chapter1;

/**
 * @author ChenJiWei
 * @version V1.0
 * @Description 可中断 不可中断
 * @date 2020/08/26
 */
public class Lock7 {

    /**
     * 可中断锁和不可中断锁。
     * 在 Java 中，synchronized 关键字修饰的锁代表的是不可中断锁，一旦线程申请了锁，就没有回头路了，只能等到拿到锁以后才能进行其他的逻辑处理。
     * 而我们的 ReentrantLock 是一种典型的可中断锁，例如使用 lockInterruptibly 方法在获取锁的过程中，突然不想获取了，那么也可以在中断之后去做其他的事情，不需要一直傻等到获取到锁才离开。
     */
}
