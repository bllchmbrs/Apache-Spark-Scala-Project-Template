package databricks.examples

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SQLContext

class ExampleClass extends Serializable {
  @transient lazy val logger = Logger.getLogger(getClass.getName)    

  def doStuff(arg:String) = {
    logger.info("Now Running the Example!")
    "Run This Example with arg: " + arg
  }
}

object ExampleClass extends Serializable {
  def main(args:Array[String]) = {
    val name = "Example Application"
    val spark = SparkSession.builder
      .appName(name)
      .getOrCreate()
    val mc = new ExampleClass()
    val data = spark.sparkContext.parallelize(args)

    data.map(mc.doStuff).foreach(println)

  }
}
