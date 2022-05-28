# Basic Usage

## Glue code

To use Cucumber Scala, all your glue code (steps or hooks) has to be defined in **classes** extending both the `ScalaDsl` trait and a language trait.

For instance, to use the English flavour:
```scala
import io.cucumber.scala.{EN, ScalaDsl}

class MyGlueClass extends ScalaDsl with EN {

  // Here some steps or hooks definitions

  Given("""I have {int} cucumbers in my belly"""){ (cucumberCount: Int) =>
    // Do something    
  }

}
```

Cucumber will automatically load all the glue code defined in classes available in the "glue path" (more details in the Run documentation) inheriting `ScalaDsl`.

### Using traits

You can define glue code in **traits** as well and have a **class** extending the traits you need.

For instance, you can do things like this:
```scala
import io.cucumber.scala.{EN, ScalaDsl}

trait StepsForThis extends ScalaDsl with EN {
  // Glue code
}

trait StepsForThat extends ScalaDsl with EN {
  // Glue code
}

class MyStepDefinitions extends StepsForThis with StepsForThat {
}
```

**Note:** using traits can help you split your tests in different groups and provide some steps only to some tests.

### Using objects

You can also define glue code in **objects**.

Be aware that by definition objects are singleton and if your glue code is stateful you will probably have "state conflicts"
between your scenarios if you use shared variables from objects.

### Using dependency-injection

Starting with cucumber-scala 8.4, it is possible to use DI modules in order to share state between steps.

You can for instance have the following definition:
```scala
import io.cucumber.scala.{EN, ScalaDsl}

class A extends ScalaDsl with EN {

  var input: String = _

  Given("""a step defined in class A with arg {string}""") { (arg: String) =>
    input = arg
  }

}

class B(a: A) extends ScalaDsl with EN {

  When("""a step defined in class B uses A""") { () =>
    // Do something with a.input
    println(a.input)
  }

}
```

To make it work, you only need a Cucumber DI module to be added as a dependency of your project
(like `cucumber-picocontainer`, or `cucumber-guice`, or any other provided by Cucumber).

## Running Cucumber tests

See also the Running Cucumber for Java [documentation](https://docs.cucumber.io/docs/cucumber/api/#running-cucumber).

Add the `cucumber-junit` dependency to your project.

Then create a runner class like this:
```scala
import io.cucumber.junit.{Cucumber, CucumberOptions}
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions()
class RunCucumberTest
```

You can define several options like:
- the "glue path" (default to current package): packages in which to look for glue code
- the "features path" (default to current folder): folder in which to look for features file
