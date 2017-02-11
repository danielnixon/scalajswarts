package org.danielnixon.scalajswarts

import org.danielnixon.warthelpers.CoursierResolver
import sbt._
import sbt.Keys._
import wartremover.WartRemover
import wartremover.WartRemover.autoImport.wartremoverClasspaths

object ScalaJSWarts extends AutoPlugin {

  object autoImport {
    val ScalaJSWart = org.danielnixon.scalajswarts.ScalaJSWart
  }

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = WartRemover && org.scalajs.sbtplugin.ScalaJSPlugin

  override lazy val projectSettings: Seq[Setting[_]] = Seq(
    wartremoverClasspaths := {
      val value = wartremoverClasspaths.value

      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, y)) if y >= 11 =>

          val artifactID = s"${BuildInfo.artifactID}_2.$y"
          val files = CoursierResolver.resolve(BuildInfo.organization, artifactID, BuildInfo.version)
          val urls = files.map(_.toURI.toURL.toString)
          urls ++ value

        case _ => value
      }
    }
  )
}
