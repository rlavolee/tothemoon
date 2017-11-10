package models.traits

import models.csv.CsvRow

trait CSVApplyAble[T] {
  def from(r: CsvRow): Option[T]
}
