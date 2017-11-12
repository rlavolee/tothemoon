package models

import models.csv.{Airport, CountryCode}
import org.scalatest.{Matchers, WordSpecLike}

import scala.collection.parallel.immutable.ParVector

class AirportSpec extends WordSpecLike with Matchers {

  private val csvFilePath = "airports.csv"

  private val airports = ParVector(Airport(6523, "Total Rf Heliport", CountryCode("US")),
    Airport(6524, "Lowell Field", CountryCode("US")))

  "Airport" should {
    "be created with correct csv row" in {
      Airport.applyFromCSV(csvFilePath) shouldEqual airports
    }
  }

}