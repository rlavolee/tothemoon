package controllers

import javax.inject.{Inject, Singleton}

import helper.DataAggregator
import helper.bindables.{FilterQuery, PaginateQuery}
import play.api.libs.json.Json
import play.api.mvc._

/**
  * Airport controller that serves airport related info.
  */
@Singleton
class AirportController @Inject()(cc: ControllerComponents, da: DataAggregator) extends AbstractController(cc) {

  def get(q: FilterQuery, p: PaginateQuery) = Action { implicit request =>
    val r = q.filter match {
      case Right(c) => da.getAirportsRunwaysFromCountryCode(c)
      case Left(s)  => da.getAirportsRunwaysFromCountryName(s)
    }

    Ok(Json.toJson(p.slice(r)))
  }

}
