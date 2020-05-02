package io.cucumber.scala

import java.util.concurrent.atomic.AtomicBoolean

import io.cucumber.core.backend._
import io.cucumber.datatable.DataTable
import io.cucumber.scala.ScalaDslTest.{Author, Cell, GroupOfAuthor, Point}
import org.junit.Assert.{assertEquals, assertTrue}
import org.junit.{Before, Test}
import org.mockito.Mockito.mock

import scala.collection.JavaConverters._
import scala.util.Try

object ScalaDslTest {

  private case class GroupOfAuthor(authors: Seq[Author])

  private case class Author(name: String, surname: String, famousBook: String)

  private case class Cell(cell: String)

  val DATATABLE: Seq[Seq[String]] = Seq(
    Seq("name", "surname", "famousBook"),
    Seq("Alan", "Alou", "The Lion King"),
    Seq("Robert", "Bob", "Le Petit Prince")
  )

  val DATATABLE_WITH_EMPTY: Seq[Seq[String]] = Seq(
    Seq("name", "surname", "famousBook"),
    Seq("Alan", "Alou", "The Lion King"),
    Seq("Robert", "[empty]", "Le Petit Prince")
  )

  private case class Point(x: Int, y: Int)

}

class ScalaDslTest {

  private val fakeState = mock(classOf[TestCaseState])

  private val invoked = new AtomicBoolean()

  private def invoke(): Unit = {
    invoked.set(true)
  }

  @Before
  def beforeEach(): Unit = {
    // Reset the invoked flag
    invoked.set(false)
  }

