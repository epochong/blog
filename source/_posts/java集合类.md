---
title: java集合类
date: 2019-07-24 19:06:39
tags: java
---

#### 类集

1. ##### ArrayList、Vector、LinkedList关系与区别（初始化、扩容、线程安全）

   - ArrayList、Vector、LinkedList都属于List接口常用类，其中ArrayLsit、Vector基于数组实现，LinkedList基于链表实现
   - ArrayList采用懒加载策略，扩容为原先数组的1.5倍
     - 在频繁查找、以及尾部的插入与删除场景下使用ArrayList
   - Vector当产生对象时就初始化内部数组（默认大小为10），扩容为原先数组2倍，采用synchronized同步方法，线程安全，性能很低（读读互斥）
   - LinkedList采用异步处理，线程不安全
     - 频繁在任意位置进行元素插入与删除使用LinkedList

2. ##### jcl中fail-fast 、fail - safe

   - **快速失败策略：**优先考虑出现异常的情况，当异常产生时，抛出异常，程序终止

     ```java
         public static int div(int a,int b) {
             if (b == 0) {
                 throw new IllegalArgumentException("除数不能为0!");
             }
             return a/b;
         }
     java.util.ConcurrentModificationException
     ```

   - 如何产生

     - modCount != expectModCount
       1. modCount：记录当前集合修改（结构化修改）次数
       2. expectModCount：记录获取集合迭代器时当前集合的修改次数

   - ConcurrentModificationException作用：避免多线程场景下数据脏读问题

   - fail-fast如何解决

     1. 遍历不要修改集合内容
     2. 使用迭代器内部的删除方法
     3. 使用fail-safe集合（比较快速失败）
        - **fail-fast集合：**java.util除了TreeMap以外的集合
        - **fail-safe：**所有juc包的集合（ConcurrentHashMap、CopyOnWriteArrayList

3. ##### Set与Map的关系

   

4. ##### hashCode与equals的关系

   - hashCode返回相等，equals不一定相等
   - equals返回相等，hashCode一定相等

5. ##### Java中实现一个类的两个对象大小比较的方式（内部排序、外部排序）

   - 

6. ##### HashMap、TreeMap、Hashtable的关系与区别

   - HashMap、TreeMap、Hashtable都是Map的常用子类，HashMap基于哈希表+红黑树（JDK1.8，Hashtable基于哈希表，TreeMap基于红黑树）
   - HashMap采用懒加载策略，采用异步处理，线程不安全，性能较高
   - Hashtable产生对象时初始化内部哈希表（默认大小为16），采用synchronized同步方法，线程安全，性能很低（读读互斥）
   - 关于null
     - HashMap：K和V都可以为null
     - Hashtable：K和V都不能为null
     - TreeMap中：K不为null，V可以为null

7. ##### HashMap内部源码解析（负载因子、树化策略、扩容策略、内部哈希算法）

   - **负载因子：**final float loadFactor;
     - **>0.75:**增加了哈希表的利用率，哈希冲突概率明显增加
     - **<0.75:**降低了哈希表的利用率，哈希冲突概率明显减小
   - **树化阈值：**static final int TREEIFY_THRESHOLD = 8;
     - **树化逻辑：**当前啊桶中链表的长度>= 8并且哈希表的长度 >=64，否则知识简单的扩容处理
     - **为何引入红黑树：**优化链表过长导致查找性能急剧降低O(N) -> O(logn)
   - **解树化阈值：**static final int UNTREEIFY_THRESHOLD = 6;
     - 当红黑树节点个数在扩容或删除时个数<=6，在下一次resize过程中会将红黑树退化为链表，节省空间
   - **容量：**int threshold = cap(哈希表长度 table.length) * loadFactor;

   ```java
        public V put(K key, V value) {
           return putVal(hash(key), key, value, false, true);
       }
       /*
         内部哈希实现：
           保留高16位（保留有效位数），让高低16位都参与异或运算，降低哈希冲突的概略
         为何不直接使用hashCode方法
           返回值普遍较大，需要开辟大量空间
        */
   
       static final int hash(Object key) {
           int h;
           return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
       }
   
   /*
   为何哈希表长度必须为2^n?
       保证哈希表中所有索引位置都可能被访问到
       16(10000)
   
   HashMap中元素真正的所以下标计算
       (n - 1) & hash
       (01111) & hash : 值都在16之内
   
   */
   final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                      boolean evict) {
           Node<K,V>[] tab; Node<K,V> p; int n, i;
            // 此时哈希表还未初始化，完成初始化操作
           if ((tab = table) == null || (n = tab.length) == 0)
               n = (tab = resize()).length;
           // 此时哈希表对应的索引下标未存储元素
           if ((p = tab[i = (n - 1) & hash]) == null)
               tab[i] = newNode(hash, key, value, null);
           // 哈希表初始化并且对应的索引下标有元素
           else {
               Node<K,V> e; K k;
               // 此时冲突位置的key值与要保存的元素key值相等,只需要将value替换
               if (p.hash == hash &&
                   ((k = p.key) == key || (key != null && key.equals(k))))
                   e = p;
               // 此时链表已经树化，采用红黑树方式存储新节点
               else if (p instanceof TreeNode)
                   e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
               // 采用链表尾插新节点
               else {
                   for (int binCount = 0; ; ++binCount) {
                       if ((e = p.next) == null) {
                           p.next = newNode(hash, key, value, null);
                           if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                               treeifyBin(tab, hash);
                           break;
                       }
                       if (e.hash == hash &&
                           ((k = e.key) == key || (key != null && key.equals(k))))
                           break;
                       p = e;
                   }
               }
               if (e != null) { // existing mapping for key
                   V oldValue = e.value;
                   if (!onlyIfAbsent || oldValue == null)
                       e.value = value;
                   afterNodeAccess(e);
                   return oldValue;
               }
           }
           ++modCount;
           if (++size > threshold)
               resize();
           afterNodeInsertion(evict);
           return null;
       }
   ```

8. ##### ConcurrentHashMap如何高效实现线程安全

9. ##### JDK1.7与JDK1.8 ConcurrentHashMap设计区别

   - **JDK1.7：**Segment是ReentrantLock的子类，将Hashtable的整张表加锁，一把锁优化为16个Segment（16把锁），锁的是当前的Segment不同Segement之间还是异步，Segment初始化为16后不可在扩容（结构：16Segment + 哈希表）
   - **JDK1.8：**锁进一步细化，结构类似于HashMap(哈希表+红黑树)锁当前同的头节点，锁的个数进一步提升（锁的个数会随着哈希表扩容而增加），支持的并发线程数进一步提升，使用CAS+synchronized来保证线程安全

