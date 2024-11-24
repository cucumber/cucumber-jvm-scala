// Cross compilation matrix
addSbtPlugin("com.eed3si9n" % "sbt-projectmatrix" % "0.10.1")

// Scalafmt (formatter)
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")

// Version policy check
addSbtPlugin("ch.epfl.scala" % "sbt-version-policy" % "3.2.1")

// Release
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.13")

// Publishing
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.12.2")
addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.3.0")
