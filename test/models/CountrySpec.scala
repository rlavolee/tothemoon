package models

import models.csv.{Country, CountryCode}
import org.scalatest.{Matchers, WordSpecLike}
import play.api.libs.json.{JsSuccess, Json}

import scala.collection.parallel.immutable.ParVector

class CountrySpec extends WordSpecLike with Matchers {

  private val csvFilePath = "countries.csv"

  private val country = Country(302672, CountryCode("AD"), "Andorra","EU","http://en.wikipedia.org/wiki/Andorra", "")

  private val countries = ParVector(
    country,
    Country(302618, CountryCode("AE"), "United Arab Emirates","AS","http://en.wikipedia.org/wiki/United_Arab_Emirates","UAE"),
    Country(302619, CountryCode("AF"), "Afghanistan","AS","http://en.wikipedia.org/wiki/Afghanistan", ""),
    Country(302755, CountryCode("US"), "United States","NA","http://en.wikipedia.org/wiki/United_States","America")
  )

  private val countryJsObject =
    Json.parse("""{"id":302672, "code":"AD", "name":"Andorra", "continent":"EU", "link":"http://en.wikipedia.org/wiki/Andorra", "keywords":""}""")


  "Country" should {
    "be created with correct csv row" in {
      Country.applyFromCSV(csvFilePath) shouldEqual countries
    }
    "be created with correct json object" in {
      Json.fromJson[Country](countryJsObject) shouldEqual JsSuccess(country)
    }
  }

}