package io.cucumber.scala

import io.cucumber.core.backend._
import io.cucumber.scala.steps.classes.{StepsA, StepsB, StepsC}
import io.cucumber.scala.steps.dependencyinjection.{Injected, Injector}
import io.cucumber.scala.steps.errors.incorrectclasshooks.IncorrectClassHooksDefinition
import io.cucumber.scala.steps.errors.staticclasshooks.StaticClassHooksDefinition
import io.cucumber.scala.steps.objects.StepsInObject
import io.cucumber.scala.steps.traits.StepsInTrait
import org.junit.Assert.{assertEquals, assertTrue, fail}
import org.junit.{Before, Test}
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito._

import java.net.URI
import java.util.function.Supplier
import scala.annotation.nowarn
import scala.jdk.CollectionConverters._
import scala.util.{Failure, Success, Try}

@nowarn
class ScalaBackendTest {

  private val fakeGlue: Glue = mock(classOf[Glue])
  private val fakeLookup: Lookup = mock(classOf[Lookup])
  private val fakeContainer: Container = mock(classOf[Container])

  private val classLoaderSupplier: Supplier[ClassLoader] = () =>
    Thread.currentThread().getContextClassLoader

  private var backend: ScalaBackend = _

  @Before
  def beforeEach(): Unit = {
    // Reset mocks
    reset(fakeGlue, fakeLookup, fakeContainer)

    // Mock the Lookup
    when(fakeLookup.getInstance(classOf[StepsA])).thenReturn(new StepsA())
    when(fakeLookup.getInstance(classOf[StepsB])).thenReturn(new StepsB())
    when(fakeLookup.getInstance(classOf[StepsC])).thenReturn(new StepsC())
    when(fakeLookup.getInstance(classOf[StepsInTrait]))
      .thenReturn(new StepsInTrait())
    when(fakeLookup.getInstance(classOf[IncorrectClassHooksDefinition]))
      .thenReturn(new IncorrectClassHooksDefinition())
    when(fakeLookup.getInstance(classOf[StaticClassHooksDefinition]))
      .thenReturn(new StaticClassHooksDefinition())
    when(fakeLookup.getInstance(classOf[Injected])).thenReturn(new Injected)
    when(fakeLookup.getInstance(classOf[Injector]))
      .thenReturn(new Injector(new Injected))

    // Create the instances
    backend = new ScalaBackend(fakeLookup, fakeContainer, classLoaderSupplier)
  }

  @Test
  def loadGlueAndBuildWorld_classes(): Unit = {
    // Load glue
    backend.loadGlue(
      fakeGlue,
      List(URI.create("classpath:io/cucumber/scala/steps/classes")).asJava
    )

    assertEquals(3, backend.scalaGlueClasses.size)
    assertTrue(
      backend.scalaGlueClasses.toSet == Set(
        classOf[StepsA],
        classOf[StepsB],
        classOf[StepsC]
      )
    )

    verify(fakeContainer, times(3)).addClass(any())
    verify(fakeContainer, times(1)).addClass(classOf[StepsA])
    verify(fakeContainer, times(1)).addClass(classOf[StepsB])
    verify(fakeContainer, times(1)).addClass(classOf[StepsC])

    verify(fakeGlue, never()).addStepDefinition(any())
    verify(fakeGlue, never()).addBeforeHook(any())
    verify(fakeGlue, never()).addBeforeStepHook(any())
    verify(fakeGlue, never()).addAfterHook(any())
    verify(fakeGlue, never()).addAfterStepHook(any())

    // Build world
    backend.buildWorld()

    verify(fakeLookup, times(3)).getInstance(any())
    verify(fakeLookup, times(1)).getInstance(classOf[StepsA])
    verify(fakeLookup, times(1)).getInstance(classOf[StepsB])
    verify(fakeLookup, times(1)).getInstance(classOf[StepsC])

    verify(fakeGlue, times(3)).addStepDefinition(any())
    verify(fakeGlue, times(1)).addBeforeHook(any())
    verify(fakeGlue, times(1)).addBeforeStepHook(any())
    verify(fakeGlue, times(1)).addAfterHook(any())
    verify(fakeGlue, times(1)).addAfterStepHook(any())

    // Building the world a second time should create new instances
    backend.disposeWorld()
    backend.buildWorld()

    verify(fakeLookup, times(6)).getInstance(any())
    verify(fakeLookup, times(2)).getInstance(classOf[StepsA])
    verify(fakeLookup, times(2)).getInstance(classOf[StepsB])
    verify(fakeLookup, times(2)).getInstance(classOf[StepsC])

    verify(fakeGlue, times(6)).addStepDefinition(any())
    verify(fakeGlue, times(2)).addBeforeHook(any())
    verify(fakeGlue, times(2)).addBeforeStepHook(any())
    verify(fakeGlue, times(2)).addAfterHook(any())
    verify(fakeGlue, times(2)).addAfterStepHook(any())
  }

