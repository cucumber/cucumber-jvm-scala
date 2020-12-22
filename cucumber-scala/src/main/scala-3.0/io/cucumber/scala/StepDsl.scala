package io.cucumber.scala

private[scala] trait StepDsl extends BaseScalaDsl {
  self =>

  final class Step(name: String) {
    def apply(regex: String): StepBody = new StepBody(name, regex)
  }

  final class Fun0(val f: Function0[Any])

  object Fun0 {

    implicit def function0AsFun0(f: Function0[Any]): Fun0 = new Fun0(f)

  }

  final class StepBody(name: String, regex: String) {

    /*
     * apply0 needs to be able to handle both calls by value and reference.
     *
     * Call by value:
     *
     *    And("^multipy the things$") {
     *      z = x * y;
     *    }
     *
     * Call by reference:
     *
     *    And("^multipy the things$") { ()=>
     *      z = x * y;
     *    }
     *
     * Call by value has the signature => Unit while call by reference has the signature () => Any
     *
     * Due to type erasure both would end up with the signature () => Unit.
     *
     * Fun0 and the implicit conversion lets us work around this.
     *
      **/
    def apply(f: => Unit): Unit = {
      val fun0 = () => f
      apply(fun0)
    }

    def apply(fun: Fun0): Unit = {
      register(Seq()) {
        case Nil => fun.f()
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    /*
     * Generated apply1 to apply22 below
     */
    inline def apply[T1](f: (T1) => Any): Unit = {
      val types = Macros.parameterTypes(f)
      register(types) {
        case List(a1: AnyRef) =>
          f(a1.asInstanceOf[T1])
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    // TODO other methods

    private def register(
        types: Seq[String]
    )(pf: PartialFunction[List[Any], Any]): Unit = {
      // TODO map types
      println(types)
      registry.registerStep(
        ScalaStepDetails(Utils.frame(self), name, regex, Seq(), pf)
      )
    }

  }

}
