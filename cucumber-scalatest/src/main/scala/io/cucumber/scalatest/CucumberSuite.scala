package io.cucumber.scalatest

import io.cucumber.core.options.{RuntimeOptionsBuilder, CucumberOptionsAnnotationParser}
import io.cucumber.core.runtime.{Runtime => CucumberRuntime}
import io.cucumber.core.plugin.event._
import org.scalatest.{Args, Status, Suite}
import org.scalatest.events._

import scala.annotation.nowarn
import scala.jdk.CollectionConverters._

/** A trait that allows Cucumber scenarios to be run with ScalaTest.
  *
  * Mix this trait into your test class and optionally annotate it with
  * `@CucumberOptions` to configure the Cucumber runtime.
  *
  * Example:
  * {{{
  * import io.cucumber.scalatest.CucumberSuite
  * import io.cucumber.core.options.CucumberOptions
  *
  * @CucumberOptions(
  *   features = Array("classpath:features"),
  *   glue = Array("com.example.stepdefinitions"),
  *   plugin = Array("pretty")
  * )
  * class RunCucumberTest extends CucumberSuite
  * }}}
  */
@nowarn
trait CucumberSuite extends Suite {

  /** Runs the Cucumber scenarios.
    *
    * @param testName An optional name of one test to run. If None, all relevant
    *                 tests should be run.
    * @param args the Args for this run
    * @return a Status object that indicates when all tests started by this method
    *         have completed, and whether or not a failure occurred.
    */
  abstract override def run(
      testName: Option[String],
      args: Args
  ): Status = {
    if (testName.isDefined) {
      throw new IllegalArgumentException(
        "Suite traits implemented by Cucumber do not support running a single test"
      )
    }

    val reporter = args.reporter
    val tracker = args.tracker
    val suiteStartTime = System.currentTimeMillis()

    reporter(SuiteStarting(
      tracker.nextOrdinal(),
      suiteName,
      suiteId,
      Some(getClass.getName),
      None,
      None,
      None,
      None,
      None
    ))

    var suiteSucceeded = true
    
    try {
      val runtimeOptions = buildRuntimeOptions()
      val classLoader = getClass.getClassLoader
      
      val runtime = CucumberRuntime
        .builder()
        .withRuntimeOptions(runtimeOptions)
        .withClassLoader(new java.util.function.Supplier[ClassLoader] {
          override def get(): ClassLoader = classLoader
        })
        .build()

      val eventBus = runtime.getBus()
      
      // Register a custom event handler to report to ScalaTest
      val eventHandler = new ScalaTestEventHandler(
        reporter,
        tracker,
        suiteId,
        suiteName,
        getClass.getName
      )
      eventBus.registerHandlerFor(classOf[TestCaseStarted], eventHandler)
      eventBus.registerHandlerFor(classOf[TestCaseFinished], eventHandler)

      runtime.run()

      suiteSucceeded = eventHandler.getTestsFailed() == 0

      if (suiteSucceeded) {
        reporter(SuiteCompleted(
          tracker.nextOrdinal(),
          suiteName,
          suiteId,
          Some(getClass.getName),
          Some(System.currentTimeMillis() - suiteStartTime),
          None,
          None,
          None,
          None
        ))
      } else {
        reporter(SuiteAborted(
          tracker.nextOrdinal(),
          s"${eventHandler.getTestsFailed()} scenario(s) failed",
          suiteName,
          suiteId,
          Some(getClass.getName),
          None,
          Some(System.currentTimeMillis() - suiteStartTime),
          None,
          None,
          None,
          None
        ))
      }

    } catch {
      case e: Throwable =>
        suiteSucceeded = false
        reporter(SuiteAborted(
          tracker.nextOrdinal(),
          e.getMessage,
          suiteName,
          suiteId,
          Some(getClass.getName),
          Some(e),
          Some(System.currentTimeMillis() - suiteStartTime),
          None,
          None,
          None,
          None
        ))
    }
    
    org.scalatest.SucceededStatus

  }

  private def buildRuntimeOptions(): io.cucumber.core.options.RuntimeOptions = {
    val annotationParser = new CucumberOptionsAnnotationParser()
    val annotationOptions = annotationParser.parse(getClass).build()
    
    new RuntimeOptionsBuilder()
      .build(annotationOptions)
  }
}

