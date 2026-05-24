package cucumber.examples.scalacalculator

import scala.collection.mutable.Queue

sealed trait Arg

object Arg {
  implicit def op(s: String): Op = Op(s)
  implicit def value(v: Double): Val = Val(v)
}

case class Op(value: String) extends Arg
case class Val(value: Double) extends Arg

class RpnCalculator {
  private val stack = Queue.empty[Double]

  private def op(f: (Double, Double) => Double) =
    stack += f(stack.dequeue(), stack.dequeue())

  def push(arg: Arg): Unit = {
    arg match {
      case Op("+")    => op(_ + _)
      case Op("-")    => op(_ - _)
      case Op("*")    => op(_ * _)
      case Op("/")    => op(_ / _)
      case Val(value) => stack += value
      case _          => ()
    }
    ()
  }

  def value: Double = stack.head
}
