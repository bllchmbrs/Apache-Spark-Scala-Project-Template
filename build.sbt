name := "ScalaSparkTemplate" // change to project name

organization := "com.github.anabranch" // change to your org

version := "0.1"

scalaVersion := "2.10.5"

libraryDependencies ++= Seq(
  // testing
  "org.scalatest"   %% "scalatest"    % "2.2.4"   % "test,it",
  "org.scalacheck"  %% "scalacheck"   % "1.12.2"      % "test,it",
  // logging
  "org.apache.logging.log4j" % "log4j-api" % "2.4.1",
  "org.apache.logging.log4j" % "log4j-core" % "2.4.1",
  // spark core
  "org.apache.spark" % "spark-core_2.10" % "1.5.1",
  "org.apache.spark" % "spark-graphx_2.10" % "1.5.1",
  "org.apache.spark" % "spark-mllib_2.10" % "1.5.1",
  "org.apache.spark" % "spark-streaming_2.10" % "1.5.1",
  "org.apache.spark" % "spark-hive_2.10" % "1.5.1",
  "org.apache.hadoop" % "hadoop-client" % "2.4.0",
  // spark packages
  "com.databricks" % "spark-csv_2.10" % "1.2.0"
)

mainClass in (Compile, packageBin) := Some("com.github.anabranch.ExampleClass")

resolvers += "bintray-spark-packages" at
  "https://dl.bintray.com/spark-packages/maven/" // allows us to include spark packages

resolvers += "Typesafe Simple Repository" at
  "http://repo.typesafe.com/typesafe/simple/maven-releases/"
// Compiler settings. Use scalac -X for other options and their description.
// See Here for more info http://www.scala-lang.org/files/archive/nightly/docs/manual/html/scalac.html
scalacOptions ++= List("-feature","-deprecation", "-unchecked", "-Xlint")

// ScalaTest settings.
// Ignore tests tagged as @Slow (they should be picked only by integration test)
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-l",
  "org.scalatest.tags.Slow", "-u","target/junit-xml-reports", "-oD", "-eS")

