package ai.faculty.sbt

import sbt.Keys.{baseDirectory, fork, resourceDirectory, scalaSource}
import sbt._
import sbt.plugins.JvmPlugin

object IntegrationTestSettingsPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = JvmPlugin

  override lazy val projectSettings = Defaults.itSettings ++ Seq(
    IntegrationTest / scalaSource  := baseDirectory.value / "it",
    IntegrationTest / resourceDirectory  := baseDirectory.value / "it" / "resources",
    IntegrationTest / fork  := true
  )

  override lazy val projectConfigurations = Seq(IntegrationTest)
}
