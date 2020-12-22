package io.cucumber.scala

import scala.reflect.ClassTag

private[scala] trait ParameterTypeDsl extends BaseScalaDsl {

  /** Register parameter type.
    *
    * @param name  used as the type name in typed expressions
    * @param regex expression to match
    * @see https://cucumber.io/docs/cucumber/cucumber-expressions
    */
  def ParameterType(name: String, regex: String) =
    new ParameterTypeBody(name, regex)

  final class ParameterTypeBody(name: String, regex: String) {

    // Important: use the piece of code in the file gen.scala to generate these methods easily

    def apply[R](f: (String) => R)(implicit tag: ClassTag[R]): Unit = {
      register { case List(p1) =>
        f(p1)
      }
    }

    def apply[R](f: (String, String) => R)(implicit tag: ClassTag[R]): Unit = {
      register { case List(p1, p2) =>
        f(p1, p2)
      }
    }

    def apply[R](
        f: (String, String, String) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register { case List(p1, p2, p3) =>
        f(p1, p2, p3)
      }
    }

    def apply[R](
        f: (String, String, String, String) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register { case List(p1, p2, p3, p4) =>
        f(p1, p2, p3, p4)
      }
    }

    def apply[R](
        f: (String, String, String, String, String) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register { case List(p1, p2, p3, p4, p5) =>
        f(p1, p2, p3, p4, p5)
      }
    }

    def apply[R](
        f: (String, String, String, String, String, String) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register { case List(p1, p2, p3, p4, p5, p6) =>
        f(p1, p2, p3, p4, p5, p6)
      }
    }

    def apply[R](
        f: (String, String, String, String, String, String, String) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register { case List(p1, p2, p3, p4, p5, p6, p7) =>
        f(p1, p2, p3, p4, p5, p6, p7)
      }
    }

    def apply[R](
        f: (String, String, String, String, String, String, String, String) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register { case List(p1, p2, p3, p4, p5, p6, p7, p8) =>
        f(p1, p2, p3, p4, p5, p6, p7, p8)
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register { case List(p1, p2, p3, p4, p5, p6, p7, p8, p9) =>
        f(p1, p2, p3, p4, p5, p6, p7, p8, p9)
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register { case List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10) =>
        f(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register { case List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11) =>
        f(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11)
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register { case List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12) =>
        f(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12)
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register {
        case List(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13) =>
          f(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13)
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register {
        case List(
              p1,
              p2,
              p3,
              p4,
              p5,
              p6,
              p7,
              p8,
              p9,
              p10,
              p11,
              p12,
              p13,
              p14
            ) =>
          f(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14)
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register {
        case List(
              p1,
              p2,
              p3,
              p4,
              p5,
              p6,
              p7,
              p8,
              p9,
              p10,
              p11,
              p12,
              p13,
              p14,
              p15
            ) =>
          f(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15)
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register {
        case List(
              p1,
              p2,
              p3,
              p4,
              p5,
              p6,
              p7,
              p8,
              p9,
              p10,
              p11,
              p12,
              p13,
              p14,
              p15,
              p16
            ) =>
          f(
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            p8,
            p9,
            p10,
            p11,
            p12,
            p13,
            p14,
            p15,
            p16
          )
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register {
        case List(
              p1,
              p2,
              p3,
              p4,
              p5,
              p6,
              p7,
              p8,
              p9,
              p10,
              p11,
              p12,
              p13,
              p14,
              p15,
              p16,
              p17
            ) =>
          f(
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            p8,
            p9,
            p10,
            p11,
            p12,
            p13,
            p14,
            p15,
            p16,
            p17
          )
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register {
        case List(
              p1,
              p2,
              p3,
              p4,
              p5,
              p6,
              p7,
              p8,
              p9,
              p10,
              p11,
              p12,
              p13,
              p14,
              p15,
              p16,
              p17,
              p18
            ) =>
          f(
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            p8,
            p9,
            p10,
            p11,
            p12,
            p13,
            p14,
            p15,
            p16,
            p17,
            p18
          )
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register {
        case List(
              p1,
              p2,
              p3,
              p4,
              p5,
              p6,
              p7,
              p8,
              p9,
              p10,
              p11,
              p12,
              p13,
              p14,
              p15,
              p16,
              p17,
              p18,
              p19
            ) =>
          f(
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            p8,
            p9,
            p10,
            p11,
            p12,
            p13,
            p14,
            p15,
            p16,
            p17,
            p18,
            p19
          )
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register {
        case List(
              p1,
              p2,
              p3,
              p4,
              p5,
              p6,
              p7,
              p8,
              p9,
              p10,
              p11,
              p12,
              p13,
              p14,
              p15,
              p16,
              p17,
              p18,
              p19,
              p20
            ) =>
          f(
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            p8,
            p9,
            p10,
            p11,
            p12,
            p13,
            p14,
            p15,
            p16,
            p17,
            p18,
            p19,
            p20
          )
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register {
        case List(
              p1,
              p2,
              p3,
              p4,
              p5,
              p6,
              p7,
              p8,
              p9,
              p10,
              p11,
              p12,
              p13,
              p14,
              p15,
              p16,
              p17,
              p18,
              p19,
              p20,
              p21
            ) =>
          f(
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            p8,
            p9,
            p10,
            p11,
            p12,
            p13,
            p14,
            p15,
            p16,
            p17,
            p18,
            p19,
            p20,
            p21
          )
      }
    }

    def apply[R](
        f: (
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String,
            String
        ) => R
    )(implicit tag: ClassTag[R]): Unit = {
      register {
        case List(
              p1,
              p2,
              p3,
              p4,
              p5,
              p6,
              p7,
              p8,
              p9,
              p10,
              p11,
              p12,
              p13,
              p14,
              p15,
              p16,
              p17,
              p18,
              p19,
              p20,
              p21,
              p22
            ) =>
          f(
            p1,
            p2,
            p3,
            p4,
            p5,
            p6,
            p7,
            p8,
            p9,
            p10,
            p11,
            p12,
            p13,
            p14,
            p15,
            p16,
            p17,
            p18,
            p19,
            p20,
            p21,
            p22
          )
      }
    }

    private def register[R](
        pf: PartialFunction[List[String], R]
    )(implicit tag: ClassTag[R]): Unit = {
      registry.registerParameterType(
        ScalaParameterTypeDetails[R](name, regex, pf, tag)
      )
    }

  }

}
