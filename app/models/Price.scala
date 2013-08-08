package models

import models.database.Prices
import org.joda.money.{CurrencyUnit, Money}
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.Play.current

/**
 * A single price value.
 */
case class Price(id: Option[Long], value: Money)

object Price {

  /**
   * Returns an unsorted list of all prices.
   */
  def find: List[Price] = DB.withSession { implicit session =>
    Query(Prices).list
  }

  /**
   * Inserts a new price for the given `Money` instance.
   */
  def insert(price: Money): Price = DB.withSession { implicit session =>
    val id = Prices.forInsert.insert(Price(None, price))
    Price(Some(id), price)
  }
}