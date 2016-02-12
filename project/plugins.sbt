resolvers += Classpaths.sbtPluginReleases

// helpful scala tools
addSbtPlugin("org.scoverage" %% "sbt-scoverage" % "1.0.4")
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.6.0")

// IDE integrations
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "3.0.0")
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

// databricks integration
addSbtPlugin("com.databricks" %% "sbt-databricks" % "0.1.4")
