---
title: 876. 链表的中间结点
date: 2019-03-03 16:52:20
tags: [algorithms,LeetCode]
---
<!-- more -->
 版权声明：wangchong https://blog.csdn.net/wfcn_zyq/article/details/88090162   
  **题目描述**

 
```
给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
如果有两个中间结点，则返回第二个中间结点。

```
 **示例 1：**

 
```
输入：[1,2,3,4,5]
输出：此列表中的结点 3 (序列化形式：[3,4,5])
返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
注意，我们返回了一个 ListNode 类型的对象 ans，这样：
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.

```
 **示例 2：**

 
```
输入：[1,2,3,4,5,6]
输出：此列表中的结点 4 (序列化形式：[4,5,6])
由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。

```
 **思路**

 
```
设置两个指针，一个指针一次走一步，一个指针一次走2步
当链表大小为奇数
-走两步的节点到尾部的时候，走一步的到中间位置
当链表大小为偶数
-走两步的节点到尾部走一步的到第二个中间节点
当链表只有一个节点时
-直接返回该节点
当链表有两个节点时
-返回第一个节点的下一个节点

```
 **解法**

 
```java
class Solution {
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
			return head;
		}
		ListNode n1 = head;
		ListNode n2 = head;
		//奇数的时候来到中点，偶数的时候来到中点前面的一个的位置
		while (n2.next != null && n2.next.next != null) { 
			n1 = n1.next; // n1 -> mid
			n2 = n2.next.next; // n2 -> end
		}
        if (n2.next != null) {
            return n1.next;
        } else {
            return n1; 
        }
      
    }
}

```
 **小结**

 
```
总结不易，切勿抄袭，帮助你理解是最终目的

```
 欢迎加我QQ：1939765238 一起学习算法

   
  