@nowarn
class ScalaTestEventHandler(
    reporter: org.scalatest.Reporter,
    tracker: org.scalatest.events.Tracker,
    suiteId: String,
    suiteName: String,
    suiteClassName: String
) extends io.cucumber.plugin.EventListener {

  private var testsFailed = 0
  private val testStartTimes = new scala.collection.mutable.HashMap[String, Long]()

  override def setEventPublisher(
      publisher: io.cucumber.plugin.event.EventPublisher
  ): Unit = {
    // Not used in this implementation
  }

  def handleTestCaseStarted(
      event: TestCaseStarted
  ): Unit = {
    val testCase = event.getTestCase()
    val testName = testCase.getName()
    val uri = testCase.getUri().toString()
    val line = testCase.getLocation().getLine()
    
    testStartTimes.put(testName, System.currentTimeMillis())
    
    reporter(TestStarting(
      tracker.nextOrdinal(),
      suiteName,
      suiteId,
      Some(suiteClassName),
      testName,
      testName,
      Some(MotionToSuppress),
      Some(LineInFile(line, uri, None)),
      None,
      None,
      None,
      None
    ))
  }

  def handleTestCaseFinished(
      event: TestCaseFinished
  ): Unit = {
    val testCase = event.getTestCase()
    val result = event.getResult()
    val testName = testCase.getName()
    val uri = testCase.getUri().toString()
    val line = testCase.getLocation().getLine()
    
    val startTime = testStartTimes.getOrElse(testName, System.currentTimeMillis())
    val duration = Some(System.currentTimeMillis() - startTime)
    
    result.getStatus() match {
      case io.cucumber.plugin.event.Status.PASSED =>
        reporter(TestSucceeded(
          tracker.nextOrdinal(),
          suiteName,
          suiteId,
          Some(suiteClassName),
          testName,
          testName,
          None,
          duration,
          None,
          Some(LineInFile(line, uri, None)),
          None,
          None,
          None,
          None
        ))
        
      case io.cucumber.plugin.event.Status.FAILED =>
        testsFailed += 1
        val error = result.getError()
        reporter(TestFailed(
          tracker.nextOrdinal(),
          error.getMessage(),
          suiteName,
          suiteId,
          Some(suiteClassName),
          testName,
          testName,
          None,
          None,
          Some(error),
          duration,
          None,
          Some(LineInFile(line, uri, None)),
          None,
          None,
          None,
          None
        ))
        
      case io.cucumber.plugin.event.Status.SKIPPED =>
        reporter(TestIgnored(
          tracker.nextOrdinal(),
          suiteName,
          suiteId,
          Some(suiteClassName),
          testName,
          testName,
          None,
          Some(LineInFile(line, uri, None)),
          None,
          None,
          None
        ))
        
      case io.cucumber.plugin.event.Status.PENDING =>
        reporter(TestPending(
          tracker.nextOrdinal(),
          suiteName,
          suiteId,
          Some(suiteClassName),
          testName,
          testName,
          None,
          duration,
          None,
          Some(LineInFile(line, uri, None)),
          None,
          None,
          None
        ))
        
      case io.cucumber.plugin.event.Status.UNDEFINED =>
        testsFailed += 1
        reporter(TestFailed(
          tracker.nextOrdinal(),
          "Step undefined",
          suiteName,
          suiteId,
          Some(suiteClassName),
          testName,
          testName,
          None,
          None,
          None,
          duration,
          None,
          Some(LineInFile(line, uri, None)),
          None,
          None,
          None,
          None
        ))
        
      case io.cucumber.plugin.event.Status.AMBIGUOUS =>
        testsFailed += 1
        val error = result.getError()
        reporter(TestFailed(
          tracker.nextOrdinal(),
          error.getMessage(),
          suiteName,
          suiteId,
          Some(suiteClassName),
          testName,
          testName,
          None,
          None,
          Some(error),
          duration,
          None,
          Some(LineInFile(line, uri, None)),
          None,
          None,
          None,
          None
        ))
        
      case io.cucumber.plugin.event.Status.UNUSED =>
        // Do nothing for unused steps
        ()
    }
    
    testStartTimes.remove(testName)
  }

  def getTestsFailed(): Int = testsFailed
}
