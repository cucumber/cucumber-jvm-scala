package io.cucumber.scala

import java.util.function.Supplier

import io.cucumber.core.backend.{
  Backend,
  BackendProviderService,
  Container,
  Lookup
}

class ScalaBackendProviderService extends BackendProviderService {

  override def create(
      lookup: Lookup,
      container: Container,
      classLoaderSupplier: Supplier[ClassLoader]
  ): Backend = {
    new ScalaBackend(lookup, container, classLoaderSupplier)
  }

}
