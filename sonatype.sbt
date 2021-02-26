sonatypeProfileName := "ai.faculty"

licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://github.com/facultyai/sbt-houserules"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/facultyai/sbt-houserules"),
    "scm:git@github.com:facultyai/sbt-houserules.git"
  )
)

developers := List(
  Developer(id="tomasmilata", name="Tomáš Milata", email="tomas@faculty.ai", url=url("https://github.com/tomas-milata"))
)
