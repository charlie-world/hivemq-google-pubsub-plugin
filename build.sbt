organization := "charlie-world"
name := "hivemq-google-pubsub-plugin"

version := "0.1.0"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.google.cloud" % "google-cloud-pubsub" % "0.34.0-beta",
  "joda-time" % "joda-time" % "2.9.4",
  "com.hivemq" % "hivemq-spi" % "3.0.0",
  "com.typesafe" % "config" % "1.3.2",
  "org.scala-lang.modules" %% "scala-java8-compat" % "0.8.0",
  "org.scalatest" %% "scalatest" % "3.0.4" % Test,
  "org.mockito" % "mockito-core" % "2.11.0" % Test,
  "org.scalacheck" %% "scalacheck" % "1.13.5" % Test
)
