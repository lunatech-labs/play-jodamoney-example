package models

import models.database.Products
import org.joda.money.{CurrencyUnit, Money}
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.Play.current

/**
 * A single price value.
 */
case class Product(id: Option[Long], price: Money)

object Product {

  /**
   * Returns an unsorted list of all prices.
   */
  def find: List[Product] = DB.withSession { implicit session =>
    Query(Products).list
  }

  /**
   * Inserts a new price for the given `Money` instance.
   */
  def insert(price: Money): Product = DB.withSession { implicit session =>
    val id = Products.forInsert.insert(Product(None, price))
    Product(Some(id), price)
  }
}