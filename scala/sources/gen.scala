
/*
 * Generates the evil looking apply methods in ScalaDsl#StepBody for Function1 to Function22
 */
for (i <- 1 to 22) {
  val ts = (1 to i).map("T" +).mkString(", ")
  val f = "(" + ts + ") => Any"
  val p1 = "def apply[" + ts + "](f: " + f + ")"
  val p2 = "(implicit " + (1 to i).map(n => "m" + n + ":Manifest[T" + n + "]").mkString(", ") + ")"
  val register = "\n  register(" +(1 to i).map(n => "m" + n).mkString(", ")  + ") {\n"
  val pf = "    case List(" + (1 to i).map("a" + _ + ":AnyRef").mkString(", ") + ") => \n      f(" + (1 to i).map(n => "a" + n + ".asInstanceOf[T" + n + "]").mkString(",\n        ") + ")"
  val closeRegister = "\n  }\n}"

  println(p1 + p2 + " = { " + register + pf + closeRegister + "\n")
}

/*
 * Generates the apply methods in ParameterTypeDsl for Function1 to Function22
 */
for (i <- 1 to 22) {
  // String, String, ..., String
  val types = (1 to i).map(_ => "String").mkString(", ")
  // p1, p2, ..., p22
  val args = (1 to i).map(j => s"p$j").mkString(", ")

  val template =
    s"""
      |def apply[R](f: ($types) => R)(implicit tag: ClassTag[R]): Unit = {
      |  register {
      |    case List($args) =>
      |       f($args)
      |  }
      |}
      |""".stripMargin

  println(template)
}