  @Test
  def testBeforeHook(): Unit = {

    class Glue extends ScalaDsl {
      Before { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeHooks.head, "", 1000)
  }

  @Test
  def testBeforeHookWithTag(): Unit = {

    class Glue extends ScalaDsl {
      Before("tagExpression") { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeHooks.head, "tagExpression", 1000)
  }

  @Test
  def testBeforeHookWithOrder(): Unit = {

    class Glue extends ScalaDsl {
      Before(42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeHooks.head, "", 42)
  }

  @Test
  def testBeforeHookWithTagAndOrder(): Unit = {

    class Glue extends ScalaDsl {
      Before("tagExpression", 42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeHooks.head, "tagExpression", 42)
  }

  @Test
  def testBeforeStepHook(): Unit = {

    class Glue extends ScalaDsl {
      BeforeStep { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeStepHooks.head, "", 1000)
  }

  @Test
  def testBeforeStepHookWithTag(): Unit = {

    class Glue extends ScalaDsl {
      BeforeStep("tagExpression") { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeStepHooks.head, "tagExpression", 1000)
  }

  @Test
  def testBeforeStepHookWithOrder(): Unit = {

    class Glue extends ScalaDsl {
      BeforeStep(42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeStepHooks.head, "", 42)
  }

  @Test
  def testBeforeStepHookWithTagAndOrder(): Unit = {

    class Glue extends ScalaDsl {
      BeforeStep("tagExpression", 42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.beforeStepHooks.head, "tagExpression", 42)
  }


  @Test
  def testAfterHook(): Unit = {

    class Glue extends ScalaDsl {
      After { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterHooks.head, "", 1000)
  }

  @Test
  def testAfterHookWithTag(): Unit = {

    class Glue extends ScalaDsl {
      After("tagExpression") { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterHooks.head, "tagExpression", 1000)
  }

  @Test
  def testAfterHookWithOrder(): Unit = {

    class Glue extends ScalaDsl {
      After(42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterHooks.head, "", 42)
  }

  @Test
  def testAfterHookWithTagAndOrder(): Unit = {

    class Glue extends ScalaDsl {
      After("tagExpression", 42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterHooks.head, "tagExpression", 42)
  }

  @Test
  def testAfterStepHook(): Unit = {

    class Glue extends ScalaDsl {
      AfterStep { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterStepHooks.head, "", 1000)
  }

  @Test
  def testAfterStepHookWithTag(): Unit = {

    class Glue extends ScalaDsl {
      AfterStep("tagExpression") { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterStepHooks.head, "tagExpression", 1000)
  }

  @Test
  def testAfterStepHookWithOrder(): Unit = {

    class Glue extends ScalaDsl {
      AfterStep(42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterStepHooks.head, "", 42)
  }

  @Test
  def testAfterStepHookWithTagAndOrder(): Unit = {

    class Glue extends ScalaDsl {
      AfterStep("tagExpression", 42) { _ =>
        invoke()
      }
    }

    val glue = new Glue()

    assertClassHook(glue.registry.afterStepHooks.head, "tagExpression", 42)
  }

  @Test
  def testDefNoArg(): Unit = {

    var invoked = false

    class Glue extends ScalaDsl with EN {
      // On a single line to avoid difference between Scala versions for the location
      //@formatter:off
      Given("Something") { invoked = true }
      //@formatter:on
    }

    val glue = new Glue()

    assertClassStepDefinition(glue.registry.stepDefinitions.head, "Something", "ScalaDslTest.scala:288", Array(), invoked)
  }

  @Test
  def testDefEmptyArg(): Unit = {

    var invoked = false

    class Glue extends ScalaDsl with EN {
      Given("Something") { () =>
        invoked = true
      }
    }

    val glue = new Glue()

    assertClassStepDefinition(glue.registry.stepDefinitions.head, "Something", "ScalaDslTest.scala:303", Array(), invoked)
  }

  @Test
  def testDefWithArgs(): Unit = {

    var thenumber = 0
    var thecolour = ""

    class Glue extends ScalaDsl with EN {
      Given("""Oh boy, (\d+) (\s+) cukes""") { (num: Int, colour: String) =>
        thenumber = num
        thecolour = colour
      }
    }

    val glue = new Glue()

    assertClassStepDefinition(glue.registry.stepDefinitions.head, """Oh boy, (\d+) (\s+) cukes""", "ScalaDslTest.scala:320", Array(new java.lang.Integer(5), "green"), thenumber == 5 && thecolour == "green")
  }

  @Test
  def testDefThrowException(): Unit = {

    class GlueWithException extends ScalaDsl with EN {
      Given("Something") { () =>
        val x = 1 + 2 // A not useful line
        throw new PendingException()
      }
    }

    val glue = new GlueWithException()

    assertClassStepDefinitionThrow(glue.registry.stepDefinitions.head, "io.cucumber.scala.ScalaDslTest$GlueWithException", "ScalaDslTest.scala", 337, Array())
  }

  @Test
  def testDocStringType(): Unit = {

    class Glue extends ScalaDsl with EN {
      DocStringType("doc") { docString =>
        new StringBuilder(docString)
      }
    }

    val glue = new Glue()

    assertClassDocStringType(glue.registry.docStringTypes.head)
  }

  @Test
  def testDataTableEntryType(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType { entry: Map[String, String] =>
        Author(entry("name"), entry("surname"), entry("famousBook"))
      }
    }

    val glue = new Glue()

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "Bob", "Le Petit Prince")
    ).asJava
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq(), ScalaDslTest.DATATABLE, expected)
  }

  @Test
  def testDataTableEntryTypeWithReplacement(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (entry: Map[String, String]) =>
        Author(entry("name"), entry("surname"), entry("famousBook"))
      }
    }

    val glue = new Glue()

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "", "Le Petit Prince")
    ).asJava
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslTest.DATATABLE_WITH_EMPTY, expected)
  }

  @Test
  def testDataTableRowType(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType { row: Seq[String] =>
        Author(row(0), row(1), row(2))
      }
    }

    val glue = new Glue()

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "Bob", "Le Petit Prince")
    ).asJava
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq(), ScalaDslTest.DATATABLE.drop(1), expected)
  }

  @Test
  def testDataTableRowTypeWithReplacement(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (row: Seq[String]) =>
        Author(row(0), row(1), row(2))
      }
    }

    val glue = new Glue()

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "", "Le Petit Prince")
    ).asJava
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslTest.DATATABLE_WITH_EMPTY.drop(1), expected)
  }

  @Test
  def testDataTableCellType(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType { cell: String =>
        Cell(cell)
      }
    }

    val glue = new Glue()

    val expected = Seq(
      Seq(Cell("Alan"), Cell("Alou"), Cell("The Lion King")).asJava,
      Seq(Cell("Robert"), Cell("Bob"), Cell("Le Petit Prince")).asJava
    ).asJava
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq(), ScalaDslTest.DATATABLE.drop(1), expected)
  }

  @Test
  def testDataTableCellTypeWithReplacement(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (cell: String) =>
        Cell(cell)
      }
    }

    val glue = new Glue()

    val expected = Seq(
      Seq(Cell("Alan"), Cell("Alou"), Cell("The Lion King")).asJava,
      Seq(Cell("Robert"), Cell(""), Cell("Le Petit Prince")).asJava
    ).asJava
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslTest.DATATABLE_WITH_EMPTY.drop(1), expected)
  }

  @Test
  def testClassDataTableTableType(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType { table: DataTable =>
        val authors = table.asMaps().asScala
          .map(_.asScala)
          .map(entry => Author(entry("name"), entry("surname"), entry("famousBook")))
          .toSeq
        GroupOfAuthor(authors)
      }
    }

    val glue = new Glue()

    val expected = GroupOfAuthor(Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "Bob", "Le Petit Prince")
    ))
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq(), ScalaDslTest.DATATABLE, expected)
  }

  @Test
  def testClassDataTableTableTypeWithReplacement(): Unit = {

    class Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (table: DataTable) =>
        val authors = table.asMaps().asScala
          .map(_.asScala)
          .map(entry => Author(entry("name"), entry("surname"), entry("famousBook")))
          .toSeq
        GroupOfAuthor(authors)
      }
    }

    val glue = new Glue()

    val expected = GroupOfAuthor(Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "", "Le Petit Prince")
    ))
    assertClassDataTableType(glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslTest.DATATABLE_WITH_EMPTY, expected)
  }

  @Test
  def testClassParameterType1(): Unit = {

    class Glue extends ScalaDsl with EN {
      ParameterType("string-builder", ".*") { str =>
        new StringBuilder(str)
      }
    }

    val glue = new Glue()

    assertClassParameterType(glue.registry.parameterTypes.head, "string-builder", Seq(".*"), classOf[StringBuilder])
  }

  @Test
  def testClassParameterType2(): Unit = {

    class Glue extends ScalaDsl with EN {
      ParameterType("coordinates", "(.+),(.+)") { (x, y) =>
        Point(x.toInt, y.toInt)
      }
    }

    val glue = new Glue()

    assertClassParameterType(glue.registry.parameterTypes.head, "coordinates", Seq("(.+),(.+)"), classOf[Point])
  }

  @Test
  def testClassParameterType3(): Unit = {

    class Glue extends ScalaDsl with EN {
      ParameterType("ingredients", "(.+), (.+) and (.+)") { (x, y, z) =>
        s"$x-$y-$z"
      }
    }

    val glue = new Glue()

    assertClassParameterType(glue.registry.parameterTypes.head, "ingredients", Seq("(.+), (.+) and (.+)"), classOf[String])
  }

  @Test
  def testClassDefaultParameterTransformer(): Unit = {

    class Glue extends ScalaDsl with EN {
      DefaultParameterTransformer { (fromValue: String, toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    val glue = new Glue()

    assertClassDefaultParameterTransformer(glue.registry.defaultParameterTransformers.head, "meat", classOf[StringBuilder], "meat-class scala.collection.mutable.StringBuilder")
  }


  @Test
  def testClassDefaultDataTableCellTransformer(): Unit = {

    class Glue extends ScalaDsl with EN {
      DefaultDataTableCellTransformer { (fromValue: String, toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    val glue = new Glue()

    assertObjectDefaultDataTableCellTransformer(glue.registry.defaultDataTableCellTransformers.head, "meat", classOf[StringBuilder], "meat-class scala.collection.mutable.StringBuilder")
  }

  @Test
  def testClassDefaultDataTableCellTransformerWithEmpty(): Unit = {

    class Glue extends ScalaDsl with EN {
      DefaultDataTableCellTransformer("[empty]") { (fromValue: String, toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    val glue = new Glue()

    assertObjectDefaultDataTableCellTransformer(glue.registry.defaultDataTableCellTransformers.head, "meat", classOf[StringBuilder], "meat-class scala.collection.mutable.StringBuilder")
    assertObjectDefaultDataTableCellTransformer(glue.registry.defaultDataTableCellTransformers.head, "[empty]", classOf[StringBuilder], "-class scala.collection.mutable.StringBuilder")
  }

  @Test
  def testClassDefaultDataTableEntryTransformer(): Unit = {

    class Glue extends ScalaDsl with EN {
      DefaultDataTableEntryTransformer { (fromValue: Map[String, String], toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    val glue = new Glue()

    assertObjectDefaultDataTableEntryTransformer(glue.registry.defaultDataTableEntryTransformers.head, Map("a" -> "b", "c" -> "d"), classOf[StringBuilder], "Map(a -> b, c -> d)-class scala.collection.mutable.StringBuilder")
  }

  @Test
  def testClassDefaultDataTableEntryTransformerWithEmpty(): Unit = {

    class Glue extends ScalaDsl with EN {
      DefaultDataTableEntryTransformer("[empty]") { (fromValue: Map[String, String], toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    val glue = new Glue()

    assertObjectDefaultDataTableEntryTransformer(glue.registry.defaultDataTableEntryTransformers.head, Map("a" -> "b", "c" -> "[empty]"), classOf[StringBuilder], "Map(a -> b, c -> )-class scala.collection.mutable.StringBuilder")
  }

  // -------------------- Test on object --------------------
  // Note: for now there is no difference between the two in ScalaDsl but better safe than sorry

  @Test
  def testObjectBeforeHook(): Unit = {

    object Glue extends ScalaDsl {
      Before { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeHooks.head, "", 1000)
  }

  @Test
  def testObjectBeforeHookWithTag(): Unit = {

    object Glue extends ScalaDsl {
      Before("tagExpression") { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeHooks.head, "tagExpression", 1000)
  }

  @Test
  def testObjectBeforeHookWithOrder(): Unit = {

    object Glue extends ScalaDsl {
      Before(42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeHooks.head, "", 42)
  }

  @Test
  def testObjectBeforeHookWithTagAndOrder(): Unit = {

    object Glue extends ScalaDsl {
      Before("tagExpression", 42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeHooks.head, "tagExpression", 42)
  }

  @Test
  def testObjectBeforeStepHook(): Unit = {

    object Glue extends ScalaDsl {
      BeforeStep { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeStepHooks.head, "", 1000)
  }

  @Test
  def testObjectBeforeStepHookWithTag(): Unit = {

    object Glue extends ScalaDsl {
      BeforeStep("tagExpression") { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeStepHooks.head, "tagExpression", 1000)
  }

  @Test
  def testObjectBeforeStepHookWithOrder(): Unit = {

    object Glue extends ScalaDsl {
      BeforeStep(42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeStepHooks.head, "", 42)
  }

  @Test
  def testObjectBeforeStepHookWithTagAndOrder(): Unit = {

    object Glue extends ScalaDsl {
      BeforeStep("tagExpression", 42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.beforeStepHooks.head, "tagExpression", 42)
  }


  @Test
  def testObjectAfterHook(): Unit = {

    object Glue extends ScalaDsl {
      After { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterHooks.head, "", 1000)
  }

  @Test
  def testObjectAfterHookWithTag(): Unit = {

    object Glue extends ScalaDsl {
      After("tagExpression") { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterHooks.head, "tagExpression", 1000)
  }

  @Test
  def testObjectAfterHookWithOrder(): Unit = {

    object Glue extends ScalaDsl {
      After(42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterHooks.head, "", 42)
  }

  @Test
  def testObjectAfterHookWithTagAndOrder(): Unit = {

    object Glue extends ScalaDsl {
      After("tagExpression", 42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterHooks.head, "tagExpression", 42)
  }

  @Test
  def testObjectAfterStepHook(): Unit = {

    object Glue extends ScalaDsl {
      AfterStep { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterStepHooks.head, "", 1000)
  }

  @Test
  def testObjectAfterStepHookWithTag(): Unit = {

    object Glue extends ScalaDsl {
      AfterStep("tagExpression") { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterStepHooks.head, "tagExpression", 1000)
  }

  @Test
  def testObjectAfterStepHookWithOrder(): Unit = {

    object Glue extends ScalaDsl {
      AfterStep(42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterStepHooks.head, "", 42)
  }

  @Test
  def testObjectAfterStepHookWithTagAndOrder(): Unit = {

    object Glue extends ScalaDsl {
      AfterStep("tagExpression", 42) { _ =>
        invoke()
      }
    }

    assertObjectHook(Glue.registry.afterStepHooks.head, "tagExpression", 42)
  }

  @Test
  def testObjectDefNoArg(): Unit = {

    var invoked = false

    object Glue extends ScalaDsl with EN {
      // On a single line to avoid difference between Scala versions for the location
      //@formatter:off
      Given("Something") { invoked = true }
      //@formatter:on
    }

    assertObjectStepDefinition(Glue.registry.stepDefinitions.head, "Something", "ScalaDslTest.scala:830", Array(), invoked)
  }

  @Test
  def testObjectDefEmptyArg(): Unit = {

    var invoked = false

    object Glue extends ScalaDsl with EN {
      Given("Something") { () =>
        invoked = true
      }
    }

    assertObjectStepDefinition(Glue.registry.stepDefinitions.head, "Something", "ScalaDslTest.scala:843", Array(), invoked)
  }

  @Test
  def testObjectDefWithArgs(): Unit = {

    var thenumber = 0
    var thecolour = ""

    object Glue extends ScalaDsl with EN {
      Given("""Oh boy, (\d+) (\s+) cukes""") { (num: Int, colour: String) =>
        thenumber = num
        thecolour = colour
      }
    }

    assertObjectStepDefinition(Glue.registry.stepDefinitions.head, """Oh boy, (\d+) (\s+) cukes""", "ScalaDslTest.scala:858", Array(new java.lang.Integer(5), "green"), thenumber == 5 && thecolour == "green")
  }

  @Test
  def testObjectDefThrowException(): Unit = {

    object GlueWithException extends ScalaDsl with EN {
      Given("Something") { () =>
        val x = 1 + 2 // A not useful line
        throw new PendingException()
      }
    }

    assertObjectStepDefinitionThrow(GlueWithException.registry.stepDefinitions.head, "io.cucumber.scala.ScalaDslTest$GlueWithException", "ScalaDslTest.scala", 873, Array())
  }

  @Test
  def testObjectDocStringType(): Unit = {

    object Glue extends ScalaDsl with EN {
      DocStringType("doc") { docString =>
        new StringBuilder(docString)
      }
    }

    assertObjectDocStringType(Glue.registry.docStringTypes.head)
  }

  @Test
  def testObjectDataTableEntryType(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType { entry: Map[String, String] =>
        Author(entry("name"), entry("surname"), entry("famousBook"))
      }
    }

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "Bob", "Le Petit Prince")
    ).asJava
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq(), ScalaDslTest.DATATABLE, expected)
  }

  @Test
  def testObjectDataTableEntryTypeWithReplacement(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (entry: Map[String, String]) =>
        Author(entry("name"), entry("surname"), entry("famousBook"))
      }
    }

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "", "Le Petit Prince")
    ).asJava
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslTest.DATATABLE_WITH_EMPTY, expected)
  }

  @Test
  def testObjectDataTableRowType(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType { row: Seq[String] =>
        Author(row(0), row(1), row(2))
      }
    }

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "Bob", "Le Petit Prince")
    ).asJava
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq(), ScalaDslTest.DATATABLE.drop(1), expected)
  }

  @Test
  def testObjectDataTableRowTypeWithReplacement(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (row: Seq[String]) =>
        Author(row(0), row(1), row(2))
      }
    }

    val expected = Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "", "Le Petit Prince")
    ).asJava
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslTest.DATATABLE_WITH_EMPTY.drop(1), expected)
  }

  @Test
  def testObjectDataTableCellType(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType { cell: String =>
        Cell(cell)
      }
    }

    val expected = Seq(
      Seq(Cell("Alan"), Cell("Alou"), Cell("The Lion King")).asJava,
      Seq(Cell("Robert"), Cell("Bob"), Cell("Le Petit Prince")).asJava
    ).asJava
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq(), ScalaDslTest.DATATABLE.drop(1), expected)
  }

  @Test
  def testObjectDataTableCellTypeWithReplacement(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (cell: String) =>
        Cell(cell)
      }
    }

    val expected = Seq(
      Seq(Cell("Alan"), Cell("Alou"), Cell("The Lion King")).asJava,
      Seq(Cell("Robert"), Cell(""), Cell("Le Petit Prince")).asJava
    ).asJava
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslTest.DATATABLE_WITH_EMPTY.drop(1), expected)
  }

  @Test
  def testObjectDataTableTableType(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType { table: DataTable =>
        val authors = table.asMaps().asScala
          .map(_.asScala)
          .map(entry => Author(entry("name"), entry("surname"), entry("famousBook")))
          .toSeq
        GroupOfAuthor(authors)
      }
    }

    val expected = GroupOfAuthor(Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "Bob", "Le Petit Prince")
    ))
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq(), ScalaDslTest.DATATABLE, expected)
  }

  @Test
  def testObjectDataTableTableTypeWithReplacement(): Unit = {

    object Glue extends ScalaDsl with EN {
      DataTableType("[empty]") { (table: DataTable) =>
        val authors = table.asMaps().asScala
          .map(_.asScala)
          .map(entry => Author(entry("name"), entry("surname"), entry("famousBook")))
          .toSeq
        GroupOfAuthor(authors)
      }
    }

    val expected = GroupOfAuthor(Seq(
      Author("Alan", "Alou", "The Lion King"),
      Author("Robert", "", "Le Petit Prince")
    ))
    assertObjectDataTableType(Glue.registry.dataTableTypes.head, Seq("[empty]"), ScalaDslTest.DATATABLE_WITH_EMPTY, expected)
  }

  @Test
  def testObjectParameterType1(): Unit = {

    object Glue extends ScalaDsl with EN {
      ParameterType("string-builder", ".*") { str =>
        new StringBuilder(str)
      }
    }

    assertObjectParameterType(Glue.registry.parameterTypes.head, "string-builder", Seq(".*"), classOf[StringBuilder])
  }

  @Test
  def testObjectParameterType2(): Unit = {

    object Glue extends ScalaDsl with EN {
      ParameterType("coordinates", "(.+),(.+)") { (x, y) =>
        Point(x.toInt, y.toInt)
      }
    }

    assertObjectParameterType(Glue.registry.parameterTypes.head, "coordinates", Seq("(.+),(.+)"), classOf[Point])
  }

  @Test
  def testObjectParameterType3(): Unit = {

    object Glue extends ScalaDsl with EN {
      ParameterType("ingredients", "(.+), (.+) and (.+)") { (x, y, z) =>
        s"$x-$y-$z"
      }
    }

    assertObjectParameterType(Glue.registry.parameterTypes.head, "ingredients", Seq("(.+), (.+) and (.+)"), classOf[String])
  }

  @Test
  def testObjectDefaultParameterTransformer(): Unit = {

    object Glue extends ScalaDsl with EN {
      DefaultParameterTransformer { (fromValue: String, toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    assertObjectDefaultParameterTransformer(Glue.registry.defaultParameterTransformers.head, "meat", classOf[StringBuilder], "meat-class scala.collection.mutable.StringBuilder")
  }

  @Test
  def testObjectDefaultDataTableCellTransformer(): Unit = {

    object Glue extends ScalaDsl with EN {
      DefaultDataTableCellTransformer { (fromValue: String, toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    assertObjectDefaultDataTableCellTransformer(Glue.registry.defaultDataTableCellTransformers.head, "meat", classOf[StringBuilder], "meat-class scala.collection.mutable.StringBuilder")
  }

  @Test
  def testObjectDefaultDataTableCellTransformerWithEmpty(): Unit = {

    object Glue extends ScalaDsl with EN {
      DefaultDataTableCellTransformer("[empty]") { (fromValue: String, toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    assertObjectDefaultDataTableCellTransformer(Glue.registry.defaultDataTableCellTransformers.head, "meat", classOf[StringBuilder], "meat-class scala.collection.mutable.StringBuilder")
    assertObjectDefaultDataTableCellTransformer(Glue.registry.defaultDataTableCellTransformers.head, "[empty]", classOf[StringBuilder], "-class scala.collection.mutable.StringBuilder")
  }

  @Test
  def testObjectDefaultDataTableEntryTransformer(): Unit = {

    object Glue extends ScalaDsl with EN {
      DefaultDataTableEntryTransformer { (fromValue: Map[String, String], toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    assertObjectDefaultDataTableEntryTransformer(Glue.registry.defaultDataTableEntryTransformers.head, Map("a" -> "b", "c" -> "d"), classOf[StringBuilder], "Map(a -> b, c -> d)-class scala.collection.mutable.StringBuilder")
  }

  @Test
  def testObjectDefaultDataTableEntryTransformerWithEmpty(): Unit = {

    object Glue extends ScalaDsl with EN {
      DefaultDataTableEntryTransformer("[empty]") { (fromValue: Map[String, String], toValueType: java.lang.reflect.Type) =>
        new StringBuilder().append(fromValue).append("-").append(toValueType)
      }
    }

    assertObjectDefaultDataTableEntryTransformer(Glue.registry.defaultDataTableEntryTransformers.head, Map("a" -> "b", "c" -> "[empty]"), classOf[StringBuilder], "Map(a -> b, c -> )-class scala.collection.mutable.StringBuilder")
  }


  private def assertClassHook(hookDetails: ScalaHookDetails, tagExpression: String, order: Int): Unit = {
    assertHook(ScalaHookDefinition(hookDetails, true), tagExpression, order)
  }

  private def assertObjectHook(hookDetails: ScalaHookDetails, tagExpression: String, order: Int): Unit = {
    assertHook(ScalaHookDefinition(hookDetails, false), tagExpression, order)
  }

  private def assertHook(hook: HookDefinition, tagExpression: String, order: Int): Unit = {
    assertEquals(tagExpression, hook.getTagExpression)
    assertEquals(order, hook.getOrder)
    hook.execute(fakeState)
    assertTrue(invoked.get())
  }

  private def assertClassStepDefinition(stepDetails: ScalaStepDetails, pattern: String, location: String, args: Array[AnyRef], check: => Boolean): Unit = {
    assertStepDefinition(ScalaStepDefinition(stepDetails, true), pattern, location, args, check)
  }

  private def assertObjectStepDefinition(stepDetails: ScalaStepDetails, pattern: String, location: String, args: Array[AnyRef], check: => Boolean): Unit = {
    assertStepDefinition(ScalaStepDefinition(stepDetails, false), pattern, location, args, check)
  }

  private def assertStepDefinition(stepDefinition: StepDefinition, pattern: String, location: String, args: Array[AnyRef], check: => Boolean): Unit = {
    assertEquals(pattern, stepDefinition.getPattern)
    assertEquals(location, stepDefinition.getLocation)
    stepDefinition.execute(args)
    assertTrue(check)
  }

  private def assertClassStepDefinitionThrow(stepDetails: ScalaStepDetails, exceptionClassName: String, exceptionFile: String, exceptionLine: Int, args: Array[AnyRef]): Unit = {
    assertStepDefinitionThrow(ScalaStepDefinition(stepDetails, true), exceptionClassName, exceptionFile, exceptionLine, args)
  }

  private def assertObjectStepDefinitionThrow(stepDetails: ScalaStepDetails, exceptionClassName: String, exceptionFile: String, exceptionLine: Int, args: Array[AnyRef]): Unit = {
    assertStepDefinitionThrow(ScalaStepDefinition(stepDetails, false), exceptionClassName, exceptionFile, exceptionLine, args)
  }

  private def assertStepDefinitionThrow(stepDefinition: StepDefinition, exceptionClassName: String, exceptionFile: String, exceptionLine: Int, args: Array[AnyRef]): Unit = {
    val tried = Try(stepDefinition.execute(args))

    assertTrue(tried.isFailure)

    val ex = tried.failed.get
    assertTrue(ex.isInstanceOf[CucumberInvocationTargetException])

    val matched = ex.asInstanceOf[CucumberInvocationTargetException]
      .getInvocationTargetExceptionCause
      .getStackTrace
      .filter(stepDefinition.isDefinedAt)
      .head

    // The result is different between Scala versions, that's why we don't check it precisely
    //assertEquals("$anonfun$can_provide_location_of_step$1", matched.getMethodName)
    assertTrue(matched.getClassName.contains(exceptionClassName))
    assertEquals(exceptionFile, matched.getFileName)
    assertEquals(exceptionLine, matched.getLineNumber)
  }

  private def assertClassDocStringType(details: ScalaDocStringTypeDetails[_]): Unit = {
    assertDocStringType(ScalaDocStringTypeDefinition(details, true))
  }

  private def assertObjectDocStringType(details: ScalaDocStringTypeDetails[_]): Unit = {
    assertDocStringType(ScalaDocStringTypeDefinition(details, false))
  }

  private def assertDocStringType(docStringType: DocStringTypeDefinition): Unit = {
    // Cannot assert much because everything is strangely private in DocStringTypeDefinition
    // Real feature tests will do the job
  }

  private def assertClassDataTableType(details: ScalaDataTableTypeDetails[_], emptyPatterns: Seq[String], dataTable: Seq[Seq[String]], expectedObj: Any): Unit = {
    assertDataTableType(ScalaDataTableTypeDefinition(details, true), emptyPatterns, dataTable, expectedObj)
  }

  private def assertObjectDataTableType(details: ScalaDataTableTypeDetails[_], emptyPatterns: Seq[String], dataTable: Seq[Seq[String]], expectedObj: Any): Unit = {
    assertDataTableType(ScalaDataTableTypeDefinition(details, false), emptyPatterns, dataTable, expectedObj)
  }

  private def assertDataTableType(dataTableType: ScalaDataTableTypeDefinition, emptyPatterns: Seq[String], dataTable: Seq[Seq[String]], expectedObj: Any): Unit = {
    assertEquals(emptyPatterns, dataTableType.emptyPatterns)

    val obj = dataTableType.dataTableType.transform(dataTable.map(_.asJava).asJava)
    assertEquals(expectedObj, obj)
  }

  private def assertClassParameterType(details: ScalaParameterTypeDetails[_], name: String, regexps: Seq[String], expectedType: Class[_]): Unit = {
    assertParameterType(ScalaParameterTypeDefinition(details, true), name, regexps, expectedType)
  }

  private def assertObjectParameterType(details: ScalaParameterTypeDetails[_], name: String, regexps: Seq[String], expectedType: Class[_]): Unit = {
    assertParameterType(ScalaParameterTypeDefinition(details, false), name, regexps, expectedType)
  }

  private def assertParameterType(parameterTypeDef: ParameterTypeDefinition, name: String, regexps: Seq[String], expectedType: Class[_]): Unit = {
    val parameterType = parameterTypeDef.parameterType()

    assertEquals(name, parameterType.getName)
    assertEquals(regexps, parameterType.getRegexps.asScala)
    assertEquals(expectedType, parameterType.getType)

    // Cannot assert more because transform method is private
  }

  private def assertClassDefaultParameterTransformer(details: ScalaDefaultParameterTransformerDetails, input: String, toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertDefaultParameterTransformer(ScalaDefaultParameterTransformerDefinition(details, true), input, toType, expectedOutput)
  }

  private def assertObjectDefaultParameterTransformer(details: ScalaDefaultParameterTransformerDetails, input: String, toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertDefaultParameterTransformer(ScalaDefaultParameterTransformerDefinition(details, false), input, toType, expectedOutput)
  }

  private def assertDefaultParameterTransformer(typeDef: DefaultParameterTransformerDefinition, input: String, toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertEquals(toType, typeDef.parameterByTypeTransformer().transform(input, toType).getClass)
    assertEquals(expectedOutput.toString, typeDef.parameterByTypeTransformer().transform(input, toType).toString)
  }

  private def assertClassDefaultDataTableCellTransformer(details: ScalaDefaultDataTableCellTransformerDetails, input: String, toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertDefaultDataTableCellTransformer(ScalaDefaultDataTableCellTransformerDefinition(details, true), input, toType, expectedOutput)
  }

  private def assertObjectDefaultDataTableCellTransformer(details: ScalaDefaultDataTableCellTransformerDetails, input: String, toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertDefaultDataTableCellTransformer(ScalaDefaultDataTableCellTransformerDefinition(details, false), input, toType, expectedOutput)
  }

  private def assertDefaultDataTableCellTransformer(typeDef: DefaultDataTableCellTransformerDefinition, input: String, toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertEquals(toType, typeDef.tableCellByTypeTransformer().transform(input, toType).getClass)
    assertEquals(expectedOutput.toString, typeDef.tableCellByTypeTransformer().transform(input, toType).toString)
  }

  private def assertClassDefaultDataTableEntryTransformer(details: ScalaDefaultDataTableEntryTransformerDetails, input: Map[String, String], toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertDefaultDataTableEntryTransformer(ScalaDefaultDataTableEntryTransformerDefinition(details, true), input, toType, expectedOutput)
  }

  private def assertObjectDefaultDataTableEntryTransformer(details: ScalaDefaultDataTableEntryTransformerDetails, input: Map[String, String], toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertDefaultDataTableEntryTransformer(ScalaDefaultDataTableEntryTransformerDefinition(details, false), input, toType, expectedOutput)
  }

  private def assertDefaultDataTableEntryTransformer(typeDef: DefaultDataTableEntryTransformerDefinition, input: Map[String, String], toType: java.lang.reflect.Type, expectedOutput: AnyRef): Unit = {
    assertEquals(toType, typeDef.tableEntryByTypeTransformer().transform(input.asJava, toType, null).getClass)
    assertEquals(expectedOutput.toString, typeDef.tableEntryByTypeTransformer().transform(input.asJava, toType, null).toString)
  }

}
