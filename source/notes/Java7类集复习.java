类集:
1.ArrayList、Vector、LinkedList关系与区别
a.ArrayList、Vector、LinkedList都属于List接口常用子类，其中ArrayList、Vector基于数组实现,
LinkedList基于链表实现

b.ArrayList采用懒加载策略,扩容为原先数组的1.5倍,采用异步处理，线程不安全，性能较高。
在频繁查找以及尾部的插入与删除场景下使用ArrayList

c.Vector当产生对象时就初始化内部数组(默认大小为10),扩容为原先数组2倍,
采用synchronized同步方法，线程安全，性能很低(读读互斥)

d.LinkedList采用异步处理，线程不安全。
频繁在任意位置进行元素插入与删除使用LinkedList。


2.jcl中fail-fast?fail-safe
什么是快速失败策略?
优先考虑出现异常的情况，当异常产生时，抛出异常，程序终止。
    public static int div(int a,int b) {
        if (b == 0) {
            throw new IllegalArgumentException("除数不能为0!");
        }
        return a/b;
    }

java.util.ConcurrentModificationException

如何产生?
modCount != expectedModCount
modCount记录当前集合修改(结构化修改)的次数
expectedModCount记录获取集合迭代器时当前集合的修改次数

ConcurrentModificationException作用:避免多线程场景下数据脏读问题

fail-fast如何解决?
1.遍历不要修改集合内容
2.使用迭代器内部的删除方法
3.使用fail-safe集合
fail-fast集合:java.util除了TreeMap以外的所有集合
juc包的集合(ConcurrentHashMap、CopyOnWriteArrayList)都属于fail-safe

3.Set与Map的关系
4.hashCode与euqals的关系
hashCode返回相等,equlas不一定相等
equals返回相等,hashCode一定相等
5.Java中实现一个类的两个对象大小比较的方式(内部排序 外部排序)

6.HashMap、TreeMap、Hashtable的关系与区别
a.HashMap、TreeMap、Hashtable都是Map的常用子类，HashMap基于哈希表+红黑树(JDK1.8之后)
,Hashtable基于哈希表,TreeMap基于红黑树。

b.HashMap采用懒加载策略,采用异步处理，线程不安全，性能较高

c.Hashtable产生对象时初始化内部哈希表(默认大小为16),采用synchronized同步方法,线程安全，
性能很低(读读互斥)

d.关于null
HashMap K与V都允许为null
TreeMap K不为null，V可以为null
Hashtable K与V都不允许为null

7.HashMap内部源码解析(负载因子 树化策略 扩容策略 内部哈希算法)
负载因子float loadFactor(默认为0.75)
树化阈值int TREEIFY_THRESHOLD = 8;
解树化阈值int UNTREEIFY_THRESHOLD = 6;
容量int threshold=loadFactor * cap(哈希表长度 table.length);
当前集合元素个数int size;

树化逻辑:当前桶中链表的长度 >=8 并且 哈希表的长度 >= 64，
否则只是简单的扩容处理.

为何引入红黑树:优化链表过长导致查找性能急剧降低O(n) -> O(logn)

解树化:当红黑树节点个数在扩容或删除时个数 <=6,在下一次resize过程中会将红黑树退化为链表,节省空间.


负载因子loadFactor > 0.75 : 增加了哈希表的利用率,哈希冲突概率明显增加
负载因子loadFactor < 0.75 : 降低了哈希表的利用率,导致频繁扩容,哈希冲突概率明显降低


8.ConcurrentHashMap如何高效实现线程安全?

9.JDK1.7与JDK1.8 ConcurrentHashMap设计区别

JDK1.7 
Segment是ReentrantLock的子类
将Hashtable的整张表加锁,一把锁优化为16个Segment(16把锁),锁的是当前的Segement
不同Segement之间还是异步.
Segement初始化为16之后不可再扩容.
结构:16Segment+哈希表

JDK1.8
ConcurrentHashMap锁进一步细化，结构类似于HashMap.哈希表+红黑树，锁当前桶的头结点,锁的个数进一步提升(锁个数会随着哈希表扩容而增加),支持的并发线程数进一步提升。
使用CAS+synchronized来保证线程安全

homework:
HashMap源码看一遍


