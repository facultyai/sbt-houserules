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
    compileScalastyle := scalastyle.in(Compile).toTask("").value,
    (compile in Compile) := ((compile in Compile) dependsOn compileScalastyle).value,
    testScalastyle := scalastyle.in(Test).toTask("").value,
    (test in Test) := ((test in Test) dependsOn testScalastyle).value,
    scalastyleConfigUrl := Some(getClass.getResource("/scalastyle-config.xml")),
    (scalastyleConfigUrl in Test) := Some(
      getClass.getResource("/scalastyle-config.xml")
    )
  )
}
