package io.cucumber.scalatest

object FilenameCompatibleNames {

  private[scalatest] def createName(
      name: String,
      uniqueSuffix: Option[Int],
      useFilenameCompatibleNames: Boolean
  ): String = {
    uniqueSuffix match {
      case Some(suffix) =>
        createName(name + " #" + suffix + "", useFilenameCompatibleNames)
      case None => createName(name, useFilenameCompatibleNames)
    }
  }

  private[scalatest] def createName(
      name: String,
      useFilenameCompatibleNames: Boolean
  ): String = {
    if (useFilenameCompatibleNames) {
      makeNameFilenameCompatible(name)
    } else {
      name
    }
  }

  private def makeNameFilenameCompatible(name: String): String = {
    name.replaceAll("[^A-Za-z0-9_]", "_")
  }

  private[scalatest] def uniqueSuffix[V, K](
      groupedByName: Map[K, Seq[V]],
      pickle: V,
      nameOf: V => K
  ): Option[Int] = {
    val withSameName = groupedByName.get(nameOf.apply(pickle))
    withSameName match {
      case Some(x) if x.size > 1 => Some(x.indexOf(pickle) + 1)
      case _                     => None
    }
  }

}
