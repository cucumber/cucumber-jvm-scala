package docstring

import io.cucumber.scala.{EN, ScalaDsl}

class DocStringSteps extends ScalaDsl with EN {

  case class JsonText(json: String)

  case class XmlText(xml: String)

  case class RawText(raw: String)

  var _text: Any = _

  DocStringType("json") { (text) =>
    JsonText(text)
  }

  DocStringType("xml") { (text) =>
    XmlText(text)
  }

  DocStringType("") { (text) =>
    RawText(text)
  }

  // Tests generic type
  DocStringType[Seq[String]]("") { (text) =>
    text.split('\n').toSeq
  }

  DocStringType[Seq[Int]]("") { (text) =>
    text.split('\n').map(_.toInt).toSeq
  }

  Given("the following json text") { (json: JsonText) =>
    _text = json
  }

  Given("the following xml text") { (xml: XmlText) =>
    _text = xml
  }

  Given("the following raw text") { (raw: RawText) =>
    _text = raw
  }

  Given("the following string list") { (list: Seq[String]) =>
    _text = list
  }

  Given("the following int list") { (list: Seq[Int]) =>
    _text = list
  }

  Then("I have a json text") {
    assert(_text.isInstanceOf[JsonText])
  }

  Then("I have a xml text") {
    assert(_text.isInstanceOf[XmlText])
  }

  Then("I have a raw text") {
    assert(_text.isInstanceOf[RawText])
  }

  Then("I have a string list {string}") { (expectedList: String) =>
    assert(_text.isInstanceOf[Seq[_]])
    assert(_text.asInstanceOf[Seq[_]].head.isInstanceOf[String])
    assert(_text.asInstanceOf[Seq[String]] == expectedList.split(',').toSeq)
  }

  Then("I have a int list {string}") { (expectedList: String) =>
    assert(_text.isInstanceOf[Seq[_]])
    assert(_text.asInstanceOf[Seq[_]].head.isInstanceOf[Int])
    assert(
      _text.asInstanceOf[Seq[Int]] == expectedList.split(',').map(_.toInt).toSeq
    )
  }

}
