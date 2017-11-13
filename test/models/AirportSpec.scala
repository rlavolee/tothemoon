package models

import models.csv.{Airport, CountryCode}
import org.scalatest.{Matchers, WordSpecLike}
import play.api.libs.json.{JsSuccess, Json}

import scala.collection.parallel.immutable.ParVector

class AirportSpec extends WordSpecLike with Matchers {

  private val csvFilePath = "airports.csv"

  private val airport = Airport(6523, "Total Rf Heliport", CountryCode("US"))

  private val airports = ParVector(airport,
    Airport(6524, "Lowell Field", CountryCode("US")))

  private val airportJsObject = Json.parse("""{"id":6523, "name":"Total Rf Heliport", "countryCode": "US"}""")

  "Airport" should {
    "be created with correct csv row" in {
      Airport.applyFromCSV(csvFilePath) shouldEqual airports
    }
    "be created with correct json object" in {
      Json.fromJson[Airport](airportJsObject) shouldEqual JsSuccess(airport)
    }
  }

}