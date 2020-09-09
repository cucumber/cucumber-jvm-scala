import groovy.text.SimpleTemplateEngine
import io.cucumber.gherkin.GherkinDialectProvider
import java.nio.file.Files

SimpleTemplateEngine engine = new SimpleTemplateEngine()
def templateSource = new File(project.baseDir, "../sources/src/main/groovy/I18n.scala.gsp").getText()

def unsupported = ["em"] // The generated files for Emoji do not compile.
GherkinDialectProvider dialectProvider = new GherkinDialectProvider()

def binding = ["dialectProvider":dialectProvider, "unsupported":unsupported]
template = engine.createTemplate(templateSource).make(binding)

def file = new File(project.baseDir, "target/generated-sources/i18n/io/cucumber/scala/I18n.scala")
Files.createDirectories(file.parentFile.toPath())
file.write(template.toString(), "UTF-8")