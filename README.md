# Scala.js Warts

[WartRemover](https://github.com/wartremover/wartremover) warts for [Scala.js](https://www.scala-js.org/).

## Usage

1. Setup [WartRemover](https://github.com/wartremover/wartremover).
2. Add the following to your `plugins.sbt`:

    ```scala
    addSbtPlugin("org.danielnixon" % "sbt-scalajswarts" % "0.1.0")
    ```

3. Add the following to your `build.sbt`:
    ```scala
    wartremoverWarnings ++= Seq(
      ScalaJSWart.UndefOrOpsPartial
   )
    ```

## Warts

### UndefOrOpsPartial

`scala.scalajs.js.UndefOrOps` has a `get` method which will throw if the value is undefined. The program should be refactored to use `scala.scalajs.js.UndefOrOps#getOrElse` or `scala.scalajs.js.UndefOrOps#fold` to explicitly handle both the defined and undefined cases.