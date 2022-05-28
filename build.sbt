import ReleaseTransformations._
import xerial.sbt.Sonatype.sonatypeSettings

// Metadata

ThisBuild / organization := "io.cucumber"
ThisBuild / organizationName := "Cucumber"
ThisBuild / organizationHomepage := Some(url("https://github.com/cucumber"))
ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/cucumber/cucumber-jvm-scala"),
    "scm:git@github.com:cucumber/cucumber-jvm-scala.git"
  )
)
ThisBuild / developers := List(
  Developer(
    "cucumber",
    "Cucumber Developers",
    "devs@cucumber.io",
    url("https://github.com/cucumber")
  )
)
ThisBuild / licenses := Seq(
  "MIT License" -> url("http://www.opensource.org/licenses/mit-license")
)
ThisBuild / description := "Cucumber for Scala"
ThisBuild / homepage := Some(
  url("https://github.com/cucumber/cucumber-jvm-scala")
)

// Scala versions

val scala212 = "2.12.15"
val scala213 = "2.13.6"
val scala3 = "3.0.2"

scalaVersion := scala213

// Library versions

val cucumberVersion = "7.3.4"
val jacksonVersion = "2.13.2"
val mockitoScalaVersion = "1.17.7"
val junitVersion = "4.13.2"

// Projects and settings

lazy val commonSettings = Seq(
  libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test,
  scalacOptions ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 12)) => ScalacOptions.scalacOptions212
      case Some((2, 13)) => ScalacOptions.scalacOptions213
      case Some((3, 0))  => ScalacOptions.scalacOptions3
      case _             => Seq()
    }
  }
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(
    publishArtifact := false
  )
  .aggregate(
    cucumberScala.projectRefs ++
      integrationTestsCommon.projectRefs ++
      integrationTestsJackson.projectRefs ++
      examples.projectRefs: _*
  )

// Main project
lazy val cucumberScala = (projectMatrix in file("cucumber-scala"))
  .settings(commonSettings)
  .settings(
    name := "cucumber-scala",
    libraryDependencies ++= Seq(
      "io.cucumber" % "cucumber-core" % cucumberVersion,
      // Users have to provide it (for JacksonDefaultDataTableTransformer)
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion % Provided,
      "junit" % "junit" % junitVersion % Test,
      ("org.mockito" %% "mockito-scala" % mockitoScalaVersion % Test)
        .cross(CrossVersion.for3Use2_13)
    ),
    libraryDependencies ++= {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, n)) if n == 12 =>
          List("org.scala-lang.modules" %% "scala-collection-compat" % "2.7.0")
        case _ => Nil
      }
    },
    Compile / unmanagedSourceDirectories ++= {
      val sourceDir = (Compile / sourceDirectory).value
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, n)) =>
          Seq(sourceDir / "scala-2")
        case Some((3, 0)) =>
          Seq(sourceDir / "scala-3")
        case _ =>
          Seq()
      }
    },
    Test / unmanagedSourceDirectories ++= {
      val testSourceDir = (Test / sourceDirectory).value
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, _)) =>
          Seq(testSourceDir / "scala-2")
        case Some((3, 0)) =>
          Seq(testSourceDir / "scala-3")
        case _ =>
          Seq()
      }
    },
    // Generate I18n traits
    Compile / sourceGenerators += Def.task {
      val file =
        (Compile / sourceManaged).value / "io/cucumber/scala" / "I18n.scala"
      IO.write(file, I18nGenerator.i18n)
      Seq(file)
    }.taskValue
  )
  .jvmPlatform(scalaVersions = Seq(scala3, scala213, scala212))

// Integration tests
lazy val integrationTestsCommon =
  (projectMatrix in file("integration-tests/common"))
    .settings(commonSettings)
    .settings(
      name := "integration-tests-common",
      libraryDependencies ++= Seq(
        "junit" % "junit" % junitVersion % Test,
        "io.cucumber" % "cucumber-junit" % cucumberVersion % Test
      ),
      publishArtifact := false
    )
    .dependsOn(cucumberScala % Test)
    .jvmPlatform(scalaVersions = Seq(scala3, scala213, scala212))

lazy val integrationTestsJackson =
  (projectMatrix in file("integration-tests/jackson"))
    .settings(commonSettings)
    .settings(
      name := "integration-tests-jackson",
      libraryDependencies ++= Seq(
        "junit" % "junit" % junitVersion % Test,
        "io.cucumber" % "cucumber-junit" % cucumberVersion % Test,
        "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion % Test
      ),
      publishArtifact := false
    )
    .dependsOn(cucumberScala % Test)
    .jvmPlatform(scalaVersions = Seq(scala3, scala213, scala212))

// Examples project
lazy val examples = (projectMatrix in file("examples"))
  .settings(commonSettings)
  .settings(
    name := "scala-examples",
    libraryDependencies ++= Seq(
      "io.cucumber" % "cucumber-junit" % cucumberVersion % Test,
      "junit" % "junit" % junitVersion % Test
    ),
    publishArtifact := false
  )
  .dependsOn(cucumberScala % Test)
  .jvmPlatform(scalaVersions = Seq(scala3, scala213, scala212))

// Version policy check

ThisBuild / versionScheme := Some("early-semver")
ThisBuild / versionPolicyIntention := Compatibility.BinaryAndSourceCompatible

// Release & Publish

Global / publishMavenStyle := true
Global / publishTo := sonatypePublishToBundle.value

// https://github.com/xerial/sbt-sonatype#using-with-sbt-release-plugin
releaseCrossBuild := true
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  // the 3 following steps are part of the Cucumber release process
  //setReleaseVersion,
  //commitReleaseVersion,
  //tagRelease,
  releaseStepCommandAndRemaining("publishSigned"),
  releaseStepCommand("sonatypeBundleRelease")
  // the 3 following steps are part of the Cucumber release process
  //setNextVersion,
  //commitNextVersion,
  //pushChanges
)
