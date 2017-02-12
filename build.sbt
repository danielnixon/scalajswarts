import scalariform.formatter.preferences._

val scala210 = "2.10.6"
val scala211 = "2.11.8"
val scala212 = "2.12.1"
val coreName = "scalajswarts"
val wartremoverVersion = "2.0.1"
val wartHelpersVersion = "0.2.0"

lazy val commonSettings = Seq(
  organization := "org.danielnixon",
  licenses := Seq("The Apache Software License, Version 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")),
  version := "0.4.0",
  publishMavenStyle := true,
  publishArtifact in Test := false,
  publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  homepage := Some(url("https://github.com/danielnixon/scalajswarts")),
  pomExtra := {
    <scm>
      <url>git@github.com:danielnixon/scalajswarts.git</url>
      <connection>scm:git:git@github.com:danielnixon/scalajswarts.git</connection>
    </scm>
      <developers>
        <developer>
          <id>danielnixon</id>
          <name>Daniel Nixon</name>
          <url>https://danielnixon.org/</url>
        </developer>
      </developers>
  },
  scalariformPreferences := scalariformPreferences.value
    .setPreference(DoubleIndentClassDeclaration, true)
    .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true),
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-Ywarn-dead-code",
    "-Ywarn-inaccessible",
    "-Ywarn-value-discard",
    "-Ywarn-numeric-widen",
    "-Ywarn-nullary-override")
)

lazy val core = Project(
  id = "core",
  base = file("core")
).settings(commonSettings ++ Seq(
  name := coreName,
  scalaVersion := scala211,
  crossScalaVersions := Seq(scala211, scala212),
  libraryDependencies ++= Seq(
    "org.wartremover" %% "wartremover" % wartremoverVersion,
    "org.danielnixon" %% "wart-helpers" % wartHelpersVersion
  ),
  scalacOptions ++= Seq("-Xlint:_", "-Ywarn-unused", "-Ywarn-unused-import")
): _*)

lazy val sbtPlug: Project = Project(
  id = "sbt-plugin",
  base = file("sbt-plugin")
).enablePlugins(
  BuildInfoPlugin
).settings(commonSettings ++ Seq(
  buildInfoKeys := Seq[BuildInfoKey](version, organization, isSnapshot, "artifactID" -> coreName, scalaVersion),
  buildInfoPackage := s"${organization.value}.$coreName",
  sbtPlugin := true,
  name := s"sbt-$coreName",
  scalaVersion := scala210,
  addSbtPlugin("org.wartremover" %% "sbt-wartremover" % wartremoverVersion),
  addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.14"),
  libraryDependencies += "org.danielnixon" %% "wart-helpers-sbt" % wartHelpersVersion,
  scalacOptions += "-Xlint"
): _*)
