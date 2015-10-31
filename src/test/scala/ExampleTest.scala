package net.sparktutorials.scaffold

import org.scalatest._
import org.apache.spark._

class Example extends BaseSpec {
  private var dataCobbler: ExampleClass = _
  
  override protected def beforeAll(): Unit = {
    super.beforeAll()
    dataCobbler = new ExampleClass()
  }

  "I Should be Able to Cobble a List" should "and it should have the right output" in {
    val toCobble = sc.parallelize(Seq("Shoe", "Boot", "Foo"))
    toCobble.map(dataCobbler.cobble).count should be (3)
    toCobble.map(dataCobbler.cobble).first should be ("Cobble the Shoe")
  }

}
