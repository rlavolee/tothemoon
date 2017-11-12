package modules

import javax.inject.Singleton

import models.csv.{Country, CsvRow}
import play.api.{Configuration, Environment, Logger}
import play.api.inject.{Binding, Module}

import scala.collection.parallel.immutable.ParVector
import scala.io.Source

@Singleton
class CountriesManager {
  Logger.info("Warming Countries Manager")

  private val csvFilePath = "csv-source/countries.csv"

  val countries: ParVector[Country] = Country.applyFromCSV(csvFilePath)

  Logger.info(s"Countries Manager ready with ${countries.length} countries")
}

class CountriesDataModule extends Module {
  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[CountriesManager]] = {
    Seq(bind[CountriesManager].toInstance(new CountriesManager))
  }
}
