package controllers

import javax.inject.{Inject, Singleton}

import helper.DataAggregator
import play.api.libs.json.Json
import play.api.mvc._

/**
  * A very small controller that renders a home page.
  */
@Singleton
class ReportController @Inject()(cc: ControllerComponents, da: DataAggregator) extends AbstractController(cc) {

  def highestNumberAirports = Action { implicit request =>
    Ok(Json.toJson(da.getTopCountriesWithHighestNumberAirports))
  }

  def lowestNumberAirports = Action { implicit request =>
    Ok(Json.toJson(da.getTopCountriesWithLowestNumberAirports))
  }

  def typeOfRunways = Action { implicit request =>
    Ok(Json.toJson(da.getTypeRunwaysPerCountry))
  }

}
