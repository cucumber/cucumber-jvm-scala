package glueclasses

import io.cucumber.scala.{EN, ScalaDsl}

class SelectedSteps extends ScalaDsl with EN {

  Given("a step defined in a selected class") {
    assert(true)
  }

  Given("a step that is defined twice but only in one selected class") {
    assert(true)
  }

}

object SelectedObjectSteps extends ScalaDsl with EN {

  Given("a step defined in a selected object") {
    assert(true)
  }

}

// Not selected: if it was loaded, the duplicate step definition would fail the scenario
class NotSelectedSteps extends ScalaDsl with EN {

  Given("a step that is defined twice but only in one selected class") {
    assert(true)
  }

}
