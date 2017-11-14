package controllers

import javax.inject.{Inject, Singleton}

import helper.DataAggregator
import play.api.libs.json.Json
import play.api.mvc._
import scala.concurrent.ExecutionContext

/**
  * Report controller that serves statistics.
  */
@Singleton
class ReportController @Inject()(cc: ControllerComponents, da: DataAggregator)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def highestNumberAirports = Action.async {
    da.getTopCountriesWithHighestNumberAirports.map { result =>
      Ok(Json.toJson(result))
    }
  }

  def lowestNumberAirports = Action.async {
    da.getTopCountriesWithLowestNumberAirports.map{ result =>
      Ok(Json.toJson(result))
    }
  }

  def typeOfRunways = Action.async {
    da.getTypeRunwaysPerCountry.map{ result =>
      Ok(Json.toJson(result))
    }
  }

}
