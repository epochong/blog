---
title: 206. 反转链表
date: 2019-03-03 17:03:19
tags: [algorithms,LeetCode]
---
<!-- more -->
 版权声明：wangchong https://blog.csdn.net/wfcn_zyq/article/details/88090434   
  **题目描述**

 
```
反转一个单链表。
示例:
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL

```
 
--------
 **思路**

 
```
设置一个之前节点pre用来指向每次反转的头结点
设置一个下一个节点next 用来保存下一个节点保证可以找到之后的节点
head节点每走一步反转一次
当走到尾部的时候，反转结束
返回pre即可

```
 **实现**

 
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
		ListNode next = null;
		while (head != null) {
			next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;
    }
}

```
 **小结**

 
```
能用几行代码实现这个功能无疑是强大的

```
 欢迎加我QQ：1939765238一起讨论算法

   
  