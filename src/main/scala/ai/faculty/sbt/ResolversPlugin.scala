package ai.faculty.sbt

import com.amazonaws.regions.{Region, Regions}
import ohnosequences.sbt.SbtS3Resolver.autoImport.{
  s3,
  s3credentials,
  s3region,
  s3resolver
}
import sbt.Keys.resolvers
import sbt._
import sbt.plugins.JvmPlugin

object ResolversPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = JvmPlugin

  override lazy val projectSettings = Seq(
    s3region := Region.getRegion(Regions.EU_WEST_1),
    s3credentials := new com.amazonaws.auth.DefaultAWSCredentialsProviderChain(),
    resolvers ++= Seq[Resolver](
      s3resolver
        .value("Snapshots resolver", s3(s"asi-snapshots-repository"))
        .withIvyPatterns,
      s3resolver
        .value("Releases resolver", s3(s"asi-releases-repository"))
        .withIvyPatterns
    )
  )
}
