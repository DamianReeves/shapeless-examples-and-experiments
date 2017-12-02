package examples.newTypeUsage

object NewtypeExamples extends App {
  import shapeless._
  import newtype._

  // MyString is a new type with String as its underlying representation and with its operations
  // provided by MyStringOps
  type MyString = Newtype[String, MyStringOps]

  // MyString constructor
  def MyString(s : String) : MyString = newtype(s)

  // Expose String#size as MyString#mySize. No other operations of String are accessible
  case class MyStringOps(s : String) {
    def mySize = s.size
  }
  implicit val mkOps = MyStringOps

  val ms = MyString("foo")
  //val s : String = ms        // Does not compile
  //val ms2 : MyString = "foo" // Does not compile

  //ms.size                    // Does not compile
  assert(ms.mySize == 3)       // Compiles. Assertion satisfied.

  val s2 = "bar"
  val ms2 = MyString(s2)

  // Verify that this is an unboxed representation
  assert(ms2 eq (s2 : AnyRef))
}