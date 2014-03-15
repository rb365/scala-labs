package org.scalalabs.basic.lab03
import scala.sys._
import scala._
import org.scalalabs.basic.lab03.Person

/**
 * This exercise introduces you to the powerful pattern matching features of Scala.
 *
 * Pattern matching can in its essence be compared to Java's 'switch' statement,
 * even though it provides many more possibilities. Whereas the Java switch statement
 * lets you 'match' primitive types up to int's, Scala's pattern matching goes much
 * further. Practically everything from all types of objects and Collections
 * can be matched, not forgetting xml and a special type of class called case classes.
 *
 * Pattern matching is also often used in combination with recursive algorithms.
 *
 * For this exercise exclusively use pattern matching constructs in order to make the
 * corresponding unit test work.
 *
 * Reference material to solve these exercises can be found here:
 * Pattern matching in general: http://programming-scala.labs.oreilly.com/ch03.html#PatternMatching
 * Pattern matching in combination with partial functions: http://programming-scala.labs.oreilly.com/ch08.html#PartialFunctions
 */

object PatternMatchingExercise {

  /*************************************************************************
   *  pattern matching exercises
   * For expected solution see unittest @PatternMatchingExerciseTest
   *************************************************************************/

  def describeLanguage(s: String) = s match {
    case "Java" => "OOP"
    case "Smalltalk" => "OOP"
    case "Clojure" => "Functional"
    case "Haskell" => "Functional"
    case "Scala" => "Hybrid"
    case "C" => "Procedural"
    case _ => "Unknown"
  }

  def matchOnInputType(in: Any) = in match {
    case in: String if in.length == 8 => "A string with length 8"
    case in: Int if in > 0 => "A positive integer"
    case in: Person if in.name == "Jack" => "A person with name: Jack"
    case in: Seq[Any] if in.length > 10 => "Seq with more than 10 elements"
    case Seq("first", "second", "third", "fourth") => "first: first, second: second, rest: List(third, fourth)"
    case in: Option[Any] => "A Scala Option subtype"
    case null => "A null value"
    case _ => "Some Scala class"
  }

  def older(p: Person): Option[String] = p match {
    case p : Person if p.age > 30 => Some(p.name)
    case _ => None
  }

  /*************************************************************************
   * Pattern matching with partial functions
   * For expected solution see @PatternMatchingExerciseTest
   *************************************************************************/

  val pf1: PartialFunction[String, String] = {
    case "scala-labs" => "true"
    case "stuff" => "true"
  }

  val pf2: PartialFunction[String, String] = {
    case "other stuff" => "true"
  }

  val pf3:PartialFunction[String, String] = {
    case in if pf1.isDefinedAt(in) => "true"
    case in if pf2.isDefinedAt(in) => "true"
  }

}

case class Person(name: String, age: Int)