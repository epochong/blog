---
title: SSM（Spring+SpringMVC+Mybatis）
date: 2019-08-02 14:38:11
tags: [Spring,MyBatis]
---

### 写在前面

**MVC 开发模式：**你肯定知道，现在的 B/S 系统大多都是遵从 MVC 的开发模式开发的。M 就是 Model，模型层，负责数据的存储，就是代码中的类，最早的时候必须符合 JavaBean 的规范；V 就是 View，视图层，负责信息的展示，就是用户看到的应用页面；C 就是 Controller，控制器，这一层做的主要是逻辑层的操作，比如用户登录的后台代码，当然现在的逻辑层，在开发的过程中是由 dao、service 和 controller 共同组成的。

##### SSM 这三者的作用

**Spring，**实际上是一个 Bean（各种类都能称为 Bean）的超级大工厂，只要是代码中哪里需要用到某个类，直接和 Spring 要就可以了。而以前我们只能用到哪个类就 new 一个，或者通过工厂模式。实际上这个大工厂被我们称作 IOC（依赖注入），就是生成类的某种方式，这大大降低了代码的耦合性。这里就能看到，硬说 Spring 是 M 层的也没啥毛病，但是 Spring 不但管理着以前的 JavaBean，同样还管理着 Dao 层的类，这就有一些不一样了。

**SpringMVC，**说 SpringMVC 是视图层的，这是肯定的。如果你用过 Servlet+JSP 的开发模式，你一定非常清楚，想要在一个 JSP 页面中绑定数据（数据显示在页面中），实际上是非常困难的，需要通过 request 的 setAttribute 方法，然后 JSP 页面上再来一个 request.id 之类的东西，总之非常不方便。还有就是 Controller 接收参数，如果要接收参数的话，Servlet 的开发只能在 get 或者 post 方法中写参数的名字。如果参数非常多，可想而知，这得多么复杂。SpringMVC 就非常简单了，如果我们想要接收很多参数的话，直接就能把这些参数封装在一个类中，把类放到参数的位置就好了，SpringMVC 就会自动帮助我们完成参数接收的过程，非常方便。实际上，在这个过程中，依然有 Spring 框架中 IOC 模块的支持。

**Mybatis，**同样的，mybatis 只能算作是 Controller 层中的一部分，即 dao。dao 也叫做持久层，作用就是将数据持久化到数据库之类的东西里面，也就是 ORM 框架。Mybatis 非常好用，以前我们做开发的时候，一般都是写 JSBC，就算不直接用 JDBC，还是会用 DBUtils 之类的数据库组件，但是这些组件并不灵活，稍微有些问题就无法读取或写入数据到数据库。Mybatis 的出现大大解决了这个问题。同样作用的框架还有 Hibernate，但是它和 Mybatis 相比就太臃肿了（Spring 同样可以整合 Hibernate）。

##### 整体架构

前端页面、Spring 容器、数据库连接池、数据库（这两部分其实可以合成一部分）、Maven 依赖管理。 

##### 目录设置

- vo，这个目录是存放和数据库对应的基本类的
- controller，这里面是存放控制器类的，这就类似于之前 Servlet 的作用
- dao，这是存放数据库映射文件的，功能和之前的 JDBC 相同
- service，服务层，controller 负责处理逻辑操作，service 负责的是和 dao 层交互，实现具体的功能，比如插入一条数据之类的
- resources，资源路径，多数的配置文件和 Mybatis 的映射文件都放在这里
- webapp，web相关界面和配置文件

##### 完成一个 web 请求的个过程大致如下

- 前端页面发起一个 http 请求：localhost:8080//queryUserInfohttp 
- 请求被 Tomcat 服务器得到，接着到 Servlet 中寻找映射路径，当然有了 SpringMVC 就无需去执行复杂的配置了，我们可以像 Servlet3.0 那样使用注解开发，SpringMVC 前端控制器的注解是@RequestMapping（”/queryUserInfohttp ”），通过请求的 url，寻找映射路径，找到对应Controller 类的方法
- Controller 中，注入了诸多的 Service，Controller 可以直接调用这些 service 进行操作
- controller 调用 Service 后，Service 就要执行对应的方法，在 Service 中同样注入了 dao 层的 mapper，即可调用相应的 mapper 方法执行数据库操作
- mybatis 中，dao 层分成两部分，分别是接口和 mapper 映射文件，调用 mapper 接口方法的时候，就会去找到对应方法的映射，这些映射就是执行数据库操作的语句，本质上还是 sql 语句
- 此项目肯定是有很多的依赖文件的，比如 springframwork，现在有了 Maven 就不需要我们手动导入了，只需要在 Maven 中配置即可
- 完成 controller 操作以后，再通过 SpringMVC 前端控制器控制页面跳转或者重定向之类的操作，但是现在一般使用 ajax 技术，提高前端页面的性能

