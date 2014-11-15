package org.scalatest.evilmonkey.patch

import org.scalatest.exceptions.{StackDepthException, StackDepthExceptionHelper}

/**
 * Some hacky patches into package private stuff I want to access from scalatest,
 * at least until I convince Bill Venners to open it up to me.
 */
object MonkeyPatch {
  def getStackDepth(stackTrace: Array[StackTraceElement], fileName: String, methodName: String, adjustment: Int = 0) = {
    StackDepthExceptionHelper.getStackDepth(stackTrace, fileName, methodName, adjustment)
  }

  def getStackDepthFun(fileName: String, methodName: String, adjustment: Int = 0) = {
    StackDepthExceptionHelper.getStackDepthFun(fileName, methodName, adjustment)
  }

  def toExceptionFunction(message: Option[String]) = {
    StackDepthException.toExceptionFunction(message)
  }
}
