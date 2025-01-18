package com.example.cafeshop

case class Charge(cc: CreditCard, amount: Double) {
  // Combine charges
  // We can only combine charges with the same credit card
  // If we try to combine charges with different credit cards, we throw an exception
  // We return a new charge with the sum of the amounts
  def combine(other: Charge): Charge =
    if (cc == other.cc)
      Charge(cc, amount + other.amount)
    else
      throw new Exception("Can't combine charges to different cards")
}