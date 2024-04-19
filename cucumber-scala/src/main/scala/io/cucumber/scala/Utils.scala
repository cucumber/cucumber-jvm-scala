package io.cucumber.scala

private[scala] object Utils {

  /** Return the stack frame to allow us to identify where in a step definition
    * file we are currently based
    */
  def frame(self: Any): StackTraceElement = {
    val frames = Thread.currentThread().getStackTrace
    val currentClass = self.getClass.getName
    // Note: the -1 check is here for Scala < 2.13 and objects
    frames.reverse
      .find(f => f.getClassName == currentClass && f.getLineNumber != -1)
      .get
  }

}
