---
title: 使用hexo搭建个人博客
date: 2019-05-11
tags: hexo
toc: true
---
<!-- more -->

##### 准备工作
######  安装hexo
 由于使用国外镜像会很慢

> 可用 get 命令查看 registry

```
npm congfig get registry
```

原版结果为


```
http://registry.npmjs.org
```

用 set 命令换成阿里的镜像就可以了

```
npm install -g cnpm --registry-https://registry.npm.taobao.org
npm config set registry http://registry.npm.taobao.org
```
然后使用(以后的直接将npm换成cnpm下载就会很快)

```
cnpm install -g hexo-cli
```
我的15.29s就安装完成了，如果使用国外镜像，反正我等了很长时间还是没有下载完
###### 当然你想使用国外镜像也行
直接

```
npm install -g hexo-cli
```

##### 执行完成后，输入以下命令，然后通过访问:[http://localhost:4000](http://localhost:4000/)

##### `hexo -v`

查看hexo版本

##### 正式开始

###### 先建一个blog文件夹
所有hexo操作都在这里面进行
###### 然后命令行进入blog文件夹下

###### 初始化

```
hexo init
```
###### 启动命令(预览)

```
hexo s(hexo server)
```
这一步下来就可以看到自己博客地址了，输入到浏览器就可以查看预生成的博客
###### 新建一篇文章

```
hexo n "first bolg"
```
###### 清理

```
hexo clean
```
###### 生成

```
hexo g
g(general)
```
然后新建的博客就被归档

- 然后通过访问:[http://localhost:4000](http://localhost:4000/)，来访问，一个本地博客就可以看到了

##### 将博客部署到远端(GitHub)
###### 打开GitHub新建一个仓库
仓库命名必须是

```
你的github用户名.github.io
```
==如果不是这个命名，在最后执行hexo d将博客部署到github上输入网址时会出现404==

后期直接在浏览器输入地址就可以访问
###### 安装插件

```
cnpm install --save hexo -deployer-git
```
不用理会WARNING
###### 设置_config.yml相关内容
使用windows命令行命令打开该文件

```
start _confing.yml
```
然后在文件末尾添加内容

```
deploy:
  type: git
  repo: https://github.com/Valentineone/epochong.github.io.git
  branch: master
```
###### 部署到仓库

```
hexo d
```
这个时候cmd窗口底部会出现

```
Branch 'master' set up to track remote branch 'master' from 'git@github.com:epochong/epochong.github.io.git'.
```
说明已经部署成功
###### 访问自己的博客
在网站中输入你刚才建的仓库的名称

```
yourusername.github.io
```
即可访问你的博客
==如果此时出现网页访问404问题==
是因为你新建的仓库名不是你的github账号的用户名

##### 解决方法
- 一种是修改github的username名称和你新建的存储库.github前的名称相同
- 一种是重新建立一个 你的github账号username.github.io

两种方法完成后重新执行
```
hexo d
```
刷新网页就能看到你建的博客了

##### 设置喜欢的主题
在下面的网站上clone到本地
```
https://github.com/litten/hexo-theme-yilia
```
###### 命令行clone

```
git clone https://github.com/litten/hexo-theme-yilia.git themes/yilia
```
克隆到本地themes文件夹下
###### 修改_config.yml中theme参数

```
theme: yilia
```

###### 清理生成加打开服务

```
hexo clean
hexo g
hexo s
```
这个时候4000的地址的博客已经换了主题
###### 推到远端

```
hexo d
```

###### 刷新你的博客
主题设置完成