FROM findepi/graalvm:java17-native

MAINTAINER Lion Li

RUN mkdir -p /ruoyi/social/logs \
    /ruoyi/social/temp \
    /ruoyi/skywalking/agent

WORKDIR /ruoyi/social

ENV SERVER_PORT=9501

EXPOSE ${SERVER_PORT}

ADD target/ruoyi-social.jar ./app.jar

ENTRYPOINT ["java", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-Dserver.port=${SERVER_PORT}", \
#            "-Dskywalking.agent.service_name=ruoyi-system", \
#            "-javaagent:/ruoyi/skywalking/agent/skywalking-agent.jar", \
            "-jar", "app.jar"]
