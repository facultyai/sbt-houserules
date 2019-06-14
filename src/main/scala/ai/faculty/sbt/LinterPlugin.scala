package ai.faculty.sbt

import sbt.Keys.autoCompilerPlugins
import sbt._
import sbt.plugins.JvmPlugin

object LinterPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = JvmPlugin

  override lazy val projectSettings = Seq(
    autoCompilerPlugins := true,
    addCompilerPlugin("org.psywerx.hairyfotr" %% "linter" % "0.1.17")
  )
}
