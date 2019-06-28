---
title: Lock体系相关
date: 2019-03-03 16:52:20
tags: [java,多线程]
---
###### Future
取得Callable接口返回值
get():V当前线程阻塞直到有返回值

###### FutureTask
在多线程并发下可以保证任务(传入的Callable或Runnable)只执行一次

[18:50]
###### 线程中提交方法
```
void execute(Runnable r);
V submit(Runnable )
```
###### CompletableFuture接口(自学)
jdk1.8新增处理异步任务的接口
###### 多线程三大核心
**分工:**
创建多个子线程执行不同的任务
jdk1.7Fork/Join 单机版人的Map/Reduce

**互斥**
任意一个时刻只有一个线程在操作临界区

```
synchronized
Lock(为解决死锁而生)
```

**同步**
线程间通信

```
wait、notify
Comdition
```
#### Lock体系
###### 死锁产生的四个必要条件
只有当以下四个条件同时满足时,程序才会死锁
1. **互斥:** 共享资源X与Y只能被一个线程占用
2. **占有且等待:** 线程T1已经拥有共享资源X,在等待共享资源Y的时候,不释放X
3. **不可抢占:** 其他线程不能强行T1线程持有的资源X
4. **循环等待**
###### Lock接口常用方法
- **void lock()** 阻塞式获取锁

- **boolean tryLock()** 非阻塞获取锁,获取成功执行任务返回true否则返回false线程直接退出
- **void lockInterruptibly() throws InterruptedException** 获取锁时响应中断

- **boolean tryLock(long time, TimeUnit unit) 
throws InterruptedException** 获取锁时支持超时,在指定时间内未获取到锁,线程直接退出
**Condition** newCondition();获取绑定到该lock对象的等待队列,每当调用一次就产生一个新的等待队列

```
try {
	lock.lock();
}finally {
	lock.unlock();
}
```

###### ==sychronized与Lock的关系与区别==
1. sychronized是JVM级别的锁，属于Java中的关键字，使用sychronized，加锁与解锁都是隐式的;Lock是Java层面的锁，加锁与解锁都需要显示使用。
2. lock可以提供一些synchronized不具备的功能，如响应中断、超时获取、非阻塞式获取、公平锁、共享锁如读写锁
3. sychronized的等待队列只有一个，而同一个Lock可以拥有多个等待队列(多个Condition对象)

**AQS:同步包装器**

基于链表的一个队列,此队列包装类线程节点,所有获取锁失败的次三成都被包装为队列节点进入AQS中排队获取锁。

###### lock与AQS的关系
lock提供锁获取成功与否的状态给AQS，AQS根据此状态来确定是否将线程置入AQS中。

###### 公平锁与非公平锁:
**公平锁:** 等待时间最长的线程优先获取到锁,只有lock有,lock默认采用非公平锁

**读写锁:**
Lock中的ReentrantReadWriteLock实现读写锁

**线程间通信:** Condition 搭配 Lock
await()
signal()/signalAll()



==kafka:分布式消息队列==
