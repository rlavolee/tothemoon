package models.csv

import models.traits.CSVApplyAble
import play.api.libs.json.{Json, OFormat}

case class Airport(id: Airport.ID, name: String, countryCode: CountryCode)

object Airport extends CSVApplyAble[Airport] {
  type ID = Int

  implicit val format: OFormat[Airport] = Json.format[Airport]

  def from(r: CsvRow): Option[Airport] = {
    r.values match {
      case List(i: String, _, _, n: String, _, _, _, _, c: String, _*)
      => Some(Airport(i.toInt, n, CountryCode(c)))
      case _ => None
    }
  }
}