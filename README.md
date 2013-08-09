# Joda Money example for Play and Slick

Sample Play framework application to demonstate using [Joda Money](http://joda-money.sourceforge.net).

This includes:

* template helper for rendering currency input - `views/helper/inputCurrency.scala.html`
* form mapping for nested `org.joda.money.Money` values - `app.models.JodaMoney.jodaMoney`
* implicit conversion from a (currency, amount) tuples to `Money` values - `app.models.JodaMoney.tuple2Money`
* Slick persistence using the MySQL `DECIMAL(13,3)` column type.

This example requires Java 7, for the use of `java.util.Currency.getDisplayName` in the page template. To use Java 6,
replace this with a message look-up.
