package models

import org.joda.money.{CurrencyUnit, Money}
import java.math.RoundingMode

/**
 * Joda Money conversions
 */
object MoneyConverters {

  /**
   * Returns a money value for a decimal amount in the given currency, rounded to the currency’s scale.
   */
  implicit def tuple2Money(money: (String, BigDecimal)): Money = {
    val currency = CurrencyUnit.of(money._1)
    Money.of(currency, money._2.bigDecimal, RoundingMode.DOWN)
  }
}