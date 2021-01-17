
/*
 * Generates the evil looking apply methods in ScalaDsl#StepBody for Function1 to Function22
 */
for (i <- 1 to 22) {
  val ts = (1 to i).map("T".+).mkString(", ")
  val f = "(" + ts + ") => Any"
  val p1 = "def apply[" + ts + "](f: " + f + ")"
  val p2 = "(implicit " + (1 to i).map(n => "m" + n + ":Manifest[T" + n + "]").mkString(", ") + ")"
  val register = "\n  register(" +(1 to i).map(n => "m" + n).mkString(", ")  + ") {\n"
  val pf = "    case List(" + (1 to i).map("a" + _ + ":AnyRef").mkString(", ") + ") => \n      f(" + (1 to i).map(n => "a" + n + ".asInstanceOf[T" + n + "]").mkString(",\n        ") + ")\n"
  val otherwise = "    case _ =>\n          throw new IncorrectStepDefinitionException()\n"
  val closeRegister = "  }\n}"

  println(p1 + p2 + ": Unit = { " + register + pf + otherwise + closeRegister + "\n")
}

/*
 * Generates the evil looking apply methods in ScalaDsl#StepBody for Function1 to Function22
 * For Scala 3
 */
for (i <- 1 to 22) {
  val ts = (1 to i).map(n => s"T$n").mkString(", ")
  val ltt = (1 to i).map(n => s"LTT[T$n]").mkString(", ")
  val pf = "    case List(" + (1 to i).map("a" + _ + ":AnyRef").mkString(", ") + ") => \n      f(" + (1 to i).map(n => "a" + n + ".asInstanceOf[T" + n + "]").mkString(",\n        ") + ")\n"

  val template =
    s"""
      |inline def apply[$ts](f: ($ts) => Any): Unit = {
      |  val types = Seq($ltt)
      |  register(types) {
      |    $pf
      |    case _ =>
      |      throw new IncorrectStepDefinitionException()
      |  }
      |}
      |""".stripMargin

  println(template)
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

/*
 * Generates the macros parameterTypes.
 * For Scala 3
 */
for (i <- 1 to 22) {

  val ts = (1 to i).map("T".+).mkString(", ")

  val template =
    s"""
      |inline def parameterTypes[$ts](): Seq[MyType] = {
      |  $${ getTypes[$ts]() }
      |}
      |""".stripMargin

  println(template)
}

/*
 * Generates the macros method getTypes.
 * For Scala 3
 */
for (i <- 1 to 22) {
  val ts = (1 to i).map(n => s"T$n").mkString(", ")
  val usings = (1 to i).map(n => s"Type[T$n]").mkString(", ")
  val reprs = (1 to i).map(n => s"TypeRepr.of[T$n]").mkString(", ")

  val template =
    s"""
      |private def getTypes[$ts]()(using $usings, Quotes): Expr[Seq[MyType]] = {
      |  import quotes.reflect._
      |  getTypesFromRepr(Seq($reprs))
      |}
      |""".stripMargin

  println(template)
}
