# jpscloud

java -Xms128m -Xmx512m -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=6666,suspend=n -jar jspcloud-admin-web.jar --spring.profiles.active=dev &
java -Xms128m -Xmx512m -jar jspcloud-admin-web.jar &