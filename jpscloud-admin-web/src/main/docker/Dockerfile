FROM frolvlad/alpine-oraclejdk8
VOLUME /tmp
ADD jpscloud-admin-web.jar adminweb.jar
RUN sh -c 'touch /adminweb.jar'
ENV JAVA_OPTS="-Xms256m -Xmx512m"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /adminweb.jar" ]