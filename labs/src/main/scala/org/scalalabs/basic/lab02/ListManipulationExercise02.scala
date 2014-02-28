package org.scalalabs.basic.lab02

object ListManipulationExercise02 {

  /**
   * Find the maximum element in a list, e.g. maxElementInList(List(1,9,3,5)) == 9
   * As usual, various ways exist: pattern matching, folding, ...
   */
  def maxElementInList(l: List[Int]): Int = {
    l.foldRight(l.head)((l,r) => Math.max(l, r))
  }

  /**
   * Calculate the sum of the equally position elements
   * of the two list
   */
  def sumOfTwo(l1: List[Int], l2: List[Int]): List[Int] = {
    var res:List[Int] = List()
    for (i <- 0 to Math.max(l1.length, l2.length)-1) {
      val a = if (l1.length <= i) 0 else l1(i)
      val b = if (l2.length <= i) 0 else l2(i)
      res = res ::: List(a + b)
    }

    res
  }

  /**
   *  For this exercise preferably make use of the sumOfTwo
   * method above
   */
  def sumOfMany(l: List[Int]*): List[Int] = {
    l.foldLeft(List[Int]())((l,r) => sumOfTwo(l, r))
  }

  case class Person(age: Int, firstName: String, lastName: String)

  /**
   * The following method is implemented in the most in-elegant way we could think of.
   * The idea is to re-write the method into more functional style. In the end, you
   * may be able to achieve the same functionality as implemented below
   * in a one-liner.
   */
  def separateTheMenFromTheBoys(persons: List[Person]): List[List[String]] = {
    val boys = persons.sortBy(_.age).foldLeft(List[String]())((l,r) => if(r.age < 18) l ::: List(r.firstName) else l)
    val men = persons.sortBy(_.age).foldLeft(List[String]())((l,r) => if(r.age >= 18) l ::: List(r.firstName) else l)
    List(boys, men)
  }

}