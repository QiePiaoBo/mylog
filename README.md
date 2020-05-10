# mylog
## 项目梗概：mylog(springcloud)
## 端口设置
    * eureka 8089
    * ribbon 8088
    * feign 8087
    * config 8086
    * utils 8085
## 项目目的：搭建基于springcloud的微服务平台用于承载各种突发奇想的小项目

## 项目目录简介：
* common    各种功能性微服务
* demos     eureka、feign、ribbon等基础微服务
* platform  平台基础微服务

## 运行说明
* ### 运行顺序：eureka - ribbon - gateway
* ### 说明：
    * 由于目前gateway只配了一个路由转发目的服务，即feign，所以只有访问feign上的接口才行
    * 示例：http://localhost:8888/dl/routeAll、http://localhost:8888/dl/routeMe
    * 说明：当前gateway配置下，上述请求会被转发到feign的/routeAll接口上，dl必须加上，不然请求会被拦截掉（全局过滤器TokenFilter）
    * 由于/routeMe设置了sleep(2000)所以会超出服务熔断时间，进而触发熔断条件而执行fallback方法，想屏蔽熔断可以将sleep注掉（熔断器Hystrix）
    * 快速刷新会触发429错误，因为网关限流器设置了一秒内最大请求次数3次（限流器requestRateLimiter）

## eureka
* ### 服务注册与发现中心
    * 主要用于各个微服务的注册与发现

## gateway
* ### 网关
    * 主要功能:
        * 路由分配
        * 熔断
        * 限流
        * 全局过滤
    
