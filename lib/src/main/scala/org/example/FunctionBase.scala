package org.example

object FunctionBase {
    // Partial application
    // It’s a higher-order function for performing what’s called partial application
    // This function, partial1, takes a value and a function of two arguments, and returns a function of one argument as its result. 
    // The name comes from the fact that the function is being applied to some but not all of the arguments it requires:
    def partial1[A, B, C](a: A, f: (A, B) => C): B => C = (b: B) => f(a, b)

    // currying
    // Let’s look at another example, currying, which converts a function f of two arguments 
    // into a function of one argument that partially applies f. 
    def curry[A, B, C](f: (A, B) => C): A => (B => C) = (a: A) => (b: B) => f(a, b)

    // uncurrying
    // The reverse of currying is uncurrying, which converts a function of one argument that returns a function into a function of two arguments.
    // In this case, we convert a function that takes an A and returns a function that takes a B into a function that takes an A and a B and returns a C.
    def uncurry[A, B, C](f: A => B => C): (A, B) => C = (a, b) => f(a)(b)

    // The compose function takes two functions, f and g, and returns a function that is the composition of f and g.
    def compose[A,B,C](f: B => C, g: A => B): A => C = (a: A) => f(g(a))  
  
    // Apply a function to each element of a list and combine the results
    // Polimorphic function that takes a list of A, an initial value of type B and a function (B, A) => B and returns a value of type B
    def mapReduce[A, B](items: List[A], initial: B)(f: (B, A) => B): B = {
        val safeItems = Option(items).getOrElse(List.empty[A])
        @annotation.tailrec
        def loop(remaining: List[A], acc: B): B = {
            remaining match {
                case Nil => acc
                case head :: tail => loop(tail, f(acc, head))
            }
        }
        loop(safeItems, initial)
    }

    // Filter a list of elements that satisfy a predicate
    // Polimorphic function that takes a list of A and a predicate function A => Boolean and returns a list of A
    def filter[A](items: List[A], p: A => Boolean): List[A] = {
        val safeItems = Option(items).getOrElse(List.empty[A])
        @annotation.tailrec
        def loop(remaining: List[A], acc: List[A]): List[A] = {
            remaining match {
            case Nil => acc
            case head :: tail =>
                if (p(head)) loop(tail, acc :+ head)
                else loop(tail, acc)
            }
        }

        loop(safeItems, List.empty) 
    }

    // Map a function over a list of elements
    // Polimorphic function that takes a list of A and a function A => A and returns a list of A
    def map[A](items: List[A], f: A => A): List[A] = {
       val safeItems = Option(items).getOrElse(List.empty[A]) 
       @annotation.tailrec
        def loop(remaining: List[A], acc: List[A]): List[A] = {
            remaining match {
            case Nil => acc
            case head :: tail => loop(tail, acc :+ f(head))
                
            }
        }

        loop(safeItems, List.empty) 
    } 
}
