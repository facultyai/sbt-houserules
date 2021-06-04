package ai.faculty.sbt

import sbt.Keys.{doc, sources}
import sbt._
import sbt.plugins.JvmPlugin

object ScaladocSettingsPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = JvmPlugin

  override lazy val projectSettings = Seq(
    // We're unsetting all sources for the docs task because we don't want to
    // have scaladoc output in the build artifact
    Compile / doc / sources := Seq.empty
  )
}
