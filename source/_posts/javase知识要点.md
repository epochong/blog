---
title: javase基础
date: 2018-11-11 16:35:20
tags: java
---
Java简介

> ==1.Java是一门半编译半解释性语言==


```
编译命令：javac

使用编译命令javac将*.java(源文件)编译为*.class

class文件为平台无关的二进制文件，提供给JVM(java虚拟机)

解释命令:java 


使用java命令实际上启动了JVM虚拟机进程(软件)来讲二进制class文件翻译为平台相关的可执行文件

主方法中字符串数组传值：
使用java命令解释二进制class文件时传值，eg：
java Day_01 hello world 
表示运行Day_01文件并将hello world 传值给主方法参数，hello是args[0],world args[1]

主方法一定在主类(public class)中定义

源文件中若有中文，编译时使用如下命令 
javac -encoding UTF-8 源文件名称
```

> ==1.Java数据类型与运算符==

> ==1.1 Java 注释==

```
a.单行注释//
b.多行注释/* */(忘了吧)
c.文档注释/** */
```

==1.2 Java 标识符(变量、常量、类、函数等的名称)==

==1.2.1 表示符的要求==
标识符由字母、数字、_ 、$ 组成，其中不能以数字开头，不能使用Java中的关键字。

==命名规范：驼峰命名法==

```
大驼峰：对于类名，类名是以大写字母开头的单词；
如果类名由多个单词组成，则每个单词首字母大写。
eg： class Demo
     class FirstDemo
小驼峰：对于变量、函数名称，如果只含有一个单词，则全部小写；如果变量名有多个小单词组成，则从第二个单词开始。每个单词首字母大写
eg： int value
     int firstValue
     int value()
     int firstValue()
常量命名：单词全部大写，多个单词间以_分割。
eg final int MY_INT_VALUE = 10;
```


###### ==1.3 数据类型==
###### 所有数据类型有默认值
##### 1.3.1 数据类型划分

##### 1.3.2 基本数据类型(8大基本数据类型)

```
数值型(byte、short、int、long、double、float)
        1      2     4     8      8      4
==整型==：
Java中，任何一个整形常量都是int类型
    
Java中，声明long常量，需要在数字后面加L(l)
    
范围小的数据类型可以自动提升为范围大的数据类型(进行数学运算时)
    
范围大的数据类型只有强制类型转换才能转为范围小的数据类型

所有数据类型默认值需要结合类来观察，方法中的局部变量不再存在默认值(局部变量必须先赋值后使用)
```


##### byte

```
给byte赋值时，整型赋值给byte在byte范围内直接赋值，否则必须强转(只接收8位)
不强转就只接收低八位
```

##### 浮点型

```
描述小数默认double类型
```


##### 字符型(char)

```
2个字节
    汉字和字母都是一个字节
    char与int可以相互转换 转换为对应的ASCII值
    字符‘0’与整型0不相等(字符数字与整型数字不相等)
```

##### 布尔型(boolean)

```
ture
false
```

==1.3.3 引用数据类型(数组、类、接口)==
==初见String类(引用数据类型)==

```
描述字符串

字符串拼接用 +
任何数据类型使用 + 与String运算，先转换为String在进行字符串拼接,若想先计算加()
```

##### ==转义字符(\\)==

```
\n,\t
```

##### 运算符
##### 基础运算符

```
% ++ -- + - 三目运算符
数据类型 变量 = 布尔表达式？ture ： false
```

##### 关系运算符(!= > < ==)
##### 逻辑运算符(|| && !)
||(短路或)

```
多个条件只要有条件返回ture，剩余条件不在判断
&&(短路与)
```

##### 位运算符(& | ~ ^ << >>)

```
&多位都要运算区别于&&只要有false就返回
```

##### ==程序结构==

```
顺序结构、分支、循环
```

==分支(if else   switch)==

```
switch(int char enum String)
case：表示分支  break 结束
default
```


==循环结构==

```
for
do while   
while
```

> ==for-each循环(用于数组的输出，不能用于数组的修改)==


```
用于数组与类集的输出，对于原集合的修改依然采用原来得for循环

for(String str ： args)
{
    
}
for(数据类型 临时变量 ： 数组名称)
{
    
}
```


#####  ==方法的定义与使用(形参只有值传递)==


```
1.在主方法中定义，在主方法中调用

public static 方法返回值 方法名称([参数类型 变量] ....)
{
方法体
    return  ；
}

当方法以void声明是，表示无返回值。
但是viod方法任然可以使用return 表示结束
```

> ==方法重载==


```
方法名称相同、参数类型、顺序或个数不同(有一不同)，与返回值无关
```

> ==方法签名==


```
方法名称、参数

开发规范：重载方法保证返回值类型一致
```

> ==方法的递归==

==Java 数组==
```
- 数组是引用数据类型，在栈上分配空间。
- new 关键字在堆上开辟空间
- 数组动态初始化(声明并开辟数组空间)
```

==语法:==

