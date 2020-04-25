# Hooks

Hooks are blocks of code that can run at various points in the Cucumber execution cycle.
They are typically used for setup and teardown of the environment before and after each scenario.

See the [reference documentation](https://docs.cucumber.io/docs/cucumber/api/#hooks).

## Scenario hooks

Scenario hooks run for every scenario.

### Before

`Before` hooks run before the first step of each scenario.

```scala
Before {
  // Do something before each scenario
}
```

### After

`After` hooks run after the last step of each scenario.

```scala
After {
  // Do something after each scenario
}
```

## Step hooks

Step hooks invoked before and after a step.

### BeforeStep

```scala
BeforeStep { 
  // Do something before step
}
```

### AfterStep

```scala
AfterStep { 
  // Do something after step
}
```

## Scenario parameter

The scenario is available as parameter in all hooks.

You can use it like this:
```scala
Before { scenario : Scenario =>
  // Do something with the scenario
}
```

## Conditional hooks

Hooks can be conditionally selected for execution based on the tags of the scenario.

```scala
Before("@browser and not @headless") { 
  // Do something before each scenario with tag @browser but not @headless
}
```

## Order

You can define an order between multiple hooks.

```scala
Before(10) { 
  // Do something before each scenario
}

Before(20) { 
  // Do something before each scenario
}
```

The **default order is 1000**.

## Conditional and order

You mix up conditional and order hooks with following syntax:
```scala
Before("@browser and not @headless", 10) {
  // Do something before each scenario
}
```
