libraryDependencies += "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value

resolvers += Resolver.jcenterRepo
addSbtPlugin("com.frugalmechanic" % "fm-sbt-s3-resolver" % "0.18.0")

