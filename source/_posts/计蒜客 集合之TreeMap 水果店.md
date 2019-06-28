---
title: TreeMap 水果店
date: 2019-05-03 15:30:16
tags: [algorithms,计蒜客,集合] 
---

<!-- more -->


 版权声明：wangchong https://blog.csdn.net/wfcn_zyq/article/details/89788070   
```
 时间限制1000ms   空间限制131072K

```
 蒜头君经营着一个不大的水果店。他认为生存之道就是经营最受顾客欢迎的水果。现在他想要一份水果销售情况的明细表，这样就可以很容易掌握所有水果的销售情况了。蒜头君告诉你每一笔销售记录的水果名称，产地和销售的数量，请你帮他生成明细表。

 **输入格式**  
 第一行是一个整数 N(0 < N \le 1000)N(0<N≤1000)，表示工有 NN 次成功的交易。其后有 NN 行数据，每行表示一次交易，由水果名称 (小写字母组成，长度不超过 100100)，水果产地 (小写字母组成，长度不超过 100100) 和交易的水果数目 (正整数，不超过 10001000) 组成.

 **输出格式**  
 请你输出一份排版格式正确 (请分析样本输出) 的水果销售情况明细表。这份明细表包括所有水果的产地、名称和销售数目的信息。水果先按产地分类，产地按字母顺序排列；同一产地的水果按照名称排序，名称按字母顺序排序。

 **样例输入**

 
```
5
apple shandong 3
pineapple guangdong 1
sugarcane guangdong 1
pineapple guangdong 3
pineapple guangdong 1

```
 **样例输出**

 
```java
guangdong
   |----pineapple(5)
   |----sugarcane(1)
shandong
   |----apple(3)

```
 **实现**

 
```java
package www.jisuanke.ds;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author wangchong
 * @date 2019/5/3 13:38
 * @email 876459397@qq.com
 * @CSDN https://blog.csdn.net/wfcn_zyq
 * @describe
 */
public class Code_06_FruitTable {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Map<String,Map<String,Integer>> cityFruit = new TreeMap<>();
        Map<String,Integer> fruitNum = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            String fruit = input.next();
            String city = input.next();
            int num = input.nextInt();
            if (cityFruit.containsKey(city)) {
                fruitNum = new TreeMap<>();
                fruitNum.putAll(cityFruit.get(city));
            } else {
                fruitNum = new TreeMap<>();
            }

            if (fruitNum.containsKey(fruit)) {
                fruitNum.put(fruit,fruitNum.get(fruit) + num);
            } else {
                fruitNum.put(fruit,num);
            }
            cityFruit.put(city,fruitNum);
        }
        for (Map.Entry<String,Map<String,Integer>> entry : cityFruit.entrySet()
             ) {
            System.out.println(entry.getKey());
            for (Map.Entry<String,Integer> fruitMap : cityFruit.get(entry.getKey()).entrySet()
                 ) {
                System.out.println("   |----" + fruitMap.getKey() + "(" + fruitMap.getValue() + ")");
            }
        }
    }
}


```
 **小tips**  
 每次换了城市的时候必须刷新fruitNum，每次遇到相同的城市必须刷新fruitNum并且将之前对应的表拿出来再赋给他才能保证不同城市不会串到一起，我在找个地方吃力很大的亏  
 **entend**  
 学了TreeMap的基础知识，我想如果TreeMap可以放基本数据类型和String类型，那么应该可以放集合，没想到真的可以，根据这一题，就尝试了一下，题目要求中有三个值，所以普通的key，value是不可能满足的，那么如果value又是一个Map那么就可以满足三个值的对应关系，一个城市对应一批水果，水果则用TreeMap存放，就可以达到输出对应每个城市的水果销售情况了。  
 我只对解法做了简单的介绍，要想明白其中的道理，必须体会我的代码中的魅力。鬼知道debug用来多长时间。

   
  