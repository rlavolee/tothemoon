package modules

import models.csv.Country

import scala.collection.parallel.immutable.ParVector

class CountriesManager {
  private val csvFilePath = "countries.csv"

  val countries: ParVector[Country] = Country.applyFromCSV(csvFilePath)
}
