package tests.docstring

import io.cucumber.scala.{EN, ScalaDsl}

class DocStringSteps extends ScalaDsl with EN {

  case class JsonText(json: String)

  case class XmlText(xml: String)

  var _text: Any = _

  DocStringType("json") { (text) =>
    JsonText(text)
  }

  DocStringType("xml") { (text) =>
    XmlText(text)
  }

  Given("the following json text") { (json: JsonText) =>
    _text = json
  }

  Given("the following xml text") { (xml: XmlText) =>
    _text = xml
  }

  Then("I have a json text") {
    assert(_text.isInstanceOf[JsonText])
  }

  Then("I have a xml text") {
    assert(_text.isInstanceOf[XmlText])
  }

}
