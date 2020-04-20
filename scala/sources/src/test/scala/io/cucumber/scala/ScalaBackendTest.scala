package io.cucumber.scala

import java.net.URI
import java.util.function.Supplier

import io.cucumber.core.backend.Glue
import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.{Before, Test}
import org.mockito.Mockito.mock

import scala.collection.JavaConverters._

class ScalaBackendTest {

  private val glue: Glue = mock(classOf[Glue])

  private var backend: ScalaBackend = _

  @Before
  def createBackend() = {
    // Note: keep unnecessary code for Scala 2.11 compat
    val classLoaderSupplier: Supplier[ClassLoader] = new Supplier[ClassLoader] {
      override def get(): ClassLoader = Thread.currentThread().getContextClassLoader
    }
    backend = new ScalaBackend(classLoaderSupplier)
  }

  @Test
  def finds_step_definitions_by_classpath_url() = {
    backend.loadGlue(glue, List(URI.create("classpath:io/cucumber/scala/steps")).asJava)
    backend.buildWorld()

    assertEquals(1, backend.scalaGlueInstances.size)
    assertTrue(backend.scalaGlueInstances.head.isInstanceOf[io.cucumber.scala.steps.Steps])
  }

}
