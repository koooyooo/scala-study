package org.sample.scalatest.mock

class Target {
  
  val concat = (a: String, b: String) => a + b
  
  def plus(a: Int, b: Int): Int = a + b
}