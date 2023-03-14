import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.annotation.tailrec
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.{Duration, DurationInt}
import scala.io.StdIn

object Main {
  def main(args: Array[String]): Unit = {
    Await.result(repeat(action, until), 60.seconds)
    println("The end")
  }

  def action() = StdIn.readLine("Enter password: ")
  def until(password: String) = {
    password == "qwerty"
  }

  def repeat[T](action: => T, until: T => Boolean)={
    @tailrec def doActionUntil(): T= {
      val pas = action
      if (until(pas)) pas
      else doActionUntil()
    }
    Future[T] {doActionUntil()}
  }
}