### Spring MVC + MyBatis

#### 面向对象

##### 视图层 - View

- 提交表单到控制器
- 到控制器.action

##### 控制层 - Controller（Handler）

- 获取表单参数
- 调用业务逻辑（调用模型层方法）
- 转向：
    - 请求转发
    - 重定向 - 到控制器

##### 模型层 - Service（xxxImpl）

- 调用持久层

##### 持久层 - Persistence（Mapper）

##### Spring管理

- **控制层：**管理Controller（配置，注解）
- **模型层：**管理类与类之间的关系（配置，注解）
- **持久层：**管理的就是Mapper
    - Spring和Spring MVC无需整合，因为Spring MVC就是Spring中的一个模块

#### 面向关系

##### MyBatis - 数据库层（缓存-Cache）

----

> 阻抗不匹配

- 引入持久层 - DAO（映射）-MyBatis

    **O->R-Mapping：**将面向对象的语言（Object）和关系型数据库（Relations）进行映射，将java中的类映射为数据库中的表

- 持久化种类

    - Hibernate（2005-2012）
    - Oracle TopLink
    - EJB2.x(CMP)
    - JPA
    - Mybatis
    
- MyBatis逆向工程 （是一个工具类，不是新的技术）

    > 反向的帮你生产po类，mapper接口，xxMapper.xml

    - 在pom.xml中添加逆向工程plugin

    - 在maven项目的resources中引入generatorConfig.xml文件

    - 配置generatorConfig.xml文件

        ```xml
        <plugins>
            <!-- mybatis-generator自动生成代码插件 -->
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.5</version>
            </plugin>
        </plugins>
        ```

    - 编写客户端调用generatorConfig.xml文件执行文件内容生成mapper和po

- MyBatis一级缓存和二级缓存

    - 一级缓存 - 第二次查询相同数据时，从一级缓存中查询

        ```java
        /**
         * 一级缓存的特点：
         * 1 mybatis的一级缓存就是sqlSession,它没有办法控制，不用也得用。
         * 2 一级缓存(sqlSession)不需要添加任何配置，就可以使用
         * 3 一级缓存在执行update,insert,delete的时候当sqlSession.commit()操作（执行修改操作的时候需要执行commit方法）时
         * 会清空一级缓存，目的是为了避免脏读。
         */
        ```

    - 二级缓存 - mapper级别的缓存（namespace）

        ```java
        /**
         * 二级缓存特点：
         * 1.需要配置
         * 2.可以管理（开启，关闭，清空，使用）
         * 各个mapper.xml之间数据可以共享，一级缓存不可共享
         * 例如，订单表的数据需要用户的信息
         */
        ```

        - sqlMapConfig.xml中配置

            ```xml
            <!--设置 常量值-->
            <settings>
                <!--开启二级缓存(mapper)总开关-->
                <setting name="cacheEnabled" value="true"/>
            </settings>
            ```

        - xxxMapper.xml中配置

            ```xml
            <!--子开关：开启各个mapper级别的缓存-->
            <!--
                mybatis的二级缓存存储介质不一定是内存，所以需要类实现序列化接口
                子属性参考官方文档，全部使用默认值
            -->
            <cache/>
            ```

