package io.cucumber.scala

import scala.quoted._

// TODO rename
case class MyType(me: String, args: List[MyType] = List())

object MyType {

  given ToExpr[MyType] with {
    def apply(myType: MyType)(using Quotes): Expr[MyType] = {
      '{
        MyType (
          ${Expr (myType.me)},
          ${Expr.ofList (myType.args.map (a => apply (a) ) )}
        )
      }
    }
  }

}

object Macros {

  inline def parameterTypes[T1](): Seq[MyType] = {
    ${ getTypes[T1]() }
  }

  inline def parameterTypes[T1, T2](): Seq[MyType] = {
    ${ getTypes[T1, T2]() }
  }

  inline def parameterTypes[T1, T2, T3](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3]() }
  }

  inline def parameterTypes[T1, T2, T3, T4](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13]() }
  }


  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21]() }
  }

  inline def parameterTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](): Seq[MyType] = {
    ${ getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22]() }
  }

  private def getTypes[T1]()(using Type[T1], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1]))
  }

  private def getTypes[T1, T2]()(using Type[T1], Type[T2], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2]))
  }

  private def getTypes[T1, T2, T3]()(using Type[T1], Type[T2], Type[T3], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3]))
  }

  private def getTypes[T1, T2, T3, T4]()(using Type[T1], Type[T2], Type[T3], Type[T4], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4]))
  }

  private def getTypes[T1, T2, T3, T4, T5]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Type[T10], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9], TypeRepr.of[T10]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Type[T10], Type[T11], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9], TypeRepr.of[T10], TypeRepr.of[T11]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Type[T10], Type[T11], Type[T12], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9], TypeRepr.of[T10], TypeRepr.of[T11], TypeRepr.of[T12]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Type[T10], Type[T11], Type[T12], Type[T13], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9], TypeRepr.of[T10], TypeRepr.of[T11], TypeRepr.of[T12], TypeRepr.of[T13]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Type[T10], Type[T11], Type[T12], Type[T13], Type[T14], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9], TypeRepr.of[T10], TypeRepr.of[T11], TypeRepr.of[T12], TypeRepr.of[T13], TypeRepr.of[T14]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Type[T10], Type[T11], Type[T12], Type[T13], Type[T14], Type[T15], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9], TypeRepr.of[T10], TypeRepr.of[T11], TypeRepr.of[T12], TypeRepr.of[T13], TypeRepr.of[T14], TypeRepr.of[T15]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Type[T10], Type[T11], Type[T12], Type[T13], Type[T14], Type[T15], Type[T16], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9], TypeRepr.of[T10], TypeRepr.of[T11], TypeRepr.of[T12], TypeRepr.of[T13], TypeRepr.of[T14], TypeRepr.of[T15], TypeRepr.of[T16]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Type[T10], Type[T11], Type[T12], Type[T13], Type[T14], Type[T15], Type[T16], Type[T17], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9], TypeRepr.of[T10], TypeRepr.of[T11], TypeRepr.of[T12], TypeRepr.of[T13], TypeRepr.of[T14], TypeRepr.of[T15], TypeRepr.of[T16], TypeRepr.of[T17]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Type[T10], Type[T11], Type[T12], Type[T13], Type[T14], Type[T15], Type[T16], Type[T17], Type[T18], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9], TypeRepr.of[T10], TypeRepr.of[T11], TypeRepr.of[T12], TypeRepr.of[T13], TypeRepr.of[T14], TypeRepr.of[T15], TypeRepr.of[T16], TypeRepr.of[T17], TypeRepr.of[T18]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Type[T10], Type[T11], Type[T12], Type[T13], Type[T14], Type[T15], Type[T16], Type[T17], Type[T18], Type[T19], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9], TypeRepr.of[T10], TypeRepr.of[T11], TypeRepr.of[T12], TypeRepr.of[T13], TypeRepr.of[T14], TypeRepr.of[T15], TypeRepr.of[T16], TypeRepr.of[T17], TypeRepr.of[T18], TypeRepr.of[T19]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Type[T10], Type[T11], Type[T12], Type[T13], Type[T14], Type[T15], Type[T16], Type[T17], Type[T18], Type[T19], Type[T20], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9], TypeRepr.of[T10], TypeRepr.of[T11], TypeRepr.of[T12], TypeRepr.of[T13], TypeRepr.of[T14], TypeRepr.of[T15], TypeRepr.of[T16], TypeRepr.of[T17], TypeRepr.of[T18], TypeRepr.of[T19], TypeRepr.of[T20]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Type[T10], Type[T11], Type[T12], Type[T13], Type[T14], Type[T15], Type[T16], Type[T17], Type[T18], Type[T19], Type[T20], Type[T21], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9], TypeRepr.of[T10], TypeRepr.of[T11], TypeRepr.of[T12], TypeRepr.of[T13], TypeRepr.of[T14], TypeRepr.of[T15], TypeRepr.of[T16], TypeRepr.of[T17], TypeRepr.of[T18], TypeRepr.of[T19], TypeRepr.of[T20], TypeRepr.of[T21]))
  }

  private def getTypes[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22]()(using Type[T1], Type[T2], Type[T3], Type[T4], Type[T5], Type[T6], Type[T7], Type[T8], Type[T9], Type[T10], Type[T11], Type[T12], Type[T13], Type[T14], Type[T15], Type[T16], Type[T17], Type[T18], Type[T19], Type[T20], Type[T21], Type[T22], Quotes): Expr[Seq[MyType]] = {
    import quotes.reflect._
    getTypesFromRepr(Seq(TypeRepr.of[T1], TypeRepr.of[T2], TypeRepr.of[T3], TypeRepr.of[T4], TypeRepr.of[T5], TypeRepr.of[T6], TypeRepr.of[T7], TypeRepr.of[T8], TypeRepr.of[T9], TypeRepr.of[T10], TypeRepr.of[T11], TypeRepr.of[T12], TypeRepr.of[T13], TypeRepr.of[T14], TypeRepr.of[T15], TypeRepr.of[T16], TypeRepr.of[T17], TypeRepr.of[T18], TypeRepr.of[T19], TypeRepr.of[T20], TypeRepr.of[T21], TypeRepr.of[T22]))
  }

  private def getTypesFromRepr(using q: Quotes)(reprs: Seq[q.reflect.TypeRepr]): Expr[Seq[MyType]] = {

    import q.reflect._

    def getMyType(tpr: TypeRepr): MyType = {
      tpr.classSymbol match {
        case Some(classSymbol) =>
          val typeParameters: List[MyType] = tpr match {
            case a: AppliedType =>
              a.args.map(at => getMyType(at))
            case _ => List()
          }
          // Temporary ugly hack for inner class in objects (not ok for class in class)
          val className = classSymbol.fullName.replace("$.", "$")
          MyType(className, typeParameters)
        case None =>
          report.error(s"Unable to handle the type ${tpr.show}, please open an issue at https://github.com/cucumber/cucumber-jvm-scala")
          throw new Exception(s"Error when applying macro")
      }
    }

    Expr(reprs.map(i => getMyType(i)))
  }

}
