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

  test("sum should return 0 for an empty list") {
    val ex1: List[Int] = Nil
    assertEquals(ListDataStructure.sum(ex1), 0)
  }

  test("sum should return the sum of all elements in the list") {
    val ex2: List[Int] = Cons(1, Cons(2, Cons(3, Nil)))
    assertEquals(ListDataStructure.sum(ex2), 6)
  }

  test("product should return 1.0 for an empty list") {
    val ex1: List[Double] = Nil
    assertEquals(ListDataStructure.product(ex1), 1.0)
  }

  test("product should return 0.0 if the list contains 0.0") {
    val ex2: List[Double] = Cons(1.0, Cons(0.0, Cons(3.0, Nil)))
    assertEquals(ListDataStructure.product(ex2), 0.0)
  }

  test("product should return the product of all elements in the list") {
    val ex3: List[Double] = Cons(1.0, Cons(2.0, Cons(3.0, Nil)))
    assertEquals(ListDataStructure.product(ex3), 6.0)
  }

  test("fill should return Nil for n <= 0") {
    assertEquals(ListDataStructure.fill(0, "a"), Nil)
    assertEquals(ListDataStructure.fill(-1, "a"), Nil)
  }

  test("fill should return a list with n elements") {
    val result = ListDataStructure.fill(3, "a")
    assertEquals(result.toString, "Cons(a, Cons(a, Cons(a, Nil)))")
  }

  test("fill should return a list with the correct elements") {
    val result = ListDataStructure.fill(2, 5)
    assertEquals(result.toString, "Cons(5, Cons(5, Nil))")
  }

  test("pattern matching on List should return the correct value") {
    val x = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil))))) match {
      case Cons(x, Cons(2, Cons(4, _))) => x  // match if the first is x and the second is 2 and the third is 4
      case Nil => 42 // match if the list is empty
      case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y // match if the first is x, the second is y and the third is 3 and the fourth is 4
      case Cons(h, t) => h + ListDataStructure.sum(t)  // match if the list is not empty
      case _ => 101 // match anything else
    }
    assertEquals(x, 3)
  }
  

  test("pattern matching on List (using apply) should return the correct value") {
    val x = ListDataStructure.apply(1,2,3,4,5) match {
      case Cons(x, Cons(2, Cons(4, _))) => x  // match if the first is x and the second is 2 and the third is 4
      case Nil => 42 // match if the list is empty
      case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y // match if the first is x, the second is y and the third is 3 and the fourth is 4
      case Cons(h, t) => h + ListDataStructure.sum(t)  // match if the list is not empty
      case _ => 101 // match anything else
    }
    assertEquals(x, 3)
  }

  test("tail should return Nil for an empty list") {
    val ex1: List[Int] = Nil
    assertEquals(ListDataStructure.tail(ex1), Nil)
  }

  test("tail should return the tail of the list") {
    val ex2: List[Int] = Cons(1, Cons(2, Cons(3, Nil)))
    assertEquals(ListDataStructure.tail(ex2).toString, "Cons(2, Cons(3, Nil))")
  }

  test("setHead should return Nil for an empty list") {
    val ex1: List[Int] = Nil
    assertEquals(ListDataStructure.setHead(ex1, 1), Nil)
  }

  test("setHead should return a new list with the new value as the head") {
    val ex2: List[Int] = Cons(1, Cons(2, Cons(3, Nil)))
    assertEquals(ListDataStructure.setHead(ex2, 5).toString, "Cons(5, Cons(2, Cons(3, Nil)))")
  }

  test("addHead should return a new list with the new value as the head") {
    val ex1: List[Int] = Nil
    assertEquals(ListDataStructure.addHead(ex1, 1).toString, "Cons(1, Nil)")

    val ex2: List[Int] = Cons(2, Cons(3, Nil))
    assertEquals(ListDataStructure.addHead(ex2, 1).toString, "Cons(1, Cons(2, Cons(3, Nil)))")
  }

  test("drop should return Nil for an empty list") {
    val ex1: List[Int] = Nil
    assertEquals(ListDataStructure.drop(ex1, 1), Nil)
  }

  test("drop should return the list without the first n elements") {
    val ex2: List[Int] = Cons(1, Cons(2, Cons(3, Nil)))
    assertEquals(ListDataStructure.drop(ex2, 2).toString, "Cons(3, Nil)")
  }


  test("drop while n < list.size should return the list without the first n elements") {
    val ex2: List[Int] = Cons(1, Cons(2, Cons(3, Nil)))

    assertEquals(ListDataStructure.dropWhile(ex2, x => x % 2 == 0).toString, "Cons(1, Cons(3, Nil))")
  }

  test("append should return the second list if the first list is empty") {
    val ex1: List[Int] = Nil
    val ex2: List[Int] = Cons(1, Cons(2, Cons(3, Nil)))
    assertEquals(ListDataStructure.append(ex1, ex2).toString, "Cons(1, Cons(2, Cons(3, Nil)))")
  }

  test("append should return the first list if the second list is empty") {
    val ex1: List[Int] = Cons(1, Cons(2, Cons(3, Nil)))
    val ex2: List[Int] = Nil
    assertEquals(ListDataStructure.append(ex1, ex2).toString, "Cons(1, Cons(2, Cons(3, Nil)))")
  }

  test("append should return the concatenation of the two lists") {
    val ex1: List[Int] = Cons(1, Cons(2, Cons(3, Nil)))
    val ex2: List[Int] = Cons(4, Cons(5, Cons(6, Nil)))
    assertEquals(ListDataStructure.append(ex1, ex2).toString, "Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Cons(6, Nil))))))")
  }

}