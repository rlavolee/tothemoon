package controllers

import javax.inject.{Inject, Singleton}

import helper.DataAggregator
import play.api.libs.json.Json
import play.api.mvc._

/**
  * A very small controller that renders a home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents, da: DataAggregator) extends AbstractController(cc) {

  def index = Action { implicit request =>
//    Ok(Json.toJson(da.getAirportsRunwaysFromCountryName("fran")))
//    Ok(Json.toJson(da.getTopCountriesWithHighestNumberAirports))
//    Ok(Json.toJson(da.getTopCountriesWithLowestNumberAirports))
    Ok(Json.toJson(da.getTypeRunwaysPerCountry))
  }

  // TODO adding rest api
  // TODO adding paginate

}
