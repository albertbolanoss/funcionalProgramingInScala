package org.example



object FunctionBase {
    // Partial application
    // It’s a higher-order function for performing what’s called partial application
    // This function, partial1, takes a value and a function of two arguments, and returns a function of one argument as its result. 
    // The name comes from the fact that the function is being applied to some but not all of the arguments it requires:
	// Partial function
	// Apply partial a function: val add5 = partial1(5, (a, b) => a + b)  then add5(3) == 8
	// Firts get the fisrt parameter a and the function and then receives b value and apply f(a, b) 
    def partial1[A, B, C](a: A, f: (A, B) => C): B => C = (b: B) => f(a, b)

    // currying
    // Let’s look at another example, currying, which converts a function f of two arguments 
    // into a function of one argument that partially applies f. 
	// Currying
	// Convert a function with two parameter to two functions with one parameter: val curry = curry((a, b) => a + b) then val add5 = curry(5)  then  add5(3) == 8
	// First get the function and then receives the first parameter a and then the second parameter b and apply the f(a,b)
    def curry[A, B, C](f: (A, B) => C): A => (B => C) = (a: A) => (b: B) => f(a, b)

    // uncurrying
    // The reverse of currying is uncurrying, which converts a function of one argument that returns a function into a function of two arguments.
    // In this case, we convert a function that takes an A and returns a function that takes a B into a function that takes an A and a B and returns a C.
	// Uncurrying
	// Convert a Currying function to a function with parameter (reverse currying)
    def uncurry[A, B, C](f: A => B => C): (A, B) => C = (a, b) => f(a)(b)

    // The compose function takes two functions, f and g, and returns a function that is the composition of f and g.
    // f and then g
    def compose[A,B,C](f: B => C, g: A => B): A => C = (a: A) => f(g(a)) 

    // Variadic functions
    // The function apply in the object List is a variadic function, meaning it accepts zero or more arguments of type A:
    // By calling this function apply and placing it in the companion object, we can invoke it with syntax like List(1,2,3,4) or List("hi","bye"), with as many values as we want separated by commas
    def apply[A](as: A*): List[A] =
        if (as.isEmpty) Nil
        else Cons(as.head, apply(as.tail: _*))

}
