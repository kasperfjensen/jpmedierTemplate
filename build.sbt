name := """jpmedier-template"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"
ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }
scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "UTF-8", "-Xfatal-warnings", "-Xlint:missing-interpolator", "-Ywarn-unused", "-Ywarn-dead-code", "-Ywarn-numeric-widen")

libraryDependencies ++= Seq(
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "ch.qos.logback.contrib" % "logback-jackson" % "0.1.4",
  "ch.qos.logback.contrib" % "logback-json-classic" % "0.1.4",
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.1.1" exclude ("com.zaxxer", "HikariCP-java6"),
  "com.typesafe.slick" %% "slick-codegen" % "3.1.1"
  )
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.36"

resolvers += Resolver.url("Typesafe Ivy releases", url("https://repo.typesafe.com/typesafe/ivy-releases"))(Resolver.ivyStylePatterns)
fork in run := false

slick <<= slickCodeGenTask // register manual sbt command

// sourceGenerators in Compile <+= slickCodeGenTask  // register automatic code generation on every compile, remove for only manual use

// code generation task
lazy val slick = TaskKey[Seq[File]]("gen-tables")
lazy val slickCodeGenTask = (sourceManaged, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
  val outputDir = (dir / "slick").getPath
  val username = "root"
  val password = "password"
  val url = "jdbc:mysql://localhost/jpmedierTemplate"
  val jdbcDriver = "com.mysql.jdbc.Driver"
  val slickDriver = "slick.driver.MySQLDriver"
  val pkg = "jp.models"
  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg, username, password), s.log))
  val fname = outputDir + "/jp/models/Tables.scala"
  Seq(file(fname))

}
