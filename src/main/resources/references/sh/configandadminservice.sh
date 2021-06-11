#!/bin/sh
# 一套Apollo环境中的ConfigService和AdminService
url="localhost:3306"
username="root"
password="root"
# Linux中：
# java -Xms256m -Xmx256m -Dapollo_profile=github -Dspring.datasource.url=jdbc:mysql://${url}/ApolloConfigDB?characterEncoding=utf8 -Dspring.datasource.username=${username} -Dspring.datasource.password=${password} -Dlogging.file=./logs/apollo-configservice.log -Dserver.port=8080 -jar apollo-configservice-1.3.0.jar &
# java -Xms256m -Xmx256m -Dapollo_profile=github -Dspring.datasource.url=jdbc:mysql://${url}/ApolloConfigDB?characterEncoding=utf8 -Dspring.datasource.username=${username} -Dspring.datasource.password=${password} -Dlogging.file=./logs/apollo-adminservice.log -Dserver.port=8090 -jar apollo-adminservice-1.3.0.jar & 
# java -Xms256m -Xmx256m -Dapollo_profile=github,auth -Ddev_meta=http://localhost:8080/ -Dserver.port=8070 -Dspring.datasource.url=jdbc:mysql://%url%/ApolloPortalDB?characterEncoding=utf8 -Dspring.datasource.username=%username% -Dspring.datasource.password=%password% -jar apollo-portal-1.3.0.jar &
# java -Xms256m -Xmx256m -Dapollo_profile=github,auth -Ddev_meta=http://localhost:8080/ -Dserver.port=8070 -Dspring.datasource.url=jdbc:mysql://localhost:3306/ApolloPortalDB?characterEncoding=utf8 -Dspring.datasource.username=root -Dspring.datasource.password=itcast0430 -jar apollo-portal-1.3.0.jar
# Windows中：
# 问题一： . &等特殊符号 一定要用双引号引起来 "." "&"
# 问题二： 访问文件的路径中 斜杠要使用 "/" 而不是 "\"
# 问题三： 命令 最后 有一个 "&" 号，不要忘记
# 问题四： 注意要指定日志文件的路径，方便查阅
java -Xms256m -Xmx256m -Dapollo_profile=github -DSpring"."datasource"."url=jdbc:mysql://${url}/ApolloConfigDB?characterEncoding=utf8"&"serverTimezone=UTC -DSpring"."datasource"."username=${username} -Dspring"."datasource"."password=${password} -Dlogging"."file"."name=./logs/apollo-service.log -jar ./configservice/apollo-configservice-1.8.2.jar &
echo "============================================================================"
java -Xms256m -Xmx256m -Dapollo_profile=github -Dspring"."datasource"."url=jdbc:mysql://${url}/ApolloConfigDB?characterEncoding=utf8"&"serverTimezone=UTC -Dspring"."datasource"."username=${username} -Dspring"."datasource"."password=${password} -Dlogging"."file"."name=./logs/apollo-adminservice.log -Dserver.port=8090 -jar ./adminservice/apollo-adminservice-1.8.2.jar & 
sleep 100000