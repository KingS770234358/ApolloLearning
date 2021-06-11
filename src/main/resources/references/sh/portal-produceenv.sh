#!/bin/sh

url="localhost:3306"
username="root"
password="root"

# java -Xms256m -Xmx256m -Dapollo_profile=github,auth -Ddev_meta=http://localhost:8080/ -Dpro_meta=http://localhost:8081/ -Dserver.port=8070 -Dspring.datasource.url=jdbc:mysql://${url}/ApolloPortalDB?characterEncoding=utf8 -Dspring.datasource.username=${username} -Dspring.datasource.password=${password} -jar apollo-portal-1.3.0.jar
# portal的运行情况可以通过浏览器是否可以登录管理系统直接观察，不需要在命令后加"&"设置为后台命令。
                                                                                      # 这里应该是8081 不是8080！！！
java -Xms256m -Xmx256m -Dapollo_profile=github,auth -Ddev_meta=http://localhost:8080/ -Dpro_meta=http://localhost:8081/ -Dserver"."port=8070 -DSpring"."datasource"."url=jdbc:mysql://${url}/ApolloPortalDB?characterEncoding=utf8"&"serverTimezone=UTC -DSpring"."datasource"."username=${username} -Dspring"."datasource"."password=${password} -Dlogging"."file"."name=./logs/portal.log -jar ./portal/apollo-portal-1.8.2.jar