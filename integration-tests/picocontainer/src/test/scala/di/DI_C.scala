package di

import io.cucumber.scala.{EN, ScalaDsl}

class DI_C(a: DI_A, b: DI_B) extends ScalaDsl with EN {

  private var combination: String = _

  When("""a step defined in class DI-C uses them both""") { () =>
    combination = a.input + b.input
  }

  Then("""both values are combined into {string}""") { (expected: String) =>
    assert(combination == expected)
  }

}
