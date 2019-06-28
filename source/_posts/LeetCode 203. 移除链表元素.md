---
title: 203. 移除链表元素
date: 2019-03-03 13:42:40
tags: [algorithms,LeetCode]
---
<!-- more -->
 版权声明：wangchong https://blog.csdn.net/wfcn_zyq/article/details/88087417   
  **题目一描述**

 
```java
删除链表中等于给定值 val 的所有节点。
示例:
输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5

```
 Java实现

 
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
   public ListNode removeElements(ListNode head, int val) {
        ListNode pre = head;
        ListNode deNode;
        for (ListNode cur = head; cur != null;) {
            if (cur.val == val && cur == head) {
                head = head.next;
                if (head == null) {
                    deNode = cur;
                    cur = head;
                    deNode = null;
                   
                } else {
                    deNode = cur;
                    cur = head;
                    deNode = null;
                    pre = head;
                }
            } else if (cur.val == val) {
                pre.next = cur.next;
                cur = pre.next;
            }else {
                pre = cur;
                cur = pre.next;
            }

        }
        return head;
    }
}

```
 **运行结果**  
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190303133727642.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)  
 **总结**

 
```
消耗内存好像有点多，才击败了0.88%的用户，代码还有需要改进的地方，请各位大佬指正啊
后面可能会有更新这个题目，优化一下算法
可能原因：使用太多的变量

```
 本人QQ：1939765238 欢迎一起学习的同学来讨论

   
  