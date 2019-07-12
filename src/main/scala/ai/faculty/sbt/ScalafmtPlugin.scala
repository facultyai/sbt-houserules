package ai.faculty.sbt

import sbt._
import sbt.plugins.JvmPlugin

object ScalafmtPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires =
    JvmPlugin && org.scalafmt.sbt.ScalafmtPlugin

  object autoImport {
    val scalafmtGenerateConfig =
      settingKey[Unit]("Write  scalafmt configuration to .scalafmt.conf")
  }

  import autoImport._

  override lazy val projectSettings = Seq(
    // See https://scalameta.org/scalafmt/docs/installation.html#pro-tip.
    scalafmtGenerateConfig := {
      val scalafmtConfContent =
        IO.readStream(getClass.getResourceAsStream("/scalafmt.conf"))

      val targetFilename = ".scalafmt.conf"
      val log = sbt.Keys.sLog.value
      log.info(s"Writing scalafmt config file to $targetFilename")
      IO.write(file(targetFilename), scalafmtConfContent)
    }
  )
}
