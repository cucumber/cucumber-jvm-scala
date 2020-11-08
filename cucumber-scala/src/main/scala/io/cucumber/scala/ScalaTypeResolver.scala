package io.cucumber.scala

import java.lang.reflect.Type

import io.cucumber.core.backend.TypeResolver

class ScalaTypeResolver(val `type`: Type) extends TypeResolver {

  override def resolve(): Type = {
    // No fancy logic needed
    `type`
  }

}