  @Test
  def loadGlueAndBuildWorld_trait(): Unit = {
    // Load glue
    backend.loadGlue(
      fakeGlue,
      List(URI.create("classpath:io/cucumber/scala/steps/traits")).asJava
    )

    assertEquals(1, backend.scalaGlueClasses.size)
    assertTrue(backend.scalaGlueClasses.toSet == Set(classOf[StepsInTrait]))

    verify(fakeContainer, times(1)).addClass(any())
    verify(fakeContainer, times(1)).addClass(classOf[StepsInTrait])

    verify(fakeGlue, never()).addStepDefinition(any())
    verify(fakeGlue, never()).addBeforeHook(any())
    verify(fakeGlue, never()).addBeforeStepHook(any())
    verify(fakeGlue, never()).addAfterHook(any())
    verify(fakeGlue, never()).addAfterStepHook(any())

    // Build world
    backend.buildWorld()

    verify(fakeLookup, times(1)).getInstance(any())
    verify(fakeLookup, times(1)).getInstance(classOf[StepsInTrait])

    verify(fakeGlue, times(1)).addStepDefinition(any())
    verify(fakeGlue, times(1)).addBeforeHook(any())
    verify(fakeGlue, times(1)).addBeforeStepHook(any())
    verify(fakeGlue, times(1)).addAfterHook(any())
    verify(fakeGlue, times(1)).addAfterStepHook(any())

    // Building the world a second time should create new instances
    backend.disposeWorld()
    backend.buildWorld()

    verify(fakeLookup, times(2)).getInstance(any())
    verify(fakeLookup, times(2)).getInstance(classOf[StepsInTrait])

    verify(fakeGlue, times(2)).addStepDefinition(any())
    verify(fakeGlue, times(2)).addBeforeHook(any())
    verify(fakeGlue, times(2)).addBeforeStepHook(any())
    verify(fakeGlue, times(2)).addAfterHook(any())
    verify(fakeGlue, times(2)).addAfterStepHook(any())
  }

  @Test
  def loadGlueAndBuildWorld_object(): Unit = {
    // Load glue
    backend.loadGlue(
      fakeGlue,
      List(URI.create("classpath:io/cucumber/scala/steps/objects")).asJava
    )

    assertEquals(0, backend.scalaGlueClasses.size)
    assertTrue(backend.scalaGlueClasses.toSet == Set())

    verify(fakeContainer, never()).addClass(any())

    verify(fakeGlue, times(1)).addStepDefinition(any())
    verify(fakeGlue, times(1)).addBeforeHook(any())
    verify(fakeGlue, times(1)).addBeforeStepHook(any())
    verify(fakeGlue, times(1)).addAfterHook(any())
    verify(fakeGlue, times(1)).addAfterStepHook(any())

    // Build world
    backend.buildWorld()

    verify(fakeLookup, never()).getInstance(any())
  }

