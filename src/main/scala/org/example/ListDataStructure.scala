package org.example

sealed trait List[+A] {
  override def toString: String = this match {
    case Nil => "Nil"
    case Cons(head, tail) => s"Cons($head, $tail)"
  }
}

case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]