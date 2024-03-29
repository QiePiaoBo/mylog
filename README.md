# mylog
## 项目梗概：mylog(springcloud)
## 端口设置
* 批处理
    * batch 9001
* 登陆注册中心
    * licence 9003
* 网关
    * plt-gateway 8888
* 工具集成中心 tools
    * model-server 无
    * sdk-server 无
    * utils-server 无
* 应用中心 ds
    * blog 9011
    
## 项目目的：搭建基于springcloud的微服务平台

### 项目目录简介：
* common    各种功能性微服务
* ds        业务中心
* platform  平台基础微服务
* tools     新抽出公用jar包

### 项目路由（持续更新 注：路由报文仅限本人电脑，如欲实验需执行下方sql以在对应库中建表插数据）
* licence
    * localhost:8888/jx/ + licence中的接口

## plt-gateway
### gateway说明：
* 说明：dl必须加上，不然请求会被拦截掉（全局过滤器TokenFilter）
* 熔断器Hystrix
* 限流器requestRateLimiter
### 平台网关
* 主要功能:
    * 路由分配
    * 熔断
    * 限流
    * 全局过滤
## tools
### 工具中心
* 主要功能:
    * 工具jar包
    * 为其他服务所调用
    * 目前提供以下功能
        * model-server 公共实体类
        * sdk-server sdk引入并提供公共方法供使用
        * utils-server 提供常用工具
## 特别注意:
* jar包引入相关的坑
    * springboot版本按照官网来
* 工具中心相关的坑
    * 在添加功能时一定要尽可能减少所添加的功能与其他jar包的交互，不然会很麻烦 现在提供的session的增删改查所使用到的用户实体类就是经过继承才解决问题的
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

-- 用户表（应用数据库即 ds数据库） 已废弃
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
INSERT INTO `user` VALUES (1, '0', 'Dylan', 'CBACCCEDFC9DD12051CFAC29A06015EF', '159****5906', '15966245906@163.com', 'man', '段', '371522', '超级管理员');
INSERT INTO `user` VALUES (2, '1', 'Lucifer', '123456', '15966245907', NULL, NULL, 'SunZihan', '371522', '管理员');
INSERT INTO `user` VALUES (4, '2', 'Duke', 'E10ADC3949BA59ABBE56E057F20F883E', '15966245908', NULL, NULL, 'LiuChanghao', '371522', '平民');
-- 权限表 已废弃
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