  @Test
  def loadGlueAndBuildWorld_once_by_classpath_url(): Unit = {
    // Load glue
    backend.loadGlue(
      fakeGlue,
      List(
        URI.create("classpath:io/cucumber/scala/steps/classes"),
        URI.create("classpath:io/cucumber/scala/steps/classes")
      ).asJava
    )

    assertEquals(3, backend.scalaGlueClasses.size)
    assertTrue(
      backend.scalaGlueClasses.toSet == Set(
        classOf[StepsA],
        classOf[StepsB],
        classOf[StepsC]
      )
    )

    verify(fakeContainer, times(3)).addClass(any())
    verify(fakeContainer, times(1)).addClass(classOf[StepsA])
    verify(fakeContainer, times(1)).addClass(classOf[StepsB])
    verify(fakeContainer, times(1)).addClass(classOf[StepsC])
  }

  @Test
  def loadGlueAndBuildWorld_class_incorrect_hooks_definitions(): Unit = {
    val result = Try {
      // Load glue
      backend.loadGlue(
        fakeGlue,
        List(
          URI.create(
            "classpath:io/cucumber/scala/steps/errors/incorrectclasshooks"
          )
        ).asJava
      )

      // Build world
      backend.buildWorld()
    }

    result match {
      case Failure(ex) if ex.isInstanceOf[UndefinedHooksException] =>
        val incorrectHookDefException =
          ex.asInstanceOf[UndefinedHooksException]
        assertEquals(6, incorrectHookDefException.undefinedHooks.size)
        val expectedMsg = """Some hooks are not defined properly:
                            | - IncorrectClassHooksDefinition.scala:11 (BEFORE_ALL)
                            | - IncorrectClassHooksDefinition.scala:14 (BEFORE)
                            | - IncorrectClassHooksDefinition.scala:17 (BEFORE_STEP)
                            | - IncorrectClassHooksDefinition.scala:20 (AFTER_ALL)
                            | - IncorrectClassHooksDefinition.scala:23 (AFTER)
                            | - IncorrectClassHooksDefinition.scala:26 (AFTER_STEP)
                            |
                            |This can be caused by defining hooks where the body returns a Int or String rather than Unit.
                            |
                            |For instance, the following code:
                            |
                            |  Before {
                            |    someInitMethodReturningInt()
                            |  }
                            |
                            |Should be replaced with:
                            |
                            |  Before {
                            |    someInitMethodReturningInt()
                            |    ()
                            |  }
                            |""".stripMargin
        assertEquals(expectedMsg, incorrectHookDefException.getMessage)
      case Failure(ex) =>
        fail(s"Expected UndefinedHooksException, got ${ex.getClass}")
      case Success(_) =>
        fail("Expected UndefinedHooksException")
    }
  }

  @Test
  def loadGlueAndBuildWorld_object_incorrect_hooks_definitions(): Unit = {
    val result = Try {
      // Load glue
      backend.loadGlue(
        fakeGlue,
        List(
          URI.create(
            "classpath:io/cucumber/scala/steps/errors/incorrectobjecthooks"
          )
        ).asJava
      )
    }

    result match {
      case Failure(ex) if ex.isInstanceOf[UndefinedHooksException] =>
        val incorrectHookDefException =
          ex.asInstanceOf[UndefinedHooksException]
        assertEquals(6, incorrectHookDefException.undefinedHooks.size)
        val expectedMsg = """Some hooks are not defined properly:
                            | - IncorrectObjectHooksDefinition.scala:11 (BEFORE_ALL)
                            | - IncorrectObjectHooksDefinition.scala:14 (BEFORE)
                            | - IncorrectObjectHooksDefinition.scala:17 (BEFORE_STEP)
                            | - IncorrectObjectHooksDefinition.scala:20 (AFTER_ALL)
                            | - IncorrectObjectHooksDefinition.scala:23 (AFTER)
                            | - IncorrectObjectHooksDefinition.scala:26 (AFTER_STEP)
                            |
                            |This can be caused by defining hooks where the body returns a Int or String rather than Unit.
                            |
                            |For instance, the following code:
                            |
                            |  Before {
                            |    someInitMethodReturningInt()
                            |  }
                            |
                            |Should be replaced with:
                            |
                            |  Before {
                            |    someInitMethodReturningInt()
                            |    ()
                            |  }
                            |""".stripMargin
        assertEquals(expectedMsg, incorrectHookDefException.getMessage)
      case Failure(ex) =>
        fail(s"Expected UndefinedHooksException, got ${ex.getClass}")
      case Success(_) =>
        fail("Expected UndefinedHooksException")
    }
  }

