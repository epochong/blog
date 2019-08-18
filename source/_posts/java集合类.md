---
title: java集合类
date: 2019-07-24 19:06:39
tags: java
---

### 类集

1. #### ArrayList、Vector、LinkedList关系与区别（初始化、扩容、线程安全）

   - ArrayList、Vector、LinkedList都属于List接口常用类，其中ArrayLsit、Vector基于数组实现，LinkedList基于链表实现
   - ArrayList采用懒加载策略，扩容为原先数组的1.5倍，采用异步处理，线程不安全，性能较高
     - **懒加载策略：**如果初始化不给参数初始化大小，默认不分配大小，当添加新元素的时候才分配大小，默认初始时大小是10
     - 在频繁查找、以及尾部的插入与删除场景下使用ArrayList
   - Vector当产生对象时就初始化内部数组（默认大小为10），扩容为原先数组2倍，采用synchronized同步方法，线程安全，性能很低（读读互斥：get方法被synchronized修饰）
   - LinkedList采用异步处理，线程不安全
     - 频繁在任意位置进行元素插入与删除使用LinkedList

2. #### jcl中fail-fast 、fail - safe

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

     > modCount 值是用来记录当前集合被修改的次数，每修改一次就进行加 1，而 exceptedModCount 是在 iterator 初始化是就已经指定的值，值为 exceptedModCount = modCount，对集合进行了三次 add 操作，所以 modCount=3，当代创建了 iterator 对象执行 exceptedModCount = modCount 语句对 exceptedModCount 进行了赋值操作，此时 exceptedModCount=3

     - modCount != expectModCount
       1. modCount：记录当前集合修改（结构化修改）次数
       2. expectModCount：记录获取集合迭代器时当前集合的修改次数

     - 代码

         ```java
         public class ListTest {
             public static void main(String[] args) {
                 ArrayList<String> arrayList = new ArrayList();
                 arrayList.add("wang");
                 arrayList.add("chong");
                 arrayList.add("fighting");
                 Iterator<String> iterator = arrayList.iterator();
                 while (iterator.hasNext()) {
                     String s = iterator.next();
                     if (s.equals("wang")) {
                         arrayList.remove("wang");
                     }
                     System.out.println(s);
                 }
             }
         }
         ```

         **报出异常**

         > Exception in thread "main" java.util.ConcurrentModificationException

         **分析**

         ```
         modCount：3
         expectModCount：3
         ```

         - 执行`arrayList.remove(“wang”);`时

             modCount加一 modCount = 4；

         - 在下一次循环`String s = iterator.next();`

         - 观察next()方法源码

             ```java
             @SuppressWarnings("unchecked")
             public E next() {
                 checkForComodification();
                 int i = cursor;//句柄，初始化为0执行一次next函数+1
                 if (i >= size)
                     throw new NoSuchElementException();
                 Object[] elementData = ArrayList.this.elementData;
                 if (i >= elementData.length)
                     throw new ConcurrentModificationException();
                 cursor = i + 1;
                 return (E) elementData[lastRet = i];
             }
             ```

         - 观察checkForComodification();方法源码

             ```java
             final void checkForComodification() {
                 if (modCount != expectedModCount)
                     throw new ConcurrentModificationException();
             }
             ```

         - 可以看出这个时候if条件成立抛出异常

     - 因此可以得到下列虽然看上去会报异常但是，并没有报的情况

         - 将上述代码的if条件改为

             ```java
             if (s.equals("chong")) {
                 arrayList.remove("chong");
             }
             ```

         - 输出

             ```java
             wang
             chong
             ```

         - 观察hasNext()方法源码

             ```java
             public boolean hasNext() {
                 return cursor != size;
             }
             ```

         > 这是因为，执行第二次next的时候之后，符合条件删除元素的值执行remove方法，arrayList.size ()变为2，此时cursor的值因为执行了两次next值刚好为2，当再执行下一次while循环中的hasNext()方法时因为cursor != size值为false没有执行，所以没有执行下一次的next方法，即没有执行checkForComodification()检查，从而无法报出异常

   - ConcurrentModificationException作用：避免多线程场景下数据脏读问题

   - fail-fast如何解决

     1. 遍历不要修改集合内容
     
     2. 使用迭代器内部的删除方法
     
         - iterator.remove()
     
         > Iterator 被创建之后会建立一个指向原来对象的单链索引表，当 list 删除元素里不会影响索引，Iterator.remove () 方法会在删除当前迭代对象的同时维护索引的一致性
     
     3. 使用fail-safe集合（比较快速失败）
        - **fail-fast集合：**java.util除了TreeMap以外的集合
        - **fail-safe：**所有juc包的集合（ConcurrentHashMap、CopyOnWriteArrayList）
     
        **观察CopyOnWriteArrayList源码**
     
        > CopyOnWriteArrayList 会先复制一个集合副本，当对集合进行修改时修改的是副本里的值，修改完后再将原来集合的引用指向这个副本，避免抛出 ConcurrentModificationException 异常
     
        ```java
        public E remove(int index) {
                final ReentrantLock lock = this.lock;
                lock.lock();
                try {
                    Object[] elements = getArray();
                    int len = elements.length;
                    E oldValue = get(elements, index);
                    int numMoved = len - index - 1;
                    if (numMoved == 0)
                        setArray(Arrays.copyOf(elements, len - 1));
                    else {
                        Object[] newElements = new Object[len - 1];
                        System.arraycopy(elements, 0, newElements, 0, index);
                        System.arraycopy(elements, index + 1, newElements, index,
                                         numMoved);
                        setArray(newElements);
                    }
                    return oldValue;
                } finally {
                    lock.unlock();
                }
            }
        ```

