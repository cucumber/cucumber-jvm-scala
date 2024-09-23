package io.cucumber.scalatest

import io.cucumber.core.eventbus.UuidGenerator

import java.util.UUID

/** This UUID generator does nothing. It is solely needed for marking purposes.
  */
final class NoUuidGenerator extends UuidGenerator {

  override def generateId: UUID = null

}
