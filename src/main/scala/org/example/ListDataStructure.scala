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

}