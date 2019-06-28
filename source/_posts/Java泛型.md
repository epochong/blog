---
title: 泛型小计
date: 2019-04-30 16:37:03
tags: [java,泛型]
---
###### jdk5 自动拆装箱
###### jdk7 switch对String的支持
##### ==JDK1.5新特性==
##### 1.方法的可变参数(数组)
> 可变参数必须放在方法参数最后一个并且有且只有一个
```
这个参数上使用的“...”实际上表示一个数组的结构。
```

##### 3.静态导入
###### 用inport static 导入一个类的所有静态域(方法与属性)
```
从JDK1.5开始，如果类中方法全是static方法，则可以直接把这个类的方法导入进来，这样就好比像在主类中定义
的方法那样，可以被主方法直接调用。
```
##### ==泛型：语法塘 守门员==
###### 守门员

```
编译的时候就拦截
Object类自动转型运行时异常
```

##### 泛型
在定义是类型不确定，只有在具体是用时才能确定类型
###### ClassCastException(RuntimeException)：在强转时，两个毫无关系的类产生的异常
###### 安全隐患：存在于强转
##### 泛型类

```
class MyClass<T> {
T value1;
}
尖括号 <> 中的 T 被称作是类型参数，
用于指代任何类型
```
引入泛型后，一个泛型类的类型在使用时已经确定好，因此无需向下转型
IDEA变量自动补全：new point<>() 后
ctrl + alt + v
JDK7后 new后面不需要写<>里面的泛型


```
<T> 中的 T 被称为
类型参数，而方法中的 T 被称为参数化类型，它不是运行时真正的参数。
```

###### 不包括进本数据类型

##### 泛型方法
```
class MyClass{
    public <T> void testMethod(T t) {
        System.out.println(t);
    }
}
```
###### 泛型方法始终以自己定义的类型参数为准。
###### 为了避免混淆，如果在一个泛型类中存在泛型方法，那么两者的类型参数最好不要同名
##### ==通配符(重点）==
##### 问题

```
在程序类中追加了泛型的定义后，
避免了ClassCastException 的问题，
但是又会产生新的情况：参数的统一问
题。
```
##### 解决：通配符
###### 用在方法级别 接受任意类型
```
我们需要的解决方案：可以接收所有的泛型类型，但是又不能够让用户随意修改。这种情况就需要使用通配符"?"来
处理

```
>  此时使用通配符"?"描述的是它可以接收任意类型，但是由于不确定类型，所以无法修改
###### ? extends 类：设置泛型上限
>  只能获得值不能设置值
现在只能确定是父类，由于子类不确定(子类有很多中种或很多层)。此时发生向下转型存在不确定性因此无法设置具体值

```
? extends Number，
表示只能够设置Number或其子类，
例如：Integer、Double等；
```
###### ? super String，表示泛型下限，只用在方法级别，表示只能够设置String及其父类Object()
> 此时方法可以设置值，因为发生天然的向上转型

#### 总结

> 只有上线通配符可以用在泛型类的声明上，T extend是Number，

##### 泛型接口
> JDK8前接口只有全场常量和方法

###### 定义一个泛型接口
```
interface IMessage<T> { // 在接口上定义了泛型
public void print(T t) ;
}
```

###### 在子类定义时继续使用泛型

```
interface IMessage<T> { // 在接口上定义了泛型
public void print(T t) ;
}
class MessageImpl implements IMessage<String> {
@Override
public void print(String t) {
System.out.println(t);
}
}
```
###### 在子类定义时继续使用泛型

```
interface IMessage<T> { // 在接口上定义了泛型
public void print(T t) ;
}
class MessageImpl<T> implements IMessage<T> {
@Override
public void print(T t) {
System.out.println(t);
}
}
```
#### ***类型擦除(向下兼容)***
> 这是因为，泛型信息只存在于代码编译阶段，在进入 JVM 之前，与泛型相关的信息会被擦除掉，专业术语叫做类型擦除,

> 通俗地讲，泛型类和普通类在 java 虚拟机内是没有什么特别的地方。

> 在泛型类被类型擦除的时候，之前泛型类中的类型参数部分如果没有指定上限，如 <T> 则会被转译成普通的
Object 类型，如果指定了上限如 <T extends String> 则类型参数就被替换成类型上限。

```
class MyClass<T,E extends Number>{
private T message;
private E text;
}
T-> Object
E-> Number

```
