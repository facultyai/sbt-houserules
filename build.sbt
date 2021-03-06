name := """sbt-houserules"""
organization := "ai.faculty"
version := "0.1.4"

sbtPlugin := true

// These plugins are added to the project that uses sbt-houserules
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.3.0")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "1.0.0")

console / initialCommands := """import ai.faculty.sbt._"""

enablePlugins(ScriptedPlugin)
// set up 'scripted; sbt plugin for testing sbt plugins
scriptedLaunchOpts ++= Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
scriptedBufferLog := false

publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
   else
    Opts.resolver.sonatypeStaging
  )