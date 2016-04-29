package org.sample.function.other

import org.scalatest.WordSpec
import org.scalatest.Matchers._

class FlatMapTest extends WordSpec {
  
  "Composite Seq" should {
    
    "be just flattened without any effect" in {
      val result = Seq(Seq(1, 2, 3), Seq(4), Seq(5, 6)).flatMap(x => x)
      result should be(Seq(1, 2, 3, 4, 5, 6))
    }
    
    "be add 10 for each Seq" in {
      val result = Seq(Seq(1, 2, 3), Seq(4), Seq(5, 6)).flatMap(x => 10 +: x )
      result should be(Seq(10, 1, 2, 3, 10, 4, 10, 5, 6))
    }
  }
  
  "A List" should {
    "be add for each value" in {
      val result = List(1, 10, 100).flatMap { x => List(x, x+1, x+2) }
      result should be(List(1, 2, 3, 10, 11, 12, 100, 101, 102))
    }
  }
  
  "Option" should {
    "keep sentence" in {
      val opt: Option[Int] = Some(1)
      val result = opt.flatMap { x => Some(x * 10)}
      result should be(Some(10))
    }
    
    "none won't be called" in {
      val opt: Option[Int] = None
      val result = opt.flatMap { x => fail}
      result should be(None)
    }
    
  }
  
  
}