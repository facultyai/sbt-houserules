import scala.sys.process._

name := """sbt-houserules"""
organization := "ai.faculty"
version := ("git describe --dirty=-SNAPSHOT" !!)

sbtPlugin := true

// These plugins are added to the project that uses sbt-houserules
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.0.2")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "1.0.0")
addSbtPlugin("ohnosequences" % "sbt-s3-resolver" % "0.19.0")

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
