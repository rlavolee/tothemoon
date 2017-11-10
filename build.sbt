name := """tothemoon"""

scalaVersion := "2.12.3"

version := "0.1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  guice,
  "com.github.julien-truffaut"  %%  "monocle-core"  % Version.monocle,
  "com.github.julien-truffaut"  %%  "monocle-macro" % Version.monocle,
  "com.github.tototoshi" %% "scala-csv" % "1.3.5"
)
