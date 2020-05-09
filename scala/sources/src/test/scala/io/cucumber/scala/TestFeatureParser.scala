package io.cucumber.scala

import java.io.{ByteArrayInputStream, InputStream}
import java.net.URI
import java.nio.charset.StandardCharsets
import java.util.UUID
import java.util.function.Supplier

import io.cucumber.core.feature.{FeatureIdentifier, FeatureParser}
import io.cucumber.core.gherkin.Feature
import io.cucumber.core.resource.Resource

object TestFeatureParser {

  def parse(source: String): Feature = {
    parse("file:test.feature", source)
  }

  def parse(uri: String, source: String): Feature = {
    parse(FeatureIdentifier.parse(uri), source)
  }

  def parse(uri: URI, source: String): Feature = {
    val supplier: Supplier[UUID] = () => UUID.randomUUID()

    new FeatureParser(supplier).parseResource(new Resource {
      override def getUri: URI = uri

      override def getInputStream: InputStream = new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8))
    }).orElse(null)
  }

}
