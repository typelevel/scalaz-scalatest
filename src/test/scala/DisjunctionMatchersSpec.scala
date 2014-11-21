package org.typelevel.scalatest

import org.scalatest.{FlatSpec, Matchers}
import org.typelevel.scalatest.DisjunctionMatchers

import scalaz.{ -\/, \/, \/- }
import scalaz.syntax.either._

class DisjunctionMatchersSpec extends FlatSpec with Matchers with Util with DisjunctionMatchers {
  val goodHovercraft = \/.right(hovercraft)
  val badTobacconist = \/.left(thisTobacconist)
  val badRecord = \/.left(thisRecord)

  it should "Match 'blind' left disjunction (i.e. not with specific element)" in {
    badTobacconist should be (left)
  }

  it should "Match alternate 'blind' -\\/ (left) disjunction syntax" in {
    badRecord should be (-\/)
  }

  it should "Match 'valued' left disjunction syntax" in {
    badTobacconist should beLeft(thisTobacconist)
  }

  it should "Match alternate 'valued' -\\/ (left) disjunction syntax" in {
    badRecord should be_-\/(thisRecord)
  }


  it should "Match 'valued' right disjunction syntax" in {
    goodHovercraft should beRight(hovercraft)
  }

  it should "Match alternate 'valued' \\/- (right) disjunction syntax" in {
    goodHovercraft should be_\/-(hovercraft)
  }

  it should "Match 'blind' right disjunction syntax (i.e. with no specific element)" in {
    goodHovercraft should be (right)
  }

  it should "Match alternate 'blind' \\/- (right) disjunction syntax" in {
    goodHovercraft should be (\/-)
  }

  it should "Match negation of left when it's right" in {
    goodHovercraft should not be left
  }

  it should "Match negation of right when it's left" in {
    badRecord should not be right
  }

  it should "Match alternate (symbolic) negation of left when it's right" in {
    goodHovercraft should not be -\/
  }

  it should "Match alternate (symbolic) negation of right when it's left" in {
    badTobacconist should not be \/-
  }
}
