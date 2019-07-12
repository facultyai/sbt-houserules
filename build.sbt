name := """sbt-houserules"""
organization := "ai.faculty"
version := "0.0.1-SNAPSHOT"

sbtPlugin := true

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.0.2")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "1.0.0")
addSbtPlugin("ohnosequences" % "sbt-s3-resolver" % "0.19.0")

// choose a test framework

// utest
//libraryDependencies += "com.lihaoyi" %% "utest" % "0.4.8" % "test"
//testFrameworks += new TestFramework("utest.runner.Framework")

// ScalaTest
//libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1" % "test"
//libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

// Specs2
//libraryDependencies ++= Seq("org.specs2" %% "specs2-core" % "3.9.1" % "test")
//scalacOptions in Test ++= Seq("-Yrangepos")

initialCommands in console := """import ai.faculty.sbt._"""

enablePlugins(ScriptedPlugin)
// set up 'scripted; sbt plugin for testing sbt plugins
scriptedLaunchOpts ++=
  Seq("-Xmx1024M", "-Dplugin.version=" + version.value)

publishTo := {
  val prefix = if (isSnapshot.value) "snapshots" else "releases"
  Some(
    Resolver.url(
      s"ASI $prefix S3 bucket",
      url(s"s3://s3-eu-west-1.amazonaws.com/asi-$prefix-repository")
    )(Resolver.ivyStylePatterns)
  )
}
publishMavenStyle := false
