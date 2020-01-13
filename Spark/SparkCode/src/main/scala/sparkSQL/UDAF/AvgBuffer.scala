package sparkSQL.UDAF
case class AvgBuffer(var sum:BigInt,var count:Int)