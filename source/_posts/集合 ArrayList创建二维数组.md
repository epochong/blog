---
title: 使用ArrayList创建二维数组
date: 2019-04-30 16:35:20
tags: [java,集合,泛型]
---
<!-- more -->
 版权声明：wangchong https://blog.csdn.net/wfcn_zyq/article/details/89712213   
  **计蒜客data structure：Code_01**

 
```java
package www.jisuanke.ds;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author wangchong
 * @date 2019/4/30 16:09
 * @email 876459397@qq.com
 * @CSDN https://blog.csdn.net/wfcn_zyq
 * @describe
 */
public class Code_01_SpecialMatrix {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        ArrayList[] arrayList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            arrayList[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x = input.nextInt();
            int y = input.nextInt();
            arrayList[x - 1].add(y);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < arrayList[i].size(); j++) {
                if (j < arrayList[i].size() - 1) {
                    System.out.print(arrayList[i].get(j) + " ");
                } else {
                    System.out.print(arrayList[i].get(j));
                }
            }
            System.out.println();
        }
    }
}

```
##### 为什么要在声明后初始化呢?
- 泛型数组初始化时不能声明泛型类型
###### 如下代码编译时通不过：
```java
List<String>[] list = new List<String>[];
        在这里可以声明一个带有泛型参数的数组，但是不能初始化该数组，因为执行了类型擦除操作后，List<Object>[] 与 List<String>[] 就是同一回事了，编译器拒绝如此声明。
```