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
      apply(() => f)
    }

    def apply(fun: Fun0): Unit = {
      register() {
        case Nil => fun.f()
        case _   =>
          throw new IncorrectStepDefinitionException()
      }
    }

    /*
     * Generated apply1 to apply22 below
     */
    def apply[T1](f: (T1) => Any)(implicit t1: Stepable[T1]): Unit = {
      register(t1) {
        case List(a1: AnyRef) =>
          f(a1.asInstanceOf[T1])
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2](
        f: (T1, T2) => Any
    )(implicit t1: Stepable[T1], t2: Stepable[T2]): Unit = {
      register(t1, t2) {
        case List(a1: AnyRef, a2: AnyRef) =>
          f(a1.asInstanceOf[T1], a2.asInstanceOf[T2])
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3](
        f: (T1, T2, T3) => Any
    )(implicit t1: Stepable[T1], t2: Stepable[T2], t3: Stepable[T3]): Unit = {
      register(t1, t2, t3) {
        case List(a1: AnyRef, a2: AnyRef, a3: AnyRef) =>
          f(a1.asInstanceOf[T1], a2.asInstanceOf[T2], a3.asInstanceOf[T3])
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3, T4](f: (T1, T2, T3, T4) => Any)(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4]
    ): Unit = {
      register(t1, t2, t3, t4) {
        case List(a1: AnyRef, a2: AnyRef, a3: AnyRef, a4: AnyRef) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3, T4, T5](f: (T1, T2, T3, T4, T5) => Any)(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5]
    ): Unit = {
      register(t1, t2, t3, t4, t5) {
        case List(a1: AnyRef, a2: AnyRef, a3: AnyRef, a4: AnyRef, a5: AnyRef) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3, T4, T5, T6](
        f: (T1, T2, T3, T4, T5, T6) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6]
    ): Unit = {
      register(t1, t2, t3, t4, t5, t6) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3, T4, T5, T6, T7](
        f: (T1, T2, T3, T4, T5, T6, T7) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7]
    ): Unit = {
      register(t1, t2, t3, t4, t5, t6, t7) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8](
        f: (T1, T2, T3, T4, T5, T6, T7, T8) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8]
    ): Unit = {
      register(t1, t2, t3, t4, t5, t6, t7, t8) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](
        f: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9]
    ): Unit = {
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](
        f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9],
        t10: Stepable[T10]
    ): Unit = {
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef,
              a10: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](
        f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9],
        t10: Stepable[T10],
        t11: Stepable[T11]
    ): Unit = {
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef,
              a10: AnyRef,
              a11: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10],
            a11.asInstanceOf[T11]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](
        f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9],
        t10: Stepable[T10],
        t11: Stepable[T11],
        t12: Stepable[T12]
    ): Unit = {
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef,
              a10: AnyRef,
              a11: AnyRef,
              a12: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10],
            a11.asInstanceOf[T11],
            a12.asInstanceOf[T12]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](
        f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9],
        t10: Stepable[T10],
        t11: Stepable[T11],
        t12: Stepable[T12],
        t13: Stepable[T13]
    ): Unit = {
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef,
              a10: AnyRef,
              a11: AnyRef,
              a12: AnyRef,
              a13: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10],
            a11.asInstanceOf[T11],
            a12.asInstanceOf[T12],
            a13.asInstanceOf[T13]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](
        f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9],
        t10: Stepable[T10],
        t11: Stepable[T11],
        t12: Stepable[T12],
        t13: Stepable[T13],
        t14: Stepable[T14]
    ): Unit = {
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef,
              a10: AnyRef,
              a11: AnyRef,
              a12: AnyRef,
              a13: AnyRef,
              a14: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10],
            a11.asInstanceOf[T11],
            a12.asInstanceOf[T12],
            a13.asInstanceOf[T13],
            a14.asInstanceOf[T14]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](
        f: (
            T1,
            T2,
            T3,
            T4,
            T5,
            T6,
            T7,
            T8,
            T9,
            T10,
            T11,
            T12,
            T13,
            T14,
            T15
        ) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9],
        t10: Stepable[T10],
        t11: Stepable[T11],
        t12: Stepable[T12],
        t13: Stepable[T13],
        t14: Stepable[T14],
        t15: Stepable[T15]
    ): Unit = {
      register(
        t1,
        t2,
        t3,
        t4,
        t5,
        t6,
        t7,
        t8,
        t9,
        t10,
        t11,
        t12,
        t13,
        t14,
        t15
      ) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef,
              a10: AnyRef,
              a11: AnyRef,
              a12: AnyRef,
              a13: AnyRef,
              a14: AnyRef,
              a15: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10],
            a11.asInstanceOf[T11],
            a12.asInstanceOf[T12],
            a13.asInstanceOf[T13],
            a14.asInstanceOf[T14],
            a15.asInstanceOf[T15]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10,
        T11,
        T12,
        T13,
        T14,
        T15,
        T16
    ](
        f: (
            T1,
            T2,
            T3,
            T4,
            T5,
            T6,
            T7,
            T8,
            T9,
            T10,
            T11,
            T12,
            T13,
            T14,
            T15,
            T16
        ) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9],
        t10: Stepable[T10],
        t11: Stepable[T11],
        t12: Stepable[T12],
        t13: Stepable[T13],
        t14: Stepable[T14],
        t15: Stepable[T15],
        t16: Stepable[T16]
    ): Unit = {
      register(
        t1,
        t2,
        t3,
        t4,
        t5,
        t6,
        t7,
        t8,
        t9,
        t10,
        t11,
        t12,
        t13,
        t14,
        t15,
        t16
      ) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef,
              a10: AnyRef,
              a11: AnyRef,
              a12: AnyRef,
              a13: AnyRef,
              a14: AnyRef,
              a15: AnyRef,
              a16: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10],
            a11.asInstanceOf[T11],
            a12.asInstanceOf[T12],
            a13.asInstanceOf[T13],
            a14.asInstanceOf[T14],
            a15.asInstanceOf[T15],
            a16.asInstanceOf[T16]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10,
        T11,
        T12,
        T13,
        T14,
        T15,
        T16,
        T17
    ](
        f: (
            T1,
            T2,
            T3,
            T4,
            T5,
            T6,
            T7,
            T8,
            T9,
            T10,
            T11,
            T12,
            T13,
            T14,
            T15,
            T16,
            T17
        ) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9],
        t10: Stepable[T10],
        t11: Stepable[T11],
        t12: Stepable[T12],
        t13: Stepable[T13],
        t14: Stepable[T14],
        t15: Stepable[T15],
        t16: Stepable[T16],
        t17: Stepable[T17]
    ): Unit = {
      register(
        t1,
        t2,
        t3,
        t4,
        t5,
        t6,
        t7,
        t8,
        t9,
        t10,
        t11,
        t12,
        t13,
        t14,
        t15,
        t16,
        t17
      ) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef,
              a10: AnyRef,
              a11: AnyRef,
              a12: AnyRef,
              a13: AnyRef,
              a14: AnyRef,
              a15: AnyRef,
              a16: AnyRef,
              a17: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10],
            a11.asInstanceOf[T11],
            a12.asInstanceOf[T12],
            a13.asInstanceOf[T13],
            a14.asInstanceOf[T14],
            a15.asInstanceOf[T15],
            a16.asInstanceOf[T16],
            a17.asInstanceOf[T17]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10,
        T11,
        T12,
        T13,
        T14,
        T15,
        T16,
        T17,
        T18
    ](
        f: (
            T1,
            T2,
            T3,
            T4,
            T5,
            T6,
            T7,
            T8,
            T9,
            T10,
            T11,
            T12,
            T13,
            T14,
            T15,
            T16,
            T17,
            T18
        ) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9],
        t10: Stepable[T10],
        t11: Stepable[T11],
        t12: Stepable[T12],
        t13: Stepable[T13],
        t14: Stepable[T14],
        t15: Stepable[T15],
        t16: Stepable[T16],
        t17: Stepable[T17],
        t18: Stepable[T18]
    ): Unit = {
      register(
        t1,
        t2,
        t3,
        t4,
        t5,
        t6,
        t7,
        t8,
        t9,
        t10,
        t11,
        t12,
        t13,
        t14,
        t15,
        t16,
        t17,
        t18
      ) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef,
              a10: AnyRef,
              a11: AnyRef,
              a12: AnyRef,
              a13: AnyRef,
              a14: AnyRef,
              a15: AnyRef,
              a16: AnyRef,
              a17: AnyRef,
              a18: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10],
            a11.asInstanceOf[T11],
            a12.asInstanceOf[T12],
            a13.asInstanceOf[T13],
            a14.asInstanceOf[T14],
            a15.asInstanceOf[T15],
            a16.asInstanceOf[T16],
            a17.asInstanceOf[T17],
            a18.asInstanceOf[T18]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10,
        T11,
        T12,
        T13,
        T14,
        T15,
        T16,
        T17,
        T18,
        T19
    ](
        f: (
            T1,
            T2,
            T3,
            T4,
            T5,
            T6,
            T7,
            T8,
            T9,
            T10,
            T11,
            T12,
            T13,
            T14,
            T15,
            T16,
            T17,
            T18,
            T19
        ) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9],
        t10: Stepable[T10],
        t11: Stepable[T11],
        t12: Stepable[T12],
        t13: Stepable[T13],
        t14: Stepable[T14],
        t15: Stepable[T15],
        t16: Stepable[T16],
        t17: Stepable[T17],
        t18: Stepable[T18],
        t19: Stepable[T19]
    ): Unit = {
      register(
        t1,
        t2,
        t3,
        t4,
        t5,
        t6,
        t7,
        t8,
        t9,
        t10,
        t11,
        t12,
        t13,
        t14,
        t15,
        t16,
        t17,
        t18,
        t19
      ) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef,
              a10: AnyRef,
              a11: AnyRef,
              a12: AnyRef,
              a13: AnyRef,
              a14: AnyRef,
              a15: AnyRef,
              a16: AnyRef,
              a17: AnyRef,
              a18: AnyRef,
              a19: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10],
            a11.asInstanceOf[T11],
            a12.asInstanceOf[T12],
            a13.asInstanceOf[T13],
            a14.asInstanceOf[T14],
            a15.asInstanceOf[T15],
            a16.asInstanceOf[T16],
            a17.asInstanceOf[T17],
            a18.asInstanceOf[T18],
            a19.asInstanceOf[T19]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10,
        T11,
        T12,
        T13,
        T14,
        T15,
        T16,
        T17,
        T18,
        T19,
        T20
    ](
        f: (
            T1,
            T2,
            T3,
            T4,
            T5,
            T6,
            T7,
            T8,
            T9,
            T10,
            T11,
            T12,
            T13,
            T14,
            T15,
            T16,
            T17,
            T18,
            T19,
            T20
        ) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9],
        t10: Stepable[T10],
        t11: Stepable[T11],
        t12: Stepable[T12],
        t13: Stepable[T13],
        t14: Stepable[T14],
        t15: Stepable[T15],
        t16: Stepable[T16],
        t17: Stepable[T17],
        t18: Stepable[T18],
        t19: Stepable[T19],
        t20: Stepable[T20]
    ): Unit = {
      register(
        t1,
        t2,
        t3,
        t4,
        t5,
        t6,
        t7,
        t8,
        t9,
        t10,
        t11,
        t12,
        t13,
        t14,
        t15,
        t16,
        t17,
        t18,
        t19,
        t20
      ) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef,
              a10: AnyRef,
              a11: AnyRef,
              a12: AnyRef,
              a13: AnyRef,
              a14: AnyRef,
              a15: AnyRef,
              a16: AnyRef,
              a17: AnyRef,
              a18: AnyRef,
              a19: AnyRef,
              a20: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10],
            a11.asInstanceOf[T11],
            a12.asInstanceOf[T12],
            a13.asInstanceOf[T13],
            a14.asInstanceOf[T14],
            a15.asInstanceOf[T15],
            a16.asInstanceOf[T16],
            a17.asInstanceOf[T17],
            a18.asInstanceOf[T18],
            a19.asInstanceOf[T19],
            a20.asInstanceOf[T20]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10,
        T11,
        T12,
        T13,
        T14,
        T15,
        T16,
        T17,
        T18,
        T19,
        T20,
        T21
    ](
        f: (
            T1,
            T2,
            T3,
            T4,
            T5,
            T6,
            T7,
            T8,
            T9,
            T10,
            T11,
            T12,
            T13,
            T14,
            T15,
            T16,
            T17,
            T18,
            T19,
            T20,
            T21
        ) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9],
        t10: Stepable[T10],
        t11: Stepable[T11],
        t12: Stepable[T12],
        t13: Stepable[T13],
        t14: Stepable[T14],
        t15: Stepable[T15],
        t16: Stepable[T16],
        t17: Stepable[T17],
        t18: Stepable[T18],
        t19: Stepable[T19],
        t20: Stepable[T20],
        t21: Stepable[T21]
    ): Unit = {
      register(
        t1,
        t2,
        t3,
        t4,
        t5,
        t6,
        t7,
        t8,
        t9,
        t10,
        t11,
        t12,
        t13,
        t14,
        t15,
        t16,
        t17,
        t18,
        t19,
        t20,
        t21
      ) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef,
              a10: AnyRef,
              a11: AnyRef,
              a12: AnyRef,
              a13: AnyRef,
              a14: AnyRef,
              a15: AnyRef,
              a16: AnyRef,
              a17: AnyRef,
              a18: AnyRef,
              a19: AnyRef,
              a20: AnyRef,
              a21: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10],
            a11.asInstanceOf[T11],
            a12.asInstanceOf[T12],
            a13.asInstanceOf[T13],
            a14.asInstanceOf[T14],
            a15.asInstanceOf[T15],
            a16.asInstanceOf[T16],
            a17.asInstanceOf[T17],
            a18.asInstanceOf[T18],
            a19.asInstanceOf[T19],
            a20.asInstanceOf[T20],
            a21.asInstanceOf[T21]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[
        T1,
        T2,
        T3,
        T4,
        T5,
        T6,
        T7,
        T8,
        T9,
        T10,
        T11,
        T12,
        T13,
        T14,
        T15,
        T16,
        T17,
        T18,
        T19,
        T20,
        T21,
        T22
    ](
        f: (
            T1,
            T2,
            T3,
            T4,
            T5,
            T6,
            T7,
            T8,
            T9,
            T10,
            T11,
            T12,
            T13,
            T14,
            T15,
            T16,
            T17,
            T18,
            T19,
            T20,
            T21,
            T22
        ) => Any
    )(implicit
        t1: Stepable[T1],
        t2: Stepable[T2],
        t3: Stepable[T3],
        t4: Stepable[T4],
        t5: Stepable[T5],
        t6: Stepable[T6],
        t7: Stepable[T7],
        t8: Stepable[T8],
        t9: Stepable[T9],
        t10: Stepable[T10],
        t11: Stepable[T11],
        t12: Stepable[T12],
        t13: Stepable[T13],
        t14: Stepable[T14],
        t15: Stepable[T15],
        t16: Stepable[T16],
        t17: Stepable[T17],
        t18: Stepable[T18],
        t19: Stepable[T19],
        t20: Stepable[T20],
        t21: Stepable[T21],
        t22: Stepable[T22]
    ): Unit = {
      register(
        t1,
        t2,
        t3,
        t4,
        t5,
        t6,
        t7,
        t8,
        t9,
        t10,
        t11,
        t12,
        t13,
        t14,
        t15,
        t16,
        t17,
        t18,
        t19,
        t20,
        t21,
        t22
      ) {
        case List(
              a1: AnyRef,
              a2: AnyRef,
              a3: AnyRef,
              a4: AnyRef,
              a5: AnyRef,
              a6: AnyRef,
              a7: AnyRef,
              a8: AnyRef,
              a9: AnyRef,
              a10: AnyRef,
              a11: AnyRef,
              a12: AnyRef,
              a13: AnyRef,
              a14: AnyRef,
              a15: AnyRef,
              a16: AnyRef,
              a17: AnyRef,
              a18: AnyRef,
              a19: AnyRef,
              a20: AnyRef,
              a21: AnyRef,
              a22: AnyRef
            ) =>
          f(
            a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10],
            a11.asInstanceOf[T11],
            a12.asInstanceOf[T12],
            a13.asInstanceOf[T13],
            a14.asInstanceOf[T14],
            a15.asInstanceOf[T15],
            a16.asInstanceOf[T16],
            a17.asInstanceOf[T17],
            a18.asInstanceOf[T18],
            a19.asInstanceOf[T19],
            a20.asInstanceOf[T20],
            a21.asInstanceOf[T21],
            a22.asInstanceOf[T22]
          )
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    private def register(
        tags: Stepable[_]*
    )(pf: PartialFunction[List[Any], Any]): Unit = {
      val types = tags.map(_.asJavaType)
      registry.registerStep(
        ScalaStepDetails(Utils.frame(self), name, regex, types, pf)
      )
    }

  }

}
