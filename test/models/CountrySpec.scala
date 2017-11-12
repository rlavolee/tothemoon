package models

import models.csv.{Country, CountryCode}
import org.scalatest.{Matchers, WordSpecLike}

import scala.collection.parallel.immutable.ParVector

class CountrySpec extends WordSpecLike with Matchers {

  private val csvFilePath = "countries.csv"

  private val countries = ParVector(
    Country(302672, CountryCode("AD"), "Andorra","EU","http://en.wikipedia.org/wiki/Andorra", ""),
    Country(302618, CountryCode("AE"), "United Arab Emirates","AS","http://en.wikipedia.org/wiki/United_Arab_Emirates","UAE"),
    Country(302619, CountryCode("AF"), "Afghanistan","AS","http://en.wikipedia.org/wiki/Afghanistan", "")
  )

  "Country" should {
    "be created with correct csv row" in {
      Country.applyFromCSV(csvFilePath) shouldEqual countries
    }
  }

}