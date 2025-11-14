package org.example

// No se necesita importar 'org.example.List', usará la estándar.

class ScalaStandardListSuite extends munit.FunSuite {

  test("hasSubsequence (Standard List): debe encontrar las subsecuencias del ejemplo: (1,2), (2,3), y (4)") {

    // Estas son ahora listas estándar de 'scala.collection.immutable.List'
    val listaPrincipal = List(1, 2, 3, 4)

    val sub1 = List(1, 2)
    val sub2 = List(2, 3)
    val sub3 = List(4)

    // Llama al nuevo objeto 'ScalaStandardList'
    assert(ScalaStandardList.hasSubsequence(listaPrincipal, sub1), "Fallo al encontrar List(1, 2)")
    assert(ScalaStandardList.hasSubsequence(listaPrincipal, sub2), "Fallo al encontrar List(2, 3)")
    assert(ScalaStandardList.hasSubsequence(listaPrincipal, sub3), "Fallo al encontrar List(4)")
  }

  test("hasSubsequence (Standard List): casos borde") {
    val lista = List(1, 2, 3)
    
    // Sublista vacía (siempre es true)
    assert(ScalaStandardList.hasSubsequence(lista, List()), "Una sublista vacía debe ser encontrada")
    
    // Lista principal vacía (solo es true si sublista también es vacía)
    assert(!ScalaStandardList.hasSubsequence(List(), List(1)), "No debe encontrar sublista en lista vacía")
    assert(ScalaStandardList.hasSubsequence(List(), List()), "Lista vacía debe contener sublista vacía")

    // Sublista no encontrada
    assert(!ScalaStandardList.hasSubsequence(lista, List(1, 3)), "No debe encontrar List(1, 3)")
    
    // Sublista idéntica
    assert(ScalaStandardList.hasSubsequence(lista, List(1, 2, 3)), "Debe encontrar una sublista idéntica")
    
    // Sublista más larga
    assert(!ScalaStandardList.hasSubsequence(lista, List(1, 2, 3, 4)), "No debe encontrar una sublista más larga")
  }
}
