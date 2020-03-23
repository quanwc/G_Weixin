# 基础镜像使用java
FROM java
# 作者
MAINTAINER quanwenchao "quan_wc@163.com"
# VOLUME 指定了临时文件目录为/tmp
# 指定临时文件目录为/tmp，其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为weixin-service.jar
ADD weixin-service-0.0.2.jar weixin-service.jar
# 运行jar包
RUN bash -c 'touch /weixin-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/weixin-service.jar"]
