package models.traits

import models.csv.CsvRow
import com.github.tototoshi.csv.CSVReader
import scala.collection.parallel.immutable.ParVector
import scala.io.Source

trait CSVApplyAble[T] {
  def from(r: CsvRow): Option[T]

  def applyFromCSV(csvFilePath: String): ParVector[T] = {
    for {
      l <- CSVReader.open(Source.fromResource(csvFilePath)).all().toVector.tail.par
      v <- from(CsvRow(l))
    } yield v
  }
}
