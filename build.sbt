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
       "com.typesafe.akka" % "akka-actor_2.11"               % "2.4.1"  ,
       "com.typesafe.akka" % "akka-stream-experimental_2.11" % "1.0-M3" ,
       "org.scalactic"     % "scalactic_2.11"                % "2.2.6"  ,
       "org.scalatest"     % "scalatest_2.11"                % "2.2.6"    % "test",
       "org.scalamock"    %% "scalamock-scalatest-support"   % "3.2.2"    % "test"
     )
  )