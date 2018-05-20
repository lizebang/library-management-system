# library-management-system

大二 Java 实验 -- 图书管理系统

仅仅为了娱乐，嘻嘻。如有错误请提 [issues](https://github.com/lizebang/library-management-system/issues/new) <-- Click Here

## how to run

### prepare

mysql -- docker

以下命令适用于是 `macOS`/`linux`，`windows` 下请自己修改 `-v ~/docker/mysql/library:/var/lib/mysql` 参数

```shell
docker run -d --name library -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -v ~/docker/mysql/library:/var/lib/mysql mysql:5.7 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```

然后创建 DATABASE `library`

```shell
docker exec -it library mysql -uroot -p123456
```

```shell
CREATE DATABASE IF NOT EXISTS `library`;
```

### run server

在 `server` 目录下执行

```shell
./gradlew bootRun
```

也可以打包成 `jar`

```shell
./gradlew build && java -jar build/libs/server-0.0.1-SNAPSHOT.jar
```

### run client

在 `client` 目录下执行

```shell
mvn package && java -jar target/client-0.0.1-SNAPSHOT.jar
```
