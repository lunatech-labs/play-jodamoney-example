package models.database

import play.api.db.slick.Config.driver.simple._
import org.joda.money.{CurrencyUnit, Money}
import models.Price
import models.MoneyConverters._

private[models] object Prices extends Table[Price]("PRICE") {

  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)

  def priceCode = column[String]("PRICE_CODE")
  def priceAmount = column[BigDecimal]("PRICE_AMOUNT", O.DBType("DECIMAL(13,2)"))

  def * = id.? ~ priceCode ~ priceAmount <> (
    { p => Price(p._1, p._2 -> p._3) } ,
    { (p: Price) => Some((p.id, p.value.getCurrencyUnit.getCode, p.value.getAmount)) })

  def forInsert = * returning id
}
