package models.reports

import models.csv.Country
import play.api.libs.json.{Json, OFormat}

case class CountryReport (country: Country, count: Int)

object CountryReport {
  implicit val format: OFormat[CountryReport] = Json.format[CountryReport]

  def highestNumber: (CountryReport, CountryReport) => Boolean = _.count > _.count

  def lowestNumber: (CountryReport, CountryReport) => Boolean = _.count < _.count
}
