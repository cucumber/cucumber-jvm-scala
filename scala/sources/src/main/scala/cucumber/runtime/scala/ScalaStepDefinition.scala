package cucumber.runtime.scala

import java.lang.reflect.Type

import cucumber.runtime.StepDefinition
import gherkin.pickles.PickleStep
import io.cucumber.stepexpression._

/**
 * Implementation of step definition for scala.
 *
 * @param frame Representation of a stack frame containing information about the context in which a
 *              step was defined. Allows retrospective queries about the definition of a step.
 *
 * @param name The name of the step definition class, e.g. cucumber.runtime.scala.test.CukesStepDefinitions
 *
 * @param pattern The regex matcher that defines the cucumber step, e.g. /I eat (.*) cukes$/

 * @param parameterInfos
 *
 * @param f Function body of a step definition. This is what actually runs the code within the step def.
 */
class ScalaStepDefinition(frame:StackTraceElement,
                          name:String,
                          pattern:String,
                          parameterInfos:Array[Type],
                          f:List[Any] => Any) extends StepDefinition {

  private[cucumber] var typeRegistry: TypeRegistry = null
  private var expression: StepExpression = null

  /**
   * Returns a list of arguments. Return null if the step definition
   * doesn't match at all. Return an empty List if it matches with 0 arguments
   * and bigger sizes if it matches several.
   */
  def matchedArguments(step: PickleStep) = {
     expression = createExpression(pattern, typeRegistry)
     val argumentMatcher = new ExpressionArgumentMatcher(expression)
     argumentMatcher.argumentsFrom(step)
  }

  private def createExpression(expression: String, typeRegistry: TypeRegistry): StepExpression = {
    if (parameterInfos.isEmpty) new StepExpressionFactory(typeRegistry).createExpression(expression)
    else {
      val parameterInfo = parameterInfos(parameterInfos.size - 1)
      new StepExpressionFactory(typeRegistry).createExpression(expression, parameterInfo, false)
    }
  }

  /**
   * The source line where the step definition is defined.
   * Example: foo/bar/Zap.brainfuck:42
   *
   * @param detail true if extra detailed location information should be included.
   */
  override def getLocation(detail: Boolean) = frame.getFileName + ":" + frame.getLineNumber

  /**
   * How many declared parameters this stepdefinition has. Returns null if unknown.
   */
  override def getParameterCount() = parameterInfos.size

  /**
   * The parameter type at index n. A hint about the raw parameter type is passed to make
   * it easier for the implementation to make a guess based on runtime information.
   * As Scala is a statically typed language, the javaType parameter is ignored
   */
  def getParameterType(index: Int, javaType: Type) = {
    parameterInfos(index)
  }

  /**
   * Invokes the step definition. The method should raise a Throwable
   * if the invocation fails, which will cause the step to fail.
   */
  override def execute(args: Array[AnyRef]): Unit = {
    f(args.toList)
  }

  /**
   * Return true if this matches the location. This is used to filter
   * stack traces.
   */
  override def isDefinedAt(stackTraceElement: StackTraceElement) = stackTraceElement == frame

  /**
   * @return the pattern associated with this instance. Used for error reporting only.
   */
  override def getPattern = pattern

  override def isScenarioScoped = false
}
