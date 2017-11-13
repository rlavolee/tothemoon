package modules

import models.csv.Runway

import scala.collection.parallel.immutable.ParVector

class RunwaysManager {
  private val csvFilePath = "runways.csv"

  val runways: ParVector[Runway] = Runway.applyFromCSV(csvFilePath)
}