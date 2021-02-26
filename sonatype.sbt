sonatypeProfileName := "ai.faculty"

publishMavenStyle := true

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
