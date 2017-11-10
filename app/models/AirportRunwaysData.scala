package models

import models.csv.{Airport, Runway}
import play.api.libs.json.{Json, OFormat}

case class AirportRunwaysData (airport: Airport, runways: List[Runway])

object AirportRunwaysData {
  implicit val format: OFormat[AirportRunwaysData] = Json.format[AirportRunwaysData]
}