==数组动态初始化==
```
数据类型[] 数组名称 = new 数据类型[长度]
int [] data = new int[5];
- 数组访问通过索引完成。数组名称[索引]。0~长度-1，索引越界时，报错(数组越界异常错误)(RuntimeException).
- 动态取得数组长度方法：数组名.length
```

```
int[] data = null;在栈内存开辟空间存放data data指向空
data = new int[3];在堆内存开辟3个空间 data指向数组手匀速地址
int[] x = data;(引用传递)
多个栈内存共同指向统一块堆内存空间
```

==数组静态初始化==

###### 开辟空间的同时不赋值
- 数组在定义同时设置内容
- 简化格式：
```
 数据类型[] 数组名称 = { ， ， ， }
 int[] data = {1,3,5,7};
 完整格式(推荐)
 数据类型[] 数组名称 = new 数据类型[]{ };
 int[] data = new int[]{1,3,5,7};
 匿名数组：只能使用完整格式并且只能使用一次
 System.out.println(int[] data = new int[]{1,3,5,7}); 
 没有任何栈内存指向数组空间
```
==二维数组==

==动态初始化==

```
开辟空间的同时赋值
数据类型[][] 数组名称 = new 数据类型[][];
```
###### 栈：引用
###### 堆：数值
###### 引用传递：多个栈内存指向同一块堆内存
==静态初始化==
```
数据类型[][] 数组名称 = new 数据类型[][];
{
    {},{},{},{},{}
}
```

==数组和方法互操作==

==方法接受数组==

```
定义一个方法用来输出数组
方法的参数是数组名
```

==方法返回数组==

```
方法返回值为数组
public static in[] init(){
    
}

```

==方法修改==

```
因为传递的是数组名是引用传递，使形参与原来的引用指向同一块堆内存
```
##### ==Java对数组的支持(JDK内置关于数组)==

==排序(所有基本数据类型进行排序)==

```
类直接调用
java.util.Arrays.sort(数组名称)
```

==实现部分数组拷贝==

```
将一个数组的部分内容替换为另一个数组的部分内容(连续内容)
System.arraycopy(原数组名称，原数组开始点，目标数组名称，目标数组开始点，拷贝长度)

```
==数组全拷贝==

```
java.util.Arrays.copyOf(原数组名称，新数组长度) -- 数组扩容
新建一个数组 将原数组拷贝进去
```
**NewDay**

##### 面向对象(OO) - 类与对象

最大的特征：可以进行现实生活的抽象

==面向对象三大特征(封装、继承、多态)==

==封装(保护性)==


```
将客观事物封装成抽象的类，并且可以把自己的数据和方法只让可信的类或对象操作，
对不可信的进行隐藏。内部操作对外部而言不可见。
```

==继承(可重用)==


```
可以使用现有类的所有功能，并且在无需重新编写原有类的基础上进行功能上的扩展。
单继承
is a原则
```

==多态==


```
一个类实例的相同方法在不同情形下有不同的表现形式
对象的向上向下转型实现多态
```


==名词拓展==

OOA：面相对象分析
OOD：面向对象设计
OOP：面向对象编程

##### 类与对象

==基本概念==


```
类：描述的是共性的概念
对象：勒种的具体的一个成员(一个具体的、可以使用的事物)
类与对象关系：
    首先产生类(生产对象的蓝图)，而后才可以产生类、对象所有行为与属性，一定在类中进行了完整的定义
    
对象之间区别在于具体属性值不同
对象引用(对象的名称)
```
==类与对象的定义与使用==

定义类语法

class 类名称{
    属性类型 属性名称1;
    属性类型 属性名称2;
    .
    .
    .
    构造方法1(){}
    构造方法2(){}
    方法1(){}
    方法2(){}
}

==对象的产生语法==

类名称 对象引用 = new 类名称();

==对象内存分析==19:50

栈内存: 存放的是局部变量(包含各种基本类型和对象引用)

堆内存: 保存的是整整的数据(类和数组的数据)

垃圾空间: 没有任何栈内存指向的空间

==private 实现封装处理==

- 当属性或方法被private 关键字修饰后，改属性或方法无法再类外部调用，只能在本类中使用
- 被private封装的属性或方法称为私有属性 私有方法 
- 要访问或者修改私有属性，必须提供以下两种方法
- setter方法：主要用于属性值的设置与修改
- getter方法：主要用于取得属性值
- private不能用在外部类，可以用在内部类
==类设计原则==

- 编写类是，类种所有属性必须使用private封装
- 属性若要被外部访问，必须提供setter与getter 方法

==构造方法==

```
定义：使用关键字new实例化对象是调用的方法
特点：
    构造方法名称与类名称相同
    一个类中至少存在一个构造方法，如果没有自定义构造方法，则系统默认产生一个无参的构造方法， 自定义构造方法，系统就不会产生默认构造方法
    构造方法无返回值类型声明
   
作用：类中属性初始化作用 默认构造函数将属性初始化默认值

构造方法重载：设置属性个数不同 (不存在属性类型不同)
    
匿名对象：没有栈内存指向的对象 
不加static关键字的方法称为普通方法，必须通过对象调用
加static关键字的方法为静态方法，可通过类直接. 就能调用
```
> 12.6

