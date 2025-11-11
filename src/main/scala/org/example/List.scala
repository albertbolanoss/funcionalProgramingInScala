package org.example


// Definion of the List2 data structure using enum
enum List2[+A]:
  case Nil
  case Cons(head: A, tail: List2[A])

// Companion object
object List2:
  // La función apply en el objeto List es una función variádica, lo que significa que acepta cero o más argumentos de tipo A
  def apply[A](as: A*): List2[A] =
    if as.isEmpty then Nil
    else Cons(as.head, apply(as.tail*))

  def sumNoTailrec(ints: List2[Int]): Int = ints match
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)

  def productNoTailrec(doubles: List2[Double]): Double = doubles match
    case Nil => 1.0
    case Cons (0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)

  def sum(ints: List2[Int]): Int =
      @annotation.tailrec
      def go(list: List2[Int], acc: Int): Int = list match
        case Nil => acc
        case Cons(x, xs) => go(xs, x + acc)
      go(ints, 0)

  def product(doubles: List2[Double]): Double =
    @annotation.tailrec
    def go(list: List2[Double], acc: Double): Double = list match
      case Nil => acc
      case Cons(0.0, _) => 0.0
      case Cons(x, xs) => go(xs, x * acc)

    go(doubles, 1.0)