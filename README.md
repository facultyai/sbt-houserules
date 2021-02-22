# sbt-houserules

Share common settings for SBT projects in Faculty.

The idea is this plugin should be applicable to all scala repositories
(not just microservices) and should be orthogonal to `sherlockml-base`.

## Features

Currently, this plugin automatically applies these settings:

- git versioning setup (links sbt artifact versions to `git describe` output)
- integration tests (enables integration tests under `it/`)
- sets options for `scalac` compiler (to e.g. print specific warnings)
- configures `scalafmt` rules and enables `sbt` tasks for autoformatting
- configures and enables `scalastyle`
- sets configuration for unit tests

## Usage

This plugin requires sbt 1.0.0+

Add this to `project/plugins.sbt`:

```scala
addSbtPlugin("ai.faculty" % "sbt-houserules" % "<plugin version>")
```



### Scalafmt sbt tasks

To check that all main, test, integration test and SBT sources are correctly
formatted, run:

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

Run `sbt scripted` for [sbt scripted tests](http://www.scala-sbt.org/1.x/docs/Testing-sbt-plugins.html).

## FAQ

_Do the rules apply to this project (itself)?_
> No. While it would be possible and nice to apply the same rules (e.g.
> formatting) to this project, I believe it might lead to confusion and make it
> more difficult to debug.
