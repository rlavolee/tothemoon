package helper

import models.AirportRunwaysData
import models.csv.{Airport, CountryCode, Runway}
import modules.{AirportsManager, CountriesManager, RunwaysManager}
import org.scalatest.{Matchers, WordSpecLike}

import scala.concurrent.duration._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Await

class DataAggregatorSpec extends WordSpecLike with Matchers {

  private val da = new DataAggregator(new CountriesManager, new AirportsManager, new RunwaysManager)

  private val airportsRunwaysFromCC =
    List(
      AirportRunwaysData(Airport(6523,"Total Rf Heliport",CountryCode("US")),List(Runway(6523,"80","80","ASPH-G","H1"))),
      AirportRunwaysData(Airport(6524,"Lowell Field",CountryCode("US")),List(Runway(6524,"2500","70","GRVL","N")))
    )

  "DataAggregator" should {
    "correctly aggregate Airports and Runways From a Country Code" in {
      val r = Await.result(da.getAirportsRunwaysFromCountryCode(CountryCode("US")), 5 seconds)
      r shouldEqual airportsRunwaysFromCC
    }
    "correctly aggregate Airports and Runways From a Country Name" in {
      val r = Await.result(da.getAirportsRunwaysFromCountryName("united"), 5 seconds)
      r shouldEqual airportsRunwaysFromCC
    }
  }

}
