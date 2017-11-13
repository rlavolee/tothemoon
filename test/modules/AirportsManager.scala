package modules

import models.csv.Airport

import scala.collection.parallel.immutable.ParVector

class AirportsManager {
  private val csvFilePath = "airports.csv"

  val airports: ParVector[Airport] = Airport.applyFromCSV(csvFilePath)
}
