package org.example

object MathBase {
    // Pure function (no side effects)
    // Mono-morphic function (only works with Int)
    // Can be evaluated Referentially Transparent
    def abs(n: Int): Int = 
        if (n < 0) -n
        else n
    
    // Pure function (no side effects)
    // Mono-morphic function (only works with Int)
    // Using tail recursion

    /*
    La anotación @annotation.tailrec en Scala se utiliza para asegurar que una función es recursiva por la cola (tail recursive).
    Sin embargo, simplemente agregar esta anotación no convierte automáticamente una función no recursiva por la cola en una que lo sea. La función ya debe ser recursiva por la cola para que la anotación sea válida.

    En el código que proporcionaste, la función loop ya es recursiva por la cola porque la llamada recursiva loop(n + 1) es la última operación que se realiza en la rama recursiva.
    Por lo tanto, la anotación @annotation.tailrec es apropiada y el código es correcto tal como está.
    */
    def factorial(n: Int): Int = {
        @annotation.tailrec
        def go(n: Int, acc: Int): Int = 
            if (n <= 0) acc
            else go(n-1, n*acc)
        go(n, 1)
    }

    // Pure function (no side effects)
    // Mono-morphic function (only works with Int)
    // Using tail recursion
    def fibonacci(n: Int): Int = {
        @annotation.tailrec
        def go(n: Int, a: Int, b: Int): Int =
            if (n == 0) a
            else go(n - 1, b, a + b)

        go(n, 0, 1)
    }

     // Polymorphic function.
    // Generict function that takes an array of element of type A.
    // and a predicate function that returns a boolean.
    // We introduce a comma-separated list of type parameters, surrounded by square brackets (here, just a single [A]), following the name of the function, in this case findFirst
    // the elements of the array are required to have the type A (since it’s an Array[A]), and the p function must accept a value of type A (since it’s a function of type A => Boolean)
    def findFirst[A](as: Array[A], p: A => Boolean): Int = {
        @annotation.tailrec
        def loop(n: Int): Int = 
            if (n >= as.length) -1
            else if (p(as(n))) n
            else loop(n + 1)
        loop(0)
    }

    // verify if an array is sorted
    // receive an array of elements of type A and a function that receives two elements of type A and returns a boolean
    def isSorted[A](as: Array[A], ordered: (A,A) => Boolean): Boolean = {
        @annotation.tailrec
        def loop(n: Int): Boolean = 
            if (n >= as.length - 1) true
            else if (ordered(as(n), as(n + 1))) loop(n + 1)
            else false
        loop(0)

    }

     // Multiply a number by 2
    // Return a function (Int, Int) => Int
    // The Function1 trait is a subtype of Function0, and it represents functions that take one argument.
    // 
    // In fact the below definition is syntactic sugar for the following definition:
    // This is an object with an apply method that takes an Int and returns an Int.
    // Similar to Function2 and Function3 
    // def multiplyByTwo(): Function1[Int, Int] =
    //     new Function1[Int, Int] {
    //         def apply(x: Int): Int = x * 2
    //     }
    def multiplyByTwo(): Function1[Int, Int] = (x: Int) => x * 2

    

    // Check if a number is less than another number
    // Return a function (Int, Int) => Boolean
    // The Function2 trait is a subtype of Function1, and it represents functions that take two arguments.
    def lessThan(): Function2[Int, Int, Boolean] = (a: Int, b: Int) => a < b


    // Sum three numbers
    // Return a function (Int, Int, Int) => Int
    // The Function3 trait is a subtype of Function1 and Function2, and it represents functions that take three arguments.
    def sumThreeNumbers(): Function3[Int, Int, Int, Int] = (a: Int, b: Int, c: Int) => a + b + c
    
}