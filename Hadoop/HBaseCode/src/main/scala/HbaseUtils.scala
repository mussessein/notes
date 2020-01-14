import java.util

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.{Cell, CellUtil, HBaseConfiguration, TableName}
import org.apache.hadoop.hbase.client.{Admin, ColumnFamilyDescriptor, ColumnFamilyDescriptorBuilder, Connection, ConnectionFactory, Get, Put, Result, ResultScanner, Scan, Table, TableDescriptor, TableDescriptorBuilder}
import org.apache.hadoop.hbase.util.Bytes
import org.slf4j.{Logger, LoggerFactory}


object HbaseUtils {

  private val log: Logger = LoggerFactory.getLogger(HbaseUtils.getClass)

  private val configuration: Configuration = HBaseConfiguration.create()
  private val connection: Connection = ConnectionFactory.createConnection(configuration)
  private val admin: Admin = connection.getAdmin
  configuration.set("hbase.zookeeper.quorum", "kube-master")
  configuration.set("hbase.zookeeper.property.clientPort", "2181")

  /**
   * 判断表是否存在
   *
   * @param table
   * @return
   */
  def isTableExist(table: String): Boolean = {
    val tableName: TableName = TableName.valueOf(table)
    admin.tableExists(tableName)
  }

  /**
   * 创建表
   */
  def createTable(table: String, columnFamilys: String*): Unit = {

    if (!isTableExist(table)) {
      if (columnFamilys.length != 0) {
        val tableName: TableName = TableName.valueOf(table)
        // 建表的描述器
        val tableDescriptorBuilder: TableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableName)
        for (columnFamily <- columnFamilys) {
          // 列族的描述器
          val column: ColumnFamilyDescriptor = ColumnFamilyDescriptorBuilder.of(columnFamily)
          // 添加列族
          tableDescriptorBuilder.setColumnFamily(column)
        }
        val tableDescriptor: TableDescriptor = tableDescriptorBuilder.build()
        admin.createTable(tableDescriptor)
        admin.close()
        connection.close()
      } else {
        log.info("==========================列族为空==========================")
      }
    } else {
      log.info("==========================表已存在==========================")
    }
  }

  /**
   * 删除表
   */
  def dropTable(table: String) = {
    if (isTableExist(table)) {
      val tableName: TableName = TableName.valueOf(table)
      admin.disableTable(tableName)
      admin.deleteTable(tableName)
      admin.close()
      connection.close()
    } else {
      log.info("==========================表不存在==========================")
    }
  }

  /**
   * 添加列族
   */
  def addColumnFamily(table: String, columnFamilys: String*): Unit = {

  }

  /**
   * 插入数据
   */
  def put(table: String, rowKey: String, columnFamily: String, column: String, value: String) = {
    val tableName: TableName = TableName.valueOf(table)
    val tableObject: Table = connection.getTable(tableName)
    val put = new Put(Bytes.toBytes(rowKey))
    put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value))
    tableObject.put(put)
    tableObject.close()
    connection.close()
  }

  /**
   * get数据
   */
  def get(table: String, rowKey: String, columnFamily: String) = {
    val tableName: TableName = TableName.valueOf(table)
    val tableObject: Table = connection.getTable(tableName)
    val get: Get = new Get(Bytes.toBytes(rowKey)).addFamily(Bytes.toBytes(columnFamily))
    val result: Result = tableObject.get(get)
    val cells: Array[Cell] = result.rawCells()
    for (cell <- cells) {
      val family: String = Bytes.toString(CellUtil.cloneFamily(cell))
      val column: String = Bytes.toString(CellUtil.cloneQualifier(cell))
      val value: String = Bytes.toString(CellUtil.cloneValue(cell))
      println("columnFamily:" + family + "\t" + "Column:" + column + "\t" + "Value:" + value)
    }
    tableObject.close()
    connection.close()
  }

  /**
   * scan
   * 扫描全表
   */
  def scan(table: String) = {
    val tableObject: Table = connection.getTable(TableName.valueOf(table))
    val scan = new Scan
    val resultScanner: ResultScanner = tableObject.getScanner(scan)
    val result: Result = resultScanner.next()
    val cells: Array[Cell] = result.rawCells()
    for (cell <- cells) {
      val rowKey: String = Bytes.toString(CellUtil.cloneRow(cell))
      val family: String = Bytes.toString(CellUtil.cloneFamily(cell))
      val column: String = Bytes.toString(CellUtil.cloneQualifier(cell))
      val value: String = Bytes.toString(CellUtil.cloneValue(cell))
      println("rowKey:" + rowKey + "\t" + "columnFamily:" + family + "\t" + "Column:" + column + "\t" + "Value:" + value)
    }
    tableObject.close()
    connection.close()
  }

  /**
   * scan
   * 获取指定 rowKey
   */
  def scan(table: String, startRow: String, stopRow: String): Unit = {
    val tableObject: Table = connection.getTable(TableName.valueOf(table))
    val scan: Scan = new Scan().withStartRow(startRow.getBytes()).withStopRow(stopRow.getBytes())
    val resultScanner: ResultScanner = tableObject.getScanner(scan)
    val result: Result = resultScanner.next()
    val cells: Array[Cell] = result.rawCells()
    for (cell <- cells) {
      val rowKey: String = Bytes.toString(CellUtil.cloneRow(cell))
      val family: String = Bytes.toString(CellUtil.cloneFamily(cell))
      val column: String = Bytes.toString(CellUtil.cloneQualifier(cell))
      val value: String = Bytes.toString(CellUtil.cloneValue(cell))
      println("startRow:" + startRow + "\t" + "stopRow:" + stopRow + "\t" + "columnFamily:" + family + "\t" + "Column:" + column + "\t" + "Value:" + value)
    }
    tableObject.close()
    connection.close()
  }

}
