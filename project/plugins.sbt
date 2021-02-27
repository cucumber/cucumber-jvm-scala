// Cross compilation matrix
addSbtPlugin("com.eed3si9n" % "sbt-projectmatrix" % "0.7.0")

// Scalafmt (formatter)
addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.2")

// Version policy check
addSbtPlugin("ch.epfl.scala" % "sbt-version-policy" % "1.0.0-RC5")

// Release
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.13")

// Publishing
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.5")
addSbtPlugin("com.jsuereth" % "sbt-pgp" % "2.1.1")
