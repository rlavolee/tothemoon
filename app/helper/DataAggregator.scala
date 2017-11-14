package helper

import java.util.NoSuchElementException
import javax.inject.{Inject, Singleton}

import models.AirportRunwaysData
import models.csv.CountryCode
import models.reports.{CountryReport, RunwayReport, SurfaceReport}
import modules.{AirportsManager, CountriesManager, RunwaysManager}

import scala.concurrent.{ExecutionContext, Future}

/**
  * Aggregate data from in memory collections.
  */
@Singleton
class DataAggregator @Inject()(cm: CountriesManager, am: AirportsManager, rm: RunwaysManager)(implicit ec: ExecutionContext) {

  /**
   * return List of airports & runways at each airport from Country Code.
   */
  def getAirportsRunwaysFromCountryCode(cc: CountryCode): Future[List[AirportRunwaysData]] = {
    val r = (for {
      a <- am.airports.filter(_.countryCode == cc)
      ard = AirportRunwaysData(a, rm.runways.filter(_.aid == a.id).toList)
    } yield ard).toList

    Future.successful(r)
  }

  /**
    * return List of airports & runways at each airport from Country Name.
    */
  def getAirportsRunwaysFromCountryName(s: String): Future[List[AirportRunwaysData]] = {
    val r = cm.countries.filter(_.name.toLowerCase.startsWith(s)).map{ country =>
      getAirportsRunwaysFromCountryCode(country.code)
    }.toList

    Future.sequence(r).map(_.flatten)
  }


  /**
    * return List of CountryReport
    * 10 countries with highest number of airports (with count)
    * and countries with lowest number of airports.
    */
  private lazy val countryReports: List[CountryReport] = {
    (for {
      groupedAirports <- am.airports.groupBy(_.countryCode)
      airportsCounts = groupedAirports._2.length
      country <- cm.countries.filter(_.code == groupedAirports._1)
    } yield {
      CountryReport(country, airportsCounts)
    }).toList
  }

  private def getTopCountriesSortedWith(f: (CountryReport, CountryReport) => Boolean) =
    countryReports.sortWith(f).take(10)

  lazy val getTopCountriesWithHighestNumberAirports: Future[List[CountryReport]] =
    Future.successful(getTopCountriesSortedWith(CountryReport.highestNumber))

  lazy val getTopCountriesWithLowestNumberAirports: Future[List[CountryReport]] =
    Future.successful(getTopCountriesSortedWith(CountryReport.lowestNumber))


  /**
    * return List of type of runways (as indicated in "surface" column) per country
    */
  lazy val getTypeRunwaysPerCountry: Future[List[SurfaceReport]] = {
    val r = (for {
      // group runway by airport ID
      s <- rm.runways.groupBy(_.aid)
      // get airport by ID to reach country code
      o <- am.airports.filter(_.id == s._1)
    } yield {
      RunwayReport(o.countryCode, s._2)
    }).foldLeft[Map[CountryCode, Set[String]]](Map.empty)((a, b) => {
      // adding surface according to country code
      val r = a.getOrElse(b.cc, Set.empty[String]) ++ b.runways.map(_.surface)
      a + (b.cc -> r)
    }).map(r => SurfaceReport(r._1, r._2)).toList

    Future.successful(r)
  }
}
