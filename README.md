# Spring MVC + Mybatis + Shiro + logback + Sitemesh3 + bootstrap3 简单框架

+ web.xml文件当中的spring.profiles.default可切换项目启动模式<br>
development:以开发环境启动，采用SQLite数据库，production：以生产环境启动，采用MySQL数据库


#### 系统启动：
+ 先要手动建表，脚本在doc/sql目录下

+ 系统采用Shiro权限框架，库表当中若无数据，则系统只能访问首页，无法进行登录<br/>
执行InitServiceTest.init()方法初始化数据方法即可登录，注意InitServiceTest类中的@ActiveProfiles("")是不是你指的环境


### PS. Mybatis代码生成器
+ mybatis的dao,model,mapper.xml文件可通过代码生成器生成简易代码
+ 可参考[mybatis-generator](http://git.oschina.net/wangxinforme/mybatis-generator "Mybatis代码生成器")


若发现问题或有疑问，欢迎大家[反馈](http://git.oschina.net/wangxinforme/springmvc-mybatis/issues)，相互交流

Vincent [Git@OSC](http://git.oschina.net/wangxinforme "Vincent Git@OSC主页")

