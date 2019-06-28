---
title: C语言不使用库函数反转字符串
date: 2018-11-25 13:22:54
tags: [algorithms,C]
---
<!-- more -->
  
  给定一个字符串，当然你也可以自己输入（更改一下语句）  
 给定：“student a am i”  
 输出：“i ma a tneduts”


```C
#include <stdio.h>
int main()
{
	int i = 0;
	int count = 0;
	char stu[] = { "student a am i" };
	char* star = stu;
	while (*star++)
	{
		count++;
	}
	star = stu;
	char* end = stu + count - 1;
	for (i = 0; star < end; i++)
	{
		char temp = *star;
		*star = *end;
		*end = temp;
		end--;
		star++;
	}
	printf("%s", stu);
}

```

  