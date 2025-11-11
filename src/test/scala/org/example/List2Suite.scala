package org.example


class List2Suite  extends munit.FunSuite {
  test("Nil should be represented as 'Nil'") {
    val ex1: List2[Double] = List2.Nil
    val ex2: List2[Int] = List2.Cons(1, List2.Nil)
    val ex3: List2[String] = List2.Cons("a", List2.Cons("b", List2.Nil))

    assert(ex1.toString == "Nil")
    assert(ex2.toString == "Cons(1,Nil)")
    assert(ex3.toString == "Cons(a,Cons(b,Nil))")
  }

  test("sum should correctly sum a List2 of integers") {
    val intList = List2(1, 2, 3, 4, 5)
    assertEquals(List2.sum(intList), 15)

    val emptyList = List2[Int]()
    assertEquals(List2.sum(emptyList), 0)
  }

  test("product should correctly multiply a List2 of doubles") {
    val doubleList = List2(1.0, 2.0, 3.0, 4.0)
    assertEquals(List2.product(doubleList), 24.0)

    val listWithZero = List2(1.0, 0.0, 3.0)
    assertEquals(List2.product(listWithZero), 0.0)

    val emptyList = List2[Double]()
    assertEquals(List2.product(emptyList), 1.0)
  }

  test("pattern matching on List2") {
    assertEquals(List2(1,2,3) match { case _ => 42 }, 42)

    // Usamos @unchecked para silenciar el warning
    val head = (List2(1,2,3): @unchecked) match {
      case List2.Cons(h, _) => h
    }
    assertEquals(head, 1)

    // Usamos @unchecked de nuevo
    val tail = (List2(1,2,3): @unchecked) match {
      case List2.Cons(_, t) => t
    }
    assertEquals(tail, List2(2,3))

    // Esta prueba sigue siendo correcta y la advertencia aquí es esperada.
    intercept[MatchError] {
      (List2(1,2,3): @unchecked) match { case Nil => 42 }
    }
  }
}
