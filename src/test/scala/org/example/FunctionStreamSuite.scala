package org.example

// import org.example.{List, Cons, Nil}

class FunctionStreamSuite  extends munit.FunSuite {
  // test("reduceRight should return the initial value for an empty list") {
  //   val list: List[Int] = Nil
  //   val result = FunctionStream.reduceRight(list, 0)((acc: Int, elem: Int) => acc + elem)
  //   assertEquals(result, 0)
  // }

  // test("reduceRight should correctly reduce a list of integers") {
  //   val list = Cons(1, Cons(2, Cons(3, Nil)))
  //   val result = FunctionStream.reduceRight(list, 0)((acc: Int, elem: Int) => acc + elem)
  //   assertEquals(result, 6)
  // }

  // test("reduceRight should correctly reduce a list of strings") {
  //   val list = Cons("a", Cons("b", Cons("c", Nil)))
  //   val result = FunctionStream.reduceRight(list, "")((acc: String, elem: String) => acc + elem)
  //   assertEquals(result, "cba")
  // }

  // test("filter should return a list with elements that match the predicate") {
  //   val list = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
  //   val result = FunctionStream.filter(list)(_ % 2 == 0)
  //   assertEquals(result.toString, "Cons(2, Cons(4, Nil))")
  // }

  // test("filter should return Nil if no elements match the predicate") {
  //   val list = Cons(1, Cons(3, Cons(5, Nil)))
  //   val result = FunctionStream.filter(list)(_ % 2 == 0)
  //   assertEquals(result, Nil)
  // }

  // test("map should apply the function to each element in the list") {
  //   val list = Cons(1, Cons(2, Cons(3, Nil)))
  //   val result = FunctionStream.map(list)(_ * 2)
  //   assertEquals(result.toString, "Cons(2, Cons(4, Cons(6, Nil)))")
  // }

  // test("map should return Nil for an empty list") {
  //   val list: List[Int] = Nil
  //   val result = FunctionStream.map(list)(_ * 2)
  //   assertEquals(result, Nil)
  // }

  // test("reverse should return the reversed list") {
  //   val list = Cons(1, Cons(2, Cons(3, Nil)))
  //   val result = FunctionStream.reverse(list)
  //   assertEquals(result.toString, "Cons(3, Cons(2, Cons(1, Nil)))")
  // }

  // test("reverse should return Nil for an empty list") {
  //   val list: List[Int] = Nil
  //   val result = FunctionStream.reverse(list)
  //   assertEquals(result, Nil)
  // }

}
