# Sugar-Copy

## 缘起

需要同步两台电脑的剪贴板。

## 构建

```shell
mvn package assembly:single
```

## 运行

```shell
java -jar sugar-copy-1.0-SNAPSHOT-jar-with-dependencies.jar 15672 192.168.50.217:15672
```

第一个参数为监听的端口，用于接收其它机器发过来的剪贴板消息，用于更新当前机器剪贴板。

后续参数为需要推送剪贴板消息的节点 host:port,用于将当前机器更新的剪贴板内容推送出去。

## 待办

+ 图片/文件类型剪贴板支持
+ GUI支持

