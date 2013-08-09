package models.database

import play.api.db.slick.Config.driver.simple._
import org.joda.money.{CurrencyUnit, Money}
import models.Product
import models.JodaMoney._

private[models] object Products extends Table[Product]("PRODUCT") {

  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)

  def priceCurrency = column[String]("PRICE_CURRENCY")
  def priceAmount = column[BigDecimal]("PRICE_AMOUNT", O.DBType("DECIMAL(13,3)"))

  def * = id.? ~ priceCurrency ~ priceAmount <> (
    { p => Product(p._1, p._2 -> p._3) } ,
    { (p: Product) => Some((p.id, p.price.getCurrencyUnit.getCode, p.price.getAmount)) })

  def forInsert = * returning id
}
