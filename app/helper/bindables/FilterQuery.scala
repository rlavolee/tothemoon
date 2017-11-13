package helper.bindables

import models.csv.CountryCode
import play.api.mvc.QueryStringBindable

case class FilterQuery (filter: Either[String, CountryCode])

object FilterQuery {
  implicit def queryStringBindable(implicit stringBinder: QueryStringBindable[String]) =
    new QueryStringBindable[FilterQuery] {
      override def bind(key: String, params: Map[String, Seq[String]]): Option[Either[String, FilterQuery]] = {
        for {
          filter <- stringBinder.bind("filter", params)
        } yield {
          filter match {
            case Right(f) => {
              if (CountryCode.assert(f)) {
                Right(FilterQuery(Right(CountryCode(f))))
              } else {
                Right(FilterQuery(Left(f)))
              }

            }
            case _ => Left("Unable to bind an FilterQuery")
          }
        }
      }

      override def unbind(key: String, value: FilterQuery): String ={
        val v: String = value.filter.getOrElse(value.filter.left.get).toString
        stringBinder.unbind("filter", v)
      }
    }
}