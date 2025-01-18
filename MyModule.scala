object MyModule {
    
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

    // Polymorphic function
    // Generict function that takes an array of element of type A
    // and a predicate function that returns a boolean
    def findFirst[A](as: Array[A], p: A => Boolean): Int = {
        @annotation.tailrec
        def loop(n: Int): Int = 
            if (n >= as.length) -1
            else if (p(as(n))) n
            else loop(n + 1)
        loop(0)
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
        println(findFirst(arr, (x: Int) => x == 5))
        
    }
        
}