==this 关键字==

```
- this 表示本类属性
    (由就近取用原则)
    表示类成员变量：this.name
    位置：构造方法，setter方法
- this 表示本类方法
    1.this调用构造方法
    例子：
         this()表示调用类中无参构造
         this(mame)表示调用类中含一个参数的构造方法
    语法：
         this(构造方法参数)
         this调用构造方法不能成“环”
    位置：构造方法且首行
    **除非有特殊声明，一行代码只能出现一次
    
    2.this调用普通方法
    语法：this.方法名(方法参数)表示调用本类中的方法
    

- this 表示当前对象
    表示当前调用该方法，属性，的对象
    位置：类中
    
    
```

###### ==static关键字(与类的实例化对象无关)==

```
1.static属性 - 静态属性(类属性)
    构造方法仅初始化普通变量，不出事static变量
    直接通过类名调用，该类所有对象共享该属性
    传统属性所具备的特征：保存在堆内存中，且每个对象独享属性。
    描述共享属性，只需在属性前添加static关键字即可
    static属性又称为类属性，保存在全局数据区--运行时常量池(独立于栈和堆，存放static final)的内存之中，所有对象都可以进行该数据区的访问
    static不能定义临时变量(仅能声明类中属性，不能声明方法中属性)

2.static方法-类方法-静态方法
    使用static定义的方法为类方法，与对象无关，直接通过类名称访问。
    使用工具方法时，一般使用静态方法java.util.Arrays.sort()-排序
  
    所有的static方法不允许调用非static定义的属性或方法
    所有的非static方法允许访问static方法或属性    
```
==代码块==

根据代码块定义的位置以及关键字，分为以下四种代码块

==普通代码块==

```
定义在方法中的代码块
    解决方法中需要重复定义的同名变量的 场景(了解)
```

##### ==构造代码块==

```
定义在类中的代码块(不加任何修饰符)
构造块优先于构造方法执行，每当有对象产生就执行一次构造块，主类中的main方法优先于构造块
完成类中普通属性初始化操作
域对象强相关
```

==静态代码块==

```
使用static修饰并且定义在类中的代码块
    1.定义在非主类中的静态代码块
    优先于构造块执行，并且无论有多少实例化对象产生能只执行一次
    2.定义在主类中的静态代码块
    主类中的静态块优先于主方法执行
    完成类中静态属性的初始化以及静态方法的调用
```
###### ==继承==

==概述==

```
继承的主要作用在与，在已有基础上进行功能的扩充操作(可重用)
要使用继承，必须满足"is a" 原则 Student is a Person
```

==继承使用==

```
子类使用extends继承父类，无需从重复编写代码的情况下拥有父类的所有成员

父类-基类

子类-派生类

只能单继承，要想实现多继承，可使用多层继承，内部类，接口

```
### 12.8
==继承使用限制==

```
1.子类对象在实例化前一定会首先实例化父类对象
先调用父类构造方法后再调用子类构造方法
2.Java指允许单继承不允许多继承
一个类只能集成一个父类，
虽然不允许多继承，但允许使用多层继承
3.在进行继承的时候，子类继承父类所有结构(包括私有域)
隐式继承：继承父类的所有私有域(包括四有属性、方法)无法直接调用
显示继承：继承父类所有非私有域，可以直接调用
```
==方法覆写(override)==

```
位置：有继承关系的类之间，子类定义了与父类相同的方法(方法名称、参数个数、类型、返回值都相同)。
被覆写的方法不能有比父类更为严格的访问控制权限
存在方法覆写时，到底调用哪一个类的同名方法：
1.当前使用的对象是通过那个类new的
2.调用方法是否被当前对象所在的类复写，一旦被覆写，一定调用被覆写后的方法
```
==访问权限==

```
private(仅限本类访问)<default(包访问权限)<public
父类中方法调用父类中的其他方法，子类继承有同名方法，子类对象调用父类private方法this停留在父类
方法覆写不能出现private权限
属性覆写(了解)：子类定义了父类的同名属性,与 函数覆写使用规则相同

```
==super关键字==
###### 使用场景
有继承存在

```
1.super调用构造方法
隐藏语句：super()10：14
    位置：子类无参构造首句，子类有参构造可省略
    作用：先调用父类无参构造
    注意：当父类不存在无参构造，必须在子类构造方法中使用super(参数) 明确指定调用父类的哪个有参构造
    当父类有有参构造时，super语句不能省略，此时的子类不存在this调用构造方法
    当父类有无参构造时，super语句可以省略，此时子类可以使用this调用构造方法
2.super调用普通方法
super.方法名(参数)表示直接从父类中找到同名方法并调用

3.super调用普通属性
super.属性名 表示直接从父类中找到同名属性调用
```
==final关键字(终结器)==

