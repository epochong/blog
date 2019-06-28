---
title: 输出该链表中倒数第k个结点
date: 2019-03-03 16:32:47
tags: [algorithms,牛客,链表]
---

<!-- more -->


 版权声明：wangchong https://blog.csdn.net/wfcn_zyq/article/details/88089768   
  **题目描述**

 
```java
输入一个链表，输出该链表中倒数第k个结点。

```
 **要求**

 
```java
时间限制：1秒 空间限制：32768K 热度指数：552571
本题知识点： 链表

```
 **思路**

 
```java
将链表遍历一遍，放入栈中，在出栈的时候，k的值减一
当k在减为0的时候，即为要求的第k个节点
但是有一下几种情况
1.k大于链表长度，此时，返回null
2.头结点head为空，直接返回null

```
 **解法**

 
```java
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
import java.util.Stack;
public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
       if(k == 1) {
           return stack.pop();
       }
        while (k-- > 1) {
            if (stack.size() == 0) {
                return null;
            }
            stack.pop();
        }
        if (k >= 0) {
            if (stack.size() == 0) {
                return null;
            }
            return stack.pop();
        }else {
             return null;
        }
       
    }
}

```
   
  