package models.reports

import models.csv.CountryCode
import play.api.libs.json.Json

case class SurfaceReport (cc: CountryCode, surfaces: Set[String])

object SurfaceReport {
  implicit val format = Json.format[SurfaceReport]
}