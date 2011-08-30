name := "diigonote"

version := "0.0.1"

scalaVersion := "2.9.1.RC4"

scalacOptions ++= Seq("-unchecked", "-deprecation")

libraryDependencies <++= (scalaVersion) { sv =>
  Seq("org.scala-lang" % "scala-swing" % sv)
}

