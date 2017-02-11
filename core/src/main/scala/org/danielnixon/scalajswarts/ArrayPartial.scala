package org.danielnixon.scalajswarts

import org.danielnixon.warthelpers.ClassWart

object ArrayPartial extends ClassWart(
  "org.danielnixon.scalajswarts.ArrayPartial",
  "scala.scalajs.js.Array",
  List(
    "apply" -> "Array#apply is disabled",
    "pop" -> "Array#pop is disabled",
    "shift" -> "Array#shift is disabled"
  )
)
