name := "jmcli"

version := "0.1"

scalaVersion := "2.13.1"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.4.0",

  "com.lihaoyi" %% "requests" % "0.4.6",
  "com.lihaoyi" %% "ujson" % "0.9.5",

  "joda-time" % "joda-time" % "2.10.5",

  "ch.qos.logback" % "logback-classic" % "1.2.3",

  "org.scalactic" %% "scalactic" % "3.1.1",
  "org.scalatest" %% "scalatest" % "3.1.1" % "test"
)