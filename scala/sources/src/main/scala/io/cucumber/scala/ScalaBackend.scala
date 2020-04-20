package io.cucumber.scala

import java.lang.reflect.Modifier
import java.net.URI
import java.util.function.Supplier
import java.util.{List => JList}

import io.cucumber.core.backend._
import io.cucumber.core.resource.{ClasspathScanner, ClasspathSupport}

import scala.collection.JavaConverters._
import scala.util.Try

class ScalaBackend(classLoaderProvider: Supplier[ClassLoader]) extends Backend {

  private val classFinder = new ClasspathScanner(classLoaderProvider)

  private[scala] var scalaGlueInstances: Seq[ScalaDsl] = Nil

  override def disposeWorld(): Unit = {
    scalaGlueInstances = Nil
  }

  override def getSnippet(): Snippet = {
    new ScalaSnippet()
  }

  override def buildWorld(): Unit = {
    // Nothing to do
  }

  override def loadGlue(glue: Glue, gluePaths: JList[URI]): Unit = {

    val dslClasses = gluePaths.asScala
      .filter(gluePath => ClasspathSupport.CLASSPATH_SCHEME.equals(gluePath.getScheme))
      .map(ClasspathSupport.packageName)
      .flatMap(basePackageName => classFinder.scanForSubClassesInPackage(basePackageName, classOf[ScalaDsl]).asScala)
      .filter(glueClass => !glueClass.isInterface)
      .filter(glueClass => glueClass.getConstructors.length > 0)

    val (clsClasses, objClasses) = dslClasses.partition(isRegularClass)

    val objInstances = objClasses.map { cls =>
      val instField = cls.getDeclaredField("MODULE$")
      instField.setAccessible(true)
      instField.get(null).asInstanceOf[ScalaDsl]
    }
    val clsInstances = clsClasses.map {
      _.newInstance()
    }

    // FIXME we should not create instances above but fill the container like Cucumber Java does
    // https://github.com/cucumber/cucumber-jvm-scala/issues/1
    //clsClasses.foreach(container.addClass(_))
    scalaGlueInstances = objInstances.toSeq ++ clsInstances

    val glueAdaptor = new GlueAdaptor(glue)

    scalaGlueInstances.foreach { glueInstance =>
      glueAdaptor.addDefinition(glueInstance.registry)
    }

    ()
  }

  private def isRegularClass(cls: Class[_]): Boolean = {
    Try(Modifier.isPublic(cls.getConstructor().getModifiers)).getOrElse(false)
  }

}
