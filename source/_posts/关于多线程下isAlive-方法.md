---
title: 关于多线程下isAlive()方法
date: 2019-08-05 16:14:40
tags: [java,多线程]
---

#### isAlive()方法

> isAlive()方法的作用是测试线程是否处于活动状态。

- **活动状态：**线程已经启动尚未终止的状态即为活动状态

#### 代码分析

- 自定义线程类

    ```java
    package unit.one.mythread_1_4;
    
    /**
     * @author epochong
     * @date 2019/8/5 13:50
     * @email epochong@163.com
     * @blog epochong.github.io
     * @describe 自定义线程类
     */
    public class CountOperate extends Thread {
        public CountOperate() {
            System.out.println("构造-Thread.currentThread().getName() = " +Thread.currentThread().isAlive());
            System.out.println("构造-this.isAlive() = " + this.isAlive());
        }
        @Override
        public void run() {
            System.out.println("run-Thread.currentThread().getName() = " + Thread.currentThread().getName()+ ":" + Thread.currentThread().isAlive());
            System.out.println("run-this.getName() = " + this.getName() + ":" + this.isAlive());
        }
    }
    ```

- 测试类

    ```java
    package unit.one.mythread_1_4;
    
    /**
     * @author epochong
     * @date 2019/8/5 13:52
     * @email epochong@163.com
     * @blog epochong.github.io
     * @describe 测试类
     */
    public class Main {
        public static void main(String[] args) {
            CountOperate countOperate = new CountOperate();
            //countOperate.start();
            Thread thread = new Thread(countOperate);
            thread.setName("A");
            thread.start();
        }
    }
    ```

- 结果

    > 构造-Thread.currentThread().getName() = main:true
    > 构造-this.isAlive() = Thread-0:false
    > run-Thread.currentThread().getName() = A:true
    > run-this.getName() = Thread-0:false

    - 调用构造的是名为main的线程，main线程是活动状态
    - this-countOperate没有调用start()方法，为非活动状态
    - 调用start()方法的是thread对象，thread在测试类中命名为A，为活动状态
    - this是countOperate对象，未使用setName为默认名称，未调用start()方法，为非活动状态

- 改为一下测试

    ```java
    package unit.one.mythread_1_4;
    
    /**
     * @author epochong
     * @date 2019/8/5 13:52
     * @email epochong@163.com
     * @blog epochong.github.io
     * @describe 测试类
     */
    public class Main {
        public static void main(String[] args) {
            CountOperate countOperate = new CountOperate();
            countOperate.start();
            Thread thread = new Thread(countOperate);
            thread.setName("A");
            //thread.start();
        }
    }
    ```

- 结果

    > 构造-Thread.currentThread().getName() = main:true
    > 构造-this.isAlive() = Thread-0:false
    > run-Thread.currentThread().getName() = Thread-0:true
    > run-this.getName() = Thread-0:true

- 解释

    - `Thread.currentThread().getName()`表示调用者线程，为活动状态
    - `this`代表当前对象`countOperate`调用的start方法，为活动状态

#### 总结

- 是否存活看调用`start()`方法的是那一个线程对象
- `thread`和`countPerate`是两个线程对象，均未命名时为程序默认的名称从Thread-0开始命名
- 因为`countPerate`继承`Thread`类对应的getter、setter调用父类的方法