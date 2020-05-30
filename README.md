# mylog
## 项目梗概：mylog(springcloud)
## 端口设置
* 网关
  * plt-gateway 8888
* 登陆注册中心
    * licence 9003
* 工具集成中心
    * lic-engine 无
* 批处理与格式转化
    * batch 9001
    * format 9002
* 功能测试项目
    * dm-ribbon 8088
    * dm-feign 8087
    
## 项目目的：搭建基于springcloud的微服务平台

### 项目目录简介：
* common    各种功能性微服务
* demos     feign、ribbon等微服务示例
* ds        业务中心
* platform  平台基础微服务
* tools     新抽出公用jar包

### 项目路由（持续更新 注：路由报文仅限本人电脑，如欲实验需执行下方sql以在对应库中建表插数据）
* feign:
    * localhost:8888/dl/ + feign中的接口
    * localhost:8888/qy/ + feign中的接口
* licence
    * localhost:8888/jx/ + licence中的接口

## plt-gateway
### gateway说明：
* 由于目前gateway只配了一个路由转发目的服务，即feign，所以只有访问feign上的接口才行
* 示例：
    * http://localhost:8888/dl/routeAll
    * http://localhost:8888/dl/routeMe
* 说明：当前gateway配置下，上述请求会被转发到feign的/routeAll接口上，dl必须加上，不然请求会被拦截掉（全局过滤器TokenFilter）
* 由于/routeMe设置了sleep(2000)所以会超出服务熔断时间，进而触发熔断条件而执行fallback方法，想屏蔽熔断可以将sleep注掉（熔断器Hystrix）
* 快速刷新会触发429错误，因为网关限流器设置了一秒内最大请求次数3次（限流器requestRateLimiter）
### 平台网关
* 主要功能:
    * 路由分配
    * 熔断
    * 限流
    * 全局过滤

## lic-engine
### 工具中心
* 主要功能:
    * 工具jar包
    * 为其他服务所调用
    * 目前提供以下功能
        * 提供用户session的增删改查方法
        * 待添加······
## 特别注意:
* jar包引入相关的坑
    * springboot相关的包最好是相近的版本的 不然会出现一些莫名其妙的方法找不到的错误
* 工具中心相关的坑
    * 切记切记，在添加功能时一定要尽可能减少所添加的功能与其他jar包的交互，不然会很麻烦
    * 现在提供的session的增删改查所使用到的用户实体类就是经过继承才解决问题的
* 其他坑
    * 还没想起来，待添加
    
~~~mysql
-- 路由表（网关数据库即 plt数据库）
CREATE TABLE `route` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '路由表主键',
  `route_id` varchar(128) COLLATE utf8mb4_croatian_ci DEFAULT NULL COMMENT '路由id',
  `uri` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL COMMENT '路由转发uri',
  `order` int(11) DEFAULT NULL COMMENT '路由优先级',
  `predicate_json` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL COMMENT '路由断言json串',
  `filter_json` varchar(255) COLLATE utf8mb4_croatian_ci DEFAULT NULL COMMENT '路由过滤器集合配置json串',
  `status` int(11) DEFAULT NULL COMMENT '状态, 0 不可用， 1 可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci;
INSERT INTO `route` VALUES (1, 'feign', 'lb://my-feign', 0, '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/qy/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"_genkey_0\":\"1\"}}]', 1);
INSERT INTO `route` VALUES (2, 'licence', 'lb://licence', 1, '[{\"args\":{\"pattern\":\"/jx/**\"},\"name\":\"Path\"}]', '[{\"args\":{\"_genkey_0\":\"1\"},\"name\":\"StripPrefix\"}]', 1);

-- 用户表（应用数据库即 ds数据库）
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `usergroup` varchar(32) DEFAULT NULL COMMENT '用户权限组',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `phone` varchar(64) DEFAULT NULL COMMENT '用户手机',
  `mail` varchar(128) DEFAULT NULL COMMENT '用户邮箱',
  `gender` varchar(8) DEFAULT NULL COMMENT '用户性别',
  `realname` varchar(32) DEFAULT NULL COMMENT '用户真实姓名',
  `cn_id` varchar(255) DEFAULT NULL COMMENT '用户身份证号',
  `description` varchar(255) DEFAULT NULL COMMENT '备用字段1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
INSERT INTO `user` VALUES (1, '0', 'Dylan', 'CBACCCEDFC9DD12051CFAC29A06015EF', '15966245906', '15966245906@163.com', 'man', '段其伦', '371522199704136514', '超级管理员');
INSERT INTO `user` VALUES (2, '1', 'Lucifer', '123456', '15966245907', NULL, NULL, '孙梓翰', '371522', NULL);
INSERT INTO `user` VALUES (4, '2', 'Duke', 'E10ADC3949BA59ABBE56E057F20F883E', '15966245908', NULL, NULL, '刘长昊', '371522', NULL);

CREATE TABLE `permission` (
  `id` int(11) NOT NULL COMMENT '权限id',
  `name` varchar(64) DEFAULT NULL COMMENT '权限名',
  `description` varchar(255) DEFAULT NULL COMMENT '权限说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `group_name` varchar(128) DEFAULT NULL COMMENT '权限组名',
  `group_description` varchar(255) DEFAULT NULL COMMENT '权限组说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
~~~
    
    
## 批处理结果
### 137907条数据
* 第一次记录：
    * 分区批处理：2229ms
    * 单线程批处理：3855ms
* 第二次记录：
    * 分区批处理执行时间：2525ms
    * 普通批处理执行时间：3510ms
* 第三次记录：
    * 分区批处理执行时间：3041ms    
    * 普通批处理执行时间：4260ms
### 274739条数据
* 第一次记录：
    * 分区批处理执行时间：5170ms
    * 普通批处理执行时间：7885ms