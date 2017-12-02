package examples

object RecordDefinitionExample {
  import shapeless._
  import record._
  import ops.hlist.{ToList,Align}
  import ops.record.{ Keys, Values }
  import shapeless.labelled._
  import syntax.singleton._

  case class Person(firstName:String, lastName:String, age:Int)
  val personGen = LabelledGeneric[Person]

  val defaultLastName = Field("lastName" ->> "")
  val defaultFirstName = Field("firstName" ->> "")


  type lastNameRepr = defaultLastName.F
  type firstNameRepr = defaultFirstName.F


  type PersonRepr =
    firstNameRepr::
    lastNameRepr::
    HNil

  def main(args:Array[String]):Unit = {

  }

  trait Field {
    type K
    type V
    type F = FieldType[K, V]
  }

  object Field {
    def apply[K0, V0](sample: FieldType[K0, V0]) = new Field { type K = K0; type V = V0 }
  }
}