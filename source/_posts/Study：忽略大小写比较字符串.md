---
title: 忽略大小写比较字符串
date: 2019-05-03 10:19:56
tags: java
---
<!-- more -->
 版权声明：wangchong https://blog.csdn.net/wfcn_zyq/article/details/89785334   
  有时候我们需要忽略大小写比较两个字符串是否相等  
 使用String类中的函数  
 **样例**

 
```java
package www.wangchong.study;

/**
 * @author wangchong
 * @date 2019/5/3 10:15
 * @email 876459397@qq.com
 * @CSDN https://blog.csdn.net/wfcn_zyq
 * @describe
 */
public class IgnoreUpperLowerCase {
    public static void main(String[] args) {
        String a = "ABC";
        String b = "abc";
        System.out.println(a.equalsIgnoreCase(b));
    }
}


```
 **输出**  
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/2019050310184537.png)

   
  