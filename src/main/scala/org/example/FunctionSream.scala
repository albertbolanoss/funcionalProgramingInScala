package org.example


// List(1,2,3) match { case _ => 42 } results in 42. Here we’re using a variable pattern, _, which matches any expression
// List(1,2,3) match { case Cons(h,_) => h } results in 1. Here we’re using a data constructor pattern in conjunction with variables to capture or bind a subexpression of the target.
// List(1,2,3) match { case Cons(_,t) => t } results in List(2,3).
// List(1,2,3) match { case Nil => 42 } results in a MatchError at runtime. A MatchError indicates that none of the cases in a match expression matched the target.


import org.example.{List, Cons, Nil}

object FunctionStream {
    // def reduceRight[A, B](items: List[A], initial: B, f: (B, A) => B): B = {
    //     @annotation.tailrec
    //     def loop(remaining: List[A], acc: B): B = {
    //     remaining match {
    //         case Nil => acc
    //         case Cons(head, tail) => loop(tail, f(acc, head))
    //     }
    //     }

    //     loop(items, initial)
    // }

    def reduceRight[A, B](items: List[A], initial: B, f: (B, A) => B): B = items match {
        case Nil => initial
        case Cons(head, tail) => f(reduceRight(tail, initial, f), head)
    }
  

    // def filter[A](list: List[A])(predicate: A => Boolean): List[A] = {
    //     @annotation.tailrec
    //     def loop(remaining: List[A], acc: List[A]): List[A] = remaining match {
    //         case Nil => acc
    //         case Cons(head, tail) =>
    //             if (predicate(head)) loop(tail, Cons(head, acc))
    //             else loop(tail, acc)
    //     }
    //     reverse(loop(list, Nil))
    // }

    def filter[A](list: List[A])(predicate: A => Boolean): List[A] = list match {
        case Nil => Nil
        case Cons(head, tail) =>
            if (predicate(head)) Cons(head, filter(tail)(predicate))
            else filter(tail)(predicate)
    }


    // def map[A, B](list: List[A])(f: A => B): List[B] = {
    //     @annotation.tailrec
    //     def loop(remaining: List[A], acc: List[B]): List[B] = remaining match {
    //         case Nil => acc
    //         case Cons(head, tail) => loop(tail, Cons(f(head), acc))
    //     }
    //     reverse(loop(list, Nil))
    // }

    def map[A, B](list: List[A])(f: A => B): List[B] = list match {
        case Nil => Nil
        case Cons(head, tail) => Cons(f(head), map(tail)(f))
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