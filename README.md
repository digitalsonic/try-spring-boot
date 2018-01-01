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

## Section 04

以Section 01为基础，引入spring-data-redis，在Redis里存放价目表，使用了两种方式：

1. 直接通过Hash存放在Redis里
2. 通过Repository将Redis当做数据库使用

需要现在127.0.0.1上启动一个Redis。

## Section 05

与Section 04相同，但不直接使用spring-data-redis，通过spring-boot-starter-data-redis简化了配置。


