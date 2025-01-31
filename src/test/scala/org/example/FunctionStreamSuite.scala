package org.example

// import org.example.{List, Cons, Nil}

class FunctionStreamSuite  extends munit.FunSuite {
  // Tests para reduceRight
  test("reduceRight should return the initial value for an empty list") {
    val list: List[Int] = Nil
    val result = FunctionStream.reduceRight(list, 0, (acc: Int, x: Int) => acc + x)
    assertEquals(result, 0)
  }

  test("reduceRight should sum all elements in a list of integers") {
    val list = Cons(1, Cons(2, Cons(3, Nil)))
    val result = FunctionStream.reduceRight(list, 0, (acc: Int, x: Int) => acc + x)
    assertEquals(result, 6)
  }

  test("reduceRight should concatenate strings in a list") {
    val list = Cons("a", Cons("b", Cons("c", Nil)))
    val result = FunctionStream.reduceRight(list, "", (acc: String, x: String) => acc + x)
    assertEquals(result, "cba")
  }

  // Tests para filter
  test("filter should return an empty list for an empty list") {
    val list: List[Int] = Nil
    val result = FunctionStream.filter(list)(_ % 2 == 0)
    assertEquals(result, Nil)
  }

  test("filter should return only even numbers from a list of integers") {
    val list = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
    val result = FunctionStream.filter(list)(_ % 2 == 0)
    assertEquals(result, Cons(2, Cons(4, Nil)))
  }

  test("filter should return an empty list if no elements satisfy the predicate") {
    val list = Cons(1, Cons(3, Cons(5, Nil)))
    val result = FunctionStream.filter(list)(_ % 2 == 0)
    assertEquals(result, Nil)
  }

  // Tests para map
  test("map should return an empty list for an empty list") {
    val list: List[Int] = Nil
    val result = FunctionStream.map(list)(_ * 2)
    assertEquals(result, Nil)
  }

  test("map should double each element in a list of integers") {
    val list = Cons(1, Cons(2, Cons(3, Nil)))
    val result = FunctionStream.map(list)(_ * 2)
    assertEquals(result, Cons(2, Cons(4, Cons(6, Nil))))
  }

  test("map should convert each element to a string") {
    val list = Cons(1, Cons(2, Cons(3, Nil)))
    val result = FunctionStream.map(list)(_.toString)
    assertEquals(result, Cons("1", Cons("2", Cons("3", Nil))))
  }

  // Tests para reverse
  test("reverse should return an empty list for an empty list") {
    val list: List[Int] = Nil
    val result = FunctionStream.reverse(list)
    assertEquals(result, Nil)
  }

  test("reverse should reverse a list of integers") {
    val list = Cons(1, Cons(2, Cons(3, Nil)))
    val result = FunctionStream.reverse(list)
    assertEquals(result, Cons(3, Cons(2, Cons(1, Nil))))
  }

  test("reverse should return the same list for a single-element list") {
    val list = Cons(1, Nil)
    val result = FunctionStream.reverse(list)
    assertEquals(result, Cons(1, Nil))
  }

}