```
1.final修饰类
    使用final定义的类不能有子类(无法使用extends继承该类)
    其中的方法默认添加final
    JDK中String类就使用final修饰
    **不能使用static 和private声明一个类
    
    String、两SB、包装类
2.final修饰方法(模板方法)
    使用final写实的方法无法被覆写
3.final修饰属性
    final修饰的属性必须在声明时赋值，可用成员方法赋值，并且该属性值无法被修改
    对于基本类型，不可修改其值
    对于引用类型，知识地址不能变，其值可变
    public final int a = 0;
    Java常量 public static final I = 0;不可修改且共享
    命名规范：全大写，多个单词用_隔开，常量声明必须在声明时赋值
    
全局常量：final static
```
##### 数据类型转换

```
当使用+-*/运算操作时，遵循以下规则：
    只要两个操作数中有一个是double、float、long，另一个也会被转换为对应的double、float、long
    否则操作数byte、int、char/short,两个操作数均会被转换为int类型
    用final声明的两类型不会更改
**
```
###### ==多态(方法覆写)==
==对象多态==
```
==向上转型(90%)(参数统一化)==

    自动转型，不需强转
    Person per = new Student();
    时间：参数统一化  
          方法参数使用父类参数fun(Person per),传来的参数为对应子类参数，其先发生向上转型
==向下转型(1%)==
    时间：父类需要调用子类扩充的功能时
    Student stu = (Student) per;
    要发生向下转型必须先发生向上转型(认爹)，否则会产生ClassCastEception类型转换异常
    
==instance of==检查当前对象，引用是否指向目标类，返回boolean
    语法:if(per instance of Student)判断per是否
```
==内部类==

在类的内部进行其他类结构的嵌套操作。

```
> 优点：
    1.内部类可以直接调用外部内任何成员变量
    2.内部类可以对外部类之外的类进行隐藏，使用内部类也是封装的一种。(人的心脏，车的发动机)，使用private修饰内部类时只能外部类内部使用
    3.使用内部类可以实现多继承对的概念(了解)
        class A
        class B
        class C{
            class InnerA extends A{
                
            }
            class InnerB extends B{
                
            }
        }
> 缺点：造成类结构复杂



```

###### 类之间相互调用
```
> 在一个类访问另一个类的私有属性，需要在另一个类中使用getter()方法
> 另一个类建立一个类的引用时用this作为参数表示当前对象
> 在一个类中定义一个设置成员变量(定义另一个类对象)
```
==内部类与外部类关系==

```
1.对于非静态内部类(成员内部类)，内部类的创建需要依赖外部类的对象，在没有外部类实例前无法创建内部了对象
2.内部类是一个相对的独立的个体，与外部类不是is a 关系，仅仅是抱在外部类内部而已
3.内部类可以直接访问外部类的元素(包含私有域)，外部类可以间接访问(通过内部类对象)内部类的所有元素(包含私有域)，内外部类可以访问彼此的私有属性(内部类直接访问，外部类通过对象间接访问)
```
==内部类分类==

1.成员内部类--类比普通方法

```
定义在类中，不加static修饰
> 依赖外部类对象，先创建外部类对象后再创建成员内部类
> 成员内部类不能拥有静态属性和方法，但可以访问外部类的静态域
> 可以拥有静态静态常量，因为常量存放在常量池中，在编译时就已经加载进去了，所以不需要考虑是否静态
声明并创建成员内部类语法:
1.在外部类内部创建成员内部类对象：就和创建普通类一模一样
2.在外部类外部创建成员内部类对象(前提：内部类没有被private封装)
语法：
外部类.内部类 内部类引用 = new 外部类().new 内部类();
Outter.Inner in = new Outter().new Inner()
```


2.静态内部类--类比静态方法

```
定义在类中，加static修饰
静态内部类与普通外部类没有任何区别，仅仅是定义一个在类的内部而已。
1.静态内部类的穿件不需要依赖外部类对象，可以直接创建
在外部类外部创建静态内部类语法：
外部类.内部类 内部类 引用 = new 外部类.内部类()
Outter.Inner in = new Outter.Inner();
2.静态内部类可以拥有普通属性，但是不能访问外部类的非static属性，只能访问静态属性

```

3.方法内部类(局部内部类)


```
定义：定义在外部类方法中的类
1.不允许使用红访问权限修饰符 public private protected 均不允许
2.方法内部类对外部完全隐藏，除了创建这个类的方法可以访问外均不能访问
3.方法内部类要想使用方法的形参，该形参必须使用final声明(JDK8之后变为隐式的final声明)
```

4.匿名内部类


```

```
#### 12.23
==抽象类和接口==
##### 强制要求子类复写方法
###### 以后尽量不要直接继承实现好的类，而要继承抽象类或者实现接口

```
class Person {
    public void print() {
        
    }
}
class Student extends Person {
    //可以不覆写print()
}
普通类无法强制要切子类覆写方法，因此产生抽象类与接口
```
###### 抽象类

```
> 抽象类就是比普通类多了一些抽象方法(0...N)。(抽象类是普通类的超集)(含抽象方法的类定是抽象类)
> 抽象类使用abstract修饰
> 抽象类不能直接产生实例化(抽象类半成品，无法直接使用)
```
###### 抽象类使用原则

