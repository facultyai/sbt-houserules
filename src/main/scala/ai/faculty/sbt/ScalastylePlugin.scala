package ai.faculty.sbt

import org.scalastyle.sbt.ScalastylePlugin.autoImport.{
  scalastyle,
  scalastyleConfigUrl
}
import sbt.Keys.{compile, test}
import sbt._
import sbt.plugins.JvmPlugin

object ScalastylePlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = JvmPlugin && org.scalastyle.sbt.ScalastylePlugin

  object autoImport {
    lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")
    lazy val testScalastyle = taskKey[Unit]("testScalastyle")
  }

  import autoImport._

  override lazy val projectSettings = Seq(
    compileScalastyle := (Compile / scalastyle).toTask("").value,
    Compile / compile := ((Compile / compile) dependsOn compileScalastyle).value,
    testScalastyle := (Test / scalastyle).toTask("").value,
    Test / test := ((Test / test) dependsOn testScalastyle).value,
    scalastyleConfigUrl := Some(getClass.getResource("/scalastyle-config.xml")),
    Test / scalastyleConfigUrl := Some(getClass.getResource("/scalastyle-config.xml"))
  )
}
