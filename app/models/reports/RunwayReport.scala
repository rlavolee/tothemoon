package models.reports

import models.csv.{CountryCode, Runway}

import scala.collection.parallel.immutable.ParVector

case class RunwayReport(cc: CountryCode, runways: ParVector[Runway])

object RunwayReport