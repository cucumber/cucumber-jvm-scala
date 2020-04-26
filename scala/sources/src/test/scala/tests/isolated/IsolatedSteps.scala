package tests.isolated

import java.util.{List => JList}
import io.cucumber.scala.{EN, ScalaDsl}

import scala.collection.JavaConverters._

class IsolatedSteps extends ScalaDsl with EN {

  var mutableValues: List[Int] = List()

  Given("""I set the list of values to""") { (values: JList[Int]) =>
    // Obviously this is silly, as we keep the previous value but this is exactly what we want to test
    // Isolated scenarios should ensure that the previous value is not kept
    mutableValues = mutableValues ++ values.asScala.toList
  }

  Given("""I multiply by {int}""") { (mult: Int) =>
    mutableValues = mutableValues.map(i => i * mult)
  }

  Then("""the list of values is""") { (values: JList[Int]) =>
    assert(mutableValues == values.asScala.toList)
  }

}
