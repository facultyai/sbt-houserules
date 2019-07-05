libraryDependencies += "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value

resolvers += Resolver.jcenterRepo

// We can't use "ohnosequences" % "sbt-s3-resolver" (that we use for pulling) plugin
// for publishing because it can't push sbt-plugins in the right format
addSbtPlugin("com.frugalmechanic" % "fm-sbt-s3-resolver" % "0.18.0")
