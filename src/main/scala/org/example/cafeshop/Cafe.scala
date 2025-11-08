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

        // The alternative with map (perhaps more explicit)
        // val purchases = (1 to n).map { _ => 
        //     buyCoffee(cc) 
        // }.toList

        // Extact coffees and charges from (Coffee, Charge) purchases
        val (coffees, charges) = purchases.unzip
        
        // Return list of coffes and one charge using combine function
        (coffees, charges.reduce((c1, c2) => c1.combine(c2)))
    }

    // Coalesce charges (fusion)
    // We group charges by credit card and combine them
    // Getting a list of charges with unique credit cards
    def coalesce(charges: List[Charge]): List[Charge] =
        // Chaining
        // Input: List(Charge(cc1, 10), Charge(cc2, 5), Charge(cc1, 8))
        // .groupBy(_.cc) =
        // Map(
        //     cc1 -> List(Charge(cc1, 10), Charge(cc1, 8)),
        //     cc2 -> List(Charge(cc2, 5))
        // )
        // .values =
        // Iterable(
        // List(Charge(cc1, 10), Charge(cc1, 8)),
        // List(Charge(cc2, 5))
        // )
        // .map(_.reduce(_ combine _))
        charges.groupBy(_.cc).values.map(_.reduce(_ combine _)).toList
}