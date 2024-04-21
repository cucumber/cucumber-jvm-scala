package io.cucumber.scalatest

import io.cucumber.core.backend.ObjectFactory

/** This object factory does nothing. It is solely needed for marking purposes.
  */
final class NoObjectFactory extends ObjectFactory {

  override def addClass(glueClass: Class[_]): Boolean = false

  override def getInstance[T](glueClass: Class[T]): T = null.asInstanceOf[T]

  override def start(): Unit = {}

  override def stop(): Unit = {}

}
