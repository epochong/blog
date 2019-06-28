---
title: 简单工厂模式 抽象工厂模式 抽象工厂方法模式
date: 2019-04-22 16:35:20
tags: [java,设计模式]
---
**一、题目分析**
**1、题目要求**
**1.1、简单工厂模式**
使用简单工厂模式模拟女娲（Nvwa）造人（Person），如果传入参数M，则返回一个Man对象，如果传入参数W，则返回一个Woman对象，请实现该场景。现需要增加一个新的Robot类，如果传入参数R，则返回一个Robot对象，对代码进行修改并注意女娲的变化。
**1.2、工厂方法模式**
海尔工厂(Haier)生产海尔空调(HaierAirCondition)，美的工厂(Midea)生产美的空调(MideaAirCondition) 。使用工厂方法模式描述该场景，绘制类图并编程实现。
**1.3、抽象工厂模式**
电脑配件生产工厂生产内存、CPU等硬件设备，这些内存、CPU的品牌、型号并不一定相同，根据下面的“产品等级结构-产品族”示意图，使用抽象工厂模式实现电脑配件生产过程并绘制相应的类图，绘制类图并编程实现。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190517203000613.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)
**2、下面简单总结一下三种工厂方法**
**2.1、简单工厂**
   简单工厂模式：专门定义一个类用来创建其它类的实例，被创建的实例通常都具有共同的父类。
**概要：**

    1. 一个抽象产品类
    2. 具体产品类
    3. 一个工厂

**优点：**
简单易于实现把类的实例化交给工厂,易于解耦。
**缺点：**
添加具体产品需要修改工厂违反OCP开放封闭原则
**2.2、工厂方法模式**
工厂方法模式：定义一个用来创建对象的接口，让子类决定实例化哪一个类，让子类决定实例化延迟到子类。
工厂方法模式是针对每个产品提供一个工厂类，在客户端中判断使用哪个工厂类去创建对象。
简单工厂模式 VS 工厂方法模式：
对于简单工厂模式而言，创建对象的逻辑判断放在了工厂类中，客户不感知具体的类，但是其违背了开闭原则，如果要增加新的具体类，就必须修改工厂类。
对于工厂方法模式而言，是通过扩展来新增具体类的，符合开闭原则，但是在客户端就必须要感知到具体的工厂类，也就是将判断逻辑由简单工厂的工厂类挪到客户端。工厂方法横向扩展很方便，假如该工厂又有新的产品要生产，那么只需要创建相应的工厂类和产品类去实现抽象工厂接口和抽象产品接口即可，而不用去修改原有已经存在的代码。
**概要：**

    1. 一个抽象产品类
    2. 多个具体产品类
    3. 一个抽象工厂
    4. 多个具体工厂 - 每一个具体产品对应一个具体工厂

**优点：**
降低了代码耦合度，对象的生成交给子类去完成
实现了开放封闭原则 - 每次添加子产品 不需要修改原有代码
**缺点：**
增加了代码量，每个具体产品都需要一个具体工厂
当增加抽象产品 也就是添加一个其他产品族 需要修改工厂 违背OCP
**2.3、抽象工厂模式**
抽象工厂模式：提供一个创建一系列相关或相互依赖对象的接口，而无需指定它们具体的类。
工厂方法模式和抽象工厂模式基本类似，可以这么理解：当工厂只生产一个产品的时候，即为工厂方法模式，而工厂如果生产两个或以上的商品即变为抽象工厂模式。
**概要：**

    1. 多个抽象产品类
    2. 具体产品类
    3. 抽象工厂类 - 声明(一组)返回抽象产品的方法
    4. 具体工厂类 - 生成(一组)具体产品

**优点：**
实现多个产品族(相关联产品组成的家族)，而工厂方法模式的单个产品,可以满足更多的生产需求很好的满足OCP开放封闭原则抽象工厂模式中我们可以定义实现不止一个接口，一个工厂也可以生成不止一个产品类 对于复杂对象的生产相当灵活易扩展
**缺点：**
扩展产品族相当麻烦，而且扩展产品族会违反OCP,因为要修改所有的工厂
由于抽象工厂模式是工厂方法模式的扩展 总体的来说 很笨重

**二、类图设计**
使用简单工厂模式模拟女娲（Nvwa）造人（Person）
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190517203017990.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)

海尔工厂(Haier)生产海尔空调(HaierAirCondition)，美的工厂(Midea)生产美的空调(MideaAirCondition) 。![在这里插入图片描述](https://img-blog.csdnimg.cn/2019051720303454.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)



电脑配件生产工厂生产内存、CPU等硬件设备，这些内存、CPU的品牌、型号并不一定相同，使用抽象工厂模式实现电脑配件生产过程并绘制相应的类图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190517203055125.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)

