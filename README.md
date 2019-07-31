# sbt-houserules

Share common settings for SBT projects in Faculty.

The idea is this plugin should be applicable to all scala repositories
(not just microservices) and should be orthogonal to `sherlockml-base`.

## Features

Currently, this plugin automatically applies these settings:

- git versioning setup (links sbt artifact versions to `git describe` output)
- integration tests (enables integration tests under `it/`)
- enables https://github.com/HairyFotr/linter
- sets up resolvers to use `asi-releases-repository` and
  `asi-snapshots-repository` to pull our scala libraries, e.g.
  `sherlockml-base`
- sets options for `scalac` compiler (to e.g. print specific warnings)
- configures `scalafmt` rules and enables `sbt` tasks for autoformatting
- configures and enables `scalastyle`
- sets configuration for unit tests

## Usage

This plugin requires sbt 1.0.0+

Add this to `project/plugins.sbt`:

```scala
// This resolver setup affects only this file - it's needed to pull sbt-houserules
// Resolvers in ../build.sbt are managed by sbt-houserules.
s3region := com.amazonaws.services.s3.model.Region.EU_Ireland
s3credentials := new com.amazonaws.auth.DefaultAWSCredentialsProviderChain()
resolvers ++= Seq[Resolver](
  Resolver.jcenterRepo,
  s3resolver.value("Snapshots resolver", s3("asi-snapshots-repository")).withIvyPatterns,
  s3resolver.value("Releases resolver", s3("asi-releases-repository")).withIvyPatterns
)

addSbtPlugin("ai.faculty" % "sbt-houserules" % "<plugin version>")
```

And add this to `project/project/plugins.sbt`*:

```scala
// This is needed to set up resolvers in ../plugins.sbt to pull sbt-houserules.
resolvers += Resolver.jcenterRepo
addSbtPlugin("ohnosequences" % "sbt-s3-resolver" % "0.19.0")
```

_*_ If you're wondering why this needs to be in `project/project/plugins.sbt`:
SBT is recursive, i.e. project in `build.sbt` is managed by `project/plugins.sbt`
which is managed by `project/project/plugins.sbt`.

## Migrating existing scala projects

See this [wozniak PR](https://bitbucket.org/theasi/wozniak/pull-requests/22/wip-houserules-plugin)
as an example.

### Codeship / sbt tasks

We've typically used this command on Codeship:

```bash
sbt -batch sbt:scalafmt::test scalafmt::test test:scalafmt::test test
```

This should be replaced by:

```bash
sbt -batch scalafmtSbtCheck scalafmtCheckAll test
```

_Note:_ with `sbt-houserules`, `scalafmtCheckAll` checks formatting of all
`.scala` sources (including `test/` and `it/`). Checking `.sbt` files is
done separately by `scalafmtSbtCheck`

## Releasing

You still need to edit the `version` in `build.sbt` - it doesn't use git
version (room for improvement). The run `sbt publish` with your AWS credentials to cloud account.

## Testing

Run `sbt scripted` for [sbt script tests](http://www.scala-sbt.org/1.x/docs/Testing-sbt-plugins.html).

## FAQ

_Do the rules apply to this project (itself)?_
> No. While it would be possible and nice to apply the same rules (e.g.
> formatting) to this project, I believe it might lead to confusion and make it
> more difficult to debug.
