package org.danielnixon.scalajswarts

object ArrayPartial extends ClassWart(
  "org.danielnixon.scalajswarts.ArrayPartial",
  "scala.scalajs.js.Array",
  List(
    "apply" -> "Array#apply is disabled",
    "pop" -> "Array#pop is disabled",
    "shift" -> "Array#shift is disabled"
  )
)