**三、程序实现**
**反射机制实现**
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019051720310776.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)
如上图所示，程序设计一个CPU接口，继承它的子类用于生产CPU，Mac生产的CPU建立MacCpu继承它，Pc上产的CPU建立PcCpu继承它。同理一个RAM接口，分别由Mac和Pc的厂家的MacRam和PcRam继承，即可生产RAM，建立一个接口IFactory，用来描述生产产品，里面包含生产CPU和生产RAM方法，继承继承该接口的类都是每一个厂家的工厂，即如图所示，MacFactory为Mac的工厂，它可以生产他们厂的CPU和RAM，PcFactory也是如此。
最重要的类莫过于Factory类，Client类通过调用它的静态方法getInstance方法参数classname为该类的全名称，通过Class.forName(类名)获得这个具体工厂类的类，通过newInstance方法实例化具体该工厂类的对象。但是因为我们不知道用户(Client)具体会想要哪一个工厂的产品，所以在抽象工厂中使用IFactory用来接收通过类名实例化的具体工厂。这样，用户只需要在传类名的时候指明具体工厂类就行了。通过IFactory下面的抽象方法，可以调用具体子类工厂的创造具体产品方法。主要类如下。
**Client用户类：**
```java
    public class Client {
        public void buyCpu(CPU cpu) {
            cpu.printCpu();
        }
        public void buyRam(RAM ram) {
            ram.printRam();
        }
        public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
            Client client = new Client();
            IFactory macFactory = Factory.getInstance("www.homework.embarkationfournext.reflect.MacFactory");
            client.buyCpu(macFactory.createCpu());
            client.buyRam(macFactory.createRam());
           IFactory pcFactory = Factory.getInstance("www.homework.embarkationfournext.reflect.PcFactory");
            client.buyCpu(pcFactory.createCpu());
            client.buyRam(pcFactory.createRam());
        }
    }
```
**Factory类：**
```java
    public class Factory {
        public static IFactory getInstance(String classname) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
            IFactory iFactory = (IFactory) Class.forName(classname).newInstance();
            return iFactory;
        }
    }
```
**IFactory接口：**
```java
    public interface IFactory {
        CPU createCpu();
        RAM createRam();
    }
    Mac工厂类：
    public class MacFactory implements IFactory {
        @Override
        public CPU createCpu() {
            return new MacCpu();
        }
    
        @Override
        public RAM createRam() {
            return new MacRam();
        }
    }
```
Pc工厂的实现同Mac工厂

**四、调试、测试及运行结果
4.1调试截图
4.1.1简单工厂模式**
类型为M则person的实例化为Man
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190517203225214.png)
类型为W则person的实例化为Woman
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190517203233972.png)
类型为R则person的实例化为Robot
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190517203238916.png)
**4.1.2工厂方法模式**

    AirConditionFactory haierFactory = new HaierFactory();
    Client client = new Client();
    client.buyAirCondition(haierFactory.createAriCondition());
    AirConditionFactory mideaFactory = new MideaFactory();
    client.buyAirCondition(mideaFactory.createAriCondition());

实例化海尔工厂hierFactory、美的工厂MideaFactory，对应指定的工厂，通过工厂生产各自的品牌的空调
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190517203308491.png)
**4.1.3抽象工厂模式**
实例化Client对象client
macFactory通过类名从工厂获得实例化MacFactory对象
PcFactory通过类名从工厂获得实例化PCFactory对象
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190517203319416.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)
**4.2测试截图**
**4.2.1简单工厂模式**
输入M创造男人
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190517203324400.png)
输入W创造女人
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190517203328115.png)
输入R创造机器人
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190517203332132.png)
**4.2.2工厂方法模式**
实例不同工厂调用创建空调方法，创建不同品牌空调
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190517203337459.png)
**4.2.3抽象工厂模式**
通过反射传入两个工厂类MacFactory和PCFactory的类名，实例化两个工厂的实例，通过createCpu()和createRam()生产不同厂的CPU、RAM。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190517203341974.png)
**五、经验归纳**
   
简单工厂模式最大的优点就是工厂内有具体的逻辑去判断生成什么产品，将类的实例化交给了工厂，这样当我们需要什么产品只需要修改工厂的调用而不需要去修改客户端，对于客户端来说降低了与具体产品的依赖工厂方法模式是简单工厂的扩展，工厂方法模式把原先简单工厂中的实现那个类的逻辑判断交给了客户端，如果像添加功能只需要修改客户和添加具体的功能，不用去修改之前的类。
抽象工厂模式进一步扩展了工厂方法模式，它把原先的工厂方法模式中只能有一个抽象产品不能添加产品族的缺点克服了，抽象工厂模式不仅仅遵循了OCP原则，而且可以添加更多产品(抽象产品),具体工厂也不仅仅可以生成单一产品，而是生成一组产品，抽象工厂也是声明一组产品，对应扩展更加灵活，但是要是扩展族系就会很笨重。

