package ai.faculty.sbt

import sbt.Keys.{baseDirectory, scalaSource, resourceDirectory}
import sbt._
import sbt.plugins.JvmPlugin

object IntegrationTestSettingsPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = JvmPlugin

  override lazy val projectSettings = Defaults.itSettings ++ Seq(
    scalaSource in IntegrationTest := baseDirectory.value / "it",
    resourceDirectory in IntegrationTest := baseDirectory.value / "it" / "resources"
  )

  override lazy val projectConfigurations: Seq[Configuration] = Seq(IntegrationTest)
}
