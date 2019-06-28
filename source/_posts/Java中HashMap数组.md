---
title: HashMap数组简单实用
date: 2019-04-30 16:37:03
tags: [java,集合,algorithms]
---
<!-- more -->
 版权声明：wangchong https://blog.csdn.net/wfcn_zyq/article/details/89712257   
  **天梯赛**

 
```java
package www.tianti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class L7_4_Shuai_Hash {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int line = input.nextInt();
        HashMap[] arr = new  HashMap[line];
        for (int i = 0; i < line; i++) {
            arr[i] = new HashMap<Integer,Integer>();
        }
        for (int i = 0; i < line; i++) {
            int nLine = input.nextInt();
            for (int j = 0; j < nLine; j++) {
                int cur = input.nextInt();
                arr[i].put(cur,cur);
            }
        }
        int nCe = input.nextInt();
        int[] chaZhao = new int[nCe];
        for (int i = 0; i < nCe; i++) {
            chaZhao[i] = input.nextInt();
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < nCe; i++) {
            int notIn = 0;
            for (int j = 0; j < line; j++) {
                if (arr[j].containsKey(chaZhao[i]) && arr[j].size() <= 1) {
                    if (!arrayList.contains(chaZhao[i])){
                        arrayList.add(chaZhao[i]);
                    }
                } else if (arr[j].containsKey(chaZhao[i])) {
                    continue;
                } else {
                    notIn++;
                }
            }
            if (notIn == line) {
                if (!arrayList.contains(chaZhao[i])) {
                    arrayList.add(chaZhao[i]);
                }
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == arrayList.size() - 1) {
                System.out.print(arrayList.get(i));
            }else {
                System.out.print(arrayList.get(i) + " ");
            }
        }
        if (arrayList.size() == 0) {
            System.out.println("No one is handsome");
        }
    }
}


```
   
  