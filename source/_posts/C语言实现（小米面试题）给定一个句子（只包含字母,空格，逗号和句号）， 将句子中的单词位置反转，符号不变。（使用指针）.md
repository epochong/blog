---
title: C语言实现（小米面试题）给定一个句子（只包含字母,空格，逗号和句号）， 将句子中的单词位置反转，符号不变。（使用指针）
date: 2018-11-25 13:35:36
tags: [algorithms,C]
---
<!-- more -->
 版权声明：wangchong https://blog.csdn.net/wfcn_zyq/article/details/84486447   
  **例子**  
 输入： _hello xiao.mi_  
 输出： _olleh [oaix.im](http://oaix.im)_  
 思路:输入一个句子（只包含字母,空格，逗号和句号），指针指向该字符串首地址，遍历字符串找到字符串中非字母处，记下该位置记录到整型数组中，调用反转字符串的数组，利用记下的位置分单词反转。

 
```
//Dev-Cpp下实现
#include <stdio.h>
char*fan(char*p,int b[10],int j)
{	char t;
	int i;
	for(i=b[j];i<(b[j+1]+b[j])/2;i++)
	{
		t=p[b[j]];p[b[j]]=p[b[j+1]];p[b[j]+1]=t;
	}
	return (p);
}
int main()
{	
	char a[100],*p=a;
	int i,j=1,b[10]={0};
	printf("输入一个句子（只包含字母,空格，逗号和句号）：\n");
	gets(a);
	for(i=0;i<100;i++)
	{
		if((a[i]<='z'&&a[i]>='a')||(a[i]>='A'&&a[i]<='Z'))
		continue;
		else b[j++]=i;//记下除字母外的字符的位置 
	} 
	for(j=0;j<10;j++)
	{
		if(b[j]==0&&b[j+1]==0)break;//没有记下位置的后边的元素默认为0，以此作为结束标志 
		else fan(a,b,j);
	}
	printf("%s",p);
}


```
   
  