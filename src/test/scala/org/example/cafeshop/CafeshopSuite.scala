package com.example.cafeshop

class CafeShopSuite extends munit.FunSuite {
  test("buyCoffee should return a Coffee and a Charge with the correct price") {
    val cc = CreditCard("1234567890L")
    val cafe = new Cafe
    val (coffee, charge) = cafe.buyCoffee(cc)

    assert(coffee.price == 2.5)
    assert(charge.cc == cc)
    assert(charge.amount == 2.5)
  }

  test("buyCoffees should return a list of Coffees and a combined Charge for multiple coffees") {
    val cc = CreditCard("1234567890L")
    val cafe = new Cafe
    val (coffees, charge) = cafe.buyCoffees(cc, 3)

    assert(coffees.size == 3)
    assert(coffees.forall(_.price == 2.5))
    assert(charge.cc == cc)
    assert(charge.amount == 7.5) // 3 coffees * 2.5 each
  }

  test("coalesce should combine charges with the same credit card and group by card") {
    val cc1 = CreditCard("1234567890L")
    val cc2 = CreditCard("9876543210L")

    val charges = List(
      Charge(cc1, 2.5),
      Charge(cc1, 3.0),
      Charge(cc2, 1.5),
      Charge(cc2, 2.0)
    )

    val cafe = new Cafe
    val coalesced = cafe.coalesce(charges)

    assert(coalesced.size == 2)
    assert(coalesced.exists(c => c.cc == cc1 && c.amount == 5.5)) // 2.5 + 3.0
    assert(coalesced.exists(c => c.cc == cc2 && c.amount == 3.5)) // 1.5 + 2.0
  }

  test("combine should add charges with the same credit card") {
    val cc = CreditCard("1234567890L")
    val charge1 = Charge(cc, 2.5)
    val charge2 = Charge(cc, 3.0)

    val combined = charge1.combine(charge2)
    assert(combined.cc == cc)
    assert(combined.amount == 5.5)
  }

  test("combine should throw an exception when combining charges with different credit cards") {
    val cc1 = CreditCard("1234567890L")
    val cc2 = CreditCard("9876543210L")

    val charge1 = Charge(cc1, 2.5)
    val charge2 = Charge(cc2, 3.0)

    intercept[Exception] {
      charge1.combine(charge2)
    }
  }

  test("Coffee should have a default price of 2.5") {
    val coffee = Coffee()
    assert(coffee.price == 2.5)
  }

  test("CreditCard should store the card number correctly") {
    val cc = CreditCard("1234567890L")
    assert(cc.number == "1234567890L")
  }
}
