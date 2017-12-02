package examples.newTypeUsage

object NewTypeTaskNameExample extends App {
  import shapeless._
  import newtype._

  type TaskName = Newtype[String, TaskNameOps]
  def TaskName(name:String):TaskName = newtype(name)

  case class TaskNameOps(name : String){
    override def equals(obj: scala.Any): Boolean = obj match {
      case other:String => other.equalsIgnoreCase(name)
      case _ => false
    }

    override def hashCode(): Int = name.toLowerCase.hashCode
  }
  implicit val mkTaskOps: TaskNameOps.type = TaskNameOps

  val task1 = TaskName("Test")
  val task2 = TaskName("test")


  println(s"task1 == task2: ${task1 == task2}")
}
