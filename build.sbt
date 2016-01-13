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

version := "0.1"

organization := "nl.surfsara"

scalaVersion := "2.10.5"

name := "spark-hbase-example"

val sparkVersion  = "1.5.2"
val hadoopVersion = "2.6.0"
val hbaseVersion  = "0.98.4-hadoop2"

libraryDependencies ++= Seq(
  "org.apache.spark"  %% "spark-core"      % sparkVersion  % "provided",
  "org.apache.spark"  %% "spark-streaming" % sparkVersion  % "provided",
  "org.apache.hadoop" %  "hadoop-client"   % hadoopVersion % "provided",
  "org.apache.hbase"  %  "hbase-common"    % hbaseVersion,
  "org.apache.hbase"  %  "hbase-server"    % hbaseVersion,
  "org.apache.hbase"  %  "hbase-protocol"  % hbaseVersion,
  "org.apache.hbase"  %  "hbase-client"    % hbaseVersion,
  "eu.unicredit"      %% "hbase-rdd"       % "0.6.0"
)

assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs @ _*)         => MergeStrategy.first
  case PathList("org", "apache", "jasper", xs @ _*)  => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
