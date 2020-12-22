package io.cucumber.scala

import scala.quoted._

// TODO rename
case class MyType(me: Class[_], args: Seq[MyType] = Seq())

object Macros {

  inline def parameterTypes[T1](fn: T1 => Any): Seq[String] = {
    ${ getTypes[T1]() }
  }

  private def getTypes[T1]()(using Type[T1], Quotes): Expr[Seq[String]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1]))
  }

  private def getTypesFromRepr(using q: Quotes)(reprs: Seq[q.reflect.TypeRepr]): Expr[Seq[String]] = {

    import q.reflect._

    def getMyType(tpr: TypeRepr): MyType = {
      val output: MyType = tpr.classSymbol match {
        case Some(clazz) =>
          val typeParameters: Seq[MyType] = tpr match {
            case a: AppliedType => a.args.map(at => getMyType(at))
            case _ => Seq()
          }
          MyType(Class.forName(clazz.fullName), typeParameters)
        case None =>
          report.error(s"Unable to handle the type ${tpr.show}, please open an issue at https://github.com/cucumber/cucumber-jvm-scala")
          throw new Exception(s"Error when applying macro")
      }
      output
    }

    Expr(reprs.map(i => getMyType(i).toString))
  }

}
