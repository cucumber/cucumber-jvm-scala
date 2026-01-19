import com.here.bom.Bom
import ReleaseTransformations._
import xerial.sbt.Sonatype.sonatypeSettings
import xerial.sbt.Sonatype.sonatypeCentralHost

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

val scala212 = "2.12.20"
val scala213 = "2.13.18"
val scala3 = "3.3.6"

scalaVersion := scala213

// Library versions

val cucumberVersion = "7.33.0"
val jacksonVersion = "2.21.0"
val jackson3Version = "3.0.3"
val mockitoScalaVersion = "2.0.0"
val junit4Version = "4.13.2"

// BOMs

lazy val junitBom = Bom("org.junit" % "junit-bom" % "6.0.2")

// Projects and settings

lazy val commonSettings = Seq(
  scalacOptions ++= {
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, 12)) => ScalacOptions.scalacOptions212
      case Some((2, 13)) => ScalacOptions.scalacOptions213
      case Some((3, _))  => ScalacOptions.scalacOptions3
      case _             => Seq()
    }
  },
  // Explicitly set target to Java 8
  scalacOptions += "-release:8",
  // Load BOMs
  junitBom,
  // Add all dependencies of the BOM in dependencyOverrides
  dependencyOverrides ++= junitBom.key.value.bomDependencies
)

lazy val junit4SbtSupport = Seq(
  libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % Test
)
lazy val junit5SbtSupport = Seq(
  libraryDependencies += "com.github.sbt.junit" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test
)

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(
    publishArtifact := false
  )
  .aggregate(
    cucumberScala.projectRefs ++
      integrationTestsCommon.projectRefs ++
      integrationTestsJackson2.projectRefs ++
      integrationTestsJackson3.projectRefs ++
      integrationTestsPicoContainer.projectRefs ++
      examplesJunit4.projectRefs ++
      examplesJunit5.projectRefs: _*
  )

// Main project
lazy val cucumberScala = (projectMatrix in file("cucumber-scala"))
  .settings(commonSettings)
  .settings(junit5SbtSupport)
  .settings(
    name := "cucumber-scala",
    libraryDependencies ++= Seq(
      "io.cucumber" % "cucumber-core" % cucumberVersion,
      // Users have to provide it (for JacksonDefaultDataTableTransformer)
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion % Provided,
      "tools.jackson.module" %% "jackson-module-scala" % jackson3Version % Provided,
      "org.junit.jupiter" % "junit-jupiter" % junitBom.key.value % Test,
      ("org.mockito" %% "mockito-scala" % mockitoScalaVersion % Test)
        .cross(CrossVersion.for3Use2_13)
    ),
    libraryDependencies ++= {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, n)) if n == 12 =>
          List("org.scala-lang.modules" %% "scala-collection-compat" % "2.14.0")
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
    .settings(junit5SbtSupport)
    .settings(
      name := "integration-tests-common",
      libraryDependencies ++= Seq(
        "org.junit.platform" % "junit-platform-suite" % junitBom.key.value % Test,
        "io.cucumber" % "cucumber-junit-platform-engine" % cucumberVersion % Test
      ),
      publishArtifact := false
    )
    .dependsOn(cucumberScala % Test)
    .jvmPlatform(scalaVersions = Seq(scala3, scala213, scala212))

lazy val integrationTestsJackson2 =
  (projectMatrix in file("integration-tests/jackson2"))
    .settings(commonSettings)
    .settings(junit5SbtSupport)
    .settings(
      name := "integration-tests-jackson2",
      libraryDependencies ++= Seq(
        "org.junit.platform" % "junit-platform-suite" % junitBom.key.value % Test,
        "io.cucumber" % "cucumber-junit-platform-engine" % cucumberVersion % Test,
        "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion % Test
      ),
      publishArtifact := false
    )
    .dependsOn(cucumberScala % Test)
    .jvmPlatform(scalaVersions = Seq(scala3, scala213, scala212))

lazy val integrationTestsJackson3 =
  (projectMatrix in file("integration-tests/jackson3"))
    .settings(commonSettings)
    .settings(junit5SbtSupport)
    .settings(
      name := "integration-tests-jackson3",
      libraryDependencies ++= Seq(
        "org.junit.platform" % "junit-platform-suite" % junitBom.key.value % Test,
        "io.cucumber" % "cucumber-junit-platform-engine" % cucumberVersion % Test,
        "tools.jackson.module" %% "jackson-module-scala" % jackson3Version % Test
      ),
      publishArtifact := false
    )
    .dependsOn(cucumberScala % Test)
    .jvmPlatform(scalaVersions = Seq(scala3, scala213, scala212))

lazy val integrationTestsPicoContainer =
  (projectMatrix in file("integration-tests/picocontainer"))
    .settings(commonSettings)
    .settings(junit5SbtSupport)
    .settings(
      name := "integration-tests-picocontainer",
      libraryDependencies ++= Seq(
        "org.junit.platform" % "junit-platform-suite" % junitBom.key.value % Test,
        "io.cucumber" % "cucumber-junit-platform-engine" % cucumberVersion % Test,
        "io.cucumber" % "cucumber-picocontainer" % cucumberVersion % Test
      ),
      publishArtifact := false
    )
    .dependsOn(cucumberScala % Test)
    .jvmPlatform(scalaVersions = Seq(scala3, scala213, scala212))

// Examples project
lazy val examplesJunit4 = (projectMatrix in file("examples/examples-junit4"))
  .settings(commonSettings)
  .settings(junit4SbtSupport)
  .settings(
    name := "scala-examples",
    libraryDependencies ++= Seq(
      "io.cucumber" % "cucumber-junit" % cucumberVersion % Test,
      "junit" % "junit" % junit4Version % Test
    ),
    publishArtifact := false
  )
  .dependsOn(cucumberScala % Test)
  .jvmPlatform(scalaVersions = Seq(scala3, scala213))

lazy val examplesJunit5 = (projectMatrix in file("examples/examples-junit5"))
  .settings(commonSettings)
  .settings(junit5SbtSupport)
  .settings(
    name := "scala-examples",
    libraryDependencies ++= Seq(
      "io.cucumber" % "cucumber-junit-platform-engine" % cucumberVersion % Test,
      "org.junit.platform" % "junit-platform-suite" % junitBom.key.value % Test
    ),
    publishArtifact := false
  )
  .dependsOn(cucumberScala % Test)
  .jvmPlatform(scalaVersions = Seq(scala3, scala213))

// Version policy check

ThisBuild / versionScheme := Some("early-semver")
ThisBuild / versionPolicyIntention := Compatibility.BinaryAndSourceCompatible

// Release & Publish

Global / publishMavenStyle := true
Global / publishTo := sonatypePublishToBundle.value
// https://github.com/xerial/sbt-sonatype?tab=readme-ov-file#sonatype-central-host
ThisBuild / sonatypeCredentialHost := sonatypeCentralHost

// https://github.com/xerial/sbt-sonatype#using-with-sbt-release-plugin
releaseCrossBuild := true
releaseVersionBump := sbtrelease.Version.Bump.NextStable // Required since 1.4.0
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  // the 2 following steps are part of the Cucumber release process
  // commitReleaseVersion,
  // tagRelease,
  releaseStepCommandAndRemaining("publishSigned"),
  releaseStepCommand("sonatypeBundleRelease"),
  setNextVersion
  // the 2 following steps are part of the Cucumber release process
  // commitNextVersion,
  // pushChanges
)
