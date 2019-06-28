---
title: '继承关系下的getter,setter调用关系.md'
date: 2019-05-28 16:39:20
tags: java
---

> 先看一段代码判断输出

```java
/**
 * @author wangchong
 * @date 2019/5/28 16:22
 * @email 876459397@qq.com
 * @CSDN https://blog.csdn.net/wfcn_zyq
 * @describe
 */
public class Test {
    public static void main(String[] args) {
        System.out.print(new B().getValue());
    }
    static class A {
        protected int value;
        public A(int v) {
            setValue(v);
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getValue() {
            try {
                value++;
                return value;
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                this.setValue(value);
                System.out.print(value + " ");
            }
            return value;
        }
    }
    static class B extends A {

        public B() {
            super(5);
            setValue(getValue() - 3);
        }

        @Override
        public void setValue(int value) {
            super.setValue(value * 2);
        }
    }
}
```

###### 输出

> 22 34 17

###### 调用关系

只要遵循以下条件

1. 父类调用父类中被子类重写的方法时,首先调用子类重写后的方法,然后调用父类的该方法
2. try-catch-finally语句中，try中含有return语句时，执行return语句前（return value保存的是没执行finally之前的值）如果没产生异常，先执行finally，然后直接执行return语句，不执行finally之后的return语句。有异常，先执行catch块，再执行finally，然后执行finally之后的return语句。

##### 即可得出答案