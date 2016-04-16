package org.sample.scalatest.mock

class MockTarget {
  
  val concat = (a: String, b: String) => a + b
  
  def plus(a: Int, b: Int): Int = a + b
}