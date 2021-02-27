package io.cucumber.scala

import io.github.gaeljw.typetrees.TypeTreeTag
import io.github.gaeljw.typetrees.TypeTreeTagMacros.typeTreeTag

import scala.annotation.targetName

private[scala] trait StepDsl extends BaseScalaDsl {
  self =>

  final class Step(name: String) {
    def apply(regex: String): StepBody = new StepBody(name, regex)
  }

  final class StepBody(name: String, regex: String) {

    inline def apply(f: => Any): Unit = {
      register() {
        case Nil => f
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    @targetName("apply_function0")
    inline def apply(fun: () => Any): Unit = {
      register() {
        case Nil => fun.apply()
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    /*
     * Generated apply1 to apply22 below
     */
    inline def apply[T1](f: (T1) => Any): Unit = {
      val t1: TypeTreeTag = typeTreeTag[T1]
      register(t1) {
        case List(a1:AnyRef) =>
          f(a1.asInstanceOf[T1])
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    inline def apply[T1, T2](f: (T1, T2) => Any): Unit = {
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      register(t1, t2) {
        case List(a1:AnyRef, a2:AnyRef) =>
          f(a1.asInstanceOf[T1],
            a2.asInstanceOf[T2])
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    inline def apply[T1, T2, T3](f: (T1, T2, T3) => Any): Unit = {
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      register(t1, t2, t3) {
        case List(a1:AnyRef, a2:AnyRef, a3:AnyRef) =>
          f(a1.asInstanceOf[T1],
            a2.asInstanceOf[T2],
            a3.asInstanceOf[T3])
        case _ =>
          throw new IncorrectStepDefinitionException()
      }
    }

    inline def apply[T1, T2, T3, T4](f: (T1, T2, T3, T4) => Any): Unit = {
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      register(t1, t2, t3, t4) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      register(t1, t2, t3, t4, t5) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      register(t1, t2, t3, t4, t5, t6) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      register(t1, t2, t3, t4, t5, t6, t7) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      register(t1, t2, t3, t4, t5, t6, t7, t8) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      val t10: TypeTreeTag = typeTreeTag[T10]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      val t10: TypeTreeTag = typeTreeTag[T10]
      val t11: TypeTreeTag = typeTreeTag[T11]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      val t10: TypeTreeTag = typeTreeTag[T10]
      val t11: TypeTreeTag = typeTreeTag[T11]
      val t12: TypeTreeTag = typeTreeTag[T12]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      val t10: TypeTreeTag = typeTreeTag[T10]
      val t11: TypeTreeTag = typeTreeTag[T11]
      val t12: TypeTreeTag = typeTreeTag[T12]
      val t13: TypeTreeTag = typeTreeTag[T13]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      val t10: TypeTreeTag = typeTreeTag[T10]
      val t11: TypeTreeTag = typeTreeTag[T11]
      val t12: TypeTreeTag = typeTreeTag[T12]
      val t13: TypeTreeTag = typeTreeTag[T13]
      val t14: TypeTreeTag = typeTreeTag[T14]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      val t10: TypeTreeTag = typeTreeTag[T10]
      val t11: TypeTreeTag = typeTreeTag[T11]
      val t12: TypeTreeTag = typeTreeTag[T12]
      val t13: TypeTreeTag = typeTreeTag[T13]
      val t14: TypeTreeTag = typeTreeTag[T14]
      val t15: TypeTreeTag = typeTreeTag[T15]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      val t10: TypeTreeTag = typeTreeTag[T10]
      val t11: TypeTreeTag = typeTreeTag[T11]
      val t12: TypeTreeTag = typeTreeTag[T12]
      val t13: TypeTreeTag = typeTreeTag[T13]
      val t14: TypeTreeTag = typeTreeTag[T14]
      val t15: TypeTreeTag = typeTreeTag[T15]
      val t16: TypeTreeTag = typeTreeTag[T16]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      val t10: TypeTreeTag = typeTreeTag[T10]
      val t11: TypeTreeTag = typeTreeTag[T11]
      val t12: TypeTreeTag = typeTreeTag[T12]
      val t13: TypeTreeTag = typeTreeTag[T13]
      val t14: TypeTreeTag = typeTreeTag[T14]
      val t15: TypeTreeTag = typeTreeTag[T15]
      val t16: TypeTreeTag = typeTreeTag[T16]
      val t17: TypeTreeTag = typeTreeTag[T17]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      val t10: TypeTreeTag = typeTreeTag[T10]
      val t11: TypeTreeTag = typeTreeTag[T11]
      val t12: TypeTreeTag = typeTreeTag[T12]
      val t13: TypeTreeTag = typeTreeTag[T13]
      val t14: TypeTreeTag = typeTreeTag[T14]
      val t15: TypeTreeTag = typeTreeTag[T15]
      val t16: TypeTreeTag = typeTreeTag[T16]
      val t17: TypeTreeTag = typeTreeTag[T17]
      val t18: TypeTreeTag = typeTreeTag[T18]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      val t10: TypeTreeTag = typeTreeTag[T10]
      val t11: TypeTreeTag = typeTreeTag[T11]
      val t12: TypeTreeTag = typeTreeTag[T12]
      val t13: TypeTreeTag = typeTreeTag[T13]
      val t14: TypeTreeTag = typeTreeTag[T14]
      val t15: TypeTreeTag = typeTreeTag[T15]
      val t16: TypeTreeTag = typeTreeTag[T16]
      val t17: TypeTreeTag = typeTreeTag[T17]
      val t18: TypeTreeTag = typeTreeTag[T18]
      val t19: TypeTreeTag = typeTreeTag[T19]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      val t10: TypeTreeTag = typeTreeTag[T10]
      val t11: TypeTreeTag = typeTreeTag[T11]
      val t12: TypeTreeTag = typeTreeTag[T12]
      val t13: TypeTreeTag = typeTreeTag[T13]
      val t14: TypeTreeTag = typeTreeTag[T14]
      val t15: TypeTreeTag = typeTreeTag[T15]
      val t16: TypeTreeTag = typeTreeTag[T16]
      val t17: TypeTreeTag = typeTreeTag[T17]
      val t18: TypeTreeTag = typeTreeTag[T18]
      val t19: TypeTreeTag = typeTreeTag[T19]
      val t20: TypeTreeTag = typeTreeTag[T20]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      val t10: TypeTreeTag = typeTreeTag[T10]
      val t11: TypeTreeTag = typeTreeTag[T11]
      val t12: TypeTreeTag = typeTreeTag[T12]
      val t13: TypeTreeTag = typeTreeTag[T13]
      val t14: TypeTreeTag = typeTreeTag[T14]
      val t15: TypeTreeTag = typeTreeTag[T15]
      val t16: TypeTreeTag = typeTreeTag[T16]
      val t17: TypeTreeTag = typeTreeTag[T17]
      val t18: TypeTreeTag = typeTreeTag[T18]
      val t19: TypeTreeTag = typeTreeTag[T19]
      val t20: TypeTreeTag = typeTreeTag[T20]
      val t21: TypeTreeTag = typeTreeTag[T21]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21) {
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
      val t1: TypeTreeTag = typeTreeTag[T1]
      val t2: TypeTreeTag = typeTreeTag[T2]
      val t3: TypeTreeTag = typeTreeTag[T3]
      val t4: TypeTreeTag = typeTreeTag[T4]
      val t5: TypeTreeTag = typeTreeTag[T5]
      val t6: TypeTreeTag = typeTreeTag[T6]
      val t7: TypeTreeTag = typeTreeTag[T7]
      val t8: TypeTreeTag = typeTreeTag[T8]
      val t9: TypeTreeTag = typeTreeTag[T9]
      val t10: TypeTreeTag = typeTreeTag[T10]
      val t11: TypeTreeTag = typeTreeTag[T11]
      val t12: TypeTreeTag = typeTreeTag[T12]
      val t13: TypeTreeTag = typeTreeTag[T13]
      val t14: TypeTreeTag = typeTreeTag[T14]
      val t15: TypeTreeTag = typeTreeTag[T15]
      val t16: TypeTreeTag = typeTreeTag[T16]
      val t17: TypeTreeTag = typeTreeTag[T17]
      val t18: TypeTreeTag = typeTreeTag[T18]
      val t19: TypeTreeTag = typeTreeTag[T19]
      val t20: TypeTreeTag = typeTreeTag[T20]
      val t21: TypeTreeTag = typeTreeTag[T21]
      val t22: TypeTreeTag = typeTreeTag[T22]
      register(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22) {
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
        tags: TypeTreeTag*
    )(pf: PartialFunction[List[Any], Any]): Unit = {
      val types = tags.map(ScalaTypeHelper.asJavaType)
      registry.registerStep(
        ScalaStepDetails(Utils.frame(self), name, regex, types, pf)
      )
    }

  }

}