- MyBatis延迟加载

    ```java
        /**
         * 延迟加载
         * 适用规则：
         * 1. 当数据特别多的时候
         * 2. 当关联查询特别频繁的时候
         * 3. 当需要效率的时候
         * 不适用的规则：
         * 上面的规则反过来
         * @throws Exception
         */
        @Test
        public void testFindOrderUserLazyLoad()throws Exception{
            SqlSession sqlSession = sqlSessionFactory.openSession();
            OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
            //仅仅查询orders一张表  select * from orders;
            List<Orders> ordersList =  ordersMapper.findorderuserlazyload();
    
    
            for(Orders orders : ordersList){
                User user = orders.getUser(); //延迟加载  select * from user where userid=???
                System.out.println(user);
            }
    
            System.out.println(ordersList);//user???
    
            sqlSession.close();
        }
    ```

    - sqlMapConfig.xml延迟加载配置

        ```xml
        <settings>
            <!--开启延迟加载的开关-->
            <setting name="lazyLoadingEnabled" value="true"/>
            <setting name="aggressiveLazyLoading" value="false"/>
        </settings>
        ```

    - 在主表的xxxMapper.xml中配置

        ```xml
        <?xml version="1.0"  encoding="UTF-8"?>
        <!DOCTYPE mapper
                PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
                "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        <mapper namespace="com.ming.mapper.OrdersMapper">
            <!--实现延迟加载-->
            <resultMap id="orderuserlazyRM" type="com.ming.po.Orders">
                <!--配置订单信息-->
                <id column="id" property="id"/>
                <result column="user_id" property="userid"/>
                <result column="number" property="number"/>
                <result column="createtime" property="createtime"/>
                <result column="note" property="note"/>
                <!--延迟加载用户信息-->
                <!--
                     1:1关系，用association
                    延迟加载[提高性能]
                    property：主表Orders中一一对应关系的对象名需要一致，需在Orders类中创建该属性名
                    javaType：一对一关系对应的
                -->
                    <!--select:指定延迟加载需要执行的statement的ID(根据user_id查询用户信息的statement)
                           该语句来自于UserMapper.xml文件中的queryUserById
        
                    column: 订单信息中关联用户信息的列名 user_id
                    关联SQL如下：
                    select orders.*,
                        (select username from user where orders.user_id = user.id) as MyUserNanme,
                        (select sex from user where orders.user_id = user.id) as MySex
                        from orders;
                       -->
                <!--1:1-->
                <association property="user" javaType="com.ming.po.User"
                             select="com.ming.mapper.UserMapper.queryUserById" column="user_id"/>
            </resultMap>
            <!--实现延迟加载-->
            <select id="findorderuserlazyload" resultMap="orderuserlazyRM">
                select * from orders;
            </select>
        </mapper>
        ```

#### 准备动作

