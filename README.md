# An Apache Spark Scala Project Template

This project lays out a simple Apache Spark Project Template. It includes small demos for testing and logging. The license is Apache 2.0. You can read about the process of creating it below or on SparkTutorials.net

# Spark in Production<a id="sec-1" name="sec-1"></a>

Like anything else, when putting things into production it's important that behavior is well scoped and understood. Things like testing come to the forefront of what you're doing and it's important to make sure that things are well scoped. After writing a couple of different projects in Scala and Spark I saw some repeated approaches and patterns I was taking, especially with regard to testing. I wanted to write and share a simple base application that people can use for themselves.

If you'd rather just take a look at the template you can [find it on my github.](https://github.com/anabranch/Apache-Spark-Scala-Project-Template) (<https://github.com/anabranch/Apache-Spark-Scala-Project-Template>)

# Requirements<a id="sec-2" name="sec-2"></a>

There are a couple of requirements that I wanted out of my project scaffold, I wanted logging that was simple to include, I wanted to make sure that testing was easy and extensible. Lastly, I wanted a simple example to help jog my memory of how I like to start my projects.

## Testing<a id="sec-2-1" name="sec-2-1"></a>

I wanted to make sure that testing was both easy and intuitive in the application. I wanted to make sure that testing involved the true Spark datatypes with everything from an RDD to a DataFrame. The framework I have chosen to build on is [ScalaTest](http://scalatest.org/), as it's got a great way of writing Spark tests. You do have to jump through some hoops regarding parallel test cases as well as trying to prevent multiple Spark contexts from being created but this template makes it easy to at least make it so that too many don't overlap.

The Most important thing to do is to be sure to sub-class the [TestBase.scala](https://github.com/anabranch/Spark-Project-Template/blob/master/src/test/scala/TestBase.scala) class. That will give you a Spark conf as well as sqlContext(which can of course be made a hive context). Once you've subclassed it as you can see in [ExampleTest.scala](https://github.com/anabranch/Spark-Project-Template/blob/master/src/test/scala/ExampleTest.scala), you simply write your test as you would a normal ScalaTest.

```scala
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
```

With this framework it's easy to build and execute tests and it always helps to get an understanding from the test suite about how well and extensively you've tested your code. By including the coverage tool, getting test coverage is as simple as:

```sh
sbt clean coverage test
```
## Logging<a id="sec-2-2" name="sec-2-2"></a>

My second major requirement was logging. I wanted it to be easy for me to remember how to include my logger when starting new projects. Luckily, it's incredibly simple to do! Hat's off to [Akhil Das](http://apache-spark-developers-list.1001551.n3.nabble.com/template/NamlServlet.jtp?macro%3Duser_nodes&user%3D291) of the Spark user Group that pointed out a simple way to [include your logger in a Serializable way](https://www.mail-archive.com/user@spark.apache.org/msg29010.html) by use of the `transient` annotation in scala. This annotation prevents the field from getting serialized.

```scala
@transient lazy val logger = Logger.getLogger(getClass.getName)
```

You can read more [about annotations here.](http://www.slideshare.net/knoldus/annotations-14963496)

# Conclusion<a id="sec-3" name="sec-3"></a>

While you may want to structure your projects different. This set up works and is the one that I will continue to update. If you want to contribute please feel free to submit a pull request!