```
1.抽象类必须有子类(abstract 与 final 不能同时出现)(final 修饰的类没有子类)(编译时出错)
2.抽象类子类(不是抽象类)必须覆写抽象类的所以抽象方法
3.若子类是抽象类，可以不覆写可以覆写
4.抽象类可以使用子类向上转型为其实例化。抽象类一定不能直接实例化对象(无论是否有抽象方法)
Person per = new Student();
5.由于抽象类强制要求子类覆写抽象方法，abstract 不可与 private 同时使用
封装具体实现子类：
抽象类中定义普通方法，普通方法有内部类继承抽象类，并覆写

```
###### 抽象类使用原则

```
> 抽象类也存在构造方法，并且子类也一定按照实例化流程先调用抽象类的构造方法，而后在调用子类的构造方法。
> final与abstract ，private 与 abstract不能同时出现
> 抽象类也分为外部抽象类与内部抽象类
> 内部抽象类的抽象方法与外部抽象类的抽象方法无关
> 当前直接继承哪个抽象类，就腹泻其抽象类方法。(若直接继承外部抽象类，则只需要覆写外部抽象类的所有抽象方法)
> 父类的内部抽象类在子类的内部抽象类中覆写
```



###### 抽象方法

```
语法：public abstract void print();
特点：没有方法体、使用abstract定义
> 本地方法也没有方法体
本地方法(调用C或者其他语言同名方法)：public native void test();

```
==模板设计模式==
###### 描述设计模式：举例+特点
学设计模式方法：找第三方

```

特点：基于抽象类，final核心算法 + 抽象方法
开闭原则(OCP): 一个软件实体如类、模块和函数接口应该对扩展开放、对修改关闭。
模板模式(模板方法模式)
模板方法定义了一个算法的步骤，并允许子类为一个或者多个步骤提供具体实现。

```
==接口(更纯粹的抽象类)-融合==

接口优先原则：即可使用接口又可使用抽象类时，优先考虑使用接口
###### 定义：接口就是抽象方法与全局常量的集合(JDK8以前)，使用interface定义接口
(为了区分接口。建议在所有接口前面追加字母I。)
```
interface IMessage{
    public static final String MSG = "I am a biter" ; // 全局常量
    public abstract void print() ; // 抽象方法
}
```

```java
子类如果要想使用接口，那么就必须使用implements关键字来实现接口，子类名以Impl结尾，同时，一个子类可以实现多个接口，
【可以使用接口来实现多继承的概念】对于接口的子类（不是抽象类）必须覆写接口中的全部抽象方法。随后可以利用子类的向上转型通过实例化子类来得到接口的实例化对象。
interface IMessage{
public static final String MSG = "I am a biter" ; // 全局常量
public abstract void print() ; // 抽象方法
}
interface INews {
public abstract String getNews() ;
}
class MessageImpl implements IMessage,INews {
public void print() {
System.out.println(IMessage.MSG) ;
}
public String getNews(){
return IMessage.MSG ; // 访问常量都建议加上类名称
}
}
public class Test{
public static void main(String[] args) {
IMessage m = new MessageImpl() ; //子类向上转型,为父接口实例化对象
m.print() ; // 调用被子类覆写过的方法
INews n = (INews) m ;//父接口的相互转换，有共同子类的情况写才可相互转换
System.out.println(n.getNews()) ;
}
}

```

###### 接口的使用原则
```java
1.接口中只有public权限
    在接口中public static final abstract 均可以省略不写，抽象类中均要写
    在以后编写接口的时候，99%的接口只提供抽象方法。很少在接口里提供全局常量；
    阿里编码规约：接口中的方法和属性不要加任何修饰符号,public也不要加，保持代码的简洁性。
2.当子类即需要实现接口又需要继承抽象类时，先使用extends继承一个抽象类，然后使用implements实现多个接口
    当父类与父接口也有共同子类是，父类与父接口亦可以相互转换
3.抽象类可以使用implements实现若干个接口，接口无法继承抽象类，(抽象类中含有普通方法，普通属性)
4.接口之间可以使用extends继承多个父接口(接口之间多继承)
interface A {
    public void printA() ;
}
interface B {
    public void printB() ;
}
interface C extends A,B { // 接口多继承
    public void printC() ;
}
class Impl implements C{
    public void printA() {}
    public void printB() {}
    public void printC() {}
}
```

###### 接口作用

```
1.定义标准(USB接口)
2.表示行为、能力(购买商品)
```
==工厂设计模式==

==简单工厂==

第三方：一个具体工厂类
解决：
###### 组成
1. 一个抽象产品接口(类)
2. 多个具体产品类
3. 一个工厂(生产所有产品)
##### 优点：

```
1.简单易于实现
2.将类的实例化操作解耦到工厂中，无需修改客户端
```

##### 缺点：

```
每当有新产品产生时，都得修改工厂类代码让其支持新商品，违反OCP原则(需要后序结合反射机制解决)
```
==工厂方法模式==

