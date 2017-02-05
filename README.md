# Scala.js Warts

[![Build Status](https://travis-ci.org/danielnixon/scalajswarts.svg?branch=master)](https://travis-ci.org/danielnixon/scalajswarts)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.danielnixon/scalajswarts_2.12/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.danielnixon/scalajswarts_2.12)

[WartRemover](https://github.com/wartremover/wartremover) warts for [Scala.js](https://www.scala-js.org/).

## Versions

| Scala.js Warts version | WartRemover version | Scala.js version   | Scala version  |
|------------------------|---------------------|--------------------|----------------|
| 0.2.0                  | 1.3.0               | 0.6.14             | 2.11.8, 2.12.1 |

## Usage

1. Setup [WartRemover](https://github.com/wartremover/wartremover).
2. Add the following to your `plugins.sbt`:

    ```scala
    addSbtPlugin("org.danielnixon" % "sbt-scalajswarts" % "0.1.0")
    ```

3. Add the following to your `build.sbt`:
    ```scala
    wartremoverWarnings ++= Seq(
      ScalaJSWart.ArrayPartial,
      ScalaJSWart.UndefOrOpsPartial
   )
    ```

## Warts

### ArrayPartial

`scala.scalajs.js.Array[T]` has `apply`, `pop` and `shift` methods, all of which can return `undefined` (even though their return type is `T`, not `UndefOr[T]`). This can lead to [`UndefinedBehaviorError`s](https://www.scala-js.org/doc/semantics.html#undefined-behaviors).

You can wrap these methods in an implicit that might look something like this:

```scala
  @SuppressWarnings(Array("org.danielnixon.scalajswarts.ArrayPartial"))
  implicit class SaferArray[A](val value: scala.scalajs.js.Array[A]) extends AnyVal {
    def applyOpt(index: Int): Option[A] = liftUndefined(value.apply(index))

    def popOpt(): Option[A] = liftUndefined(value.pop())

    def shiftOpt(): Option[A] = liftUndefined(value.shift())

    private def liftUndefined[T <: scala.Any](v: T): Option[T] = {
      if (scalajs.js.isUndefined(v)) None else Some(v)
    }
  }
```

### UndefOrOpsPartial

`scala.scalajs.js.UndefOrOps` has a `get` method which will throw if the value is undefined. The program should be refactored to use `UndefOrOps#getOrElse` or `UndefOrOps#fold` to explicitly handle both the defined and undefined cases.

## See also

* [ExtraWarts](https://github.com/danielnixon/extrawarts): Extra WartRemover warts.
* [PlayWarts](https://github.com/danielnixon/playwarts):  WartRemover warts for [Play Framework](https://www.playframework.com/).
* [SlickWarts](https://github.com/danielnixon/slickwarts): WartRemover warts for [Slick](http://slick.typesafe.com/).
