package controllers

import javax.inject.{Inject, Singleton}

import helper.DataAggregator
import helper.bindables.{FilterQuery, PaginateQuery}
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.ExecutionContext

/**
  * Airport controller that serves airport related info.
  */
@Singleton
class AirportController @Inject()(cc: ControllerComponents, da: DataAggregator)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def get(q: FilterQuery, p: PaginateQuery) = Action.async { implicit request =>
    (q.filter match {
      case Right(c) => da.getAirportsRunwaysFromCountryCode(c)
      case Left(s)  => da.getAirportsRunwaysFromCountryName(s)
    }).map{ result =>
      Ok(Json.toJson(p.slice(result)))
    }
  }

}
