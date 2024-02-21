# 基础镜像
FROM openjdk:17-jdk-alpine
# author
MAINTAINER xusage
# 挂载目录
VOLUME /home/xusage
# 创建目录
RUN mkdir -p /home/xusage
# 指定路径
WORKDIR /home/xusage
# 复制jar文件到路径
COPY ./target/springboot-registration-0.0.1-SNAPSHOT.jar /home/xusage/springboot-registration.jar
# 启动系统服务
ENTRYPOINT java $JAVA_OPTS -jar springboot-registration.jar
