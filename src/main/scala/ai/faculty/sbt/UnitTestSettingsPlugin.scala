package ai.faculty.sbt

import sbt.Keys.fork
import sbt._
import sbt.plugins.JvmPlugin

object UnitTestSettingsPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = JvmPlugin

  override lazy val projectSettings = Seq(
    Test / fork := true
  )
}
