package org.example

// import org.example.ListDataStructure._
import org.example.{List, Cons, Nil}

object FunctionStream {
    def reduceRight[A, B](items: List[A], initial: B, f: (B, A) => B): B = {
        @annotation.tailrec
        def loop(remaining: List[A], acc: B): B = {
        remaining match {
            case Nil => acc
            case Cons(head, tail) => loop(tail, f(acc, head))
        }
        }

        loop(items, initial)
    }
  

    def filter[A](list: List[A])(predicate: A => Boolean): List[A] = {
        @annotation.tailrec
        def loop(remaining: List[A], acc: List[A]): List[A] = remaining match {
            case Nil => acc
            case Cons(head, tail) =>
                if (predicate(head)) loop(tail, Cons(head, acc))
                else loop(tail, acc)
        }
        reverse(loop(list, Nil))
    }


    def map[A, B](list: List[A])(f: A => B): List[B] = {
        @annotation.tailrec
        def loop(remaining: List[A], acc: List[B]): List[B] = remaining match {
            case Nil => acc
            case Cons(head, tail) => loop(tail, Cons(f(head), acc))
        }
        reverse(loop(list, Nil))
    }


    def reverse[A](list: List[A]): List[A] = {
        @annotation.tailrec
        def loop(remaining: List[A], acc: List[A]): List[A] = remaining match {
            case Nil => acc
            case Cons(head, tail) => loop(tail, Cons(head, acc))
        }
        loop(list, Nil)
    }
}