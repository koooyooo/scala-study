package org.sample.scalatest.mock

import org.scalamock.scalatest.MockFactory
import org.scalatest.WordSpec

class MockTargetTest extends WordSpec with MockFactory {
  
  "Target" should {
    
    "be tested as Mock object" in {
      val mockTarget = mock[MockTarget]
      
      // mockは 依存先に対し、入力の検証と出力の整備を事前設定する (3回しか呼ばれないことも暗黙的に定義)
      (mockTarget.plus _).expects(1, 2).returning(10)
      (mockTarget.plus _).expects(2, 4).returning(20)
      (mockTarget.plus _).expects(2, 4).returning(30)
      
      
      assert(10 == mockTarget.plus(1, 2))
      assert(20 == mockTarget.plus(2, 4))
      assert(30 == mockTarget.plus(2, 4)) 
      // assert( 0 == mockTarget.plus(3, 6)) // 計画外の呼び出しを行うとエラーとなる
    }
    
    
    "be tested as Stub object" in {
      val stubTarget = stub[MockTarget]
      
      // stubは依存先に対し、出力の整備だけを事前設定する  (3回しか呼ばれないことも暗黙的に定義)
      (stubTarget.plus _).when(1, 2).returns(10)
      (stubTarget.plus _).when(2, 4).returns(20)
      (stubTarget.plus _).when(2, 4).returns(20)
      
      assert(10 == stubTarget.plus(1, 2))
      assert(20 == stubTarget.plus(2, 4))
      assert(20 == stubTarget.plus(2, 4))
      assert(0  == stubTarget.plus(3, 6)) // 計画外の呼び出しもwhenのスコープ外ならエラーとならない (但し結果は 0に変わる)
      
      // 依存性の検証は後から行う
      (stubTarget.plus _).verify(1, 2).once()
      (stubTarget.plus _).verify(2, 4).twice()
      
      (stubTarget.plus _).verify(0, 0).never() // 呼ばれないことも検証できる
      
      
    }
    
  }
  
  
}