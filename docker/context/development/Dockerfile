#FROM findepi/graalvm:java17-native
#FROM bellsoft/liberica-openjdk-debian:17
FROM bellsoft/liberica-openjdk-alpine:17
#FROM openjdk:17-jdk

# 作者
MAINTAINER chanbeiyu

ARG VERSION
ARG SERVICE_NAME
ARG SERVICE_PORT
ENV VERSION=$VERSION
ENV SERVICE_NAME=$SERVICE_NAME
ENV SERVICE_PORT=$SERVICE_PORT

# 运行参数
ARG JAVA_OPTS
ARG SPRING_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ENV SPRING_OPTS=$SPRING_OPTS

# 系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8

# 声明一个挂载点，容器内此路径会对应宿主机的某个文件夹
VOLUME /tmp

# 应用构建成功后的jar文件被复制到镜像内，名字也改成了app.jar
ADD ./build/${SERVICE_NAME}-${VERSION}.jar /home/app/app.jar

# 声明运行时端口
EXPOSE ${SERVICE_PORT}

# 启动容器时的进程
ENTRYPOINT java ${JAVA_OPTS} \
           -Djava.security.egd=file:/dev/./urandom \
           -jar \
           /home/app/app.jar \
           ${SPRING_OPTS}