###### 工厂也变成接口(抽象工厂接口)-多个产品呈现出家族式特点时

```
interface ComputerFactory {
    Computer createComputer();
}
class ApplwFactory implements ComputerFactor {
    public Computer createComputer(){
        return new MacbookPro();
    }
}
```

##### 组成
```
1. 一个抽象产品类
2. 多个具体产品类
3. 一个抽象工厂
4. 多个具体工厂 - 每一个具体产品对应一个具体工厂
```
##### 工厂方法模式是针对每个产品家族提供一个工厂类，在客户端中判断使用哪个家族产品，使用哪个工厂类去生产对象
==代理模式(第三方--代理类)==
##### 两个子类共同实现一个接口，其中一个子类负责真实业务实现，另外一个子类完成辅助真实业务主题的操作。

```
买mac
真实买的：你
辅助买的：代购

```
## 12.29
==三大特殊类==
##### String类详解
1. 实例化方式

```java
1.1 直接赋值(用的最多)
    String str = "hello";
    String str1 = "hello";
两个栈内存指向同一堆内存
自动入池，到对象数组
---
String类采用共享设计模式：
    在JVM底层实际上会自动维护一个对象池（字符串对象池），如果现在采用了直接赋值的模式进行String类的对象
实例化操作，那么该实例化对象（字符串内容）将自动保存到这个对象池之中。如果下次继续使用直接赋值的模式
声明String类对象，此时对象池之中如若有指定内容，将直接进行引用；如若没有，则开辟新的字符串对象而后将
其保存在对象池之中以供下次使用
1.2 用过构造方法赋值
    String str = new String("hello");
1.2.1 采用构造方法实例化对象不会入池
手工入池：intern();
    String str = new String("hello").intern();和直接赋值相等
1.2.2 使用构造方法实例化String 对象时会产生两块内存空间，其中一块为垃圾空间
```
2. 字符串相等比较

```
"==" 比较的两个操作数的值，
    对于基本类型比较的是值的大小
    对于引用比较堆内存地址是否相等
比较字符串内容是否相等时，使用equals()
```
3. 字符串常量都是String类的匿名对象

```
字符串常量：使用双引号括起来的内容字符串常量。所有字符串常量都是String的匿名对象
null 指针不能引用方法，null.equals(),可以写到括号内，避免null.
```
4. 字符串常量不可变更

```java
String str = "hello";
str = str + " world";
str = str + " !!!";
str 最终指向一个新的堆空间 "hello world !!!"
> 字符串+操作不要出现太多次数，会产生大量的垃圾空间
```
5. 字符与字符串的相互转换
##### 字符串就是一个字符数组(重要)
5.1char[] -> String
```java
调用String的构造方法实现
public String(char value[]):所有转
public String(char value[],int offset,int count):部分换
```
5.2 String -> char 

```java
public char charAt(int index):取得字符串指定索引的字符
public char[] toCharArray();
char[] c = str.toCharArray();
```
##### 判断字符串是否由全数字组成

```java
 转化为字符数组，遍历
```
###### 5.3 String -> byte

```java
public byte[] getBytes();将字符串全部转为字节数组
public byte[] getBytes(String charSetName):将字符串已制定编码转为字节数组
```

##### 字符串比较

```java
equals():区分大小写比较
boolean equalsIgnoreCase():不区分大小写比较
int compareTo(String anotherString):当碰到第一个不相等的字符时，终止比较，返回两个字符的ASCAII差值，当前字符串-目标字符串

```
#### 字符串查找(重要)

```java
public boolean contains(String str):判断一个子字符串是否存在
public int indexOf(String str):从头开始查找子字符串的位置，若找到返回开始索引，否则返回-1
public int lastIndexOf(String str):从后向前查找指定字符串，若找到返回开始索引，否则返回-1
public boolean startsWith(String prefix):判断是否以指定字符串开头
publci boolean endsWith(String suffix):判断是否以指定结尾
```
##### 字符串替换处理

```java
public String replaceAll(String regex,String replacment):将regex 内容的字符串全部替换为replacement
```
##### 字符串拆分
```java
将一个完整的字符串按照指定格式拆分为若干个子字符串
public String[] split(String regex,int limit):
将字符串按照指定格式拆分为limit个子字符串，若为空则将指定字符用转义字符表示
```
##### 字符串截取[)

```
public String substring(int beginIndex)
    从指定索引位置开始截取到字符串结尾
public String substing(int beginIndex,int endIndex)
    截取部分内容
    
首字符大写处理(重要)
    public static String upperCaseFirstChar(String str){
        return str.substring(0,1).toUpperCase()+str.sbustring(1);    
    }
    
```

##### 其他操作方法(JavaSE 8)
##### 判断字符串是否为空

```

```

==两个sb类：StringBuffer类和StringBuilder类==

