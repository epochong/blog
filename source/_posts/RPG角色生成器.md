---
title: RPG角色生成器
date: 2019-04-30 19:59:00
tags: [java,practice]
---
  **需求**  
 **1.功能描述**  
 几乎所有的RPG游戏（一种源自《龙与地下城》的游戏类型）在进入游戏时都会让用户自己来创建自己喜欢的角色。本次上机要求编写一个简化的创建游戏角色的程序。

<!-- more -->

 **2.游戏角色应有的属性**  
 本题目要求的游戏角色应有以下属性：名字、性别、种族、职业、力量、敏捷、体力、智力、智慧、生命值和魔法值。  
 名字：不超过50个字符。  
 性别：可以选择男性和女性。  
 种族：一共可选五个种族，人类、精灵、兽人、矮人和元素。  
 职业：可选六种职业，狂战士、圣骑士、刺客、猎手、祭司和巫师。  
 其余属性均为整数。  
 本题目要求首先用户输入角色姓名，然后由用户选择角色性别，然后由用户选择种族，然后选择职业，然后自动分配力量、敏捷、体力、智力和智慧属性，并计算生命值和魔法值。  
 生命值=体力*20。  
 魔法值=（智力+智慧）*10。  
 **3.职业限制**  
 很多职业会限制某些种族选择，例如兽人不能就职圣骑士等等，种族和职业的限制表如下：  
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418224506907.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)  
 所以在要求用户选择职业时，输出信息里面只能有用户所选择种族可以就职的职业。  
 **4.初始属性**  
 本题目要求力量、敏捷、体力、智力和智慧要求是随机值（利用随机数函数来取得随机数），但是五项属性的总和应该是100，并且应该和职业相关。例如狂战士的体力和力量就要比较高，而巫师需要较高的智力，而祭司则需要较高的智慧。各职业初始属性的大致比例应遵从下表：  
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418224608779.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)  
 例如，前面示意图中的祭司的初始属性，大致满足该比例，但是应该是随机的。  
 然后利用属性值计算生命值和魔法值。  
 **5.显示信息**  
 最后向用户显示该角色的所有信息，然后询问用户是否满意，如用户不满意则重新创建，若用户满意则程序结束，并将用户创建角色的相关信息写入文件保存。  
 **一、题目分析**  
 用户自己来创建自己喜欢的角色。本次上机要求编写一个简化的创建游戏角色的程序。  
 RPG游戏角色初始化，很容易想到设计一个角色类Character，这个类的属性信息即为角色的的属性，设置每一个属性的getter和setter方法，对属性进行值的设置和获取  
 设置一个主类RPG来对角色属性进行具体的设置，要求符合题目的要求设置满足不同种族之间有职业选择限制，各个职业的属性比例应符合对应角色的真实比例，这些属性随机生成，但大致满足比例，所以只需要在比例附近设置一个范围，生成的随机基本上都满足就比例。但是，在生成属性值的时候，要求总和要保证为100，那么只需要在生成最后一个属性值的时候，用100减去前面生成的值即可，但是，这有不能保证最后一个一定为一个正数，因为前面生成的数都有可能都偏向比比例大的方向生成随机数，所以就需要不断的进行这一步生成随机数的过程，一定会有生成满足最后一个不为负数的时候，这不会等很长时间因为过程完全随机很容易就得到偏向比例下方和偏向上方大致中和。  
 最后向用户显示该角色的所有信息，然后询问用户是否满意，如用户不满意则重新创建，若用户满意则程序结束，并将用户创建角色的相关信息写入文件保存。  
 **二、类图设计**  
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418224758310.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)  
 三、程序实现  
 程序主要实现


```java
/**
 * 对用户的输入做检查,确保只有输入0或1才能进行下一步
 * @return 整型0或1
 */
private static int get01()

/**
 * 对用户的输入做检查,确保只有输入0至2才能进行下一步
 * @return 整型0至2
 */
private static int get02()

/**
 * 设置其他的属性包括力量、敏捷、体力、智力
 * @param op 职业
 * @return 一个含有4个数的数组分别表示力量、敏捷、体力、智力
 */
private static int[] setOther(String op)
/**
 * 设置种族
 * @param select 选择的选项
 * @return 对应的选项的的种族
 */
private static String setRace(int select)
/**
 * 设置职业
 * @param op 种族
 * @return 对应种族的下属的选择的职业
 */
private static String setOccupation(String op)
/**
 * 设置其他的属性包括力量、敏捷、体力、智力
 * @param op 职业
 * @return 一个含有4个数的数组分别表示力量、敏捷、体力、智力
 */
private static int[] setOther(String op)

```
 **完整实现**  
 **Character类**


