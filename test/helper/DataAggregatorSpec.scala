package helper

import javax.inject.Inject

import models.AirportRunwaysData
import models.csv.{Airport, CountryCode, Runway}
import modules.{AirportsManager, CountriesManager, RunwaysManager}
import org.scalatest.{AsyncWordSpec, Matchers}

import scala.concurrent.ExecutionContext

class DataAggregatorSpec @Inject()(implicit ec: ExecutionContext)  extends AsyncWordSpec with Matchers {

  private val da = new DataAggregator(new CountriesManager, new AirportsManager, new RunwaysManager)

  private val airportsRunwaysFromCC =
    List(
      AirportRunwaysData(Airport(6523,"Total Rf Heliport",CountryCode("US")),List(Runway(6523,"80","80","ASPH-G","H1"))),
      AirportRunwaysData(Airport(6524,"Lowell Field",CountryCode("US")),List(Runway(6524,"2500","70","GRVL","N")))
    )

  "DataAggregator" should {
    "correctly aggregate Airports and Runways From a Country Code" in {
      da.getAirportsRunwaysFromCountryCode(CountryCode("US")).map(r => r shouldEqual airportsRunwaysFromCC)
    }
    "correctly aggregate Airports and Runways From a Country Name" in {
      da.getAirportsRunwaysFromCountryName("united").map(r => r shouldEqual airportsRunwaysFromCC)
    }
  }

}
