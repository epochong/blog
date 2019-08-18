---
title: 集合的remove方法引起的数组越界问题
date: 2019-07-16 11:10:40
tags: [集合,algorithms]
---

> 本文主要讲述我在做算法题时，运用集合中的remove方法时所引发的数组越界异常的问题

- 先看原问题

![题目](https://epochong-1259598883.cos.ap-chengdu.myqcloud.com/mubang.jpg )

```
 {% asset_img 木棒拼图.jpg 题目%}
```

- 问题解析

  1. 创建集合
  2. 添加、删除元素
  3. 从下到大排序元素
  4. 前n-1个元素的和大于最后一个元素即为Yes，否则为No

- 有坑的解法

  ```java
  import java.util.ArrayList;
  import java.util.Collections;
  import java.util.List;
  import java.util.Scanner;
  
  /**
   * @author epochong
   * @date 2019/7/15 22:51
   * @email epochong@163.com
   * @blog epochong.github.io
   * @describe
   */
  public class 木棒拼图 {
      public static void main(String[] args) {
          Scanner input = new Scanner(System.in);
          while (input.hasNextInt()) {
              int n = input.nextInt();
              List<Integer> list = new ArrayList <>();
              for (int i = 0; i < n; i++) {
                  int op = input.nextInt();
                  int len = input.nextInt();
                  if (op == 1) {
                      list.add(len);
                  } else {
                      //坑在这，只要改为list.remove(new Integer(len))即可
                      list.remove(len);
                  }
                  Collections.sort(list);
                  int other = 0;
                  for (int j = 0; j < list.size(); j++) {
                      if (j < list.size() - 1) {
                          other += list.get(j);
                      }
                  }
                  if (other <= list.get(list.size() - 1)) {
                      System.out.println("No");
                  } else {
                      System.out.println("Yes");
                  }
              }
          }
      }
  }
  
  ```

- 错误情况

  ![越界](集合的remove方法引起的数组越界问题/数组越界.png)

- 修改后即为正常

  

![通过](集合的remove方法引起的数组越界问题\通过.png)

- 先看remove方法

  - 继承关系

    ```java
    public interface Collection<E> extends Iterable<E>  {
        boolean remove(Object o);
    }
    ```

    ```java
    public interface List<E> extends Collection<E> {
    		E remove(int index);
    }
    ```

    ```java
    public class ArrayList<E> extends AbstractList<E>
            implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
                public E remove(int index);
                public boolean remove(Object o);
    }
    ```

    ```java
        public boolean remove(Object o) {
            if (o == null) {
                for (int index = 0; index < size; index++)
                    if (elementData[index] == null) {
                        fastRemove(index);
                        return true;
                    }
            } else {
                for (int index = 0; index < size; index++)
                    if (o.equals(elementData[index])) {
                        fastRemove(index);
                        return true;
                    }
            }
            return false;
        }
    ```

  - 所以指定元素删除时删除的是对象，最好是先new一个相同值的对象，不要使用直接传值的方式，这样向上转型虽然可以达到效果，但是牛客后台会报数组越界异常，如果在牛课上测试，建议使用第一种方法

    