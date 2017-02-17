package org.typelevel.scalatest

import scalaz.concurrent.Task
import org.scalactic.source
import org.scalatest.exceptions.{ StackDepthException, TestFailedException }

import scalaz.{ -\/, \/- }

trait TaskValues {
  import scala.language.implicitConversions

  /**
   * Implicit conversion that adds a `syncValue` method to `scalaz.concurrent.Task`
   *
   * @param task the `scalaz.concurrent.Task` on which to add the `syncValue` method
   */
  implicit def convertTaskToTaskable[A](task: Task[A])(implicit pos: source.Position) = new Taskable(task, pos)

  // TODO: Fix me as a proper repl session example
  /**
   * Container class for matching success
   * type stuff in `scalaz.concurrent.Task` containers,
   * similar to `org.scalatest.OptionValues.Valuable`
   *
   * Meant to allow you to make statements like:
   *
   * <pre class="stREPL">
   *   task.syncValue should be &gt; 15
   * </pre>
   *
   * Where it only matches if the task didn't fail and the result is `\/-(9)`
   *
   * Otherwise your test will fail, indicating that it was left instead of right
   *
   * @param task A `scalaz.concurrent.Task` object to try converting to a `Taskable`
   *
   * @see org.scalatest.OptionValues.Valuable
   */
  class Taskable[A](task: Task[A], pos: source.Position) {
    def syncValue: A = {
      task.unsafePerformSyncAttempt match {
        case \/-(right) =>
          right
        case -\/(left) =>
          throw new TestFailedException((_: StackDepthException) => Some(s"Task failed: Resulting $left is -\\/, expected \\/-."), None, pos)
      }
    }

    def failValue: Throwable = {
      task.unsafePerformSyncAttempt match {
        case \/-(right) =>
          throw new TestFailedException((_: StackDepthException) => Some(s"Task failed: Resulting $right is \\/-, expected -\\/."), None, pos)
        case -\/(left) =>
          left
      }
    }
  }
}

// TODO: Fix me as a proper repl session example
/**
 *
 * Companion object for easy importing – rather than mixing in – to allow `TaskValues` operations.
 *
 * This will permit you to invoke a `syncValue` method on an instance of a `scalaz.concurrent.Task`,
 * which attempts the Task and then attempts to return the right of the resulting disjunction.
 *
 *
 * Meant to allow you to make statements like:
 *
 * <pre class="stREPL">
 *   task.syncValue should be &gt; 15
 * </pre>
 *
 * Where it only matches if the task didn't fail and the result is `\/-(9)`
 *
 * Otherwise your test will fail, indicating that it was left instead of right.
 *
 * @see org.scalatest.OptionValues.Valuable
 */
object TaskValues extends TaskValues
