
/*
 * Generates the evil looking apply methods in StepDsl#StepBody for Function1 to Function22
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
 * Generates the evil looking apply methods in StepDsl#StepBody for Function1 to Function22
 * Scala 3
 */
for (i <- 1 to 22) {
  val ts = (1 to i).map("T".+).mkString(", ")
  val tagsDef = (1 to i).map(n => s"val t$n: TypeTreeTag = typeTreeTag[T$n]").mkString("\n")
  val tagsParam = (1 to i).map(n => s"t$n").mkString(", ")
  val listParams = (1 to i).map("a" + _ + ":AnyRef").mkString(", ")
  val pf =  (1 to i).map(n => "a" + n + ".asInstanceOf[T" + n + "]").mkString(",\n        ")

  println(s"""
    |inline def apply[$ts](f: ($ts) => Any): Unit = {
    |  $tagsDef
    |  register($tagsParam) {
    |    case List($listParams) =>
    |      f($pf)
    |    case _ =>
    |      throw new IncorrectStepDefinitionException()
    |  }
    |}""".stripMargin)
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