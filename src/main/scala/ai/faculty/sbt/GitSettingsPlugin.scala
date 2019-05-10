package ai.faculty.sbt

import com.typesafe.sbt.GitPlugin
import com.typesafe.sbt.SbtGit.git

import sbt._

// This replicates settings from com.typesafe.sbt.GitVersioning but enables 
// the plugin by default
object GitSettingsPlugin extends AutoPlugin {

  override def requires = sbt.plugins.IvyPlugin && GitPlugin
  override def trigger = allRequirements
 
  override def buildSettings = GitPlugin.autoImport.versionWithGit
  override def projectSettings =  Seq(git.useGitDescribe := true)

}