```
> 由于字符串常量不可变更，为了方便进行字符串内容的修改，引入两个sb类
在字符串中使用+进行字符串内容的拼接操作，会产生大量垃圾空间
引入两个sb类后，字符串拼接用append(各种类型)：StringBuffer
String与sb的相互转换
String->StringBuffer
1.调用StringBuffer()构造方法
    StringBuffer(str)
    调用append()
2.StringBuffer -> String
toString()


```

> 面试题

```
请解释String类与StringBuffer、StringBuilder的区别
1.字符串常量不可变更，而StringBuffer与StringBuilder内容可以修改(append())String变量在使用+进行字符串拼接时候，javac编译器会将String变量变为StringBuilder而后进行append处理
2.StringBuilder采用同步处理，线程安全，性能较低，StringBuilder采用异步处理，线程不安全，性能较高

StringBuilder下
    字符串反转：reverse();
    内容删除: delete(int start ,int end );
    插入数据：insert(int offset ,插入内容)
    sb.delete.insert(0,123);
    
```
==Object 类(最重要的类)==

##### Object是Java默认提供的一个类。Java里面除了Object类，所有的类都是存在继承关系的。默认会继承Object父类。即，所有类的对象都可以使用Object进行接收。

1. 获取对象信息
2. 
```
    在使用对象直接输出的时候，默认输出的是一个地址编码。如果现在使用的是String类
该类对象直接输出的是内容，主要原因就是toString()方法的问题。
    通过以上代码发现，默认Object类提供的toString()方法只能够得到一个对象的地址（
这是所有对象都共同具备的特征）。如若觉得默认给出的toString()方法功能不足，就在需要的子类上覆写toString()方法
系统输出对象时，默认调用对象的toString()
```
==对象比较equals()方法==

```
> "==" 比较的是值是否相等
    基本数据类型：存放数据大小
    引用数据类型：比较的是存放的地址是否想等
> 进行两个引用类型内容的比较实用equals()方法比较,必须覆写equals()
    public boolean equals(Object obj) {
        //判断地址
        if(this == obj) {
            return true;
            //是否是Person对象
            if(!(obj instanceof Person)) {
                return false;
            }
            //窜入的一定是Person对象并且地址不相等
            //向下转型脱掉小马甲，要比较Person的属性是否相等
            Persong per = (Person) obj;
            retrun per.name.equals(this.name) && per.age == this.age;
        }
    }

```
3. Object除了是所有类的父类外、Object类还可以接收数组与接口。(Object可以接收所有引用类型)

==包装类==
##### 基本数据类型封装到类中
```
class IntDemo {
    private int num ;
    public IntDemo(int num) {
        this.num = num ;
}
public int intValue() {
        return this.num ;
    }
}
```
==Java中的包装类==
##### 数值型包装类(Number类的子类)：
Byte、Double、Short、Long、Float、Integer(int)
##### 对象包装类(Object类的直接子类)
Boolean、Character(char)

==装箱与拆箱(手工JDK1.5前)==
##### 装箱(将基本数据类型变为包装类对象，利用每个包装类提供的构造方法实现装箱处理)
##### 拆箱(将包装类中包装的基本数据类型取出。利用Number类中提供的六种方法)

```
Integer num = new Integer(55) ; // 装箱
int data = num.intValue() ; // 拆箱
System.out.println(data);
```

==JDK1.5之后提供了自动拆装箱==

##### 使用包装类对象和使用基本数据类型一样
```
Integer intDemo = 20
System.out.println(intDemo);

// 自动装箱
Integer x = 55 ;
// 可以直接利用包装类对象操作
System.out.println(++x * 5 );
> 自动
```
##### 对于Integer var = ？？(自动装箱) 在-128 - 127 之间赋值，Integer对象在Integer常量池产生，会复用已有对象。在这个区间外的所以后数据在堆上产生，不会复用已有对象
判断两个包装类对象是否相等，实用equals方法比较
##### 到底选择包装类还是基本类型
1. 强制要求：所有POJO类(自己定义的Java类：简单Java类)的属性均使用包装类
2. 推荐：所有局部变量使用基本数据类型(方法中)
##### 字符串与基本数据类型的转换
String -> 基本类型

```
使用包装类(对应)提供的parseXXX方法
eg：Integer.parseInt("123");
如果包含非数字，报错：java.lang.NumberFormatException(只存在于数值型包装类中Number的子类)非数字的字符串转为数值型
eg:Integer.parseInt("123a");
以后在进行字符串与数值转换时，首先判断字符串是否有纯数字组成。


```
1. "" + 数据类型
2. 基本类型 -> String.valueOf(各种数据类型)
==包的定义和使用==

##### 1.1包(package)的定义

```
以后再进入源文件时先定义包名称
Java包的本质就是一个文件夹，避免类名重复的问题。
在源文件首行使用package定义包名
```

###### 编译时加上 -d 参数

```
javac -d 源文件所在路径-绝对路径(当前路径 .)源文件名称
javac -d . Test.java
```

###### 运行时使用类的全名称(包名.类名)

```
java www.bit.java.Test
```

##### 2.包的导入(不同包内文件相互调用)

