package databricks.examples

import org.scalatest._
import org.apache.spark._

class Example extends BaseSpec {
  private var myInstance: ExampleClass = _
  
  override protected def beforeAll(): Unit = {
    super.beforeAll()
    myInstance = new ExampleClass()
  }

  "I Should be Able to Cobble a List" should "and it should have the right output" in {
    val myRDD = sc.parallelize(Seq("Bar", "Foo", "Foo"))
    myRDD.map(myInstance.doStuff).count should be (3)
    myRDD.map(myInstance.doStuff).first should be ("Run This Example with arg: Bar")
  }

}
