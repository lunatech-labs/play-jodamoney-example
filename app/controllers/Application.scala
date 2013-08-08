package controllers

import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import org.joda.money.{Money, CurrencyUnit}

object Application extends Controller {

  /**
   * Maps a currency code and amount to an `org.joda.money.Money` instance.
   */
  val currencyForm = Form(
    mapping(
      "code" -> text,
      "amount" -> bigDecimal
    )( (code, amount) => Money.of(CurrencyUnit.of(code), amount.bigDecimal) )
     ( (money: Money) => Some(money.getCurrencyUnit.getCode, money.getAmount) )
  )

  def index = Action {
    Ok(views.html.index(currencyForm.fill(Money.of(CurrencyUnit.EUR, 0))))
  }

  def create = Action { implicit request =>
    currencyForm.bindFromRequest.fold(
      form => BadRequest(form.errorsAsJson),
      money => Ok(money.toString)
    )
  }
  
}