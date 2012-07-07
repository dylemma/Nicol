import sbt._
import Keys._

object General {
  val settings = Defaults.defaultSettings ++ Seq(
    organization := "com.github.scan",
    version := "0.1.1",
    crossScalaVersions := Seq("2.9.1", "2.9.0"),
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
    libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.10.0" % "test"
  )
}

object NicolEngine extends Build {
  lazy val nicol = Project(
    "nicol", file("."), settings = General.settings ++ Seq (
      mainClass in (Test, run) := Some("nicol.App")
    )
  ) dependsOn nicolCore aggregate (nicolCore, nicolTiles)

  lazy val nicolCore = Project(
    "nicol-core", file("core"), settings = General.settings ++ LWJGLPlugin.lwjglSettings
  )

  lazy val nicolTiles = Project(
    "nicol-tiles", file("tiles"), settings = General.settings
  )  dependsOn nicolCore
}
