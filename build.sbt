lazy val commonSettings = Seq (
  organization := "jp.co.sample",
  version := "0.1",
  scalaVersion := "2.11.4"
)

lazy val root = 
  (project in file(".")).
  settings(commonSettings: _*).
  settings(
     name := "scala-study",
     scalacOptions += "-deprecation",
     libraryDependencies ++= Seq (
       "com.typesafe.akka" % "akka-actor_2.11" % "2.4.1"
     
     )
  )