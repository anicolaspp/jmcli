# jmcli
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.anicolaspp/ojai-testing_2.11/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.anicolaspp/jmcli_2.12)

A JVM based CLI for MapR

This library offers easy access to MapR management tooling in the same way the famous `maprcli` tool does. The main idea is to present a JVM way to interact with MapR clusters. 

`jmcli` uses the underlaying MapR Api Server by making REST calls to different APIs. However, all these interactions are hidden behind a clean and straight forward interfaces that reassemble the powerful `maprcli`. 

## The MapR CLI Client

In order to start communicating with the MapR cluster, we must first create a `MapRCLIClient`. The following code shows how this can be done. 

```scala
 val client = MapRCLI
      .withHost("https://172.20.60.107:8443")
      .withAuth("user", "mypass")
```

Notice that we must pass the URL where the MapR Api Server is running and the authentication pieces if the cluster if secured.  
Once we have a client, then we can start interacting with the MapR cluster.

## Managing Volumes

We can use `jmcli` to create MapR Volumes in the following way. 

- Create volume only with a given `name`.

```scala      
val volumeCreate = client
      .volume()
      .create("npereztest")

val response = volumeCreate.run()      
```

- Create volume with `name` and some other args. 

```scala
val volumeCreate = client
      .volume()
      .create("nperez", ("path", "/user/nperez"), ("advisoryquota", "500G"), ("topology", "/data/ssd"))

val response = volumeCreate.run()   
```

- List existing volumes.

```scala
client.volume().list().run()
```

Notice that each `maprcli` command can be accessed using `jmcli` using the same fluent API. 

- Mounting and unmounting a volume

```scala
val mount = client.volume().mount("nperez", "/user/nperez")

val unmount = client.volume().unmount("nperez")

mount.run()
unmount.run()
```              

In the current version of `jmcli` we are providing access to only some of the possible APIs. We are going to be adding more as the library evolves. 

The following is the available `volume` APIs.

- `volume list`
- `volume create`
- `volume mount`
- `volume unmount`
- `volume info`
- `volume snapshot list`
- `volume snapshot create`
- `volume snapshot remove`
- `volume modify`
- `volume remove`
- `volume rename`
- `volume showmounts`

## Managing Tables

Tables can also be through by `jmcli`.

- Creating tables.

```scala

val createTable = client
  .table()
  .create("/user/mytable")

createTable.run()
```

- Listing column families on table.

```scala
val cfQuery = client
 .table()
 .cf()
 .list("/user/mytable")
 
cdQuery.run()
```
The `table` API support the following commands

- `table create`
- `table cf list`
- `table delete`
- `table edit`
- `table info`
- `table region list`
- `table region split`

We will continue adding more soon. 

## Managing Streams

Stream can also be managed in the following way. 

```scala
client.stream().create("/mystream")
```
Again, the same fluent API use by `maprcli` can be used here. 

The following is the list of available entry points. 

- `stream assign list`
- `stream create`
- `stream cursor list`
- `stream cursor delete`
- `stream edit`
- `stream delete`
- `stream info`
- `stream topic create`
- `stream topic delete`
- `stream topic info`
- `stream topic list`

We will continue adding more soon. 

## Sync and Async Commands 

All APIs presented in this library run `synchronously`. However, we can run the `asynchronously` by importing the `com.github.anicolasp.mapr.cli.runnable.Async`. The following snippet shows an example. 

```scala
import com.github.anicolasp.mapr.cli.runnable.Async._

client.volume().list().runAsync().foreach(list => doSomeThing(list))
```

## Java 

When using java, this libray uses the exact same API. The following shows a simple example. 

```java 
package com.github.anicolasp.test;

import com.github.anicolasp.mapr.cli.client.Client;
import com.github.anicolasp.mapr.cli.client.MapRCLI;
import com.github.anicolasp.mapr.cli.runnable.RunnableQuery;

public class App {
    public static void main(String[] args) {
        Client client = MapRCLI
                .withHost("https://<api server ip>:8443")
                .withAuth("user", "pass");

        RunnableQuery query = client.volume().list();

       query.run();
    }
}
```

## Linking

You can get this library from Maven Central using the following snippet.

```xml
<dependency>
 <groupId>com.github.anicolaspp</groupId>
 <artifactId>jmcli_2.13</artifactId>
 <version>1.0.0</version>
</dependency>
```
