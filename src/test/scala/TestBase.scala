package net.sparktutorials.scaffold

import org.scalatest._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

abstract class BaseSpec extends FlatSpec with BeforeAndAfterAll with Matchers {
  private val master = "local"
  private val appName = "testing"
  var sc: SparkContext = _
  var sqlContext: SQLContext = _

  val conf = new SparkConf()
    .setMaster(master)
    .setAppName(appName)
    .set("spark.driver.allowMultipleContexts","true")

  override protected def beforeAll(): Unit = {
    super.beforeAll()
    sc = SparkContext.getOrCreate(conf)
    sqlContext = SQLContext.getOrCreate(sc)
  }

  override protected def afterAll(): Unit = {
    try {
      sc.stop()
      sc = null
      sqlContext = null
    } finally {
      super.afterAll()
    }
  }
}

