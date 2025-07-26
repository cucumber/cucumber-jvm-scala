package io.cucumber.scala

private[scala] object Utils {

  /** Return the stack frame to allow us to identify where in a step definition
    * file we are currently based
    */
  def frame(self: Any): StackTraceElement = {
    val frames = Thread.currentThread().getStackTrace
    val currentClass = self.getClass.getName
    // Note: the -1 check is here for Scala < 2.13 and objects
    findLast(frames)(f =>
      f.getClassName == currentClass && f.getLineNumber != -1
    ) match {
      case Some(stackFrame) => stackFrame
      case None             =>
        throw new IllegalStateException(
          s"Not able to find stack frame for $currentClass"
        )
    }
  }

  // Copied from Scala 2.13 library, not available in 2.12 nor in scala-collections-compat
  private def findLast[A](seq: Array[A])(p: A => Boolean): Option[A] = {
    val it = seq.reverseIterator
    while (it.hasNext) {
      val elem = it.next()
      if (p(elem)) return Some(elem)
    }
    None
  }

}
