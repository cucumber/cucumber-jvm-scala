# Hooks

Hooks are blocks of code that can run at various points in the Cucumber execution cycle.
They are typically used for setup and teardown of the environment before and after all/each scenario or step.

See the [reference documentation](https://docs.cucumber.io/docs/cucumber/api/#hooks).

## Static hooks

Static hooks run once before/after all scenarios.

**Note:** static hooks can only be defined inside `object`s (not classes).

### BeforeAll

`BeforeAll` hooks run once before all scenarios.

```scala
import io.cucumber.scala.{EN, ScalaDsl}

class Steps extends ScalaDsl with EN {

    BeforeAll {
      // Do something before all scenarios
      // Must return Unit
    }

}
```

### AfterAll

`AfterAll` hooks run once after all scenarios.

```scala
import io.cucumber.scala.{EN, ScalaDsl}

class Steps extends ScalaDsl with EN {

    AfterAll {
      // Do something after each scenario
      // Must return Unit
    }

}
```

## Scenario hooks

Scenario hooks run for every scenario.

### Before

`Before` hooks run before the first step of each scenario.

```scala
import io.cucumber.scala.{EN, ScalaDsl, Scenario}

class Steps extends ScalaDsl with EN {

    Before { scenario : Scenario =>
      // Do something before each scenario
      // Must return Unit
    }
    
    // Or:
    Before {
      // Do something before each scenario
      // Must return Unit
    }

}
```

### After

`After` hooks run after the last step of each scenario.

```scala
import io.cucumber.scala.{EN, ScalaDsl, Scenario}

class Steps extends ScalaDsl with EN {

    After { scenario : Scenario =>
      // Do something after each scenario
      // Must return Unit
    }
    
    // Or:
    After {
      // Do something after each scenario
      // Must return Unit
    }

}
```

## Step hooks

Step hooks invoked before and after a step.

### BeforeStep

```scala
import io.cucumber.scala.{EN, ScalaDsl, Scenario}

class Steps extends ScalaDsl with EN {

    BeforeStep { scenario : Scenario =>
      // Do something before step
      // Must return Unit
    }
    
    // Or:
    BeforeStep {
      // Do something before step
      // Must return Unit
    }

}
```

### AfterStep

```scala
import io.cucumber.scala.{EN, ScalaDsl, Scenario}

class Steps extends ScalaDsl with EN {

    AfterStep { scenario : Scenario =>
      // Do something after step
      // Must return Unit
    }
    
    // Or:
    AfterStep {
      // Do something after step
      // Must return Unit
    }

}
```

## Conditional hooks

Hooks can be conditionally selected for execution based on the tags of the scenario.

```scala
import io.cucumber.scala.{EN, ScalaDsl}

class Steps extends ScalaDsl with EN {

    Before("@browser and not @headless") { 
      // Do something before each scenario with tag @browser but not @headless
      // Must return Unit
    }

}
```

Note: this cannot be applied to static hooks (`BeforeAll`/`AfterAll`).

## Order

You can define an order between multiple hooks.

```scala
import io.cucumber.scala.{EN, ScalaDsl}

class Steps extends ScalaDsl with EN {

    Before(10) { 
      // Do something before each scenario
      // Must return Unit
    }
    
    Before(20) { 
      // Do something before each scenario
      // Must return Unit
    }

}
```

The **default order is 1000**.

## Conditional and order

You mix up conditional and order hooks with following syntax:
```scala
import io.cucumber.scala.{EN, ScalaDsl}

class Steps extends ScalaDsl with EN {

    Before("@browser and not @headless", 10) {
      // Do something before each scenario
      // Must return Unit
    }

}
```

Note: this cannot be applied to static hooks (`BeforeAll`/`AfterAll`).
