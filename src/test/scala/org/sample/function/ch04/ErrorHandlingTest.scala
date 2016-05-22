package org.sample.function.ch04

import org.scalatest.Matchers._
import org.scalatest.WordSpec

import scala.util.{Failure, Success, Try}

/**
  * Created by koyo on 2016/05/22.
  */
class ErrorHandlingTest extends WordSpec {

  case class User(id: Int, name: String)

  "Option" should {

    def getUserOp(name: String): Option[User] = {
      name match {
        case "ex"   => throw new Exception()
        case "none" => None
        case _      => Some(User(1, name))
      }
    }

    // Optionを使う場合、内部の例外は漏洩する
    "not able to handle exception in function" in {
      an [Exception] should be thrownBy {getUserOp("ex")}
    }

    "none can be handled as default" in {
      val result = getUserOp("none").map(_.name).getOrElse("default")
      result should be("default")
    }

    "none can be handled as Exception" in {
      an [NoSuchElementException] should be thrownBy {
        getUserOp("none").map(_.name).getOrElse(throw new NoSuchElementException)
      }
    }
  }

  "Try" should {

    def getUserTry(name: String): Try[User] = {
      name match {
        case "ex" => Failure(new NoSuchElementException)
        case _ => Success(User(1, name))
      }
    }

    "handle exception as failure" in {
      val result = getUserTry("ex").map(_.name).getOrElse("default")
      result should be("default")
    }

  }

  "Excercise 4.3" should {

    def map2[A, B, C] (a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = {
      (a, b) match {
        case (None, None)  => None // Noneが含まれているものは変数を使えない
        case (None, _)     => None
        case (_, None)     => None
        case x: (Some[A], Some[B]) => Some(f(x._1.get, x._2.get))
      }
    }

    def multipleStrOpt(strOp: Option[String], intOp: Option[Int]) = {
      map2(strOp, intOp)(multipleStr)
    }

    def multipleStr(str: String, num: Int) = str * num

    "map None if A is None" in {
      multipleStrOpt(None, None) should be(None)
      multipleStrOpt(None, Some(3)) should be(None)
      multipleStrOpt(Some("Hello"), None) should be(None)
      multipleStrOpt(Some("Hello"), Some(3)) should be(Some("HelloHelloHello"))
    }

  }

}
