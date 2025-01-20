package org.example

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FunctionBaseSuite extends AnyFunSuite {

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

  test("mapReduce calculates the total of a list") {
    val stock = List(
      Stock("Laptop", 2, BigDecimal("1200.50")),
      Stock("Mouse", 5, BigDecimal("25.75")),
      Stock("Monitor", 3, BigDecimal("320.99")),
      Stock("Teclado", 4, BigDecimal("75.00"))
    )
    val total = FunctionBase.mapReduce(stock, BigDecimal(0)) { (acc: BigDecimal, item: Stock) =>
      acc + (item.quantity * item.price)
    }
    assert(total == BigDecimal("3792.72"))
  }

  test("filter filters a list based on a predicate") {
    val stock = List(
      Stock("Laptop", 2, BigDecimal("1200.50")),
      Stock("Mouse", 5, BigDecimal("25.75")),
      Stock("Monitor", 3, BigDecimal("320.99")),
      Stock("Teclado", 4, BigDecimal("75.00"))
    )
    val filteredStock = FunctionBase.filter(stock, (item: Stock) => item.quantity >= 3)
    assert(filteredStock == List(
      Stock("Mouse", 5, BigDecimal("25.75")),
      Stock("Monitor", 3, BigDecimal("320.99")),
      Stock("Teclado", 4, BigDecimal("75.00"))
    ))
  }

  test("filter works with integers") {
    val numbers = List(1, 2, 3, 4, 5, 6)
    val evenNumbers = FunctionBase.filter(numbers, (x: Int) => x % 2 == 0)
    assert(evenNumbers == List(2, 4, 6))
  }
}
