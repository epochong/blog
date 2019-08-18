---
title: Java-反射
date: 2019-08-11 20:47:02
tags: java
---

### 反射 - 框架设计的灵魂

**框架：**半成品软件。可以在框架的基础上进行软件开发，简化编码

**反射：**将类的各个组成部分封装为其他对象，这就是发射机制。

#### Java代码在计算机中经历的三个阶段

- **Source源代码阶段：**`javac`编译`.java`文件成`.class`字节码文件
    - 属性
    - 构造方法
    - 方法

- Class类阶段
    - 成员变量：Filed[]  fields
    - 构造方法：Constructor[]  cons
    - 成员方法：Method[]  methods
- Runtime运行阶段

#### 反射好处

> 编译器的对象方法代码提示的实现

1. 可以在程序运行中，操作类的对象
2. 可以解耦，提高程序的可扩展性

### Java中的反射

- "反"的操作核心的处理就在于Object类的一个方法:，取得Class对象: 

    ```java
    public final native Class<?> getClass();
    ```

- java中使用Class类描述java中的类的信息

    - ==Class类描述接口与类组成，Class对象由JVM在第一次加载类时产生，并且全局唯一==

- 三种获得Class对象的方法

    1. 任何类的实例化对象可以通过Object类中的getClass()方法取得Class类对象。 

    2. "类.class":直接根据某个具体的类来取得Class类的实例化对象。 

    3. 使用Class类提供的方法:public static Class<?> forName(String className) throws  ClassNotFoundException 

        ```java
        public class Test {
        	public static void main(String[] args) throws ClassNotFoundException{ 		Class<?> cls = Class.forName("java.util.Date") ; 					  System.out.println(cls.getName()); 
        	} 
        }
        ```

    - ==基本类型：int.class-->int，包装类型：Interge.class--->Classjava.util.Integer==
    - ==多线程模式下，synchronized(Test.class)全局锁，因为Class对象唯一，所以可以锁==

#### Class类方法解释

> 利用反射可以做出一个对象具备的所有操作行为，最为关键的是这一切的操作都可以基于Object进行。

##### 实例化类对象方法 -   `newInstance()`

```java
public T newInstance() throws InstantiationException, IllegalAccessException
```

- 通过该方法实例化对象

    ```java
    //?可用Date替换
    Class<?> cls = Class.forName("java.util.Date") ; 
    Object obj = cls.newInstance() ;
    // 实例化对象，等价于 new java.util.Date() ;
    ```

    - ==通过反射可以破坏类的封装性，实例化私有构造方法==

- java实例化类对象的方式

    1. 直接new
    2. 通过工厂模式
    3. 通过反射

    - ==通过反射可以解决传统的工厂模式带来的增加产品要修改工厂的弊端==

##### 取得父类信息的方法
- 取得类的包名称

     - 方法声明

         ```java
         public Package getPackage()
         //Package是一个类，用来描述包信息的类
         ```

     - 使用方法

         ```java
         Class<?> cls = Date.class ;
         //不加getName()前面显示类型，package
         System.out.println(cls.getPackage().getName());//只显示包名称
         ```

- 取得父类Class对象

    	- 方法声明
      
        	```java
        // 本地方法
        public native Class<? super T> getSuperclass();
        ```
        
    - ==java 的一个设计理念是，与泛型相关的异常最好是在编译期间就被发现，因此设计了 extends 与 super 这两种方式==
        - **List<? extends T>** 表示该集合中存在的都是类型 T 的子类，包括 T 自己。
        - **List<? super T>** 表示该集合中存的都是类型 T 的父类，包括 T 自己。
    
- 取得父类实现的接口们

    - 方法声明
    
    	```java
    	public Class<?>[] getInterfaces()
    	```
    
    - 方法应用
    
        ```java
        // 取得CLS类的Class类对象
        Class<?> cls = CLS.class ; 
        // 取得实现的父接口对象 
        Class<?>[] iClass = cls.getInterfaces() ; 
        for (Class<?> class1 : iClass) { 
        	System.out.println(class1.getName()); 
        }
        ```
    

##### 通过反射获取构造方法

- 取得指定参数类型的public构造

    ```java
    public Constructor<T> getConstructor(Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException
    ```

- 取得类中的所有public构造

    ```java
    public Constructor<?>[] getConstructors() throws SecurityException
    ```

    - 只能取得类中所有public的权限的
    - 本类，不可为父类，与继承无关

- 取得类中的所有构造方法（不限权限）

    ```java
    public Constructor<?>[]getDeclaredConstructors()throwsSecurityException
    ```

##### 关于返回值Constructor类

- **重要方法，**根据参数实例化对象的方法

    ```java
    public T newInstance(Object ... initargs)
    ```

    - 方法应用

        ```java
        Class<?> cls = Person.class ; // 取得指定参数类型的构造方法对象 
        Constructor<?> cons = cls.getConstructor(String.class,int.class) ; System.out.println(cons.newInstance("epochong",29));
        ```

- **getName()：**返回包名.类名

