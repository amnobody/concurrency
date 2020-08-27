package com.leonard.lock.chapter5;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * DESC: 锁的讲解
 * 为什么要进行锁的降级：
 * 如果我们在刚才的方法中，一直使用写锁，最后才释放写锁的话，虽然确实是线程安全的，但是也是没有必要的，因为我们只有一处修改数据的代码：
 * obj = new Object();
 * 后面我们对于 data 仅仅是读取。
 * 如果还一直使用写锁的话，就不能让多个线程同时来读取了，持有写锁是浪费资源的，降低了整体的效率，所以这个时候利用锁的降级是很好的办法，可以提高整体性能。
 *
 * 为什么不直接释放读锁：
 * 因为我们还要打印(或者理解为返回 缓存对象） 一开始加读锁是为了在读取过程中，不会有其他线程修改了缓存
 *
 * 读读操作可以多个线程同时操作
 * 先获取写锁 在不释放写锁的情况下 获取读锁 然后释放写锁的操作在一个线程中发生叫做读写锁的降级
 *
 * @author JiWei.Chen
 * @date 2020/08/27
 */
public class CacheManage {

    private Object object;
    private boolean cacheValid;

    final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    void updateCache() {
        //首先获取读锁
        reentrantReadWriteLock.readLock().lock();
        if (!cacheValid) {
            //如果缓存失效 释放读锁 获取写锁
            reentrantReadWriteLock.readLock().unlock();
            reentrantReadWriteLock.writeLock().lock();

            //这里需要重新判断缓存是否有效 因为在上一步 释放读 获取写的间隙 可能会有其他线程修改数据
            if (!cacheValid) {
                //模拟重新设置缓存数据
                object = new Object();
                cacheValid = true;
            }
            //不释放写锁 这里直接获取读锁 这就是读写锁的降级
            reentrantReadWriteLock.readLock().lock();

            //do some thing

            //然后释放写锁 但是仍保持读锁
            reentrantReadWriteLock.writeLock().unlock();
        }

        //最后
        System.out.println(object);
        reentrantReadWriteLock.readLock().unlock();
    }
}
