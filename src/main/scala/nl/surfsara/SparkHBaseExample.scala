/*
 * Copyright 2016 SURFsara B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.surfsara

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkContext, SparkConf}
import unicredit.spark.hbase._

object SparkHBaseExample {
  def main(args: Array[String]) {

    val sConf = new SparkConf()
    val sc = new SparkContext(sConf)

    implicit val config = HBaseConfig()

    val table = "Fill in <namespace>:<table>"
    val columnFamily = "Fill in <columnfamily>"

    /* Create a new RDD from a Scala collection */
    val myrdd: RDD[(String, Map[String, String])] = sc.parallelize(Array(
      ("<rowkey 1>", Map("<columnqualifier A>" -> "<value X>")),
      ("<rowkey 2>", Map("<columnqualifier B>" -> "<value Y>"))
    ))

    /* Store the RDD in HBase */
    myrdd.toHBase(table, columnFamily)

    /* Create an RDD from HBase table scan */
    val hbaserdd = sc.hbase[String](table, Map(
      "cf1" -> Set("cq1", "cq2"),
      "cf2" -> Set("cq3" )
    ))
    println(hbaserdd.first)

  }
}
