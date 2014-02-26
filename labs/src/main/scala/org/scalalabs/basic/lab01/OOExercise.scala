package org.scalalabs.basic.lab01
import scala.language.implicitConversions
/**
 * The goal of this exercise is to get familiar basic OO constructs in scala
 *
 * Fix the code so that the unit test 'CurrencyExerciseTest' passes.
 *
 * In order for the tests to pass you need to do the following:
 * 
 * Exercise 1:
 * - Create a class Euro
 * - Provide it with two constructor parameters: euro:Int, cents:Int
 * - Provide the cents field with default value: 0
 * - Provide an immutable field named: inCents that converts euro + cents into cents.
 * - Create an object Euro with a factory method named: fromCents that creates an Euro based on cents.
 * - Create a method named: + to the Euro class that adds another Euro
 * - Create a method named: * to the Euro class that multiplies an Euro
 * 
 * Exercise 2:
 * - Create an abstract class Currency
 * - Provide it with one constructor parameter: symbol:String
 * - Extend the previously created Euro class from Currency
 * - Override the toString method of Euro to represent the following String:
 *   -> symbol + ': ' + euro + ',' + cents.  E.g: EUR 200,05
 * - In case the cents are 0 use this representation:
 *   -> symbol + ': ' + euro + ',--. E.g.: EUR 200.--
 *   
 * Exercise 3:
 * - Mix the Ordered trait in Euro
 * - Implement the compare method  
 * 
 * Exercise 4:
 * - Provide an implicit class that adds a *(euro:Euro) method to Euro
 * - Create a new currency Dollar
 * - Provide a conversion method that converts from Euro to Dollar
 */
class Euro(var euro:Int = 0, var cents:Int = 0) extends Currency("EUR") with Ordered[Euro]{
  val inCents = euro*100 + cents
  def + (operand:Euro) = Euro.fromCents(inCents + operand.inCents)
  def * (operand:Int) = Euro.fromCents(inCents * operand)

  override def toString = symbol + ": " + euro + "," +
    (if (cents == 0) "--" else if(cents < 10) "0" + cents else cents)

  def compare(that: Euro): Int = this.inCents - that.inCents
}

object Euro {
  def fromCents(value:Int) = new Euro(value/100, value%100)

  implicit class extension(mult : Int) {
    def *(euro:Euro) = Euro.fromCents(mult * euro.inCents)
  }

  implicit def dollarToEuro(dollar : Dollar) = Euro.fromCents((dollar.inCents * 142) / 150)
}

class Dollar(var dollars:Int = 0, var cents:Int = 0) extends Currency("USD") with Ordered[Dollar]{
  val inCents = dollars*100 + cents

  def compare(that: Dollar): Int = this.inCents - that.inCents
}


abstract class Currency(var symbol:String)