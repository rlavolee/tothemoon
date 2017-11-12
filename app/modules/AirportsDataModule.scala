package modules

import javax.inject.Singleton

import models.csv.{Airport, CsvRow}
import play.api.{Configuration, Environment, Logger}
import play.api.inject.{Binding, Module}

import scala.collection.parallel.immutable.ParVector
import scala.io.Source

@Singleton
class AirportsManager {
  Logger.info("Warming Airport Manager")

  private val csvFilePath = "csv-source/airports.csv"

  val airports: ParVector[Airport] = Airport.applyFromCSV(csvFilePath)

  Logger.info(s"Airport Manager ready with ${airports.length} countries")
}

class AirportsDataModule extends Module {
  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[AirportsManager]] = {
    Seq(bind[AirportsManager].toInstance(new AirportsManager))
  }
}
