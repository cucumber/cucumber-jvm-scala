# Scala implementation details

This page covers some details about the Cucumber Scala implementation.

## Running a Cucumber test

### Backend

From Cucumber core perspective, the entrypoint of a Cucumber implementation is what is called "backend".

The `BackendServiceLoader` core service looks for a `BackendProviderService` implementation.
Ours is defined in the class `ScalaBackendProviderService`.

The implementing class also has to be registered as a "Java Service" in the `META-INF/services/io.cucumber.core.backend.BackendProviderService` file (in the `resources` folder).

### Loading the glue

When a Cucumber test starts, a Cucumber Runner starts and a `ScalaBackend` instance is created.
The `ScalaBackend` instance will be used for running all the scenarios which are part of the test (defined by the _features path_ and the _glue path_).

The first thing the Runner does is to "load the glue", that is find all the hooks and step definitions and register them.
This is handled by the `ScalaBackend#loadGlue()` method.

#### Scala implementation

In the Cucumber Scala implementation, loading the glue code means:
- finding all the **classes** inheriting `io.cucumber.scala.ScalaDsl` in the _glue path_, and for each:
  - add it to the `Container` instance provided by Cucumber Core
- finding all the **objects** singletons instances inheriting `io.cucumber.scala.ScalaDsl` in the _glue path_ and for each:
  - extract the hooks and step definitions from it
  - add the definitions to the `Glue` instance provided by Cucumber Core, as NOT `ScenarioScoped`

Ideally all the glue code should be instantiated further (see next section), this is why we register classes (actually a list of `Class`) to the Container.
But this cannot work for objects because they are by definitions singletons and already instantiated way before Cucumber.
Thus, objects are not registered in the Container and their lifecycle is out of Cucumber scope.

### Running a scenario

For each scenario, the `buildWorld()` method of the backend is called.
This is where the glue code should be initialized.

#### Scala implementation

For each **class** identified when loading the glue:
- an instance is created by the `Lookup` provided by Cucumber Core
- hooks and steps definitions are extracted from it
- definitions are added to the `Glue` instance provided by Cucumber Core, as `ScenarioScoped`

Being `ScenarioScoped` ensure instances are flushed at the end of the scenario and recreated for the next one.

## Scala DSL

The Scala DSL is made in a way that any class instance or object extending it contains what we call a **registry**:
a list of the hooks and step definitions it contains.
This is the purpose of `ScalaDslRegistry`.

The registry is populated when the class instance or the object is created.
Unlike other implementations there is no need to use annotations or reflection here.
This is actually **similar to the Java8/Lambda implementation**.
