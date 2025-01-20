name := "FuncionalProgramingInScala"

version := "1.0"

scalaVersion := "3.4.2"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.17" % Test,
  "org.scalatestplus" %% "junit-4-13" % "3.2.10.0" % Test,
  "junit" % "junit" % "4.13.2" % Test
)

Compile / scalaSource := baseDirectory.value / "lib" / "src" / "main" / "scala"
Test / scalaSource := baseDirectory.value / "lib" / "src" / "test" / "scala"