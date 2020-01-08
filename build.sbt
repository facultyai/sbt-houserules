name := """sbt-houserules"""
organization := "ai.faculty"
version := "0.0.2-SNAPSHOT"

sbtPlugin := true

// to pull `"ohnosequences" % "sbt-s3-resolver" % "0.19.0"` below
resolvers += Resolver.jcenterRepo

// These plugins are added to the project that uses sbt-houserules
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.3.0")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "1.0.0")
addSbtPlugin("ohnosequences" % "sbt-s3-resolver" % "0.19.0")

initialCommands in console := """import ai.faculty.sbt._"""

enablePlugins(ScriptedPlugin)
// set up 'scripted; sbt plugin for testing sbt plugins
scriptedLaunchOpts ++= Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
scriptedBufferLog := false

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
