package net.evilmonkeylabs.scalatest

import org.scalatest.evilmonkey.patch.MonkeyPatch
import org.scalatest.exceptions.TestFailedException

import scalaz.{ -\/, \/, \/- }

trait DisjunctionValues {
  import scala.language.implicitConversions


  /**
   * Implicit conversion that adds a `value` method to `scalaz.\/`
   *
   * @param disjunction the `scalaz.\/` on which to add the `value` method
   */
  implicit def convertDisjunctionToDisjunctionated[E, T](disjunction: E \/ T) = new Disjunctionable(disjunction)

  // TODO: Fix me as a proper repl session example
  /**
   * Container class for matching success
   * type stuff in `scalaz.\/` containers,
   * similar to `org.scalatest.OptionValues.Valuable`
   *
   * Meant to allow you to make statements like:
   *
   * <pre class="stREPL">
   *   result.value should be &gt; 15
   * </pre>
   *
   * Where it only matches if result is `\/-(9)`
   *
   * Otherwise your test will fail, indicating that it was left instead of right
   *
   * @param disjunction A `scalaz.\/` object to try converting to a `Disjunctionable`
   *
   * @see org.scalatest.OptionValues.Valuable
   */
  class Disjunctionable[E, T](disjunction: E \/ T) {
    def value: T = {
      disjunction match {
        case \/-(right) =>
          right
        case -\/(left) =>
          throw new TestFailedException(sde => Some(s"$left is -\\/, expected \\/-."), None,
                                               MonkeyPatch.getStackDepthFun("DisjunctionValues.scala", "value"))
      }

    }
  }

}


// TODO: Fix me as a proper repl session example
/**
 *
 * Companion object for easy importing – rather than mixing in – to allow `DisjunctionValues` operations.
 *
 * This will permit you to invoke a `value` method on an instance of a `scalaz.\/`,
 * which attempts to unwrap the right disjunction
 *
 * Similar to `org.scalatest.OptionValues.Valuable`
 *
 * Meant to allow you to make statements like:
 *
 * <pre class="stREPL">
 *   result.value should be &gt; 15
 * </pre>
 *
 * Where it only matches if result is `\/-(9)`
 *
 * Otherwise your test will fail, indicating that it was left instead of right
 *
 * @see org.scalatest.OptionValues.Valuable
 */
object DisjunctionValues extends DisjunctionValues
