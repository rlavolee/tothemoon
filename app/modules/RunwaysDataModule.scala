package modules

import javax.inject.Singleton

import com.github.tototoshi.csv.CSVReader
import models.csv.{CsvRow, Runway}
import play.api.inject.{Binding, Module}
import play.api.{Configuration, Environment, Logger}

import scala.collection.parallel.immutable.ParVector
import scala.io.Source

@Singleton
class RunwaysManager {
  Logger.info("Warming Runways Manager")

  private val csvFilePath = "csv-source/runways.csv"

  val runways: ParVector[Runway] = {
    for {
      l <- CSVReader.open(Source.fromResource(csvFilePath)).all().toVector.tail.par
      v <- Runway.from(CsvRow(l))
    } yield v
  }

  Logger.info(s"Runways Manager ready with ${runways.length} countries")
}

class RunwaysDataModule extends Module {
  override def bindings(environment: Environment, configuration: Configuration): Seq[Binding[RunwaysManager]] = {
    Seq(bind[RunwaysManager].toInstance(new RunwaysManager))
  }
}
