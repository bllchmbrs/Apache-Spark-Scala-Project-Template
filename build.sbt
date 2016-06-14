name := "ScalaSparkTemplate" // change to project name
organization := "com.github.anabranch" // change to your org
version := "0.2-SNAPSHOT"
scalaVersion := "2.10.5"
// Databricks uses 2.10.5 (as of 02/12/2016)

libraryDependencies ++= Seq(
  // spark core
  "org.apache.spark" %% "spark-core" % "1.6.0",
  "org.apache.spark" %% "spark-sql" % "1.6.0",
  "org.apache.spark" %% "spark-hive" % "1.6.0" % "provided",

  // spark-modules
  // "org.apache.spark" %% "spark-graphx" % "1.6.0",
  // "org.apache.spark" %% "spark-mllib" % "1.6.0",
  // "org.apache.spark" %% "spark-streaming" % "1.6.0",

  // spark packages
  "com.databricks" %% "spark-csv" % "1.3.0",

  // testing
  "org.scalatest"   %% "scalatest"    % "2.2.4"   % "test,it",
  "org.scalacheck"  %% "scalacheck"   % "1.12.2"      % "test,it",

  // logging
  "org.apache.logging.log4j" % "log4j-api" % "2.4.1",
  "org.apache.logging.log4j" % "log4j-core" % "2.4.1"
)

// allows us to include spark packages
resolvers += "bintray-spark-packages" at
  "https://dl.bintray.com/spark-packages/maven/"

resolvers += "Typesafe Simple Repository" at
 "http://repo.typesafe.com/typesafe/simple/maven-releases/"

//////////
///// Databricks Settings
//////////

// Your username to login to Databricks
dbcUsername := sys.env("DATABRICKSUSERNAME")

// Your password (Can be set as an environment variable)
dbcPassword := sys.env("DATABRICKSPASSWORD")
// Gotcha: Setting environment variables in IDE's may differ.
// IDE's usually don't pick up environment variables from .bash_profile or .bashrc

// The URL to the Databricks REST API
dbcApiUrl := "https://your-sub-domain.cloud.databricks.com/api/1.2"

// Add any clusters that you would like to deploy your work to. e.g. "My Cluster"
dbcClusters += "my-cluster"  // Add "ALL_CLUSTERS" if you want to attach your work to all clusters

// An optional parameter to set the location to upload your libraries to in the workspace
// e.g. "/Shared/libraries"
// This location must be an existing path and all folders must exist.
// NOTE: Specifying this parameter is *strongly* recommended as many jars will be uploaded to your cluster.
// Putting them in one folder will make it easy for your to delete all the libraries at once.
// Default is "/"
dbcLibraryPath := "/Shared/Libraries"

// Whether to restart the clusters everytime a new version is uploaded to Databricks.
dbcRestartOnAttach := false // Default true

//////////
///// END Databricks Settings
//////////

mainClass in (Compile, packageBin) := Some("com.github.anabranch.ExampleClass")

// Compiler settings. Use scalac -X for other options and their description.
// See Here for more info http://www.scala-lang.org/files/archive/nightly/docs/manual/html/scalac.html
scalacOptions ++= List("-feature","-deprecation", "-unchecked", "-Xlint")

// ScalaTest settings.
// Ignore tests tagged as @Slow (they should be picked only by integration test)
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-l",
  "org.scalatest.tags.Slow", "-u","target/junit-xml-reports", "-oD", "-eS")

