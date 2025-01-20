package org.example

import org.scalatest.funsuite.AnyFunSuite
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MathBaseSuite extends AnyFunSuite {
 test("abs should return the absolute value of a number") {
    assert(MathBase.abs(5) == 5)
    assert(MathBase.abs(-5) == 5)
    assert(MathBase.abs(0) == 0)
  }

  test("factorial should return the factorial of a number") {
    assert(MathBase.factorial(0) == 1)
    assert(MathBase.factorial(1) == 1)
    assert(MathBase.factorial(5) == 120)
  }

  test("fibonacci should return the nth Fibonacci number") {
    assert(MathBase.fibonacci(0) == 0)
    assert(MathBase.fibonacci(1) == 1)
    assert(MathBase.fibonacci(2) == 1)
    assert(MathBase.fibonacci(3) == 2)
    assert(MathBase.fibonacci(10) == 55)
  }

  test("findFirst should return the index of the first element matching the predicate") {
    val arr = Array(1, 2, 3, 4, 5)
    assert(MathBase.findFirst(arr, (x: Int) => x == 3) == 2)
    assert(MathBase.findFirst(arr, (x: Int) => x == 6) == -1)
    assert(MathBase.findFirst(arr, (x: Int) => x > 4) == 4)
  }

  test("isSorted should verify if an array is sorted based on the provided ordering function") {
    val arr1 = Array(1, 2, 3, 4, 5)
    val arr2 = Array(5, 4, 3, 2, 1)
    assert(MathBase.isSorted(arr1, (a: Int, b: Int) => a <= b) == true)
    assert(MathBase.isSorted(arr2, (a: Int, b: Int) => a >= b) == true)
    assert(MathBase.isSorted(arr1, (a: Int, b: Int) => a >= b) == false)
  }

  test("multiplyByTwo should return a function that multiplies a number by 2") {
    val multiply = MathBase.multiplyByTwo()
    assert(multiply(2) == 4)
    assert(multiply(5) == 10)
    assert(multiply(0) == 0)
  }

  test("lessThan should return a function that checks if one number is less than another") {
    val lessThan = MathBase.lessThan()
    assert(lessThan(2, 5) == true)
    assert(lessThan(5, 2) == false)
    assert(lessThan(3, 3) == false)
  }

  test("sumThreeNumbers should return a function that sums three numbers") {
    val sum = MathBase.sumThreeNumbers()
    assert(sum(1, 2, 3) == 6)
    assert(sum(0, 0, 0) == 0)
    assert(sum(-1, -2, -3) == -6)
  }

  test("andThen should compose two functions") {
    val f = (x: Double) => math.Pi / 2 - x
    val cosAndSen = f andThen math.sin

    assert(cosAndSen(0) == 1)
  }
}