- 添加jar包到工程中（pom.xml）

    - Spring
        - core
        - context
        - aop
        - beans
        - web
        - web-mvc
        - jsp
        - servlet
        - jstl
        - el
    - mybatis
        - mybatis-core
        - mybatis-spring
    - DB
        - mysql
        - DBCP（数据源）
        - spring_jdbc
        - spring_tx
        - weaver（切面）
        - log4j.jar

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
    
      <groupId>com.ming</groupId>
      <artifactId>ssmdemo</artifactId>
      <version>1.0-SNAPSHOT</version>
      <packaging>war</packaging>
    
      <name>ssmdemo</name>
      <!-- FIXME change it to the project's website -->
      <url>http://www.example.com</url>
    
      <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.7</maven.compiler.source>
        <maven.compiler.target>1.7</maven.compiler.target>
      </properties>
    
    
    
    <dependencies>
          <!--*****************************spring *******************************************-->
          <!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.1.5.RELEASE</version>
          </dependency>
    
          <!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.1.5.RELEASE</version>
          </dependency>
    
          <!-- https://mvnrepository.com/artifact/org.springframework/spring-beans -->
          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>5.1.5.RELEASE</version>
          </dependency>
    
          <!-- https://mvnrepository.com/artifact/org.springframework/spring-web -->
          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>5.1.5.RELEASE</version>
          </dependency>
    
    
          <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.1.5.RELEASE</version>
          </dependency>
    
          <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.1.5.RELEASE</version>
          </dependency>
    
          <!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
          <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>5.1.5.RELEASE</version>
          </dependency>
    
          <!--********************************jsp + servlet + jstl + el**************************-->
    
          <!-- https://mvnrepository.com/artifact/javax.servlet.jsp/jsp-api -->
          <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>jsp-api</artifactId>
            <version>2.2</version>
            <scope>provided</scope>
          </dependency>
    
          <!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
          <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
          </dependency>
    
          <!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
          <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
          </dependency>
    
          <dependency>
            <groupId>taglibs</groupId>
            <artifactId>standard</artifactId>
            <version>1.1.2</version>
          </dependency>
    
          <!-- https://mvnrepository.com/artifact/javax.el/javax.el-api -->
          <dependency>
            <groupId>javax.el</groupId>
            <artifactId>javax.el-api</artifactId>
            <version>3.0.0</version>
          </dependency>
    
    
    
    
    
    
          <!--*****************************mybatis + spring ****************************************-->
          <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
          <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.1</version>
          </dependency>
    
    
    
          <!--**********************************mybatis********************************************-->
    
          <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
          <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.1</version>
          </dependency>
    
          <!--*************************************MYSQL   +   DBCP************************************************-->
          <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
          <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.38</version>
          </dependency>
          <!-- https://mvnrepository.com/artifact/commons-dbcp/commons-dbcp -->
          <dependency>
            <groupId>commons-dbcp</groupId>
            <artifactId>commons-dbcp</artifactId>
            <version>1.4</version>
          </dependency>
    
          <!--********************************************log4j***********************************************-->
          <!-- https://mvnrepository.com/artifact/log4j/log4j -->
          <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
          </dependency>
    
          <!--**********************************************JUnit4*******************-->
          <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
          </dependency>
    
          <!--*****************************ASPectJ***********************************-->
          <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.7.2</version>
          </dependency>
      </dependencies>
    
    
      <build>
        <finalName>ssmdemo</finalName>
        <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
          <plugins>
            <plugin>
              <artifactId>maven-clean-plugin</artifactId>
              <version>3.1.0</version>
            </plugin>
            <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
            <plugin>
              <artifactId>maven-resources-plugin</artifactId>
              <version>3.0.2</version>
            </plugin>
            <plugin>
              <artifactId>maven-compiler-plugin</artifactId>
              <version>3.8.0</version>
            </plugin>
            <plugin>
              <artifactId>maven-surefire-plugin</artifactId>
              <version>2.22.1</version>
            </plugin>
            <plugin>
              <artifactId>maven-war-plugin</artifactId>
              <version>3.2.2</version>
            </plugin>
            <plugin>
              <artifactId>maven-install-plugin</artifactId>
              <version>2.5.2</version>
            </plugin>
            <plugin>
              <artifactId>maven-deploy-plugin</artifactId>
              <version>2.8.2</version>
            </plugin>
          </plugins>
        </pluginManagement>
    
          <resources>
              <resource>
                  <directory>src/main/java</directory>
                  <includes>
                      <include>**/*.xml</include>
                      <include>**/*.properties</include>
                  </includes>
              </resource>
    
              <resource>
                  <directory>src/main/resources</directory>
                  <includes>
                      <include>**/*.xml</include>
                      <include>**/*.properties</include>
                  </includes>
              </resource>
          </resources>
    
      </build>
    </project>
    ```

- **核心配置文件：**Core Config file - **sqlMapConfig.xml**
  
    - 数据库相关
        - netstat -an:查看端口号（3306）
    - 添加mapper信息
    
- **映射文件：**mapper Config file - **xxxMapper.xml**
  
    - **po(Persisten Object):**持久化对象，将要映射到数据库中的类com.epochong.po.User.java
    - **资源文件：**mapper包下建立User.xml，对应的po包下有几个就建几个，作用是建立对象和数据库中的表关系
        - **id：**statementID
        - **parameterType：**int通过这个给占位符？赋值
        - **resultType：**com.epochong.User从sql中查询的结果返回值
        - **#{}：**变量的位置，基本类型可以使用任意名字，非基本类型必须用value
        - **${}：**拼接字符串的符号，与#{}可互相转换
        - **namespace：**指定空间，因为mapper有多个，必须有标识区别
    
- **log4j.properties**
  
    - 显示执行sql的日志信息
    
- **db.properties**(drivermname,url,pass)

    ```properties
    jdbc.driver = com.mysql.jdbc.Driver
    jdbc.url = jdbc:mysql://localhost:3306/mybatis?charset=utf8&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    jdbc.username = root
    jdbc.password = root
    ```

- **applicationContext-controller.xml**

    - `<mvc:annocation-driven>`
    	
    	- 映射器
    	- 适配器
    	
    - 视图解析器

        ```xml
        <bean id="resourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
                <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
                <property name="prefix" value="/jsp/"/>
                <property name="suffix" value=".jsp"/>
            </bean>
        ```

    - 处理器（注解）

        ```xml
        <context:component-scan base-package="com.epochong.controller"/>
        ```

- **applicationContext-mapper**

- **web.xml**

    - 配置前端控制器（只做处理器）

        用来找后台的控制器

        ```xml
        <?xml version="1.0" encoding="UTF-8"?>
        <!DOCTYPE web-app PUBLIC
                "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
                "http://java.sun.com/dtd/web-app_2_3.dtd" >
        
        <web-app>
        
            <display-name>ssmdemo</display-name>
        
            <context-param>
                <param-name>contextConfigLocation</param-name>
                <param-value>classpath:spring/applicationContext-*.xml</param-value>
            </context-param>
        
            <listener>
                <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
            </listener>
        
            <servlet>
                <servlet-name>springmvc</servlet-name>
                <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
                <init-param>
                    <param-name>contextConfigLocation</param-name>
                    <param-value>classpath:spring/applicationContext-controller.xml</param-value>
                    <!--classpath:指的就是webapp所有的后端东西最终都要加载到webapp中，自带/不需要在spring前加/-->
                </init-param>
            </servlet>
        
            <servlet-mapping>
                <servlet-name>springmvc</servlet-name>
                <url-pattern>/</url-pattern>
            </servlet-mapping>
        
        </web-app>
        
        ```

- **applicationContext-mapper.xml：**数据库相关配置

    - driver、username、password、url
    - mapper

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:mvc="http://www.springframework.org/schema/mvc"
           xmlns:context="http://www.springframework.org/schema/context"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
           http://www.springframework.org/schema/mvc
           http://www.springframework.org/schema/mvc/spring-mvc.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context.xsd">
    
        <!--spring管理mybatis  管理他的配置文件和映射文件-->
    
        <context:property-placeholder location="classpath:db.properties"/>
    
        <!--数据源-->
        <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource">
            <property name="driverClassName" value="${jdbc.driver}"/>
            <property name="url" value="${jdbc.url}"/>
            <property name="username" value="${jdbc.username}"/>
            <property name="password" value="${jdbc.password}"/>
            <property name="maxActive" value="100"/>
            <property name="maxIdle" value="30"/>
        </bean>
    
        <!--整合sqlMapConfig文件，一个sessionFactory对应一个数据源，如果是数据库对应一个数据库-->
        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <!--本地资源配置文件，这就被spring管了-->
            <property name="configLocation" value="classpath:mybatis/sqlMapConfig.xml"/>
    
            <property name="dataSource" ref="dataSource"/>
        </bean>
    
        <!--Spring管理mapper：com.epochong.mapper.*xml-->
        <bean id="mapperScannner" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
            <!--Spring扫描mapper包（*.xml）-->
            <property name="basePackage" value="com.ming.mapper"/>
            <!--[重点]此处不能使用ref,只能用value-->
            <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        </bean>
    </beans>
    ```

