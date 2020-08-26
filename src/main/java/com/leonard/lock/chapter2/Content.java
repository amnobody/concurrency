package com.leonard.lock.chapter2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DESC: 悲观锁和乐观锁实质

 *
 * @author JiWei.Chen
 * @date 2020/08/26
 */
public class Content {

    /**
     * 典型场景：
     * 1. 悲观锁：synchronized 关键字和 Lock 接口
     * Java 中悲观锁的实现包括 synchronized 关键字和 Lock 相关类等，
     * 以 Lock 接口为例，例如 Lock 的实现类 ReentrantLock，类中的 lock() 等方法就是执行加锁，而 unlock() 方法是执行解锁。
     * 处理资源之前必须要先加锁并拿到锁，等到处理完了之后再解开锁，这就是非常典型的悲观锁思想。
     * 2.乐观锁：原子类
     * 乐观锁的典型案例就是原子类，例如 AtomicInteger 在更新数据时，就使用了乐观锁的思想，多个线程可以同时操作同一个原子变量。
     * 3.数据库实践
     * 使用select ... for update 则是悲观锁的体现
     *      (设置autocommit=0 begin ... commit) 涉及到索引或者主键命中 锁表或者行级锁
     * 如果使用版本号则是乐观锁的实践
     *  update table set status = 0 and version = 2 where id = x and version = 1
     *
     * 对比：
     * 虽然悲观锁确实会让得不到锁的线程阻塞，但是这种开销是固定的。悲观锁的原始开销确实要高于乐观锁，但是特点是一劳永逸，就算一直拿不到锁，也不会对开销造成额外的影响。
     * 反观乐观锁虽然一开始的开销比悲观锁小，但是如果一直拿不到锁，或者并发量大，竞争激烈，导致不停重试，那么消耗的资源也会越来越多，甚至开销会超过悲观锁。
     *
     * 使用场景：
     * 悲观锁适合用于并发写入多、临界区代码复杂、竞争激烈等场景，这种场景下悲观锁可以避免大量的无用的反复尝试等消耗。
     * 乐观锁适用于大部分是读取，少部分是修改的场景，也适合虽然读写都很多，但是并发并不激烈的场景。在这些场景下，乐观锁不加锁的特点能让性能大幅提高。
     */
    public static void main(String[] args) {
        synchronized (Content.class) {

        }

        Lock lock = new ReentrantLock();
        //悲观锁 操作资源之前先获取锁
        lock.lock();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //然后释放资源
            lock.unlock();
        }
    }
}
