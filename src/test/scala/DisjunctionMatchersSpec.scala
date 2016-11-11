package org.typelevel.scalatest

import org.scalatest.{ FlatSpec, Matchers }

import scalaz.{ -\/, \/, \/- }
import scalaz.syntax.either._

class DisjunctionMatchersSpec extends TestBase with DisjunctionMatchers {
  val goodHovercraft = \/.right(hovercraft)
  val badTobacconist = \/.left(thisTobacconist)
  val badRecord = \/.left(thisRecord)
  "DisjunctionMatchers" should {
    "Match 'blind' left disjunction (i.e. not with specific element)" in {
      badTobacconist should be(left)
    }

    "Match alternate 'blind' -\\/ (left) disjunction syntax" in {
      badRecord should be(-\/)
    }

    "Match 'valued' left disjunction syntax" in {
      badTobacconist should beLeft(thisTobacconist)
    }

    "Match alternate 'valued' -\\/ (left) disjunction syntax" in {
      badRecord should be_-\/(thisRecord)
    }

    "Match 'valued' right disjunction syntax" in {
      goodHovercraft should beRight(hovercraft)
    }

    "Match alternate 'valued' \\/- (right) disjunction syntax" in {
      goodHovercraft should be_\/-(hovercraft)
    }

    "Match 'blind' right disjunction syntax (i.e. with no specific element)" in {
      goodHovercraft should be(right)
    }

    "Match alternate 'blind' \\/- (right) disjunction syntax" in {
      goodHovercraft should be(\/-)
    }

    "Match negation of left when it's right" in {
      goodHovercraft should not be left
    }

    "Match negation of right when it's left" in {
      badRecord should not be right
    }

    "Match alternate (symbolic) negation of left when it's right" in {
      goodHovercraft should not be -\/
    }

    "Match alternate (symbolic) negation of right when it's left" in {
      badTobacconist should not be \/-
    }
  }
}