#### 测试类

##### MyBatis APIs

- **sqlSessionFactory:**在MyBatis的框架中对应一个数据库

- **sqlSession：**进行增删改查操作,对Connection的轻量级封装，可以进行增删改查

- 增

- 删

- 改

- 查

    - **执行selectOne():**

        ```java
        User user = sqlSession.selectOne("test.queryUserById",1);
        ```

    - **执行selectList:**返回多个对象信息

##### 动态代理 - MapperProxy（约定优于配置）-  持久层（Dao -> Mapper）

- 遵守六个约定（4  +  2）
    1. 一定要设置namespace：（mapper接口全路径）
    2. 包含id属性
    3. 包含parameterTyep属性
    4. 包含resultType属性
    5. `.java`、与之对应的`.xml`位于同一个包
    6. `.java`、与之对应的`.xml`文件命名一致
    
- 配置说明

    - **mpper.xml**

        ```xml
        <mapper namespace="">
            <!--establish some relevant which  between object and table from database-->
            <!--
                parameter explain:
                 id: statementId
                 parameterType: input_param from sql statement
                 resultType:
         			* sql语句查询的返回值是啥就写啥，如：int，String
        			  int result = userMapper.queryUserByCount(userBz);
        			* 如果返回值是多行结果，就写数据库中查询的那个表与程序中对应的实体类的全路径，
        				如：返回值设置为具体类
        				   测试的时候用List<>接收多个结果
        				   List<UserEx>  userExList = userMapper.querUserByZH(userBz);
        				   resultType="com.epochong.po.UserEx"
        			* 如果返回值在实体类中找不到
        				就在扩展类中添加一些属性满足查询要求即可
        			* 增加扩展类的方法过于麻烦
        				使用resultMap方式，既可以实现别名查询，也可以实现多表查询
        
                 #{}: 
            		#{
               	 		填写parameterType指定的参数类中的实际属性,
                 		名字必须相同，
              	 		如果这个属性也是对象，
               	 		在 . 这个对象里面的属性
            	 	}
         
            -->
            <select id="" parameterType="" resultType="">
            </select>
        
        </mapper>
        ```

    - 别名查询

        ```xml
            <resultMap id="myResultMap" type="com.epochong.po.User">
                <id column="id" property="id"/>
                <result column="MyName" property="username"/>
                <result column="MySex" property="sex"/>
                <result column="MyAddress" property="address"/>
            </resultMap>
            <select id="queryUserByAlias2" resultMap="myResultMap">
                select
                    username as MyName,
                    sex MySex,
                    address MyAddress
                from
                    user;
            </select>
            <!--选值包含多个值变量，需要封装List属性
        		List<User> userExList = userMapper.queryUserByAlias2();
        	-->
        
        ```

    - 多表查询

        ```xml
        <!--
            id：resultMap：id标识，与下面引用对应
            type：主表对应的类的全路径
        -->
        <resultMap id="queryOrderToItemsResultMap" type="com.ming.po.Orders">
            <!--orders-->
            <!--user   association-->
            <!--orderdetail   collection-->
            <!--
                id：数据库中主键id，名称需与数据库中的字段相同
                property：映射的主题类中与之对应的id，字段需与属性定义的名称相同
            -->
            <id column="id" property="id"/>
            <!--
                select语句中的其他字段需要全部包含，
                用以下形式与之对应
            -->
            <result column="user_id" property="userid"/>
            <result column="number" property="number"/>
            <result column="createtime" property="createtime"/>
            <result column="note" property="note"/>
            <!--
                association：一对一关系，
                            需在Oders表中添加字段    private User user;     表示一对一关系
                property：数据库中与主表一对一的关系表，参数为主表对应的实体类中的别的表的属性名称
                javaType：与之对应的实体类的全路径
            -->
            <association property="user" javaType="com.ming.po.User">
                <id column="user_id" property="id"/>
                <result column="username" property="username"/>
                <result column="sex" property="sex"/>
                <result column="address" property="address"/>
            </association>
            <!--
                collection：主表与该表为一对多的关系
                            需在Orders表中添加字段   private List<Orderdetail> 			     				 orderdetailList;     表示一对多关系
                property：主表类中别的表的属性名称
                ofType：该表的全路径
            -->
            <collection property="orderdetailList" ofType="com.ming.po.Orderdetail">
                <id column="OrderDetailID" property="id"/>
                <result column="orders_id" property="ordersId"/>
                <result column="items_id" property="itemsId"/>
                <result column="items_num" property="itemsNum"/>
                <!--items  collection-->
                <!--
                    主表与表items没有直接关系，是通过表Orderdetail表的与之有间接关系
                    所以先在Orderdetail表中新增    private Items items;属性表示一对一关系
                    然后应在该标签内部新增association标签与之对应
                -->
                <association property="items" javaType="com.ming.po.Items">
                    <id column="ItemsID" property="id"/>
                    <result column="name" property="name"/>
                    <result column="detail" property="detail"/>
                    <result column="price" property="price"/>
                </association>
            </collection>
        
        
        </resultMap>
        <select id="queryOrderToItems" resultMap="queryOrderToItemsResultMap">
            select
                orders.*,
                user.username,
                user.address,
                user.sex,
                orderdetail.id as OrderDetailID,
                orderdetail.orders_id,
                orderdetail.items_id,
                orderdetail.items_num,
                items.id as ItemsID,
                items.name,
                items.detail,
                items.price
            from
                orders,
                user,
                orderdetail,
                items
            where
                orders.user_id = user.id
                and
                orders.id = orderdetail.orders_id
                and
                orderdetail.items_id = items.id;
        </select>
        ```

        

    
