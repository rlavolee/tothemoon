package helper.bindables

import play.api.mvc.QueryStringBindable

case class PaginateQuery (page: Int, limit: Int = 20) {

  assert(page > 0, "page must be > 0")

  def slice[T](l: List[T]): List[T] =
    l.slice((page - 1) * limit, page * limit)
}

object PaginateQuery {

  implicit def paginateQueryBindable(implicit intBinder: QueryStringBindable[Int]) = {
    new QueryStringBindable[PaginateQuery] {
      override def bind(key: String, params: Map[String, Seq[String]]) = {
        for {
          page <- intBinder.bind(s"$key.page", params)
          limit <- Some(intBinder.bind(s"$key.limit", params).getOrElse(Right[String, Int](20)))
        } yield {
          (page, limit) match {
            case (Right(p), Right(l)) => Right(PaginateQuery(p, l))
            case x                    => Left(s"$x isn't bindable to PaginateQuery")
          }
        }
      }

      override def unbind(key: String, value: PaginateQuery) =
        intBinder.unbind(s"$key.page", value.page) + "&" + intBinder.unbind(s"$key.limit", value.limit)
    }
  }

}
