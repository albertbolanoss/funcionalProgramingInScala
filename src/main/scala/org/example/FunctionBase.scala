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

}