3. #### Set与Map的关系

   

4. #### hashCode与equals的关系

- hashCode () 方法和 equal () 方法的作用其实一样，在 Java里都是用来对比两个对象是否相等一致，那么 equal () 既然已经能实现对比的功能了，为什么还要 hashCode () 呢？

- 因为重写的 equal（）里一般比较的比较全面比较复杂，这样效率就比较低，而利用 hashCode () 进行对比，则只要生成一个 hash 值进行比较就可以了，效率很高，那么 hashCode () 既然效率这么高为什么还要 equal () 呢？

- 因为 hashCode () 并不是完全可靠，有时候不同的对象他们生成的 hashcode 也会一样（生成 hash 值得公式可能存在的问题），所以 hashCode () 只能说是大部分时候可靠，并不是绝对可靠。所以得出以下结论

   - hashCode返回相等，equals不一定相等
   - equals返回相等，hashCode一定相等
   
- <p><font color = "red">总的来说</font></p>

   所有对于需要大量并且快速的对比的话如果都用 equal () 去做显然效率太低，所以解决方式是，每当需要对比的时候，首先用 hashCode () 去对比，如果 hashCode () 不一样，则表示这两个对象肯定不相等（也就是不必再用 equal () 去再对比了）, 如果 hashCode () 相同，此时再对比他们的 equal ()，如果 equal () 也相同，则表示这两个对象是真的相同了，这样既能大大提高了效率也保证了对比的绝对正确性！

- <p><font color = red> 关于HashSet中检查重复元素的原理

   > hashset 里要求对象不能重复，则他内部必然要对添加进去的每个对象进行对比，而他的对比规则就是像上面说的那样，先 hashCode ()，如果 hashCode () 相同，再用 equal () 验证，如果 hashCode () 都不同，则肯定不同，这样对比的效率就很高了

   - hashCode () 和 equal () 都是基本类 Object 里的方法，Object 里 hashCode () 里面只是返回当前对象的地址（两个对象的==操作的判断），如果是这样的话，相同的一个类，new 两个对象，由于他们在内存里的地址不同，则他们的 hashCode（）不同，所以这显然不是我们想要的，所以我们必须重写我们类的 hashCode () 方法，根据属性值计算哈希值

