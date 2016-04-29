package org.sample.function.ch5

import org.scalatest.Matchers._
import org.scalatest.WordSpec
import scala.collection.MapLike

class OptionTest extends WordSpec {

  // None は受け付けられるのか？
  "List" should {

    "[map] use map to convert it's type" in {
      val result = List(1, 2, 3).map {x => String.valueOf(x * 3)}
      result should be(List("3", "6", "9"))
    }

    "[map] not use map when it has no-match case pattern" in {
      an[MatchError] should be thrownBy {
        val result = List(1, 2, 3).map { x =>
          x match {
            case 1 => 10
            case 2 => 20
          }
        }
      }
    }

    "[collect] use collect when it has no-match case patten" in {
      // collect don't use leading value "x"
      val result = List(1, 2, 3).collect {
          case 1 => 10
          case 2 => 20
        }
      result should be(List(10, 20))
    }

    "[collect first] use when it can collect value" in {
      val result = List(1, 2, 3).collectFirst{
        case 2 => "two"
        case 3 => "three"
      }
      result should be(Some("two"))
    }

    "[flatten] use flatten to make a List flatten" in {
      val result = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9)).flatten
      result should be(List(1, 2, 3, 4, 5, 6, 7, 8, 9))
    }

    "[flatMap] use flatMap to operate Coll elements " in {
      val result = List(List(1, 2), List(3, 4, 5), List(6, 7, 8))
        .flatMap((x: List[Int]) => x match {
          case List(x, y) => List(x, y)              // 要素数が 2         => バラして配置
          case l: List[Int] if 15 < l.sum => Some(l) // 要素の合計が 15超え => そのまま
          case _ => None                             // それ以外           => 除去
        })
      result should be(List(1, 2, List(6, 7, 8)))
    }

    "[zip] use zip to mix 2 collection by their index" in {
      val result = List(1, 2, 3, 4).zip(List('a, 'b, 'c))
      result should be(List((1, 'a), (2, 'b), (3, 'c))) // *1) 要素数は短い方に合わせる. *2) 要素は Tupleとなる
    }

    "[unzip] use unzip one collection" in {
      val result = List((1, 'a), (2, 'b), (3, 'c)).unzip
      result should be((List(1, 2, 3), List('a, 'b, 'c))) // 2つのListは Tupleに纏められる
    }
  }

  "Map" should {

    "[mapValue] use mapValue to convert its value by (value)" in {
      val result = Map(1 -> 10, 2 -> 20, 3 -> 30).mapValues(x => x * 2)
      result should be(Map(1 -> 20, 2 -> 40, 3 -> 60))
    }

    "[transform] use transform to convert its value by (key, value)" in {
      val result = Map(1 -> 10, 2 -> 20, 3 -> 30).transform((k, v) => k + v)
      result should be(Map(1 -> 11, 2 -> 22, 3 -> 33))
    }

    "[flatMap]" in {
      // TODO Map#flatMapをどう活用するのか？
      // val result = Map(1 -> 10, 2 -> 20, 3 -> 30).flatMap((k: Int, v: Int) => Option(k))
    }

    "[keys] use keys to make keySet" in {
      val result = Map('a -> 10, 'b -> 20, 'c -> 30).keys
      result should be(Set('a, 'b, 'c))
    }

    "[keySet] use keySet to make keySet" in {
      val result = Map('a -> 10 ,'b -> 20, 'c -> 30).keySet
      result should be(Set('a, 'b, 'c))
    }

    "[values] use values to make values" in {
      val result = Map('a -> 10 ,'b -> 20, 'c -> 30).values
      println(result.getClass)
      // TODO 検証の仕方が不明
      result.toSeq should be(Seq(10, 20, 30))
    }

  }


  "" should {
    "" in {

      // flatMapは 各要素が渡されて、各要素をラップしたものを返すと、それが展開されたものが提供される。Noneは消える

      val result = List(1, 2, 3)
        .flatMap((x: Int) => List(x * 2))

      result should be(List(2, 4, 6))

      val result3 = List(Some(List(1, 2, 3)), Some(List(4, 5, 6)), None)
        .flatMap((x: Option[List[Int]]) => x)
      println(result3)
    }
  }

}