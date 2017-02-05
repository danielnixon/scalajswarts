package org.danielnixon.scalajswarts

import wartremover.Wart

object ScalaJSWart {
  val ArrayPartial: Wart = wart("ArrayPartial")
  val UndefOrOpsPartial: Wart = wart("UndefOrOpsPartial")

  private def wart(name: String) = {
    Wart.custom(s"org.danielnixon.scalajswarts.$name")
  }
}
