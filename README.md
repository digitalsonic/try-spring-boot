# Spring Boot演示

## Section 01

演示基本的Spring Boot发布一个REST服务，内容包含：

1. Spring Boot通过注解发布REST服务
2. Spring Data JPA的查询
3. Lombok注解简化POJO书写
4. Spring Boot自动配置H2数据库

    注：original目录是从start.spring.io下载的初始工程

## Section 02

以Section 01为基础，将Tomcat和LogBack替换为Jetty和Log4j2。配置了一些简单的参数，比如random。

自定义了一个HealthIndicator。

## Section 03

以Section 01为基础，自定义了一组starter和autoconfiguration，启动时配置Barista信息。

