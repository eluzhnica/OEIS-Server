name := "OEIS_SERVER"

version := "1.0"

lazy val `oeis_server` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( jdbc , anorm , cache , ws, "org.sorm-framework" % "sorm" % "0.3.8" )

unmanagedJars in Compile += file("lib/oeis_1.jar")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

//fork in run := true
