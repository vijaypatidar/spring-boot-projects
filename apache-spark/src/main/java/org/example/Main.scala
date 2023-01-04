package org.example

import org.apache.spark.{SparkConf, SparkContext}

object Main {
  def main(array: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("wiki_test") // create a spark config object
    val sc = new SparkContext(conf) // Create a spark context
    val lines = Array("a b c", "a b")
    val wordFreq = sc.parallelize(lines).flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)
    wordFreq.sortBy(s => -s._2).map(x => (x._2, x._1)).top(10) // Get the top 10 words. Swap word and count to sort by count.
    println(s"================\nword frequency as map: ${wordFreq.collectAsMap().toString()}")
  }
}
