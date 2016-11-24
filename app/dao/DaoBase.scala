package dao

import scala.concurrent.Future

trait DaoBase[T]{
  def nowAsTimestamp() = {
    val utilDate = new java.util.Date()
    val now = new java.sql.Timestamp(utilDate.getTime())
    now
  }

  def get(id: Int): Future[Option[T]]

  def create(createContentTag: T): Future[Int]

  def all(): Future[Seq[T]]

  def update(updateContentTag: T): Future[Option[Int]]
}
