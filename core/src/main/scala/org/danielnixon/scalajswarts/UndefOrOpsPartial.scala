package org.danielnixon.scalajswarts

import org.danielnixon.warthelpers.ClassWart

// TODO: orNull as well?
object UndefOrOpsPartial extends ClassWart(
  "org.danielnixon.scalajswarts.UndefOrOpsPartial",
  "scala.scalajs.js.UndefOrOps",
  List(
    "get" -> "UndefOrOps#get is disabled - use UndefOrOps#getOrElse or UndefOrOps#fold instead"
  )
)
