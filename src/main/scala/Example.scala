package net.sparktutorials.scaffold

import org.apache.log4j.{Logger};
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.hive.HiveContext

class ExampleClass extends Serializable {
  @transient lazy val logger = Logger.getLogger(getClass.getName)    

  def cobble(arg:String) = {
    logger.info("Now cobbling!")
    "Cobble the " + arg
  }
}

object ExampleClass extends Serializable {
  def main(args:Array[String]) = {
    val name = "Example Application"
    val conf = new SparkConf().setAppName(name)
    val sc = new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    val mc = new ExampleClass()
    val data = sc.parallelize(args)

    data.map(mc.cobble).foreach(println)

  }
}
