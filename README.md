# Scala.js Warts

[![Build Status](https://travis-ci.org/danielnixon/scalajswarts.svg?branch=master)](https://travis-ci.org/danielnixon/scalajswarts)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.danielnixon/scalajswarts_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.danielnixon/scalajswarts_2.12)

[WartRemover](https://github.com/wartremover/wartremover) warts for [Scala.js](https://www.scala-js.org/).

## Versions

| Scala.js Warts version | WartRemover version | Scala.js version   | Scala version  |
|------------------------|---------------------|--------------------|----------------|
| 0.1.0                  | 1.3.0               | 0.6.14             | 2.11.8, 2.12.1 |

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

`scala.scalajs.js.UndefOrOps` has a `get` method which will throw if the value is undefined. The program should be refactored to use `UndefOrOps#getOrElse` or `UndefOrOps#fold` to explicitly handle both the defined and undefined cases.

## See also

* [ExtraWarts](https://github.com/danielnixon/extrawarts): Extra WartRemover warts.
* [PlayWarts](https://github.com/danielnixon/playwarts):  WartRemover warts for [Play Framework](https://www.playframework.com/).
* [SlickWarts](https://github.com/danielnixon/slickwarts): WartRemover warts for [Slick](http://slick.typesafe.com/).
