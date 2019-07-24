---
title: 求N个数的最大公约数和最小公倍数。
date: 2019-04-04 20:54:25
tags: [java,algorithms]
---

<!-- more -->

 版权声明：wangchong https://blog.csdn.net/wfcn_zyq/article/details/88697857   
  一. 题目分析  
 求N个数的最大公约数和最小公倍数。  
 可以通过递归的方法实现一个接着一个的方法调用求N个数的最大公约数  
 二.算法设计思路  
 2.1方法注释  
 2.1.1求最大公因数

 
```java
/**
 * 辗转相除法的递归调用
 * @param a 整数1
 * @param b 整数2
 * @return a,b的最大公约数
 */
private static int gcd(int a , int  b) {
    if(a % b == 0)
        return b;
    return gcd( b,a % b);
}

```
 2.1.2求最小公倍数

 
```java
/**
 *
 * @param a 整数1
 * @param b 整数2
 * @return a,b的最小公倍数
 */
private static int multiple(int a, int b) {
    int temp;
    temp = gcd(a,b);
    return (a * b/temp);
}

```
 2.1.3输入检查

 
```java
/**
 * 获取正确的输入数据
 * @return 一个数组，包含n个数，这两个数输入正确的输入格式，可以用来求最大公约数，和最小公倍数
 */
private static int[] getLegalFormat() {
    Scanner input = new Scanner(System.in);
    System.out.println("Input n:");
    int n = input.nextInt();
    int[] arr = new int[n];
    boolean judge = true;
    String str[] = new String[n];
    for (int i = 0; i < n; i++) {
        str[i] = null;
    }
    int[] numStr = new int[n];
    for (int i = 0; i < n; i++) {
        numStr[i] = 0;
    }
    //对输入异常的处理
    while (judge) {
        System.out.println("Please input n numbers:");
        for (int i = 0; i < n; i++) {
            str[i] = input.next();
        }
        try {
            for (int i = 0; i < n; i++) {
                Integer.parseInt(str[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("The input format is wrong, please input again!");
            e.printStackTrace();
            continue;
        }
       for (int i = 0; i < n; i++) {
           numStr[i] = Integer.parseInt(str[i]);
       }
       int positiveNum  = 0;
       for (int i = 0; i < n; i++) {
           if (numStr[i] < 1) {
               System.out.println("You must input two positive integers!Input again!");
               judge = true;
               positiveNum++;
               break;
           }
       }
       if (positiveNum > 0) {
           continue;
       }
       judge = false;
    }
   for (int i = 0; i < n; i++) {
       arr[i] = Integer.parseInt(str[i]);
   }
    return arr;
}

```
 #### 3.经验归纳


求N个数的的最大公约数和最小公倍数，一般都是求两个数的，但是N个数怎么求呢？想到递归。
算法的核心在于 
```java
for (int i = 2; i < input.length; i++) {
    gcdResult = gcd(gcd(input[i - 2], input[i - 1]), input[i]);
    multipleResult = multiple(multiple(input[i - 2],input[i - 1]), input[i]);
}
```
##### 这一个递归调用
我们可以通过先算前两个的公约数在用前两个的公约数求后面的公约数，这样求出来的结果就是他们的公约数。
通过这次作业，我对递归的算法理解更加深刻了。


   
  