package com.example.cafeshop

object Main {
  def main(args: Array[String]): Unit = {
    val cafe = new Cafe
    
    val cc_user1 = CreditCard("1234-5678-9012-3456")
    val cc_user2 = CreditCard("9999-9999-9999-9999")

    val (coffee_user1, charge_user1) = cafe.buyCoffee(cc_user1)
    println(s"Compré un café de precio ${coffee_user1.price}, cargo: ${charge_user1.amount}, use the cc_user1 ${charge_user1.cc.number}")

    val (coffees_user1, totalCharge_user1) = cafe.buyCoffees(cc_user1, 3)
    println(s"Compré ${coffees_user1.size} cafés, cargo total: ${totalCharge_user1.amount}, use the cc_user1 ${totalCharge_user1.cc.number}")

    val (coffees_user2, totalCharge_user2) = cafe.buyCoffees(cc_user2, 3)
    println(s"Compré ${coffees_user2.size} cafés, cargo total: ${totalCharge_user2.amount}, use the cc_user1 ${totalCharge_user2.cc.number}")

    val coalesced = cafe.coalesce(List(charge_user1, totalCharge_user1,  totalCharge_user2))
    println(s"Coalesced charges: $coalesced")
  }
}
