---
title: Hankson问题
date: 2019-04-04 20:53:58
tags: algorithms
---
<!-- more -->
 版权声明：wangchong https://blog.csdn.net/wfcn_zyq/article/details/88697716   
  **1.题目描述**

 
```java
Hanks博士是BT（Bio-Tech，生物技术）领域的知名专家，他的儿子名叫Hankson。现在，刚刚放学回家的Hankson正在思考一个有趣的问题。
今天在课堂上，老师讲解了如何求两个正整数c1和c2的最大公约数和最小公倍数。现在Hankson认为自己已经熟练地掌握了这些知识，他开始思考一个“求公约数”和“求公倍数”之类问题的“逆问题”，这个问题是这样的：已知正整数a0,a1,b0,b1，设某未知正整数x满足：
1、  x和a0的最大公约数是a1；
2、  x和b0的最小公倍数是b1。
Hankson的“逆问题”就是求出满足条件的正整数x。但稍加思索之后，他发现这样的x并不唯一，甚至可能不存在。因此他转而开始考虑如何求解满足条件的x的个数。请你帮助他编程求解这个问题。
输入格式
  输入第一行为一个正整数n，表示有n组输入数据。接下来的n行每行一组输入数据，为四个正整数a0，a1，b0，b1，每两个整数之间用一个空格隔开。输入数据保证a0能被a1整除，b1能被b0整除。
输出格式
输出共n行。每组输入数据的输出结果占一行，为一个整数。
对于每组数据：若不存在这样的x，请输出0；
若存在这样的x，请输出满足条件的x的个数；
样例输入
2
41 1 96 288
95 1 37 1776
样例输出
6
2

```
 **2.算法设计思路**

 **2.1方法注释**  
 **2.1.1求最大公因数**

 
```
/**
 * 辗转相除法的递归调用
 * @param a 整数1
 * @param b 整数2
 * @return a,b的最大公约数
 */
private static int gcd(int a , int  b) {
    if(a % b == 0)
        return  b;
    return gcd( b,a % b);
}

```
 **2.1.2求最小公倍数**

 
```
/**
 *
 * @param a 整数1
 * @param b 整数2
 * @return a,b的最小公倍数
 */
private static int multiple(int a, int b) {
    return (a * b / gcd(a,b));
}

```
 **2.1.3输入检查**

 
```java
/**
 * 获取正确的输入数据
 * @return 一个数组，包含n个数，这两个数输入正确的输入格式，可以用来求最大公约数，和最小公倍数
 */
    private static int[] getLegalFormat() {
    Scanner input = new Scanner(System.in);
    int[] arr = new int[4];
    boolean judge = true;
    String str[] = new String[4];
    for (int i = 0; i < 4; i++) {
        str[i] = null;
    }
    int[] numStr = new int[4];
    for (int i = 0; i < 4; i++) {
        numStr[i] = 0;
    }
    //对输入异常的处理
    while (judge) {
        for (int i = 0; i < 4; i++) {
            str[i] = input.next();
        }
        try {
            for (int i = 0; i < 4; i++) {
                Integer.parseInt(str[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("The input format is wrong, please input again!");
            e.printStackTrace();
            continue;
        }
        for (int i = 0; i < 4; i++) {
            numStr[i] = Integer.parseInt(str[i]);
        }
        for (int i = 0; i < 4; i++) {
            if (numStr[i] < 1) {
                System.out.println("You must input two positive integers!Input again!");
                judge = true;
                continue;
            }
        }
        if (numStr[0] % numStr[1] != 0 && numStr[3] % numStr[2] != 0) {
            System.out.println("You must ensure a0 % a1 == 0 and b1 % b0 == 0.");
            continue;
        } else {
            judge = false;
        }

    }
    for (int i = 0; i < 4; i++) {
        arr[i] = Integer.parseInt(str[i]);
    }
    return arr;
}

```
 **2.1.4插入排序**

 
```java
/**
 * 插入排序
 * @param arr 待排序数组
 */
private static void insertionSort(int[] arr) {
    if (arr == null || arr.length < 2) {
        return;
    }
    for (int i = 0; i < arr.length; i++) {
        for (int j = i; j > 0; j--) {
            if (arr[j] < arr[j - 1]) {
                swap(arr,j,j-1);
            }
        }
    }
}

```
 **2.1.5交换两个数的值**

 
```java
/**
 * 交换数组中两个数的值
 * @param arr 数组
 * @param i 位置 i
 * @param j 位置 j
 */
private static void swap(int[] arr, int i,int j) {
    arr[i] = arr[i] ^ arr[j];
    arr[j] = arr[i] ^ arr[j];
    arr[i] = arr[i] ^ arr[j];
}

```
 **2.1.6拷贝数组**

 