- <p><font color = 'red'>常用的哈希码的算法

   1. Object 类的 hashCode. 返回对象的内存地址经过处理后的结构，由于每个对象的内存地址都不一样，所以哈希码也不一样。

   2. String 类的 hashCode. 根据 String 类包含的字符串的内容，根据一种特殊算法返回哈希码，只要字符串所在的堆空间相同，返回的哈希码也相同。

       **String类hashCode()源码：**只要字符串字面值相同hashCode就相同

       ```java
       public int hashCode() {
           int h = hash;
           if (h == 0 && value.length > 0) {
               char val[] = value;
       
               for (int i = 0; i < value.length; i++) {
                   h = 31 * h + val[i];
               }
               hash = h;
           }
           return h;
       }
       ```

   3. Integer 类，返回的哈希码就是 Integer 对象里所包含的那个整数的数值，例如 Integer i1=new Integer (100),i1.hashCode 的值就是 100 。由此可见，2 个一样值相同的 Integer 对象，返回的哈希码也一样。

- <p><font color = blue>总结一哈
       
   </p>

   	1. equals 方法用于比较对象的内容是否相等（覆盖以后）
    	2. hashcode 方法只有在集合中用到
    	3. 将对象放入到集合中时，首先判断要放入对象的 hashcode 值与集合中的任意一个元素的 hashcode 值是否相等，如果不相等直接将该对象放入集合中。如果 hashcode 值相等，然后再通过 equals 方法判断要放入对象与集合中的任意一个对象是否相等，如果 equals 判断不相等，直接将该元素放入到集合中，否则不放入。

5. ##### Java中实现一个类的两个对象大小比较的方式（内部排序、外部排序）

   1. <p><font color = red>实现Comparable接口（内部排序接口）

       > 若一个类实现了Comparable接口，就意味着“**该类支持排序**”。 即然实现Comparable接口的类支持排序，假设现在存在“实现Comparable接口的类的对象的List列表(或数组)”，则该List列表(或数组)可以通过 Collections.sort（或 Arrays.sort）进行排序。  

       > “实现Comparable接口的类的对象”可以用作“有序映射(如TreeMap)”中的键或“有序集合(TreeSet)”中的元素， 而不需要指定比较器。

   - **Comparable** **定义**

       ```java
       public interface Comparable<T> { 
       public int compareTo(T o); 
       }
       ```

       - 说明

           > 1. 返回负数:表示当前对象小于比较对象 
           >
           > 2. 返回0:表示当前对象等于目标对象 
           >
           > 3. 返回正数:表示当前对象大于目标对象

   2. <p><font color = red>Comparator(外部排序接口)
           
       </p>

       > 控制某个类的次序，而该类本身不支持排序(即没有实现Comparable接口。言外之意实现Comparable的类只能实现升序或降序，一旦设置就确定了，但是Compartor实现的比较器可以根据比较器的不同实现不同的排序，不用改变源代码)，这个“比较器”只需要实现Comparator接口即可。

   - Comparator定义

       ```java
       package java.util; 
       
       public interface Comparator<T> { 
       int compare(T o1, T o2); 
       boolean equals(Object obj); 
       } 
       ```

       - 说明

           > compare和Comparable的compareTo意义一样
           >
           > equals方法在Object中已经存在，

   <p><font color = green>Comparable接口与Compartor接口的关系：</font></p>
   - Comparable是排序接口，若一个类实现了Comparable接口，意味着该类支持排序

         是一个内部比较器(自己去和别人比)

   - Compartor接口是比较器接口，类本身不支持排序，专门有若干个第三方的比较器(实
       现了Compartor接口的类)来进行类的排序，是一个外部比较器(策略模式)

   <p><font  colort = blue>

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

