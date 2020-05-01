package io.cucumber.scala

<% dialectProvider.getLanguages().findAll { !unsupported.contains(it) }.each { language -> %>
trait ${language.replaceAll("[\\s-]", "_").toUpperCase()} {
  this: ScalaDsl =>
<% dialectProvider.getDialect(language, null).stepKeywords.findAll { !it.contains('*') && !it.matches("^\\d.*") }.sort().unique().each { kw -> %>
  val ${java.text.Normalizer.normalize(kw.replaceAll("[\\s',!]", ""), java.text.Normalizer.Form.NFC)} = new Step("${java.text.Normalizer.normalize(kw.replaceAll("[\\s',!]", ""), java.text.Normalizer.Form.NFC)}")
<% } %>
}
<% } %>
