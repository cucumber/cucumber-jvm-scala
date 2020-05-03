# Upgrading from 4.x to 5.x

If you are using Cucumber Scala 4.7.x and want to upgrade to 5.x, please note there are some major changes in addition to the Cucumber upgrade itself.

Starting from version 5.x, Cucumber Scala will try to be as close as possible to the Cucumber Java implementation while remaining Scala oriented.
This means that package names, parameters order, internal code... will be more consistent with the Java implementation.

See also the [CHANGELOG](https://github.com/cucumber/cucumber-jvm/blob/master/CHANGELOG.md) from Cucumber Core.

## Packages

All Cucumber Scala classes are now under `io.cucumber.scala` package instead of `cucumber.api.scala`.

## Hooks

The `Before`, `BeforeStep`, `After` and `AfterStep` definitions have slightly changed:
- to apply only to scenarios with some tags, the `String*` parameter is replaced by a single tag expression of type `String`.
This changes comes from Cucumber itself.
- if providing both an _order_ and a _tag expression_, the _order_ is now the second parameter instead of the first.
This is more consistent with the Java implementation.

For instance, the following code:

```scala
Before(1, "@tag1", "@tag2") { _ =>
  // Do Something    
}
```

Is replaced by:

```scala
Before("@tag1 or @tag2", 1) { _ =>
  // Do Something    
}
```

### Other changes

As a side effect the following usage no longer compiles:
```scala
Before() { _ =>
  // Do something
}
```
It can be replaced with:
```scala
Before { _ =>
  // Do something
}
```

See also the [Hooks documentation](hooks.md).

## Transformers

If you are using transformers defined with `TypeRegistryConfigurer`, please note that this is now deprecated in Cucumber Core.

The recommended way to define transformers is to define them in glue code. See [Transformers](./transformers.md).

## Under the hood

### Instantiate glue classes per scenario

Before Cucumber Scala 5.x, glue classes (classes extending `ScalaDsl`) were instantiated only once for a test suite.

This means that if you wanted to keep state between steps of your scenarios, you had to make sure the state was not shared to other scenarios by using hooks or manual checks.

Starting from Cucumber Scala 5.x, **each scenario creates new glue class instances**.

You should not notice any change unless you rely on state kept between scenarios in your glue classes.
Please note that this is not the proper way to keep a state.
You might want to use an `object` for this purpose.  
