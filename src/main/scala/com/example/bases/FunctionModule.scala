package com.example.bases

object FPConceptsModule {
    
    // Pure function (no side effects)
    // Mono-morphic function (only works with Int)
    // Can be evaluated Referentially Transparent
    def abs(n: Int): Int = 
        if (n < 0) -n
        else n
    
    // Pure function (no side effects)
    // Mono-morphic function (only works with Int)
    // Using tail recursion
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


    // Pure function (no side effects)
    // Mono-morphic function (only works with Int)
    private def formatAbs(x: Int) = {
        val msg = "The absolute value of %d is %d"
        msg.format(x, abs(x))
    }


    // Pure function (no side effects)
    // Mono-morphic function (only works with Int)
    private def formatFactorial(x: Int) = {
        val msg = "The factorial value of %d is %d"
        msg.format(x, factorial(x))
    }

    // High Order function (receieve a function as a parameter or return a function)
    // The previous function formatAbs and formatFactorial can be optimized using high order function.
    def formatInteger(name: String, n: Int, f: Int => Int): String = {
        val msg = "The %s value of %d is %d"
        msg.format(name, n, f(n))
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
    // Also can define this one like a val, un this case it is a function
    def multiplyByTwo(): Function1[Int, Int] = (x: Int) => x * 2
    
    // In fact the above definition is syntactic sugar for the following definition:
    // This is an object with an apply method that takes an Int and returns an Int.
    // def multiplyByTwo(): Function1[Int, Int] =
    //     new Function1[Int, Int] {
    //         def apply(x: Int): Int = x * 2
    //     }

    // Check if a number is less than another number
    // Return a function (Int, Int) => Boolean
    def lessThan(): Function2[Int, Int, Boolean] = (a: Int, b: Int) => a < b
    
    // In fact the above definition is syntactic sugar for the following definition:
    // This is an object with an apply method that takes an Int and returns an Int.
    // def lessThan(): Function2[Int, Int, Boolean] =
    //     new Function2[Int, Int, Boolean] {
    //         def apply(a: Int, b: Int) = a < b
    //     }


    // Sum three numbers
    // Return a function (Int, Int, Int) => Int
    def sumThreeNumbers(): Function3[Int, Int, Int, Int] = (a: Int, b: Int, c: Int) => a + b + c
    
    // In fact the above definition is syntactic sugar for the following definition:
    // This is an object with an apply method that takes an Int and returns an Int.
    // def sumThreeNumbers(): Function3[Int, Int, Int, Int] =
    //     new Function3[Int, Int, Int, Int] {
    //         def apply(a: Int, b: Int, c: Int): Int = a + b + c
    //     }


    // Partial application
    // It’s a higher-order function for performing what’s called partial application
    // This function, partial1, takes a value and a function of two arguments, and returns a function of one argument as its result. The name comes from the fact that the function is being applied to some but not all of the arguments it requires:
    def partial1[A, B, C](a: A, f: (A, B) => C): B => C = (b: B) => f(a, b)


    // currying
    // Let’s look at another example, currying, which converts a function f of two arguments 
    // into a function of one argument that partially applies f. 
    def curry[A, B, C](f: (A, B) => C): A => (B => C) = (a: A) => (b: B) => f(a, b)

    // uncurrying
    // The reverse of currying is uncurrying, which converts a function of one argument that returns a function into a function of two arguments.
    // In this case, we convert a function that takes an A and returns a function that takes a B into a function that takes an A and a B and returns a C.
    def uncurry[A, B, C](f: A => B => C): (A, B) => C = (a, b) => f(a)(b)


    // Let’s look at a final example, function composition, which feeds the output of one function to the input of another function. 
    // Again, the implementation of this function is fully determined by its type signature.
    def compose[A,B,C](f: B => C, g: A => B): A => C = (a: A) => f(g(a))
    //def compose[A,B,C](f: B => C, g: A => B): A => C = a => f(g(a))


    // Map Reduce 
    // This is a higher-order function that takes a list of A, an initial value of type B, and a function f that combines an A with a B to produce a B.
    def mapReduce[A, B](list: List[A], initial: B)(f: (B, A) => B): B = {
        @annotation.tailrec
        def loop(remaining: List[A], acc: B): B = {
            remaining match {
                case Nil => acc
                case head :: tail => loop(tail, f(acc, head))
            }
        }
        loop(list, initial)
    }

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



    def main(args: Array[String]): Unit = {
        println(formatAbs(-42))
        println(factorial(7))
        
    
        var n = 0
        while(n <= 7) {
            print(fibonacci(n) + " ")
            n += 1
        }
        println()
        println(formatInteger("absolute", -42, abs))
        println(formatInteger("factorial", 7, factorial))

        val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        
        // find first
        println(findFirst(arr, (x: Int) => x == 5))

        // is sorted?
        println(isSorted(arr, (x: Int, y: Int) => x < y))

        // Calling HOFs with anonymous functions
        // The syntax (x: Int) => x == 9 is a function literal or anonymous function.
        println(findFirst(Array(7, 9, 13), (x: Int) => x == 9))

        // Functions as values in Scala
        // When we define a function literal, what is actually being defined in Scala is an object with a method called apply. 
        // Scala has a special rule for this method name, so that objects that have an apply method can be called as if they were themselves methods. 
        // When we define a function literal like (a, b) => a < b, this is really syntactic sugar for object creation:
        // lessThan has type Function2[Int,Int,Boolean], which is usually written (Int,Int) => Boolean. 
        // Note that the Function2 interface (known in Scala as a trait) has an apply method.
        val lessThan = new Function2[Int, Int, Boolean] {
            def apply(a: Int, b: Int) = a < b
        }

        val b = lessThan.apply(10, 20)
        println(b)

        // Partial application
        val add: (Int, Int) => Int = (a: Int, b: Int) => a + b
        val add5 = partial1(5, add)
        println(add5(10)) // Output: 15
        println(add5(3))  // Output: 8


        val sustract: (Int, Int) => Int = (a: Int, b: Int) => a - b
        //                       A, f: (A, B) => C
        val sustract5 = partial1(5, sustract)
        //      (b: B) => f(a, b)    
        println(sustract5(10)) // Output: 5

        // Currying
        val curriedAdd = curry(add)
        val add6 = curriedAdd(6)
        println(add5(10)) // Output: 16
        println(add5(3))  // Output: 9

        // Uncurrying:
        val curried: Int => String => String = x => y => s"$x and $y"
        val uncurried: (Int, String) => String = uncurry(curried)
        // Testing the uncurried function
        println(uncurried(42, "hello")) // Output: "42 and hello"


        // compose
        val f: Int => String = x => s"Value: $x"
        val g: Double => Int = d => (d * 2).toInt
        val composed: Double => String = compose(f, g)

        // Probando la función compuesta
        println(composed(3.5)) // Output: "Value: 7"
    }
        
}