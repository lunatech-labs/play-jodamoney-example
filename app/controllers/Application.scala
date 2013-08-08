package controllers

import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import org.joda.money.{Money, CurrencyUnit}
import models.Price
import models.MoneyConverters

object Application extends Controller {

  /**
   * Maps a currency code and amount to an `org.joda.money.Money` instance.
   */
  val currencyForm = Form(
    mapping(
      "code" -> text,
      "amount" -> bigDecimal
    )( (code, amount) => MoneyConverters.tuple2Money(code, amount) )
     ( (money: Money) => Some(money.getCurrencyUnit.getCode, money.getAmount) )
  )

  def index = Action {
    Ok(views.html.index(currencyForm.fill(Money.of(CurrencyUnit.EUR, 0)), Price.find))
  }

  /**
   * Inserts a `Money` value into the database.
   */
  def create = Action { implicit request =>
    currencyForm.bindFromRequest.fold(
      form => BadRequest(views.html.index(form, Price.find)),
      money => {
        Price.insert(money)
        Redirect(routes.Application.index)
      }
    )
  }
  
}