package ai.faculty.sbt

import com.typesafe.sbt.SbtGit.git

import sbt._

// This replicates settings from com.typesafe.sbt.GitVersioning but enables
// the plugin by default
object GitPlugin extends AutoPlugin {

  override def requires = sbt.plugins.IvyPlugin && com.typesafe.sbt.GitPlugin
  override def trigger = allRequirements

  override def buildSettings =
    com.typesafe.sbt.GitPlugin.autoImport.versionWithGit
  override def projectSettings = Seq(git.useGitDescribe := true)

}
