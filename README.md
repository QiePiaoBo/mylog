# mylog
## 项目梗概：mylog(springcloud)
## 端口设置
* 网关
  * plt-gateway 8888
* 批处理与格式转化
    * batch 9001
    * format 9002
* 登陆注册中心
    * licence 9003
* 工具验证中心
    * dm-ribbon 8088
    * dm-feign 8087
    
## 项目目的：搭建基于springcloud的微服务平台用于承载各种突发奇想的小项目

## 项目目录简介：
* common    各种功能性微服务
* demos     feign、ribbon等微服务示例
* platform  平台基础微服务

## 运行说明
* ### 运行顺序：eureka - ribbon - gateway
* ### 说明：
    * 由于目前gateway只配了一个路由转发目的服务，即feign，所以只有访问feign上的接口才行
    * 示例：http://localhost:8888/dl/routeAll、http://localhost:8888/dl/routeMe
    * 说明：当前gateway配置下，上述请求会被转发到feign的/routeAll接口上，dl必须加上，不然请求会被拦截掉（全局过滤器TokenFilter）
    * 由于/routeMe设置了sleep(2000)所以会超出服务熔断时间，进而触发熔断条件而执行fallback方法，想屏蔽熔断可以将sleep注掉（熔断器Hystrix）
    * 快速刷新会触发429错误，因为网关限流器设置了一秒内最大请求次数3次（限流器requestRateLimiter）


## gateway
* ### 网关
    * 主要功能:
        * 路由分配
        * 熔断
        * 限流
        * 全局过滤
    
## PS:
* springboot相关的包最好是相近的版本的 不然会出现一些莫名其妙的方法找不到的错误
* 

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