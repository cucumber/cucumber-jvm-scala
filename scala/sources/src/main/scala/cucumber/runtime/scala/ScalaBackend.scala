package cucumber.runtime.scala

import java.lang.reflect.Modifier
import java.net.URI
import java.util.{List => JList}

import cucumber.api.scala.ScalaDsl
import cucumber.runtime.{Backend, Glue}
import cucumber.runtime.io.{ResourceLoader, ResourceLoaderClassFinder}
import cucumber.runtime.snippets.{FunctionNameGenerator, SnippetGenerator}
import gherkin.pickles.PickleStep
import io.cucumber.stepexpression.TypeRegistry

import scala.collection.JavaConverters._

class ScalaBackend(resourceLoader: ResourceLoader, typeRegistry: TypeRegistry) extends Backend {
  private val snippetGenerator = new SnippetGenerator(new ScalaSnippetGenerator(), typeRegistry.parameterTypeRegistry())
  private var instances: Seq[ScalaDsl] = Nil


  def getStepDefinitions = instances.flatMap(_.getStepDefs(typeRegistry))

  def getBeforeHooks = instances.flatMap(_.beforeHooks)

  def getAfterHooks = instances.flatMap(_.afterHooks)

  def getAfterStepHooks = instances.flatMap(_.afterStepHooks)

  def getBeforeStepHooks = instances.flatMap(_.beforeStepHooks)

  def disposeWorld(): Unit = {
    instances = Nil
  }

  def getSnippet(step: PickleStep, keyword: String, functionNameGenerator: FunctionNameGenerator) = snippetGenerator.getSnippet(step, keyword, functionNameGenerator)

  def buildWorld(): Unit = {
    //I don't believe scala has to do anything to clean out its world
  }

  def loadGlue(glue: Glue, gluePaths: JList[URI]): Unit = {

    val cl = Thread.currentThread().getContextClassLoader
    val classFinder = new ResourceLoaderClassFinder(resourceLoader, cl)
    val packages = gluePaths.asScala
    val dslClasses = packages flatMap {
      classFinder.getDescendants(classOf[ScalaDsl], _).asScala
    } filter { cls =>
      try {
        cls.getDeclaredConstructor()
        true
      } catch {
        case e: Throwable => false
      }
    }

    val (clsClasses, objClasses) = dslClasses partition { cls =>
      try {
        Modifier.isPublic(cls.getConstructor().getModifiers)
      } catch {
        case e: Throwable => false
      }
    }
    val objInstances = objClasses map { cls =>
      val instField = cls.getDeclaredField("MODULE$")
      instField.setAccessible(true)
      instField.get(null).asInstanceOf[ScalaDsl]
    }
    val clsInstances = clsClasses.map {
      _.newInstance()
    }

    instances = objInstances.toSeq ++ clsInstances

    getStepDefinitions map {
      glue.addStepDefinition(_)
    }
    getBeforeHooks map {
      glue.addBeforeHook(_)
    }
    getAfterHooks map {
      glue.addAfterHook(_)
    }
    getAfterStepHooks map {
      glue.addAfterStepHook(_)
    }
    getBeforeStepHooks map {
      glue.addBeforeStepHook(_)
    }
    ()
  }

}
