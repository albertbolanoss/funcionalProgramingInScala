package com.example.cafeshop

object Main {
  def main(args: Array[String]): Unit = {
    val cafe = new Cafe
    val cc = CreditCard("1234-5678-9012-3456")
    val cc2 = CreditCard("9999-9999-9999-9999")

    val (coffee, charge) = cafe.buyCoffee(cc)
    println(s"Compré un café de precio ${coffee.price}, cargo: ${charge.amount}, use the cc ${charge.cc.number}")

    val (coffees, totalCharge) = cafe.buyCoffees(cc, 3)
    println(s"Compré ${coffees.size} cafés, cargo total: ${totalCharge.amount}, use the cc ${totalCharge.cc.number}")

    val coalesced = cafe.coalesce(List(totalCharge, Charge(cc2, 5.0)))
    println(s"Coalesced charges: $coalesced")
  }
}
