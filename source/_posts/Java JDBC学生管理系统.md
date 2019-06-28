---
title: 实现学生管理系统
date: 2019-04-23 16:35:20
tags: [java,JDBC]
---
#### 构建数据库信息

先创建一个数据库,然后在数据库中构建表格

##### 创建学生表

```sql
create table Students (
 id int primary key auto_increment,
 number varchar(5),
 name varchar(20),
 sex varchar(2),
 age TINYINT(150)
)
```
###### 在学生信息表中插入信息
```sql
insert into students values (null,'1001','王崇','男',18),
	(null,'1002','高洋洋','男',23),(null,'1003','焦宇珠','男',20),(null,'1004','女同学','女',18);
```
##### 创建课程表
```sql
create table C (
 id int primary key auto_increment,
 Cno TINYINT(20),
 Cname varchar(10)
);
```
###### 向课程表中插入信息
```sql
insert into C values (null,1,'数据库'),
	(null,2,'Java'),(null,3,'程序设计方法');
```
##### 创建成绩表
```sql
create table SC (
 id int primary key auto_increment,
 SCno TINYINT(20),
 Snumber varchar(5),
 Cno TINYINT(10),
 Score TINYINT(100)
);
```
###### 向成绩表中插入信息
```sql
insert into SC values (null,1,'1001',3,100),
	(null,2,'1002',2,90),(null,1,'1003',1,95),(null,1,'1004',2,100);
```

#### 下面建立Java(JDBC)与数据库的连接

##### 准备工作

1. 新建一个java项目
2. 在项目文件夹下新建一个libs文件用于放JDBC驱动mysql的jar包,并设置编译时加载jar包
3. 写java代码,使用JDBC连接mysql

###### 实现

###### 书写工具类

方便连接数据库

```java
package cn.epochong;

import java.sql.*;

/**
 * @author wangchong
 * @date 2019/5/23 7:54
 * @email 876459397@qq.com
 * @CSDN https://blog.csdn.net/wfcn_zyq
 * @describe
 */
public class JdbcUtils {
    //可以把几个字符串定义成常量：用户名，密码，URL，驱动类
    private static final String USER = "wangchong";
    private static final String PWD = "wangchong";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc ?serverTimezone=UTC";
    private static final String DRIVER= "com.mysql.jdbc.Driver";
    /**
     * 注册驱动
     */
	/*    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/
    /**
     * 得到数据库的连接
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PWD);
    }
    /**
     * 关闭所有打开的资源
     */
    public static void close(Connection conn, Statement stmt) {
        if (stmt!=null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn!=null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 关闭所有打开的资源
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(conn, stmt);
    }
}

```

###### 用户注册类

```java
package cn.epochong;
import java.sql.*;
import java.util.Scanner;

/**
 * @author wangchong
 * @date 2019/5/23 7:57
 * @email 876459397@qq.com
 * @CSDN https://blog.csdn.net/wfcn_zyq
 * @describe
 */
public class Login {
    //从控制台上输入的用户名和密码
    public static void main(String[] args) {

    }
    /**
     * 登录的方法
     */
    public static boolean login() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("登录界面");
        System.out.println("请输入用户名：");
        String name = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        Connection connection = JdbcUtils.getConnection();
        //写成登录 SQL 语句，没有单引号
        String sql = "select * from user where name=? and password=?";
        //得到语句对象
        PreparedStatement ps = connection.prepareStatement(sql);
        //设置参数
        ps.setString(1, name);
        ps.setString(2,password);
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            System.out.println("用户:"+ name + " 登录成功!" );
            //释放资源,子接口直接给父接口
            JdbcUtils.close(connection,ps,resultSet);
            return true;
        }
        else {
            System.out.println("登录失败");
            JdbcUtils.close(connection,ps,resultSet);
            return false;
        }

    }
}
```

###### 建立学生信息类

用于保存学生信息

