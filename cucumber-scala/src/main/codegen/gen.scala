
/*
 * Generates the evil looking apply methods in StepDsl#StepBody for Function1 to Function22
 * Scala 3
 */
for (i <- 1 to 22) {
  val ts = (1 to i).map("T".+).mkString(", ")
  val implicits = (1 to i).map(n => s"t$n: Stepable[T$n]").mkString(", ")
  val implicitsParams = (1 to i).map(n => s"t$n").mkString(", ")
  val listParams = (1 to i).map("a" + _ + ":AnyRef").mkString(", ")
  val pf =  (1 to i).map(n => "a" + n + ".asInstanceOf[T" + n + "]").mkString(",\n        ")

  println(s"""
    |def apply[$ts](f: ($ts) => Any)(using $implicits): Unit = {
    |  register($implicitsParams) {
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

/*
 * Generates the Stepable implicit methods
 */
for (i <- (1 to 9).reverse) {

  val underscores = (1 to i).map(_ => "_").mkString(", ")
  val types = (1 to i).map(j => s"X$j").mkString(", ")
  val typesStepable = (1 to i).map(j => s"X$j: Stepable").mkString(", ")
  val typeArgs = (1 to i).map(j => s"implicitly[Stepable[X$j]].asJavaType").mkString(", ")

  val template =
    s"""
       |implicit def stepable$i[T[$underscores], $typesStepable](implicit ct: ClassTag[T[$types]]): Stepable[T[$types]] =
       |    new Stepable[T[$types]] {
       |      def asJavaType: JavaType =
       |        new ScalaParameterizedType(
       |          ct.runtimeClass,
       |          Array(
       |            $typeArgs
       |          )
       |        )
       |    }""".stripMargin

  println(template)
}
