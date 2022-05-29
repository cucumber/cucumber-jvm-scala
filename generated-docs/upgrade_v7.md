# Upgrading from 6.x to 7.x

Upgrading from v6 should be straightforward.
Prior to upgrading to v7.0.0 upgrade to latest v6.x and stop using all deprecated features.
Some features will log a deprecation warning.

See also:
- [Cucumber Scala CHANGELOG](../CHANGELOG.md)

## Scala 3 support

This release brings Scala 3 support.

### Syntactic changes

If you use Scala 3, you might need to change slightly some of your glue code:
- parenthesis are now necessary even around a single-argument step or hook definition
```scala mdoc:compile-only
import io.cucumber.scala.{EN, ScalaDsl}

class Steps extends ScalaDsl with EN {

    // Won't compile anymore
    Given("Something {}") { str: String =>
        // ...
    }
    
    // Instead use:
    Given("Something {}") { (str: String) =>
      // ...
    }

}
```
- hooks must explicitly return `Unit` (most of the time you already had compile errors with such statements in Scala 2.x as well)
```scala mdoc:compile-only
import io.cucumber.scala.{EN, ScalaDsl}

class Steps extends ScalaDsl with EN {

    Before {
      // ... // Some code not retuning Unit
      () // Explicit Unit
    }

}
```

### Other changes

The line numbers provided in reports might slightly change
from start of a step definition to end of a step definition in some cases.
In case of errors, these line numbers should be more accurate than before.