```java
package cn.epochong;

/**
 * @author wangchong
 * @date 2019/5/26 5:48
 * @email 876459397@qq.com
 * @CSDN https://blog.csdn.net/wfcn_zyq
 * @describe
 */
public class Student {
    String number;
    String name;
    String sex;
    int age;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

##### 新建一个管理类StudentManage.java

###### 主函数

```java
    public static void main(String[] args) throws Exception{
        while (true) {
            if (Login.login()) {
              break;
            } else {
                System.out.println("用户名和密码不匹配,请重新输入!");
            }
        }
        while (true) {
            int operateSelect = operationMenu();

            switch(operateSelect){
                case 1:
                    studentManage();
                    break;
                case 2:
                    chooseManage();
                    break;
                case 3:
                    //classManage();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        }

    }
```

###### 用户登录后开始对数据库中的表操作

```java
    public static int operationMenu() {
        int n = 0;
        System.out.println("***************");
        System.out.println("*  0.退出管理  *");
        System.out.println("*  1.学生管理  *");
        System.out.println("*  2.选课管理  *");
        //System.out.println("*  3.课程管理  *");
        System.out.println("***************");
        System.out.println("请选择:>");
        n = get03();
        return n;
    }
```

###### 选择1实现学生管理

```java
public static void studentManage () throws Exception{
        //showStudents();
        while (true) {
            int select = studentManageMenu();
            switch(select){
                case 1:
                    //添加信息
                    insert();
                    //showStudents();
                    break;
                case 2:
                    //删除信息
                    delete();
                    //showStudents();
                    break;
                case 3:
                    //修改信息
                    update();
                    //showStudents();
                    break;
                case 0:
                    //修改信息
                    break;
                default:
                    break;
            }
            if (select == 0) {
                break;
            }
        }
    }
```

###### 1.学生信息管理菜单

```java
 public static int studentManageMenu() {
        int n = 0;
        System.out.println("***************");
        System.out.println("*  0.结束管理  *");
        System.out.println("*  1.添加学生  *");
        System.out.println("*  2.删除学生  *");
        System.out.println("*  3.修改学生  *");
        System.out.println("***************");
        System.out.println("请选择:>");
        n = get03();
        return n;
    }
```

###### 2.插入信息

```java
private static void insert() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into students values(null,?,?,?,?)");
        Scanner input = new Scanner(System.in);
        System.out.print("输入学生学号:>");
        String studentNumber = input.nextLine();
        ps.setString(1,studentNumber);
        System.out.print("输入学生姓名:>");
        String studentName = input.nextLine();
        ps.setString(2, studentName);
        System.out.print("输入学生性别:>");
        String studentSex = input.nextLine();
        ps.setString(3,studentSex);
        System.out.print("输入学生年龄:>");
        int age = input.nextInt();
        ps.setInt(4,age);
        int row = ps.executeUpdate();
        System.out.println("插入了" + row + "条记录");
        JdbcUtils.close(connection,ps);
    }
```

###### 3.删除信息

```java
private static void delete() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("delete from students where id=?");
        System.out.println("请输入你想删除的ID:");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        ps.setInt(1,id);
        int row = ps.executeUpdate();
        System.out.println("删除了" + row + "条记录");
        JdbcUtils.close(connection,ps);
    }
```

###### 4.更新信息

```java
public static void update() throws Exception {
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement("update students set number =?, name =? , sex = ?, age  = ? where number =?");
        Scanner input = new Scanner(System.in);
        System.out.println("请输入你要修改学生的学号:>");
        String studentNubmer = input.nextLine();
        Student student = getStudentMessageByStudentNumber(studentNubmer);
        ps.setString(5,studentNubmer);
        System.out.println("请输入你想修改那一列:>");
        while (true) {
            int select = updateMenu();
            if (select == 0) {
                break;
            }
            if (select == 1) {
                System.out.print("输入学号:>");
                String number = input.nextLine();
                ps.setString(1,number);
                ps.setString(2,student.getName());
                ps.setString(3,student.getSex());
                ps.setInt(4,student.getAge());

            }
            if (select == 2) {
                System.out.print("输入姓名:>");
                String name = input.nextLine();
                ps.setString(1,student.getNumber());
                ps.setString(2,name);
                ps.setString(3,student.getSex());
                ps.setInt(4,student.getAge());
            }
            if (select == 3) {
                System.out.println("输入性别:>");
                String sex = input.nextLine();
                ps.setString(1,student.getNumber());
                ps.setString(2,student.getName());
                ps.setString(3,sex);
                ps.setInt(4,student.getAge());
            }
            if (select == 4) {
                System.out.print("输入年龄:>");
                int age = input.nextInt();
                ps.setString(1, student.getNumber());
                ps.setString(2,student.getName());
                ps.setString(3,student.getSex());
                ps.setInt(4,age);
            }
            int row = ps.executeUpdate();
            System.out.println("更新" + row + "条记录");
        }

        JdbcUtils.close(connection,ps);
    }

