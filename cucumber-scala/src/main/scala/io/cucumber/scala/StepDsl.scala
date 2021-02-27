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
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    /*
     * Generated apply1 to apply22 below
     */
    def apply[T1](f: (T1) => Any)(implicit m1: Manifest[T1]): Unit = {
      register(m1) {
        case List(a1: AnyRef) =>
          f(a1.asInstanceOf[T1])
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2](
        f: (T1, T2) => Any
    )(implicit m1: Manifest[T1], m2: Manifest[T2]): Unit = {
      register(m1, m2) {
        case List(a1: AnyRef, a2: AnyRef) =>
          f(a1.asInstanceOf[T1], a2.asInstanceOf[T2])
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3](
        f: (T1, T2, T3) => Any
    )(implicit m1: Manifest[T1], m2: Manifest[T2], m3: Manifest[T3]): Unit = {
      register(m1, m2, m3) {
        case List(a1: AnyRef, a2: AnyRef, a3: AnyRef) =>
          f(a1.asInstanceOf[T1], a2.asInstanceOf[T2], a3.asInstanceOf[T3])
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    def apply[T1, T2, T3, T4](f: (T1, T2, T3, T4) => Any)(implicit
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4]
    ): Unit = {
      register(m1, m2, m3, m4) {
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5]
    ): Unit = {
      register(m1, m2, m3, m4, m5) {
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6]
    ): Unit = {
      register(m1, m2, m3, m4, m5, m6) {
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7]
    ): Unit = {
      register(m1, m2, m3, m4, m5, m6, m7) {
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8]
    ): Unit = {
      register(m1, m2, m3, m4, m5, m6, m7, m8) {
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9]
    ): Unit = {
      register(m1, m2, m3, m4, m5, m6, m7, m8, m9) {
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9],
        m10: Manifest[T10]
    ): Unit = {
      register(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10) {
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9],
        m10: Manifest[T10],
        m11: Manifest[T11]
    ): Unit = {
      register(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11) {
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9],
        m10: Manifest[T10],
        m11: Manifest[T11],
        m12: Manifest[T12]
    ): Unit = {
      register(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12) {
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9],
        m10: Manifest[T10],
        m11: Manifest[T11],
        m12: Manifest[T12],
        m13: Manifest[T13]
    ): Unit = {
      register(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13) {
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9],
        m10: Manifest[T10],
        m11: Manifest[T11],
        m12: Manifest[T12],
        m13: Manifest[T13],
        m14: Manifest[T14]
    ): Unit = {
      register(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14) {
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9],
        m10: Manifest[T10],
        m11: Manifest[T11],
        m12: Manifest[T12],
        m13: Manifest[T13],
        m14: Manifest[T14],
        m15: Manifest[T15]
    ): Unit = {
      register(
        m1,
        m2,
        m3,
        m4,
        m5,
        m6,
        m7,
        m8,
        m9,
        m10,
        m11,
        m12,
        m13,
        m14,
        m15
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9],
        m10: Manifest[T10],
        m11: Manifest[T11],
        m12: Manifest[T12],
        m13: Manifest[T13],
        m14: Manifest[T14],
        m15: Manifest[T15],
        m16: Manifest[T16]
    ): Unit = {
      register(
        m1,
        m2,
        m3,
        m4,
        m5,
        m6,
        m7,
        m8,
        m9,
        m10,
        m11,
        m12,
        m13,
        m14,
        m15,
        m16
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9],
        m10: Manifest[T10],
        m11: Manifest[T11],
        m12: Manifest[T12],
        m13: Manifest[T13],
        m14: Manifest[T14],
        m15: Manifest[T15],
        m16: Manifest[T16],
        m17: Manifest[T17]
    ): Unit = {
      register(
        m1,
        m2,
        m3,
        m4,
        m5,
        m6,
        m7,
        m8,
        m9,
        m10,
        m11,
        m12,
        m13,
        m14,
        m15,
        m16,
        m17
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9],
        m10: Manifest[T10],
        m11: Manifest[T11],
        m12: Manifest[T12],
        m13: Manifest[T13],
        m14: Manifest[T14],
        m15: Manifest[T15],
        m16: Manifest[T16],
        m17: Manifest[T17],
        m18: Manifest[T18]
    ): Unit = {
      register(
        m1,
        m2,
        m3,
        m4,
        m5,
        m6,
        m7,
        m8,
        m9,
        m10,
        m11,
        m12,
        m13,
        m14,
        m15,
        m16,
        m17,
        m18
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9],
        m10: Manifest[T10],
        m11: Manifest[T11],
        m12: Manifest[T12],
        m13: Manifest[T13],
        m14: Manifest[T14],
        m15: Manifest[T15],
        m16: Manifest[T16],
        m17: Manifest[T17],
        m18: Manifest[T18],
        m19: Manifest[T19]
    ): Unit = {
      register(
        m1,
        m2,
        m3,
        m4,
        m5,
        m6,
        m7,
        m8,
        m9,
        m10,
        m11,
        m12,
        m13,
        m14,
        m15,
        m16,
        m17,
        m18,
        m19
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9],
        m10: Manifest[T10],
        m11: Manifest[T11],
        m12: Manifest[T12],
        m13: Manifest[T13],
        m14: Manifest[T14],
        m15: Manifest[T15],
        m16: Manifest[T16],
        m17: Manifest[T17],
        m18: Manifest[T18],
        m19: Manifest[T19],
        m20: Manifest[T20]
    ): Unit = {
      register(
        m1,
        m2,
        m3,
        m4,
        m5,
        m6,
        m7,
        m8,
        m9,
        m10,
        m11,
        m12,
        m13,
        m14,
        m15,
        m16,
        m17,
        m18,
        m19,
        m20
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9],
        m10: Manifest[T10],
        m11: Manifest[T11],
        m12: Manifest[T12],
        m13: Manifest[T13],
        m14: Manifest[T14],
        m15: Manifest[T15],
        m16: Manifest[T16],
        m17: Manifest[T17],
        m18: Manifest[T18],
        m19: Manifest[T19],
        m20: Manifest[T20],
        m21: Manifest[T21]
    ): Unit = {
      register(
        m1,
        m2,
        m3,
        m4,
        m5,
        m6,
        m7,
        m8,
        m9,
        m10,
        m11,
        m12,
        m13,
        m14,
        m15,
        m16,
        m17,
        m18,
        m19,
        m20,
        m21
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
        m1: Manifest[T1],
        m2: Manifest[T2],
        m3: Manifest[T3],
        m4: Manifest[T4],
        m5: Manifest[T5],
        m6: Manifest[T6],
        m7: Manifest[T7],
        m8: Manifest[T8],
        m9: Manifest[T9],
        m10: Manifest[T10],
        m11: Manifest[T11],
        m12: Manifest[T12],
        m13: Manifest[T13],
        m14: Manifest[T14],
        m15: Manifest[T15],
        m16: Manifest[T16],
        m17: Manifest[T17],
        m18: Manifest[T18],
        m19: Manifest[T19],
        m20: Manifest[T20],
        m21: Manifest[T21],
        m22: Manifest[T22]
    ): Unit = {
      register(
        m1,
        m2,
        m3,
        m4,
        m5,
        m6,
        m7,
        m8,
        m9,
        m10,
        m11,
        m12,
        m13,
        m14,
        m15,
        m16,
        m17,
        m18,
        m19,
        m20,
        m21,
        m22
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
        manifests: Manifest[_ <: Any]*
    )(pf: PartialFunction[List[Any], Any]): Unit = {
      registry.registerStep(
        ScalaStepDetails(Utils.frame(self), name, regex, manifests, pf)
      )
    }

  }

}
