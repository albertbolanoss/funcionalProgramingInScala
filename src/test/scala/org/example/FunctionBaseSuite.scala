package org.example


class FunctionBaseSuite extends munit.FunSuite {
  case class Stock(name: String, quantity: Int, price: BigDecimal) {}

  test("partial1 applies a partial function") {
    val multiply: (Int, Int) => Int = (a, b) => a * b
    val multiplyBy2 = FunctionBase.partial1(2, multiply)
    assert(multiplyBy2(5) == 10)
    assert(multiplyBy2(0) == 0)
  }

  test("curry transforms a two-argument function into a curried function") {
    val add: (Int, Int) => Int = (a, b) => a + b
    val curriedAdd = FunctionBase.curry(add)
    val add10 = curriedAdd(10)
    assert(add10(5) == 15)
    assert(add10(0) == 10)
  }

  test("uncurry transforms a curried function back into a two-argument function") {
    val curriedAdd: Int => Int => Int = a => b => a + b
    val uncurriedAdd = FunctionBase.uncurry(curriedAdd)
    assert(uncurriedAdd(10, 5) == 15)
    assert(uncurriedAdd(3, 7) == 10)
  }

  test("compose combines two functions") {
    val multiplyBy2: Int => Int = x => x * 2
    val add3: Int => Int = x => x + 3
    val composedFunction = FunctionBase.compose(multiplyBy2, add3)
    assert(composedFunction(4) == 14) // (4 + 3) * 2
    assert(composedFunction(0) == 6)  // (0 + 3) * 2
  }

  test("apply should return an empty list when no arguments are provided") {
    val result = FunctionBase.apply()
    assertEquals(result, Nil)
  }

  test("apply should return a list with a single element when one argument is provided") {
    val result = FunctionBase.apply(1)
    assertEquals(result, Cons(1, Nil))
  }

  test("apply should return a list with multiple elements when multiple arguments are provided") {
    val result = FunctionBase.apply(1, 2, 3)
    assertEquals(result, Cons(1, Cons(2, Cons(3, Nil))))
  }

  test("apply should handle different types of elements") {
    val result = FunctionBase.apply("a", "b", "c")
    assertEquals(result, Cons("a", Cons("b", Cons("c", Nil))))
  }

  test("apply should handle a mix of different types") {
    val result = FunctionBase.apply(1, "a", 3.14)
    assertEquals(result, Cons(1, Cons("a", Cons(3.14, Nil))))
  }
}