```

###### 选择2进行成绩管理

```java
    public static void chooseManage() throws Exception {
        while (true) {
            int select = chooseMenu();
            switch(select){
                case 1:
                    //添加信息
                    selectScoreInSCByNumber(selectByName());
                    break;
                case 2:
                    selectScoreInSCByClassNumber(selectByClassName());
                    break;
                case 0:
                    //修改信息
                    break;
                default:
                    break;
            }
            if (select == 0) {
                break;
            }
        }
    }
```

**用到的函数**

###### 1.成绩查询菜单

```java
    public static int chooseMenu() {
        System.out.println("********************");
        System.out.println("*    成绩查询       *");
        System.out.println("********************");
        System.out.println("*  0.结束查询       *");
        System.out.println("*  1.按学生姓名查询  *");
        System.out.println("*  2.按课程名称查询  *");
        System.out.println("*********************");
        System.out.println("请选择:>");
        int n = get03();
        return n;
    }
```

###### 2.按学生姓名查询

```java
public static String selectByName() throws Exception{
    //1) 得到连接对象
    Connection connection = JdbcUtils.getConnection();
    //2) 得到语句对象
    PreparedStatement studentsPS = connection.prepareStatement("select * from Students where name = ?");
    String studentNumber = null;
    ResultSet rs = null;
    //循环遍历取出每一条记录
    while (true) {
        System.out.println("输入学生姓名:");
        Scanner input = new Scanner(System.in);
        String studentName = input.nextLine();
        studentsPS.setString(1,studentName);
        rs = studentsPS.executeQuery();
        while(rs.next()) {
            //根据姓名在Students表中查到学号
            studentNumber = rs.getString("number");

        }
        if (!studentNumber.equals(null)) {
            break;
        } else {
            System.out.println("没有该学生的记录,重新输入吧!");
        }
    }
```

###### 3.在成绩表中通过学号查询信息

```java
public static void selectScoreInSCByNumber(String studentNumber) throws Exception{
    //1) 得到连接对象
    Connection connection = JdbcUtils.getConnection();
    //根据查到的学号在成绩表中查中所有的成绩,只显示姓名和成绩
    PreparedStatement scPrepareStatement = connection.prepareStatement("select * from SC where Snumber = ?");
    scPrepareStatement.setString(1,studentNumber);
    ResultSet resultSet = scPrepareStatement.executeQuery();
    while(resultSet.next()) {
        int score = resultSet.getInt("Score");
        String className = getClassNameByClassNumber(resultSet.getString("Cno"));
        //5) 输出的控制台上
        System.out.println("课程名:" + className + "  成绩:" + score);
    }
}
```

###### 4.在课程表中通过课程编号查询课程名称

```java
public static String getClassNameByClassNumber(String cno) throws Exception{
    //1) 得到连接对象
    Connection connection = JdbcUtils.getConnection();
    //2) 得到语句对象
    PreparedStatement ps = connection.prepareStatement("select * from C where Cno = ?");
    ps.setString(1,cno);
    ResultSet rs = ps.executeQuery();
    String className = null;
    while(rs.next()) {
        className = rs.getString("Cname");
    }
    rs.close();
    ps.close();
    connection.close();
    return className;
}
```
