// Cross compilation matrix
addSbtPlugin("com.eed3si9n" % "sbt-projectmatrix" % "0.8.0")

// Scala 3 (Dotty)
addSbtPlugin("ch.epfl.lamp" % "sbt-dotty" % "0.5.3")

// Scalafmt (formatter)
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.2")

// Version policy check
addSbtPlugin("ch.epfl.scala" % "sbt-version-policy" % "1.0.1")

// Release
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.13")

// Publishing
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.7")
addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.1.2")
