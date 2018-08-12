# jpscloud

java -cp D:\soft\apache-maven-3.3.9\repository\org\jasypt\jasypt\1.9.2\jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="123456" password=root algorithm=PBEWithMD5AndDES

java -Xms128m -Xmx512m -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=6666,suspend=n -jar jpscloud-admin-web.jar --spring.profiles.active=dev &
nohup java -Xms128m -Xmx512m -jar jpscloud-admin-web.jar >/dev/null 2>&1 &
只输出错误信息到日志文件
nohup ./program >/dev/null 2>log &
什么信息也不要
nohup ./program >/dev/null 2>&1 &
参数说明：

-XX:MetaspaceSize=128m （元空间默认大小）

-XX:MaxMetaspaceSize=128m （元空间最大大小）

-Xms1024m （堆最大大小）

-Xmx1024m （堆默认大小）

-Xmn256m （新生代大小）

-Xss256k （棧最大深度大小）

-XX:SurvivorRatio=8 （新生代分区比例 8:2）

-XX:+UseConcMarkSweepGC （指定使用的垃圾收集器，这里使用CMS收集器）

知识点：JDK8之后把-XX:PermSize 和 -XX:MaxPermGen移除了，取而代之的是

-XX:MetaspaceSize=128m （元空间默认大小）

-XX:MaxMetaspaceSize=128m （元空间最大大小）

JDK 8开始把类的元数据放到本地化的堆内存(native heap)中，这一块区域就叫Metaspace，中文名叫元空间。

-d 是让容器后台运行
-p 是将容器内的端口映射到 docker 所在系统的端口
-t 是打开一个伪终端，以便后续可以进入查看控制台 log
docker run -d -p 8888:8888 -t jpscloud-admin-web
docker stop 容器ID或容器名 
docker kill 容器ID或容器名 :直接关闭容器
docker rm 容器ID或容器名 
1.先 docker ps -a 寻找已经停止运行的实例 
2.docker rm 59ec 删除实例 
3.docker ps -a 查看实例已经删除 

docker rmi 容器ID或容器名 
1. 首先 docker images 查看所有镜像 
2. 然后 docker rmi fe200 删除镜像 
3.再次通过 docker images 查看镜像 发现已经删除 
