package org.example

import munit.FunSuite

class ListDataStructureSuite extends FunSuite {
  test("Nil should be represented as 'Nil'") {
    val ex1: List[Double] = Nil
    assert(ex1.toString == "Nil")
  }

  test("Cons should be represented correctly") {
    val ex2: List[Int] = Cons(1, Nil)
    assert(ex2.toString == "Cons(1, Nil)")

    val ex3: List[String] = Cons("a", Cons("b", Nil))
    assert(ex3.toString == "Cons(a, Cons(b, Nil))")
  }
}