- **toSting()：**才是真正的返回构造方法的信息：权限、参数列表

==Class类通过反射实例化类对象（cls.newInstance()）的时候，只能够调用类中的无参构造。如果现在类中没有无参构造则无法使用Class类调用，只能够通过Constructor对象，明确的构造调用实例化处理。==

##### 反射调用普通方法（***）

- 取得全部普通方法

    ```java
    public Method[] getMethods() throws SecurityException
    ```

- 取得指定的普通方法

    ```java
    //方法名称，这个方法的参数类型的class对象，按照参数顺序，依次填写对应的class对象
    //如果该方法没有参数，只填写方法的名称即可
    public Method getMethod(String name, Class<?>... parameterTypes)
    ```

##### 关于两个方法实现机制

以上两个方法范辉的类型是`java.lang.reflflect.Method`类的对象，在此类中提供有一个调用方法的支持:

- 调用

    ```java
    public Object invoke(Object obj, Object... args)throws IllegalAccessException,
    IllegalArgumentException,
    InvocationTargetException
    ```

- 应用

    ```java
    // 任何时候调用类中的普通方法都必须有实例化对象 
    Class<?> cls = Class.forName("www.epochong.Person") ; 
    // 取得setName这个方法的实例化对象,设置方法名称与参数类型 
    Object obj = cls.newInstance() ; 
    Method setMethod = cls.getMethod("setName", String.class) ; 
    // 随后需要通过Method类对象调用指定的方法，调用方法需要有实例化对象,同时传入参数 
    
    // 相当于Person对象.setName("epochong") ; 
    setMethod.invoke(obj, "epochong") ; 
    Method getMethod = cls.getMethod("getName") ; 
    // 相当于Person对象.getName() ; 
    Object result = getMethod.invoke(obj) ; 
    System.out.println(result) ;
    ```

    ==此类操作的好处是:不再局限于某一具体类型的对象，而是可以通过Object类型进行所有类的方法调用。==

##### 反射调用类中的属性


> 不带Declared只能访问public的变量，带Declared不考虑修饰符，但是在访问私有属性的时候要通过暴力反射`setAccessible(true)`的方式获取才能操作

- 获取成员变量得到和设置值

    - `File[]  getFields()`：获取该类和父类所有public修饰的变量

    - `Field[] getField(String name)`：获取该类指定名称的public变量

        ```java
        Class personClass = Person.class;
        Field a = personClass.getField("a");
        Person p = new Person();
        //设置属性值
        a.set(p,"epochong");
        //获取属性值
        Object value = a.get(p);
        ```

    - `Field[] getDeclaredFields()`：不考虑修饰符，获取该类所有属性

    - `Field[] getDeclaredField(String name)`：不考虑修饰符，获取指定属性

        - 忽略访问权限修饰符的安全检查（暴力反射）

            ```java
            Field d = personClass.getDeclaredField("d");
            d.setAccessible(true);
            ```

    
    - 应用
    
        ```java
        class Person { 
        	private String name ; 
        }
        public class Test { 
        	public static void main(String[] args) throws Exception {
        		Class<?> cls=Class.forName("com.epochong.Person") ; // 实例化本类对象 
        		Object obj = cls.newInstance() ; // 操作name属性 
        		Field nameField = cls.getDeclaredField("name") ; 					nameField.set(obj, "epochong") ; // 相当于对象.name = "epochong" 		 
                System.out.println(nameField.get(obj)); // 取得属性 
        		} 
        }
        ```
    
    - Field类中的一个有用的方法
    
        ```java
        public Class<?> getType()
        ```
    
        - 应用
    
            ```java
            public class Person {
                private Integer id;
                protected String name ;
                public int age ;
            }
            ```
    
            ```java
            public class Test1 {
                public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
                    Class<?> cls = Class.forName("com.epochong.Person");
                    Object obj = cls.newInstance();
                    Field idField = cls.getDeclaredField("id");
                    System.out.println(idField.getType());
                    System.out.println(idField.getType().getName());
                    System.out.println(idField.getType().getSimpleName());
            
                }
            }
            ```
    
        - 输出结果
    
            ```java
            class java.lang.Integer
            java.lang.Integer
            Integer
            ```
    
        - 如果是基本类型结果为
    
            ```java
            int
            int
            int
            ```

##### 动态设置封装（暴力反射）

> 获得该类的私有属性，并对其赋值

- 方法

    ```java
    public void setAccessible(boolean flag) throws SecurityException
    ```

    - 应用

        ```java
        class Person { 
            private String name ;
        }
        public class Test { 
            public static void main(String[] args) throws Exception { 
            Class<?> cls = Class.forName("www.bit.java.testthread.Person") ; // 实例化本类对象 
            Object obj = cls.newInstance() ; // 操作name属性 
            Field nameField = cls.getDeclaredField("name") ; // 取消封装 
            nameField.setAccessible(true) ; 
            // ---------------------------- 
            nameField.set(obj, "epochong") ; // 相当于对象.name = "epochong" 
            System.out.println(nameField.get(obj)); // 取得属性
        	}
        }
        ```