```java
package www.homework.EmbarkationFour;

public class Character {
    private String name;//名字
    private String sex;//性别
    private String race;//种族
    private String occupation;//职业
    private int power;//力量
    private int agility;//敏捷
    private int physical;//体力
    private int brains;//智力
    private int wit;//智慧
    private int life;//生命值
    private int magic;//魔法值


    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
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

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }


    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getPhysical() {
        return physical;
    }

    public void setPhysical(int physical) {
        this.physical = physical;
    }

    public int getBrains() {
        return brains;
    }

    public void setBrains(int brains) {
        this.brains = brains;
    }

    public int getWit() {
        return wit;
    }

    public void setWit(int wit) {
        this.wit = wit;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        this.magic = magic;
    }
}


```
 **RPG类**


```java
package www.homework.EmbarkationFour;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class RPG {
    /**
     * 对用户的输入做检查,确保只有输入0或1才能进行下一步
     * @return 整型0或1
     */
    private static int get01() {
        Scanner input = new Scanner(System.in);
        while (true) {
            String re = input.nextLine();
            if (re.equals("0") || re.equals("1")) {
                return Integer.valueOf(re);
            } else {
                System.out.println("没有该选项,重新输入吧！");
            }
        }
    }

    /**
     * 对用户的输入做检查,确保只有输入0至2才能进行下一步
     * @return 整型0至2
     */
    private static int get02() {
        Scanner input = new Scanner(System.in);
        while (true) {
            String re = input.nextLine();
            if (re.equals("0") || re.equals("1") || re.equals("2") ) {
                return Integer.valueOf(re);
            } else {
                System.out.println("没有该选项,重新输入吧！");
            }
        }
    }
    private static int get03() {
        Scanner input = new Scanner(System.in);
        while (true) {
            String re = input.nextLine();
            if (re.equals("0") || re.equals("1") || re.equals("2") || re.equals("3") ) {
                return Integer.valueOf(re);
            } else {
                System.out.println("没有该选项,重新输入吧！");
            }
        }
    }
    private static int get04() {
        Scanner input = new Scanner(System.in);
        while (true) {
            String re = input.nextLine();
            if (re.equals("0") || re.equals("1") || re.equals("2") || re.equals("3") || re.equals("4")) {
                return Integer.valueOf(re);
            } else {
                System.out.println("没有该选项,重新输入吧！");
            }
        }
    }
    private static int get05() {
        Scanner input = new Scanner(System.in);
        while (true) {
            String re = input.nextLine();
            if (re.equals("0") || re.equals("1") || re.equals("2") || re.equals("3") || re.equals("4") || re.equals("5")) {
                return Integer.valueOf(re);
            } else {
                System.out.println("没有该选项,重新输入吧！");
            }
        }
    }

    /**
     * 设置种族
     * @param select 选择的选项
     * @return 对应的选项的的种族
     */
    private static String setRace(int select) {
        switch (select) {
            case 0 :
                return "人类";
            case 1:
                return "精灵";
            case 2:
                return "兽人";
            case 3:
                return "矮人";
            case 4:
                return "元素";
            default:
                return null;
        }
    }

    /**
     * 设置职业
     * @param op 种族
     * @return 对应种族的下属的选择的职业
     */
    private static String setOccupation(String op) {
        int select;
        switch (op) {
            case "人类":
                System.out.print("选择您的职业(0狂战士,1圣骑士,2刺客,3猎手,4祭司,5巫师):");
                select = get05();
                if (select == 0) {
                    return "狂战士";
                } else if (select == 1) {
                    return "圣骑士";
                } else if (select == 2) {
                    return "刺客";
                } else if (select == 3) {
                    return "猎手";
                } else if (select == 4) {
                    return "祭司";
                } else if (select == 5) {
                    return "巫师";
                }
                break;
            case "精灵":
                System.out.print("选择您的职业(0刺客,1猎手,2祭司,3巫师):");
                select = get03();
                if (select == 0) {
                    return "刺客";
                } else if (select == 1) {
                    return "猎手";
                } else if (select == 2) {
                    return "祭司";
                } else if (select == 3) {
                    return "巫师";
                }
                break;
            case "兽人":
                System.out.print("选择您的职业(0狂战士,1猎手,2祭司):");
                select = get02();
                if (select == 0) {
                    return "狂战士";
                } else if (select == 1) {
                    return "猎手";
                } else if (select == 2) {
                    return "祭司";
                }
                break;
            case "矮人":
                System.out.print("选择您的职业(0狂战士,1圣骑士,2祭司):");
                select = get02();
                if (select == 0) {
                    return "狂战士";
                } else if (select == 1) {
                    return "圣骑士";
                } else if (select == 2) {
                    return "祭司";
                }
                break;
            case "元素":
                System.out.print("选择您的职业(0祭司,1巫师):");
                select = get01();
                if (select == 0) {
                    return "祭司";
                } else if (select == 1) {
                    return "巫师";
                }
                break;
            default:
                return null;
        }
        return null;
    }

    /**
     * 设置其他的属性包括力量、敏捷、体力、智力
     * @param op 职业
     * @return 一个含有4个数的数组分别表示力量、敏捷、体力、智力
     */
    private static int[] setOther(String op) {
        int[] arr = new int[5];
        while (true) {
            switch (op) {
                case "狂战士":
                    arr[0] = (int) (Math.random() * 11) + 35;
                    arr[1] = (int) (Math.random() * 11) + 15;
                    arr[2] = (int) (Math.random() * 11) + 25;
                    arr[3] = (int) (Math.random() * 11);
                    arr[4] = 100 - arr[0] - arr[1] - arr[2] - arr[3];
                    break;
                case "圣骑士":
                    arr[0] = (int) (Math.random() * 11) + 20;
                    arr[1] = (int) (Math.random() * 11) + 10;
                    arr[2] = (int) (Math.random() * 11) + 25;
                    arr[3] = (int) (Math.random() * 11) + 15;
                    arr[4] = 100 - arr[0] - arr[1] - arr[2] - arr[3];
                    break;
                case "刺客":
                    arr[0] = (int) (Math.random() * 11) + 15;
                    arr[1] = (int) (Math.random() * 11) + 30;
                    arr[2] = (int) (Math.random() * 11) + 15;
                    arr[3] = (int) (Math.random() * 11) + 10;
                    arr[4] = 100 - arr[0] - arr[1] - arr[2] - arr[3];

                    break;
                case "猎手":
                    arr[0] = (int) (Math.random() * 11) + 10;
                    arr[1] = (int) (Math.random() * 11) + 35;
                    arr[2] = (int) (Math.random() * 11) + 5;
                    arr[3] = (int) (Math.random() * 11) + 30;
                    arr[4] = 100 - arr[0] - arr[1] - arr[2] - arr[3];
                    break;
                case "祭司":
                    arr[0] = (int) (Math.random() * 11) + 10;
                    arr[1] = (int) (Math.random() * 11) + 15;
                    arr[2] = (int) (Math.random() * 11) + 10;
                    arr[3] = (int) (Math.random() * 11) + 30;
                    arr[4] = 100 - arr[0] - arr[1] - arr[2] - arr[3];
                    break;
                case "巫师":
                    arr[0] = (int) (Math.random() * 11) + 5;
                    arr[1] = (int) (Math.random() * 11) + 15;
                    arr[2] = (int) (Math.random() * 11) + 5;
                    arr[3] = (int) (Math.random() * 11) + 15;
                    arr[4] = 100 - arr[0] - arr[1] - arr[2] - arr[3];
                    break;
                }
                if (arr[4] > 0) {
                     break;
                }
             }
                return arr;
    }

    /**
     * 显示 character各个属性信息
     * @param character character对象(RPG角色)
     */
    private static void show(Character character) {
        System.out.println("=========================");
        System.out.println("姓名" + "         " + character.getName());
        System.out.println("=========================");
        System.out.println("性别" + "         " + character.getSex());
        System.out.println("=========================");
        System.out.println("种族" + "         " + character.getRace());
        System.out.println("=========================");
        System.out.println("职业" + "         " + character.getOccupation());
        System.out.println("=========================");
        System.out.println("力量" + "         " + character.getPower());
        System.out.println("=========================");
        System.out.println("敏捷" + "         " + character.getAgility());
        System.out.println("=========================");
        System.out.println("体力" + "         " + character.getPhysical());
        System.out.println("=========================");
        System.out.println("智力" + "         " + character.getBrains());
        System.out.println("=========================");
        System.out.println("智慧" + "         " + character.getWit());
        System.out.println("=========================");
        System.out.println("生命值" + "       " + character.getLife());
        System.out.println("=========================");
        System.out.println("魔法值" + "       " + character.getMagic());
    }
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        while (true) {
            Character character = new Character();
            while (true) {
                System.out.print("输入您游戏角色的姓名:");
                String name = input.nextLine();
                character.setName(name);
                if (name.length() >= 50) {
                    System.out.println("名字不能超过50个字符！重新输入吧！");
                } else {
                    break;
                }
            }
            System.out.print("选择您游戏角色的姓别(0男性,1女性):");
            int select = get01();
            if (select == 0) {
                character.setSex("男");
            } else {
                character.setSex("女");
            }
            System.out.print("选择您游戏角色的种族(0人类,1精灵,2兽人,3矮人,4元素):");
            select = get04();
            character.setRace(setRace(select));
            character.setOccupation(setOccupation(character.getRace()));
            int[] arr = setOther(character.getOccupation());
            character.setPower(arr[0]);
            character.setAgility(arr[1]);
            character.setPhysical(arr[2]);
            character.setBrains(arr[3]);
            character.setWit(arr[4]);
            character.setLife(arr[2] * 20);
            character.setMagic((arr[3] + arr[4]) * 10);
            show(character);
            System.out.println("你是否满意这个角色呢？你要满意我就存文件了,你要是不满意呢,那就重新创建了。");
            System.out.println("0.满意   1.也不行呀");
            select = get01();
            if (select == 0) {
                File file = new File("F:\\rpg.txt");
                Writer writer = new FileWriter(file);
                writer.write("姓名:" + character.getName() + "\r\n");
                writer.write("性别:" + character.getSex() + "\r\n");
                writer.write("种族:" + character.getRace() + "\r\n");
                writer.write("职业:" + character.getOccupation() + "\r\n");
                writer.write("力量:" + character.getPower() + "\r\n");
                writer.write("敏捷:" + character.getAgility() + "\r\n");
                writer.write("体力:" + character.getPhysical() + "\r\n");
                writer.write("智力:" + character.getBrains() + "\r\n");
                writer.write("智慧:" + character.getWit() + "\r\n");
                writer.write("生命值:" + character.getLife() + "\r\n");
                writer.write("生命值:" + character.getMagic() + "\r\n");
                writer.close();
                System.out.println("既然都满意了,那就祝您生活愉快吧!");
                System.out.println("bay:)");
                break;
            }
        }
    }
}


```
 四、调试、测试及运行结果  
 4.1调试截图


