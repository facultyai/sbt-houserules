package ai.faculty.sbt

import sbt.Keys.{console, scalacOptions}
import sbt._
import sbt.plugins.JvmPlugin

object ScalacOptionsPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = JvmPlugin

  override lazy val projectSettings = Seq(
    scalacOptions ++= Seq(
      "-feature",
      "-Ywarn-unused-import",
      "-Ywarn-dead-code",
      "-Ywarn-unused",
      "-Ywarn-value-discard",
      "-Yno-adapted-args",
      "-Xlint:_"
    ),
    scalacOptions in (Compile, console) ~= (_ filterNot (_ == "-Ywarn-unused-import"))
  )
}