```
使用import 
import www.bit.java.util.Message;
```
##### 自动编译

```
编译器会将当前路径下所有*.java源文件按照使用顺序进行一次性的编译
javac -d . ./*.java
```
##### jar包

```
jar包实际上就是所有class文件的压缩包
```
##### cmd切换到当前路径的两种方式

```
1. shift + 右键
2. cd 拖文件夹
```

##### jar命令

```
jar Message.jar Message.class
```

二. 访问控制权限

```
> private(私有访问权限，仅供本类使用)
> default(包访问权限)
> protected(继承访问权限)
> publi(公共访问权限)
```

##### 2.1 包访问权限default

```
在一个包的所有源文件均可以访问
```

##### 2.2继承访问权限protected

```
仅供子类使用
```
##### SE最后一个设计模式(单例***最重要)
##### 单例：类的对象有且只有一个

```
> 首先控制产生对象数量-将构造方法私有化(从源头控制对象数量)
> 类构造方法私有化:任何其他类均无法产生此类对象
> 唯一的一个对象产生于类内部
> 唯一的属性为静态属性，并且类中提供静态方法取得此对象(类的外部无法产生对象，因此无法调用对象)

> 饿汉式单例
package test;
class Singleton{
// 在类的内部可以访问私有结构，所以可以在类的内部产生实例化对象
private static Singleton instance = new Singleton() ;
    private Singleton() { // private声明构造
}
public static Singleton getInstance() {
    return instance ;
}
public void print() {
    System.out.println("Hello World");
    }
}
public class SingletonTest {
    public static void main(String[] args) {
    Singleton singleton = null ; // 声明对象
    singleton = Singleton.getInstance() ;
    singleton.print();
    }
}
饿汉： 上来就new(一般就写)
懒汉： 用时才new(存在线程安全模式问题，多线程并发下可能会产生不止一个对象)

> 三个核心组成
    构造方法私有化
    类内部提供静态私有对象
    类内部提供静态方法
```
##### 懒汉单例
```java
package test;
class Singleton{
private static Singleton instance ;
private Singleton() { // private声明构造
}
public static Singleton getInstance() {
if (instance==null) { // 表示此时还没有实例化
instance = new Singleton() ;
}
return instance ;
}
public void print() {
System.out.println("Hello World");
}
}
public class SingletonTest {
public static void main(String[] args) {
    Singleton singleton = null ; // 声明对象
    singleton = Singleton.getInstance() ;
    singleton.print();
    }
}
```
==异常==
##### 4.1异常类体系


```
> Error类描述Java运行时内部错误或资源耗尽错误(OOM,StackOverFlowError).
> Exception:
> RuntimeException:程序出错
    ClassCastException:向下转型是要先向上转型
    NullPointerException:空指针异常
    NumberFormatException:格式转换异常
    ArrayIndexOutofBoundsException:数组越界异常
> IOException:程序本身没有问题，I/O异常(打开一个不存在的文件)
> 受查异常:所有其他已异常，必须强制用户进行异常处理(要么try/catch要么给上层抛出)
> 非受查异常:所有Error以及RuntimeException直接子类，不强制进行异常处理(try catch)
    空指针，数组越界
```
##### 4.2异常处理格式

```
try {
    //可能出现异常的代码
}[catch ...] {//可选0-n
    //出现异常后咋办
    //输出错误堆栈信息
    e.printStackTrace();
}[finally] {//可选0-n
    //异常的出口
}
try-catch(可以有多个catch)
try-finally
try-catch(可以有多个catch)-finally

finally代码块无论如何都会执行
    若try或catch块中有return语句，则在return语句执行之前一定会执行finally代码块
    finally保证重点代码一定执行的一种机制
```
##### throws
###### 用在方法声明上，明确告诉调用者本方法可能产生的异常，但方法本身不处理，将异常抛出
###### ctrl+alt+T  ：将选中的代码包括在
###### Alt + enter ：代码修改提示
###### Alt + Shift + insert： getter setter 方法

```
try catch：异常之后可以执行
throws 往回扔main()扔回JVM，回不来了异常之后代码无法执行
    用在方法体中，人为进行异常抛出
    如果希望自己产生异常类对象而非由JVM产生，就可以在代码块中使用throw来抛出异常(一般与分支语句搭配使用来抛出自定义异常)
    try{
        throw new Exception("今天不上班");    
    }
    catch(Exception e){
        
    }
```
##### 自定义异常
###### 用户可以继承Exception或TUNtimeException来实现自定义异常

```
class AddException extends RuntimeException {
    public AddException () {
        super("两束相加不能为2");
    }
}

throw new AddException()
```
##### 断言assert
assert 布尔表达式："出错时语句";否则向下执行

```
默认不启用，要启用断言 使用参数 -ea(启用时参数,VM option) -d(运行参数)
int num = 20；
asesrt num == 55 : "错误num 应当为55";
```
##### 栈溢出网站
    将异常黏贴上去就行
##### IDEA插件
背景插件：Background Image plus：view-> set background image
花括号： Rainbow Brackets


