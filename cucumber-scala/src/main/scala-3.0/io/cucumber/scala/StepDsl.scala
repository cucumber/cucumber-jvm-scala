package io.cucumber.scala

import io.cucumber.scala.Macros.parameterTypes

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
    inline def apply(f: => Unit): Unit = {
      val fun0 = () => f
      apply(fun0)
    }

    inline def apply(fun: Fun0): Unit = {
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
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef) =>
          f(a1.asInstanceOf[T1])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2](f: (T1, T2) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef) =>
          f(a1.asInstanceOf[T1],
            a2.asInstanceOf[T2])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3](f: (T1, T2, T3) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef) =>
          f(a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4](f: (T1, T2, T3, T4) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef) =>
          f(a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5](f: (T1, T2, T3, T4, T5) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef) =>
          f(a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6](f: (T1, T2, T3, T4, T5, T6) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef) =>
          f(a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7](f: (T1, T2, T3, T4, T5, T6, T7) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef) =>
          f(a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8](f: (T1, T2, T3, T4, T5, T6, T7, T8) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef) =>
          f(a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef) =>
          f(a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef, a10:AnyRef) =>
          f(a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef, a10:AnyRef, a11:AnyRef) =>
          f(a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3],
            a4.asInstanceOf[T4],
            a5.asInstanceOf[T5],
            a6.asInstanceOf[T6],
            a7.asInstanceOf[T7],
            a8.asInstanceOf[T8],
            a9.asInstanceOf[T9],
            a10.asInstanceOf[T10],
            a11.asInstanceOf[T11])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef, a10:AnyRef, a11:AnyRef, a12:AnyRef) =>
          f(a1.asInstanceOf[T1],
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
            a12.asInstanceOf[T12])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef, a10:AnyRef, a11:AnyRef, a12:AnyRef, a13:AnyRef) =>
          f(a1.asInstanceOf[T1],
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
            a13.asInstanceOf[T13])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef, a10:AnyRef, a11:AnyRef, a12:AnyRef, a13:AnyRef, a14:AnyRef) =>
          f(a1.asInstanceOf[T1],
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
            a14.asInstanceOf[T14])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef, a10:AnyRef, a11:AnyRef, a12:AnyRef, a13:AnyRef, a14:AnyRef, a15:AnyRef) =>
          f(a1.asInstanceOf[T1],
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
            a15.asInstanceOf[T15])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef, a10:AnyRef, a11:AnyRef, a12:AnyRef, a13:AnyRef, a14:AnyRef, a15:AnyRef, a16:AnyRef) =>
          f(a1.asInstanceOf[T1],
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
            a16.asInstanceOf[T16])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef, a10:AnyRef, a11:AnyRef, a12:AnyRef, a13:AnyRef, a14:AnyRef, a15:AnyRef, a16:AnyRef, a17:AnyRef) =>
          f(a1.asInstanceOf[T1],
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
            a17.asInstanceOf[T17])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef, a10:AnyRef, a11:AnyRef, a12:AnyRef, a13:AnyRef, a14:AnyRef, a15:AnyRef, a16:AnyRef, a17:AnyRef, a18:AnyRef) =>
          f(a1.asInstanceOf[T1],
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
            a18.asInstanceOf[T18])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef, a10:AnyRef, a11:AnyRef, a12:AnyRef, a13:AnyRef, a14:AnyRef, a15:AnyRef, a16:AnyRef, a17:AnyRef, a18:AnyRef, a19:AnyRef) =>
          f(a1.asInstanceOf[T1],
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
            a19.asInstanceOf[T19])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef, a10:AnyRef, a11:AnyRef, a12:AnyRef, a13:AnyRef, a14:AnyRef, a15:AnyRef, a16:AnyRef, a17:AnyRef, a18:AnyRef, a19:AnyRef, a20:AnyRef) =>
          f(a1.asInstanceOf[T1],
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
            a20.asInstanceOf[T20])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef, a10:AnyRef, a11:AnyRef, a12:AnyRef, a13:AnyRef, a14:AnyRef, a15:AnyRef, a16:AnyRef, a17:AnyRef, a18:AnyRef, a19:AnyRef, a20:AnyRef, a21:AnyRef) =>
          f(a1.asInstanceOf[T1],
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
            a21.asInstanceOf[T21])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }


    inline def apply[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22](f: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22) => Any): Unit = {
      val types = parameterTypes(f)
      register(types) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef, a4:AnyRef, a5:AnyRef, a6:AnyRef, a7:AnyRef, a8:AnyRef, a9:AnyRef, a10:AnyRef, a11:AnyRef, a12:AnyRef, a13:AnyRef, a14:AnyRef, a15:AnyRef, a16:AnyRef, a17:AnyRef, a18:AnyRef, a19:AnyRef, a20:AnyRef, a21:AnyRef, a22:AnyRef) =>
          f(a1.asInstanceOf[T1],
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
            a22.asInstanceOf[T22])

        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

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
