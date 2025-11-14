package org.example

  /* 
  https://www.scala-lang.org/api/current/scala/collection/immutable/List.html

  List existe en la biblioteca estándar de Scala, y usaremos la versión de la biblioteca estándar en capítulos posteriores. 
  La principal diferencia entre la List desarrollada aquí y la versión de la biblioteca estándar es que Cons se llama :: (dos puntos, dos puntos), 
  que se asocia a la derecha. Así que 1 :: 2 :: Nil es igual a 1 :: (2 :: Nil), que es igual a List(1,2).
   */
  /* 
  def take(n: Int): List[A] - Devuelve una lista que consiste en los primeros n elementos de esta.
  def takeWhile(f: A => Boolean): List[A] - Devuelve una lista que consiste en el prefijo válido más largo de esta cuyos elementos pasan todos el predicado f.
  def forall(f: A => Boolean): Boolean - Devuelve true si y solo si todos los elementos de esta pasan el predicado f.
  def exists(f: A => Boolean): Boolean - Devuelve true si algún elemento de esta pasa el predicado f.
  scanLeft y scanRight - Son similares a foldLeft y foldRight, pero devuelven la List de resultados parciales en lugar de solo el valor acumulado final.
   */


/**
 * Este objeto contiene la implementación de hasSubsequence
 * utilizando la biblioteca estándar de Scala (scala.collection.immutable.List).
 */
object ScalaStandardList {
  
  /* 
    Al poner el import dentro del objeto, las definiciones importadas (List, Nil, :: de la biblioteca estándar) tienen prioridad sobre las definiciones en el ámbito del paquete (tu org.example.List).
   */
  import scala.annotation.tailrec
  import scala.collection.immutable.{List, Nil, ::}

  /**
   * Comprueba si una lista 'sup' contiene a 'sub' como subsecuencia.
   * Esta versión utiliza la List ESTÁNDAR de Scala.
   */
  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = {

    /**
     * Verifica si `list` (la lista estándar) COMIENZA con `prefix`.
     */
    @tailrec
    def startsWith(list: List[A], prefix: List[A]): Boolean = (list, prefix) match {
      // 1. Si el prefijo (sub) es Nil (vacío), siempre es verdadero.
      //    'Nil' aquí es scala.collection.immutable.Nil
      case (_, Nil) => true

      // 2. Si las cabezas coinciden, seguimos verificando las colas.
      //    '::' aquí es scala.collection.immutable.::
      case (h1 :: t1, h2 :: t2) if h1 == h2 => startsWith(t1, t2)

      // 3. En cualquier otro caso (no coinciden, o 'list' se acaba
      //    antes que 'prefix'), es falso.
      case _ => false
    }

    /**
     * Función principal que itera sobre la lista 'sup'.
     */
    @tailrec
    def check(remainingSup: List[A]): Boolean = remainingSup match {
      // 1. Si 'sup' se acaba (es Nil), solo es verdadero
      //    si 'sub' también es Nil.
      case Nil => sub.isEmpty // .isEmpty es idiomático para la List estándar

      // 2. Si 'remainingSup' comienza con 'sub', hemos terminado.
      case _ if startsWith(remainingSup, sub) => true

      // 3. Si no, probamos de nuevo con la cola (tail) de 'remainingSup'.
      case _ :: t => check(t)
    }

    // Inicia el proceso
    check(sup)
  }
}
