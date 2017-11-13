package models.csv

import play.api.libs.json.{Format, JsString, Reads, Writes}

case class CountryCode(value: String) extends AnyVal {
  override def toString: String = value
}

object CountryCode {

  def assert: String => Boolean = _.length <= 2

  implicit val reads: Reads[CountryCode] = Reads(_.validate[String].filter(assert).map(CountryCode(_)))

  implicit val format: Format[CountryCode] = Format(reads, Writes(v => JsString(v.value)))
}