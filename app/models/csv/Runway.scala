package models.csv

import models.traits.CSVApplyAble
import play.api.libs.json.{Json, OFormat}

case class Runway(aid: Airport.ID, length: String, width: String, surface: String, le_ident: String)

object Runway extends CSVApplyAble[Runway] {

  implicit val format: OFormat[Runway] = Json.format[Runway]

  def from(r: CsvRow): Option[Runway] = {
    r.values match {
      case List(_, apt: String, _, l: String, w: String, s: String, _, _, le_ident: String, _*)
      => Some(Runway(apt.toInt, l, w, s, le_ident))
      case _ => None
    }
  }
}