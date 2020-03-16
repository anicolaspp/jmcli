# jmcli

A JVM based CLI for MapR

The library will allow you to do something like for creating and mounting a volume:

```scala
 val client = MapRCLI
      .withHost("https://172.20.60.107:8443")
      .withAuth("user", "mypass")
      
val volumeCreate = client
      .volume()
      .create("npereztest", List(("path", "/user/npereztest")))

val response = volumeCreate.run()      
```

or creating tables:

```scala

val createTable = client
  .table()
  .create("/user/mytable", List(("ttl", "48400")))

createTable.runAsync()
```
