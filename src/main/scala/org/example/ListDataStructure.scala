package org.example

sealed trait List[+A] {
  override def toString: String = this match {
    case Nil => "Nil"
    case Cons(head, tail) => s"Cons($head, $tail)"
  }
}

case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object ListDataStructure {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(head, tail) => head + sum(tail)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(head, tail) => head * product(tail)
  }

  // def fill[A](n: Int, a: A): List[A] = {
  //   if (n <= 0) Nil
  //   else Cons(a, fill(n - 1, a))
  // }

  def fill[A](n: Int, a: A): List[A] = n match {
    case 0 => Nil
    case _ if n > 0 => Cons(a, fill(n - 1, a))
    case _ => Nil // Este caso maneja cualquier valor negativo de `n`
  }
  
  // The function apply in the object List is a variadic function, meaning it accepts zero or more arguments of type A:
  // By calling this function apply and placing it in the companion object, 
  // we can invoke it with syntax like List(1,2,3,4) or List("hi","bye"), 
  //  with as many values as we want separated by commas (we sometimes call this the list literal or just literal syntax).
  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))


  def tail[A](list: List[A]): List[A] = list match {
    case Nil => Nil
    case Cons(_, tail) => tail
  }

  def setHead[A](list: List[A], newValue: A): List[A] = list match {
    case Nil => Nil
    case Cons(_, tail) => Cons(newValue, tail)
  }

  def addHead[A](list: List[A], newValue: A): List[A] = list match {
    case Nil => Cons(newValue, Nil)
    case Cons(head, tail) => Cons(newValue, Cons(head, tail))
  }

  def drop[A](list: List[A], n: Int): List[A] = list match {
    case Nil => Nil
    case Cons(_, tail) if n > 0 => drop(tail, n - 1)
    case _ => list
  }

  def dropWhile[A](list: List[A], f: A => Boolean): List[A] = list match {
    case Nil => Nil
    case Cons(head, tail) if f(head) => dropWhile(tail, f)
    case Cons(head, tail) => Cons(head, dropWhile(tail, f))
  }

  def append[A](list1: List[A], list2: List[A]): List[A] = list1 match {
    case Nil => list2
    case Cons(h,t) => Cons(h, append(t, list2))
  }


  def init[A](as: List[A]): List[A] = as match {
    case Nil => Nil
    case Cons(_, Nil) => Nil
    case Cons(head, tail) => Cons(head, init(tail))
  }


  /**
  The syntax for calling this version of dropWhile looks like dropWhile(xs)(f). 
  That is, dropWhile(xs) is returning a function, which we then call with the argument f 
  (in other words, dropWhile is curried[7]). 
  The main reason for grouping the arguments this way is to assist with type inference. 
  We can now use dropWhile without annotations:
  */
  def dropWhileWithCurried[A](as: List[A])(f: A => Boolean): List[A] = as match {
      case Nil => Nil
      case Cons(head, tail) if f(head) => dropWhileWithCurried(tail)(f)
      case Cons(head, tail) => Cons(head, dropWhileWithCurried(tail)(f))
  }

  // Reduce the boilerplate of the previous functions using currying
  /*
    foldRight(Cons(1, Cons(2, Cons(3, Nil))), 0)((x,y) => x + y)
    1 + foldRight(Cons(2, Cons(3, Nil)), 0)((x,y) => x + y)
    1 + (2 + foldRight(Cons(3, Nil), 0)((x,y) => x + y))
    1 + (2 + (3 + foldRight(Nil, 0)((x,y) => x + y)))
    1 + (2 + (3 + (0)))
    6
  */
  // using foldRight is not possible to short-circuit the computation
  // La implementación de foldRight que has proporcionado no es stack safe porque no es recursiva por la cola (tail recursive).
  // Esto significa que cada llamada recursiva a foldRight agrega un nuevo marco a la pila de llamadas (call stack).
  // Para listas grandes, esto puede resultar en un desbordamiento de la pila (StackOverflowError), ya que la pila de llamadas tiene un límite de tamaño.
  def foldRight[A,B](as: List[A], z: B)(f: (A, B) => B): B = as match {
      case Nil => z
      case Cons(head, tail) => f(head, foldRight(tail, z)(f))
  }

  // using foldLeft is possible to short-circuit the computation
  // Para hacer una versión stack safe, podemos implementar foldLeft, que es recursiva por la cola. 
  // En foldLeft, la recursión ocurre al final de la función, lo que permite que el compilador optimice la recursión y reutilice el mismo marco de pila.
  def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    // case Cons(0, _) => 0  // short-circuit
    case Cons(head, tail) => foldLeft(tail, f(z, head))(f)
  }

  def reverse[A](as: List[A]): List[A] = {
    foldLeft(as, Nil: List[A])((acc, x) => Cons(x, acc))
  }

  // Implementing foldRight in terms of foldLeft is indeed a useful exercise because it allows us to make foldRight tail-recursive and stack-safe.
  // Let's explore how to do this and also consider the reverse scenario.
  def foldRightUsinFoldLeft[A, B](as: List[A], acc: B, f: (A, B) => B): B = {
    foldLeft(reverse(as), acc)((b, a) => f(a, b))
  }

  def appendUsingFoldLeft[A](a1: List[A], a2: List[A]): List[A] =
    foldLeft(reverse(a1), a2)((acc, elem) => Cons(elem, acc))

  def appendUsingFoldRight[A](a1: List[A], a2: List[A]): List[A] =
    foldRight(a1, a2)((elem, acc) => Cons(elem, acc))

  def toString[A](as: List[A]): List[A] = as match {
    case Nil => Nil
    case Cons(head, tail) => Cons(head, toString(tail))
  }

  def map[A, B](as: List[A], f: A => B): List[B] = as match {
    case Nil => Nil
    case Cons(head, tail) => Cons(f(head), map(tail, f))
  }

  def filter[A, B](as: List[A], f: A => Boolean): List[A] = as match {
    case Nil => Nil
    case Cons(head, tail) if(f(head)) => Cons(head, tail)
    case Cons(_, tail) => filter(tail, f)
  }

  /*
  _ + _     ⟶   (x, y) => x + y
  _ * 2     ⟶   x => x * 2
  _.head    ⟶   xs => xs.head
  _ drop _  ⟶   (xs, n) => xs.drop(n)

  val dropFirstN = _ drop _
  println(dropFirstN(List(1, 2, 3, 4, 5), 2)) // Output: List(3, 4, 5)
  */

  def sum2(ns: List[Int]): Int = foldRight(ns, 0)(_ + _) // _ + _ is a shorthand for (x, y) => x + y

  def sum3(ns: List[Int]): Int = foldLeft(ns, 0)(_ + _) // _ + _ is a shorthand for (x, y) => x + y

  def product2(ns: List[Double]): Double = foldRight(ns, 1.0)(_ * _) // _ * _ is a shorthand for (x, y) => x * y

  def product3(ns: List[Double]): Double = foldLeft(ns, 1.0)(_ * _)

  def length[A](as: List[A]): Int = foldRight(as, 0)((_, acc) => acc + 1)

  def lengthLeft[A](as: List[A]): Int = foldLeft(as, 0)((acc, _) => acc + 1)

}