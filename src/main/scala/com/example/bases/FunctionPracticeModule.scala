package com.example.bases

case class Stock (name: String, quantity: Int, price: BigDecimal) { }

object FunctionPracticeModule {
    val cost = (quantity: Int, price: BigDecimal) => price * quantity

    val tax = (a: Double, b: BigDecimal) => b * (1 + a)

    def calculateSubTotal(quantity: Int, price: BigDecimal): BigDecimal = price * quantity

    def filterByStock(items: List[Stock], minimun: Int): List[(Stock)] =
        items.filter((item: Stock) => item.quantity >= minimun)

    def filter[A](items: List[A], p: A => Boolean): List[A] = {
        @annotation.tailrec
        def loop(remaining: List[A], acc: List[A]): List[A] = {
            remaining match {
            case Nil => acc // Caso base: si la lista está vacía, retorna el acumulador
            case head :: tail =>
                if (p(head)) loop(tail, acc :+ head) // Si cumple el predicado, agrégalo al acumulador
                else loop(tail, acc) // Si no, continúa sin agregarlo
            }
        }

        loop(items, List.empty) 
    } 

    def curry[A, B, C](f: (A, B) => C): A => (B => C) = (a: A) => (b: B) => f(a, b)

    def compose[A,B,C](f: B => C, g: A => B): A => C = (a: A) => f(g(a))

    //  def manualReduce[A, B](list: List[A], initial: B)(f: (B, A) => B): B  // Alsc it can defined the parameters using ()() instead of using , 
    def mapReduce[A, B](list: List[A], initial: B, f: (B, A) => B): B = {
        @annotation.tailrec
        def loop(remaining: List[A], acc: B): B = {
            remaining match {
                case Nil => acc
                case head :: tail => loop(tail, f(acc, head))
            }
        }
        loop(list, initial)
    }

    def main(args: Array[String]): Unit = {
        val stock = List(
            Stock("Laptop", 2, BigDecimal("1200.50")),
            Stock("Mouse", 5, BigDecimal("25.75")),
            Stock("Monitor", 3, BigDecimal("320.99")),
            Stock("Teclado", 4, BigDecimal("75.00"))
        )

        println(calculateSubTotal(10, BigDecimal("150.25")))
        println(cost(10, BigDecimal("150.25")))
        println(filterByStock(stock, 3))
        println(filterByStock(stock, 3))

        val curryDiscount = curry(tax)
        val taxes19 = curryDiscount(0.19)
        println(taxes19(BigDecimal("1200.50")))
        
        // val total = mapReduce(stock, BigDecimal(0)) { (acc, item) => acc + (item.quantity * item.price) }
        val total = mapReduce(stock, BigDecimal(0), (acc, item) => acc + (item.quantity * item.price))

        println(s"El total es: $total")     
   }
}

