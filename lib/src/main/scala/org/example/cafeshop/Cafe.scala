package com.example.cafeshop

// A cafe buys coffee and coalesces charges
class Cafe  {
    // Buy a coffee
    // To avoid side effects, we return a tuple with the coffee and the charge
    def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
        val cup = new Coffee()
        (cup, Charge(cc, cup.price))
    }

    // Buy n coffees
    // We return a tuple with the list of coffees and the total charge
    def buyCoffees(cc: CreditCard, n: Int): (List[Coffee], Charge) = {
        val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))
        val (coffees, charges) = purchases.unzip
        (coffees, charges.reduce((c1, c2) => c1.combine(c2)))
    }

    // Coalesce charges (fusion)
    // We group charges by credit card and combine them
    // Getting a list of charges with unique credit cards
    def coalesce(charges: List[Charge]): List[Charge] =
        charges.groupBy(_.cc).values.map(_.reduce(_ combine _)).toList
}