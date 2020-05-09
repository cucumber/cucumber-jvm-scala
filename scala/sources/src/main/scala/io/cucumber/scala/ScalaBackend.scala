package io.cucumber.scala

import java.lang.reflect.Modifier
import java.net.URI
import java.util.function.Supplier
import java.util.{List => JList}

import io.cucumber.core.backend._
import io.cucumber.core.resource.{ClasspathScanner, ClasspathSupport}

import scala.jdk.CollectionConverters._
import scala.util.Try

class ScalaBackend(lookup: Lookup, container: Container, classLoaderProvider: Supplier[ClassLoader]) extends Backend {

  private val classFinder = new ClasspathScanner(classLoaderProvider)

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
      val glueInstance = lookup.getInstance(glueClass)
      glueAdaptor.loadRegistry(glueInstance.registry, scenarioScoped = true)
    }
  }

  override def loadGlue(glue: Glue, gluePaths: JList[URI]): Unit = {

    glueAdaptor = new GlueAdaptor(glue)

    val dslClasses = gluePaths.asScala
      .filter(gluePath => ClasspathSupport.CLASSPATH_SCHEME.equals(gluePath.getScheme))
      .map(ClasspathSupport.packageName)
      .flatMap(basePackageName => classFinder.scanForSubClassesInPackage(basePackageName, classOf[ScalaDsl]).asScala)
      .filter(glueClass => !glueClass.isInterface)

    val (clsClasses, objClasses) = dslClasses.partition(isRegularClass)

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

  private def isRegularClass(cls: Class[_]): Boolean = {
    Try(Modifier.isPublic(cls.getConstructor().getModifiers)).getOrElse(false)
  }

}
