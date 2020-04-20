import groovy.text.SimpleTemplateEngine
import gherkin.GherkinDialectProvider

def engine = new SimpleTemplateEngine()
def templateSource = new File(project.baseDir, "..${File.separator}sources${File.separator}src${File.separator}main${File.separator}groovy${File.separator}I18n.scala.gsp").getText()

def unsupported = ["em"]
def dialectProvider = new GherkinDialectProvider()
def binding = ["dialectProvider":dialectProvider, "unsupported":unsupported]
template = engine.createTemplate(templateSource).make(binding)
def file = new File(project.baseDir, "target${File.separator}generated-sources${File.separator}i18n${File.separator}io${File.separator}cucumber${File.separator}scala${File.separator}I18n.scala")
file.parentFile.mkdirs()
file.write(template.toString(), "UTF-8")