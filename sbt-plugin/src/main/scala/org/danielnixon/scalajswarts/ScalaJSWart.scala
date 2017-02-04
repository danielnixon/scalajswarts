package org.danielnixon.scalajswarts

import wartremover.Wart

object ScalaJSWart {
  val UndefOrOpsPartial: Wart = wart("UndefOrOpsPartial")

  private def wart(name: String) = {
    Wart.custom(s"org.danielnixon.scalajswarts.$name")
  }
}
