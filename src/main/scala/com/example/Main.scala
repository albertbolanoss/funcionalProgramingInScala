package com.example

import com.example.cafeshop.{Cafe, Charge, CreditCard}

object Main {
  def main(args: Array[String]): Unit = {
    val cafe = new Cafe()
    val creditCard1 = CreditCard(11111)
    val creditCard2 = CreditCard(22222)
    val creditCard3 = CreditCard(33333)

    val order1 = cafe.buyCoffees(creditCard1, 2)
    val order2 = cafe.buyCoffees(creditCard2, 5)
    val order3 = cafe.buyCoffees(creditCard3, 3)

    val orders: List[(Charge)] = List(order1._2, order2._2, order3._2)
    cafe.coalesce(orders).foreach(println)
  }
}