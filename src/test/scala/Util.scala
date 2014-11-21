package org.typelevel.scalatest.test

trait Util {

  val thisRecord = "I will not buy this record, it is scratched."
  val thisTobacconist = "Ah! I will not buy this tobacconist's, it is scratched."
  val hovercraft = "Yes, cigarettes. My hovercraft is full of eels."
  /**
   * Shamelessly swiped from Scalatest.
   */
  def thisLineNumber = {
    val st = Thread.currentThread.getStackTrace

    if (!st(2).getMethodName.contains("thisLineNumber"))
      st(2).getLineNumber
    else
      st(3).getLineNumber
  }

}