```java
/**
 * 拷贝数组
 * @param arr 源数组
 * @return arr的数组拷贝
 */
private static int[] copyArray(int[] arr) {
    int[] res = new int[arr.length];
    System.arraycopy(arr, 0, res, 0, arr.length);
    return res;
}

```
 **源码**

 
```java
package ChapterTwo;

import java.util.Scanner;

public class HanksonRight {
    /**
     * 辗转相除法的递归调用
     * @param a 整数1
     * @param b 整数2
     * @return a,b的最大公约数
     */
    private static int gcd(int a , int  b) {
        if(a % b == 0)
            return  b;
        return gcd( b,a % b);
    }
    /**
     *
     * @param a 整数1
     * @param b 整数2
     * @return a,b的最小公倍数
     */
    private static int multiple(int a, int b) {
        return (a * b / gcd(a,b));
    }

    /**
     * 求x的个数
     * @param a0 a0
     * @param a1 a1
     * @param b0 b0
     * @param b1 b1
     * @param max max
     * @return 满足条件的x的个数
     */
    private static int getCount(int a0, int a1, int b0, int b1,int max) {
        int count = 0;
        for (int x = 1; x <= max; x++) {
            if(gcd(x,a0) == a1 && multiple(x,b0) == b1)
                count++;
        }
        return count;
    }

    /**
     * 插入排序
     * @param arr 待排序数组
     */
    private static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr,j,j-1);
                }
            }
        }
    }

    /**
     * 交换数组中两个数的值
     * @param arr 数组
     * @param i 位置 i
     * @param j 位置 j
     */
    private static void swap(int[] arr, int i,int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 拷贝数组
     * @param arr 源数组
     * @return arr的数组拷贝
     */
    private static int[] copyArray(int[] arr) {
        int[] res = new int[arr.length];
        System.arraycopy(arr, 0, res, 0, arr.length);
        return res;
    }
    /**
     * 获取正确的输入数据
     * @return 一个数组，包含n个数，这两个数输入正确的输入格式，可以用来求最大公约数，和最小公倍数
     */
        private static int[] getLegalFormat() {
        Scanner input = new Scanner(System.in);
        int[] arr = new int[4];
        boolean judge = true;
        String str[] = new String[4];
        for (int i = 0; i < 4; i++) {
            str[i] = null;
        }
        int[] numStr = new int[4];
        for (int i = 0; i < 4; i++) {
            numStr[i] = 0;
        }
        //对输入异常的处理
        while (judge) {
            for (int i = 0; i < 4; i++) {
                str[i] = input.next();
            }
            try {
                for (int i = 0; i < 4; i++) {
                    Integer.parseInt(str[i]);
                }
            } catch (NumberFormatException e) {
                System.out.println("The input format is wrong, please input again!");
                e.printStackTrace();
                continue;
            }
            for (int i = 0; i < 4; i++) {
                numStr[i] = Integer.parseInt(str[i]);
            }
            for (int i = 0; i < 4; i++) {
                if (numStr[i] < 1) {
                    System.out.println("You must input two positive integers!Input again!");
                    judge = true;
                    continue;
                }
            }
            if (numStr[0] % numStr[1] != 0 && numStr[3] % numStr[2] != 0) {
                System.out.println("You must ensure a0 % a1 == 0 and b1 % b0 == 0.");
                continue;
            } else {
                judge = false;
            }

        }
        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        return arr;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input n:");
        int n = input.nextInt();
        int[] result = new int[n];
        System.out.println("Input " + n + " row numbers(row by 4 cols,ensure a0 % a1 == 0 and b1 % b0 == 0):" );
        for (int i = 0; i < result.length; i++) {
            int[] data = getLegalFormat();
            int[] arr = copyArray(data);
            insertionSort(data);
            result[i] = getCount(arr[0],arr[1],arr[2],arr[3],data[data.length - 1]);
        }
        for (int aResult : result) {
            System.out.println(aResult);
        }
    }
}

```
 3.经验归纳

 
```java
解题的时候最重要，找到解题的方法，本题的方法，在于只要找到输入的4个数中最大的值，然后x的值一定小于最大值，因为a0 % a1 == 0 and b1 % b0 == 0，知道这个之后，就可以先将输入的数组拷贝到另外一个数组中，然后将原数组排序，找到最大值，并设计以下方法
private static int getCount(int a0, int a1, int b0, int b1,int max) {
    int count = 0;
    for (int x = 1; x <= max; x++) {
        if(gcd(x,a0) == a1 && multiple(x,b0) == b1)
            count++;
    }
    return count;
}
用来计数。
从而可以看出解题的关键在于找到解决问题的方法。

```
   
  