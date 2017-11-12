name := """tothemoon"""

scalaVersion := "2.12.3"

version := "0.1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  guice,
  "com.github.tototoshi" %% "scala-csv" % "1.3.5",
  "org.scalatest" %% "scalatest" % "3.0.4" % Test
)
