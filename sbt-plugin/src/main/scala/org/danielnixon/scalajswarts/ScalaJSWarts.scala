package org.danielnixon.scalajswarts

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
          val version = BuildInfo.version
          val jarWithVersion = s"$artifactID-$version.jar"
          val org = BuildInfo.organization

          // TODO: Can we persuade sbt to resolve this for us without making it a library dependency?
          val url = if (BuildInfo.isSnapshot) {
            val path = Path.userHome / ".ivy2" / "local" / org / artifactID / version / "jars" / s"$artifactID.jar"
            path.toURI.toURL
          } else {
            val orgPath = org.replace('.', '/')
            new URL(s"https://repo1.maven.org/maven2/$orgPath/$artifactID/$version/$jarWithVersion")
          }

          val to = target.value / jarWithVersion
          if (!to.exists) {
            streams.value.log.info(s"Downloading [$url].")
            IO.download(url, to)
          }
          streams.value.log.info(s"Adding [$jarWithVersion] to WartRemover classpath.")
          to.toURI.toString +: value
        case _ => value
      }
    }
  )
}
