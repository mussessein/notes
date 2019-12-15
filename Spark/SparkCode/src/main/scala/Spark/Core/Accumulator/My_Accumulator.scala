package Spark.Core.Accumulator

import java.util

import org.apache.spark.util.AccumulatorV2

/**
  * 自定义累加器对象:返回给Driver含有"H"的单词
  * 1. 继承AccumulatorV2
  * 2. 实现抽象方法
  */
class My_Accumulator extends AccumulatorV2[String, util.ArrayList[String]] {
  var list = new util.ArrayList[String]()

  // isZero就是初始化,为空即初始化
  override def isZero: Boolean = list.isEmpty

  // 复制累加器
  override def copy(): AccumulatorV2[String, util.ArrayList[String]] = {
    new My_Accumulator
  }

  // 重置累加器对象
  override def reset(): Unit = {
    list.clear()
  }

  override def add(v: String): Unit = {
    if (v.contains("H")) {
      list.add(v)
    }
  }

  // 合并累加器
  override def merge(other: AccumulatorV2[String, util.ArrayList[String]]): Unit = {
    list.addAll(other.value)
  }

  // 获取累加器结果
  override def value: util.ArrayList[String] = list
}
