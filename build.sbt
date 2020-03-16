import ReleaseTransformations._

name := "jmcli"

scalaVersion := "2.13.1"

organization in ThisBuild := "com.github.anicolaspp"


lazy val jmcli = project.in(file("."))
    .settings(
      homepage := Some(url("https://github.com/anicolaspp/jmcli")),

      scmInfo := Some(ScmInfo(url("https://github.com/anicolaspp/jmcli"), "git@github.com:anicolaspp/jmcli.git")),

      pomExtra := <developers>
        <developer>
          <name>Nicolas A Perez</name>
          <email>anicolaspp@gmail.com</email>
          <organization>anicolaspp</organization>
          <organizationUrl>https://github.com/anicolaspp</organizationUrl>
        </developer>
      </developers>,

      licenses += ("MIT License", url("https://opensource.org/licenses/MIT")),

      publishMavenStyle := true,

      publishTo in ThisBuild := Some(
        if (isSnapshot.value)
          Opts.resolver.sonatypeSnapshots
        else
          Opts.resolver.sonatypeStaging
      ),

      publishArtifact in Test := false,

      pomIncludeRepository := { _ => true },

      releasePublishArtifactsAction := PgpKeys.publishSigned.value,

      releaseProcess := Seq[ReleaseStep](
        checkSnapshotDependencies,              // : ReleaseStep
        inquireVersions,                        // : ReleaseStep
        runClean,                               // : ReleaseStep
        runTest,                                // : ReleaseStep
        setReleaseVersion,                      // : ReleaseStep
        commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
        tagRelease,                             // : ReleaseStep
        publishArtifacts,                       // : ReleaseStep, checks whether `publishTo` is properly set up
        setNextVersion,                         // : ReleaseStep
        commitNextVersion,                      // : ReleaseStep
        pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
      ),

      resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",

      libraryDependencies ++= Seq(
        "com.lihaoyi" %% "requests" % "0.4.6",
        "com.lihaoyi" %% "ujson" % "0.9.5",

        "joda-time" % "joda-time" % "2.10.5",

        "ch.qos.logback" % "logback-classic" % "1.2.3",

        "org.scalactic" %% "scalactic" % "3.1.1",
        "org.scalatest" %% "scalatest" % "3.1.1" % "test"
      )
    )

//addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

assemblyMergeStrategy in assembly := {
  case PathList("org", "apache", "spark", "unused", xs@_*) => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

assemblyJarName := s"${name.value}-${version.value}.jar"

