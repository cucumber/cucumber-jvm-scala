package io.cucumber.scala

import io.cucumber.core.backend._
import io.cucumber.core.backend.discovery.{
  GlueDiscoveryRequest,
  GlueDiscoverySelectorResolver
}
import io.cucumber.core.resource.ClasspathScanner
import io.cucumber.scala.ScalaBackend.isRegularClass

import java.lang.reflect.Modifier
import java.util.function.Supplier
import scala.jdk.CollectionConverters._
import scala.util.{Failure, Try}

object ScalaBackend {

  /** @return
    *   true if it's a class, false if it's an object
    */
  private[scala] def isRegularClass(cls: Class[_]): Try[Boolean] = {
    Try {
      // Object don't have constructors
      cls.getConstructors.headOption
        .map(_.getModifiers)
        .exists(Modifier.isPublic)
    }.recoverWith { case ex: Throwable =>
      Failure(new UnknownClassType(cls, ex))
    }
  }

}

class ScalaBackend(
    lookup: Lookup,
    container: Container,
    classLoaderProvider: Supplier[ClassLoader]
) extends Backend {

  private val resolver = new GlueDiscoverySelectorResolver(
    new ClasspathScanner(classLoaderProvider),
    (cls: Class[_]) =>
      classOf[ScalaDsl] != cls && classOf[ScalaDsl].isAssignableFrom(cls)
  )

  private var glueAdaptor: GlueAdaptor = _
  private[scala] var scalaGlueClasses: Seq[Class[_ <: ScalaDsl]] = Nil

  override def disposeWorld(): Unit = {
    // Nothing to do
  }

  override def getSnippet(): Snippet = {
    new ScalaSnippet()
  }

  override def buildWorld(): Unit = {
    // Instantiate all the glue classes and load the glue code from them
    scalaGlueClasses.foreach { glueClass =>
      val glueInstance = Option(lookup.getInstance(glueClass))
      glueInstance match {
        case Some(glue) =>
          glueAdaptor.loadRegistry(glue.registry, scenarioScoped = true)
        case None =>
          throw new CucumberBackendException(
            s"Not able to instantiate class ${glueClass.getName}. Please report this issue to cucumber-scala project."
          )
      }
    }
  }

  override def loadGlue(glue: Glue, request: GlueDiscoveryRequest): Unit = {

    glueAdaptor = new GlueAdaptor(glue)

    // The predicate only applies to the classes found by scanning the glue paths
    val dslClasses = resolver
      .resolve(request)
      .iterator()
      .asScala
      .map { cls =>
        // Can only happen for classes explicitly selected (glue classes)
        if (!classOf[ScalaDsl].isAssignableFrom(cls)) {
          throw new CucumberBackendException(
            s"Class ${cls.getName} is not a glue class, it must extend ${classOf[ScalaDsl].getName}."
          )
        }
        cls.asSubclass(classOf[ScalaDsl])
      }
      .filter(glueClass =>
        glueClass != classOf[ScalaDsl] && !glueClass.isInterface
      )
      .toSeq
      .distinct

    // Voluntarily throw exception if not able to identify if it's a class
    val (clsClasses, objClasses) =
      dslClasses.partition(c => isRegularClass(c).get)

    // Retrieve Scala objects (singletons)
    val objInstances = objClasses.map { cls =>
      val instField = cls.getDeclaredField("MODULE$")
      instField.setAccessible(true)
      instField.get(null).asInstanceOf[ScalaDsl]
    }

    // Regular Scala classes are added to the container, they will be instantiated by the container depending on its logic
    // Object are not because by definition they are singletons
    clsClasses.foreach { glueClass =>
      container.addClass(glueClass)
      scalaGlueClasses = scalaGlueClasses :+ glueClass
    }

    // For object, we add the definitions here, once for all
    objInstances.foreach { glueInstance =>
      glueAdaptor.loadRegistry(glueInstance.registry, scenarioScoped = false)
    }

    ()
  }

}
