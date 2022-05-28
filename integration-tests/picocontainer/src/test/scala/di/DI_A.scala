package di

import io.cucumber.scala.{EN, ScalaDsl}

class DI_A extends ScalaDsl with EN {

  var input: String = _

  Given("""a step defined in class DI-A with arg {string}""") { (arg: String) =>
    input = arg
  }

}
