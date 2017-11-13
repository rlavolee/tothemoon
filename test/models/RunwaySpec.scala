package models

import models.csv.Runway
import org.scalatest.{Matchers, WordSpecLike}
import play.api.libs.json.{JsSuccess, Json}

import scala.collection.parallel.immutable.ParVector

class RunwaySpec extends WordSpecLike with Matchers {

  private val csvFilePath = "runways.csv"

  private val runway = Runway(6523, "80", "80", "ASPH-G", "H1")

  private val runways = ParVector(
    runway,
    Runway(6524, "2500", "70", "GRVL", "N"),
    Runway(6525, "2300", "200", "TURF", "01")
  )

  private val runwayJsObject =
    Json.parse("""{"aid":6523, "length":"80", "width":"80", "surface":"ASPH-G", "le_ident":"H1"}""")

  "runway" should {
    "be created with correct csv row" in {
      Runway.applyFromCSV(csvFilePath) shouldEqual runways
    }
    "be created with correct json object" in {
      Json.fromJson[Runway](runwayJsObject) shouldEqual JsSuccess(runway)
    }
  }

}