```java
Character类下的各个属性对应的部分
private String name;//名字
private String sex;//性别
private String race;//种族
private String occupation;//职业
private int power;//力量
private int agility;//敏捷
private int physical;//体力
private int brains;//智力
private int wit;//智慧
private int life;//生命值
private int magic;//魔法值

```
 当输入角色名称和角色性别时  
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225126200.png)  
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225130764.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)  
 当选择完种族和职业的时候后面就会随机生成其他属性的值

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225137318.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)

 4.2测试截图  
 输入名字超过50个字符，报错，重新输入  
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225235881.png)

 选择性别时，输入不正确的选项，报错，直到输入正确进行下一步  
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225240320.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)

 选择角色时，输入不正确的选项，报错，直到输入正确进行下一步

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225245437.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)

 选择职业时，输入不正确的选项，报错，直到输入正确进行下一步

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225412739.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)

 选择是人类时，只有以下5个选项，并且生成符合比例的属性值

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225418619.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)

 选择是精灵时，只有以下4个选项，并且生成符合比例的属性值

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225425577.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)

 选择是兽人时，只有3个职业选择，并且生成符合比例的属性值

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225433644.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)

 选择是矮人时，只有3个职业选择，并且生成符合比例的属性值  
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225439473.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)

 选择是元素时，只有2个职业选择，并且生成符合比例的属性值

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225445911.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)

 最终询问是否满意角色的生成，进行错误输入检查

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225459574.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225504246.png)

 ![在这里插入图片描述](https://img-blog.csdnimg.cn/2019041822550840.png)

 满意了就将信息存入rpg.txt文件下  
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225513280.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)  
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190418225522352.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dmY25fenlx,size_16,color_FFFFFF,t_70)  
 五、经验归纳  
 本次作业加强了我们对类概念的掌握，使编写程序更加面向对象化，实现Character类单独实现RPG角色属性，然后进行正常的输入输出检查，get01()，get02()，get03()，get04()，get05()，专门获得满足需求的值，对函数的设计也变的更加模块化。 再次练习了对文件类的操作。  
 总体来说这次练习相对简单。

   
