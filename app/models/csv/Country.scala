package models.csv

import models.traits.CSVApplyAble
import play.api.libs.json.{Json, OFormat}

case class Country(id: Int, code: CountryCode, name: String, continent: String, link: String, keywords: String)

object Country extends CSVApplyAble[Country] {

  implicit val format: OFormat[Country] = Json.format[Country]

  def from(r: CsvRow): Option[Country] = {
    r.values match {
      case List(i: String, c: String, n: String, co: String, l: String, k: String)
             => Some(Country(i.toInt, CountryCode(c), n, co, l, k))
      case _ => None
    }
  }
}