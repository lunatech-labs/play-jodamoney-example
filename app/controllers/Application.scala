package controllers

import play.api.mvc._
import play.api.data.{Mapping, Form}
import play.api.data.Forms._
import org.joda.money.{Money, CurrencyUnit}
import models.Product
import models.JodaMoney.jodaMoney

object Application extends Controller {

  /**
   * Maps a product form to a `Product`, which includes an `org.joda.money.Money` instance.
   */
  val productForm = Form(
    mapping(
      "id" -> ignored(Option.empty[Long]),
      "price" -> jodaMoney
    )(Product.apply _)(Product.unapply _)
  )

  /**
   * Render the web page, including the form.
   */
  def index = Action {
    Ok(views.html.index(productForm.fill(Product(None, Money.of(CurrencyUnit.EUR, 0))), Product.find))
  }

  /**
   * Inserts a `Product` value into the database.
   */
  def create = Action { implicit request =>
    productForm.bindFromRequest.fold(
      form => BadRequest(views.html.index(form, Product.find)),
      product => {
        Product.insert(product.price)
        Redirect(routes.Application.index)
      }
    )
  }

}