  @Test
  def loadGlueAndBuildWorld_class_static_class_hooks_definitions(): Unit = {
    val result = Try {
      // Load glue
      backend.loadGlue(
        fakeGlue,
        List(
          URI.create(
            "classpath:io/cucumber/scala/steps/errors/staticclasshooks"
          )
        ).asJava
      )

      // Build world
      backend.buildWorld()
    }

    result match {
      case Failure(ex) if ex.isInstanceOf[ScenarioScopedStaticHookException] =>
        val incorrectHookDefException =
          ex.asInstanceOf[ScenarioScopedStaticHookException]
        assertEquals(2, incorrectHookDefException.staticHooks.size)
        val expectedMsg = """Some hooks are not defined properly:
                            | - StaticClassHooksDefinition.scala:11
                            | - StaticClassHooksDefinition.scala:14
                            |
                            |This can be caused by defining static hooks (BeforeAll/AfterAll) in a class rather than in a object.
                            |Such hooks can only be defined in a static context.
                            |""".stripMargin
        assertEquals(expectedMsg, incorrectHookDefException.getMessage)
      case Failure(ex) =>
        fail(s"Expected ScenarioScopedStaticHookException, got ${ex.getClass}")
      case Success(_) =>
        fail("Expected ScenarioScopedStaticHookException")
    }
  }

  @Test
  def loadGlueAndBuildWorld_DI(): Unit = {
    // Load glue
    backend.loadGlue(
      fakeGlue,
      List(
        URI.create("classpath:io/cucumber/scala/steps/dependencyinjection")
      ).asJava
    )

    assertEquals(2, backend.scalaGlueClasses.size)
    assertTrue(
      backend.scalaGlueClasses.toSet == Set(
        classOf[Injected],
        classOf[Injector]
      )
    )

    verify(fakeContainer, times(2)).addClass(any())
    verify(fakeContainer, times(1)).addClass(classOf[Injected])
    verify(fakeContainer, times(1)).addClass(classOf[Injector])

    // Build world
    backend.buildWorld()

    verify(fakeLookup, times(2)).getInstance(any())
    verify(fakeLookup, times(1)).getInstance(classOf[Injected])
    verify(fakeLookup, times(1)).getInstance(classOf[Injector])

    // Building the world a second time should create new instances
    backend.disposeWorld()
    backend.buildWorld()

    verify(fakeLookup, times(4)).getInstance(any())
    verify(fakeLookup, times(2)).getInstance(classOf[Injected])
    verify(fakeLookup, times(2)).getInstance(classOf[Injector])

  }

  @Test
  def isRegularClass_class(): Unit = {
    assertEquals(true, ScalaBackend.isRegularClass(classOf[StepsA]).get)
  }

  @Test
  def isRegularClass_class_with_trait(): Unit = {
    assertEquals(true, ScalaBackend.isRegularClass(classOf[StepsInTrait]).get)
  }

  @Test
  def isRegularClass_class_with_args(): Unit = {
    assertEquals(true, ScalaBackend.isRegularClass(classOf[Injector]).get)
  }

  @Test
  def isRegularClass_object(): Unit = {
    assertEquals(
      false,
      ScalaBackend.isRegularClass(StepsInObject.getClass).get
    )
  }

}
