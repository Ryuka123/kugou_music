package com.daoke360.kugou_music.common

import com.alibaba.fastjson.JSON
import com.daoke360.kugou_music.caseclass.IPRule
import com.daoke360.kugou_music.constants.LogConstants
import com.daoke360.kugou_music.utils.Utils
import org.apache.commons.lang.StringUtils

import scala.collection.mutable


/**
  * Created by 黑桃K on 2018-5-7.
  * 刀客程序员淘宝旗舰店：https://daoke360.taobao.com/
  * 刀客程序员官网：http://www.daoke360.com
  * QQ:272488352
  */
object LogAnalysis {

  /**
    * 解析ip
    *
    * @param ip
    * @param ipRules
    * @param logMap
    */
  private def handleIP(ip: String, ipRules: Array[IPRule], logMap: mutable.Map[String, String]) = {
    val regionInfo = IPAnalysis.analysisIP(ip, ipRules)
    logMap.put(LogConstants.LOG_COLUMNS_NAME_COUNTRY, regionInfo.country)
    logMap.put(LogConstants.LOG_COLUMNS_NAME_PROVINCE, regionInfo.province)
    logMap.put(LogConstants.LOG_COLUMNS_NAME_CITY, regionInfo.city)
    logMap.put(LogConstants.LOG_COLUMNS_NAME_IP, ip)
  }

  /**
    * 处理请求数据
    *
    * @param requestParams 请求参数 GET /?bData=eyJrdGluZ1Rva2VuIjoiQzVRUzZUZUNnb3NZcURkOGVmcnZaZEZoVkpWWmZtNXNLdktzR3VxQVp5MFpGN242RFhpZXB4XC8wM0prdm1zN2RkeDk2Y2s5aG05N1FKM0h0SmZxOHpRPT0iLCJiZWhhdmlvcktleSI6IkRGU0o0MDAiLCJiZWhhdmlvckRhdGEiOnsiem9uZ0tleSI6IkZNNzAyIiwicHJvZ3JhbUlkIjoiMzEwMTMxIiwiYWxidW1JZCI6IjE2MjQ5IiwiYW5jaG9ySWQiOiIxMTUwNSIsInBsYXlUaW1lIjowLCJvbi1vZmYiOmZhbHNlfX0= HTTP/1.1
    * @param logMap        日志集合map
    */
  private def handleRequestParams(requestParams: String, logMap: mutable.Map[String, String]) = {
    val fields = requestParams.split("[?]")
    if (fields.length == 2) {
      //fields(1)==>bData=eyJrdGluZ1Rva2VuIjoiQzVRUzZUZUNnb3NZcURkOGVmcnZaZEZoVkpWWmZtNXNLdktzR3VxQVp5MFpGN242RFhpZXB4XC8wM0prdm1zN2RkeDk2Y2s5aG05N1FKM0h0SmZxOHpRPT0iLCJiZWhhdmlvcktleSI6IkRGU0o0MDAiLCJiZWhhdmlvckRhdGEiOnsiem9uZ0tleSI6IkZNNzAyIiwicHJvZ3JhbUlkIjoiMzEwMTMxIiwiYWxidW1JZCI6IjE2MjQ5IiwiYW5jaG9ySWQiOiIxMTUwNSIsInBsYXlUaW1lIjowLCJvbi1vZmYiOmZhbHNlfX0= HTTP/1.1
      val dataArray = fields(1).split(" ")
      if (dataArray.length == 2) {
        //bData=eyJrdGluZ1Rva2VuIjoiQzVRUzZUZUNnb3NZcURkOGVmcnZaZEZoVkpWWmZtNXNLdktzR3VxQVp5MFpGN242RFhpZXB4XC8wM0prdm1zN2RkeDk2Y2s5aG05N1FKM0h0SmZxOHpRPT0iLCJiZWhhdmlvcktleSI6IkRGU0o0MDAiLCJiZWhhdmlvckRhdGEiOnsiem9uZ0tleSI6IkZNNzAyIiwicHJvZ3JhbUlkIjoiMzEwMTMxIiwiYWxidW1JZCI6IjE2MjQ5IiwiYW5jaG9ySWQiOiIxMTUwNSIsInBsYXlUaW1lIjowLCJvbi1vZmYiOmZhbHNlfX0=
        val data = dataArray(0).split("=")
        if (data.length == 2) {
          val behavior = data(0)
          if (behavior.equals("bData")) {
            logMap.put(LogConstants.lOG_COLUMNS_NAME_BEHAVIOR_FLAG, "bData")
          } else if (behavior.equals("pData")) {
            logMap.put(LogConstants.lOG_COLUMNS_NAME_BEHAVIOR_FLAG, "pData")
          }
          //取出base64转码的字符串
          val base64EncodeString = data(1)
          val jsonStr = new String(Utils.base64Decode(base64EncodeString))
          JSON.parseObject(jsonStr).entrySet().toArray.foreach(t2 => {
            val kv = t2.toString.split("=")
            logMap.put(kv(0), kv(1))
          })
        }
      }
    }
  }

  //处理behaviorData数据, {"albumId":"16249","anchorId":"11505","on-off":false,"playTime":0,"programId":"310131","zongKey":"FM702"}
  def handleBehaviorData(logMap: mutable.Map[String, String]) ={
  }

  /**
    * 解析单条日志方法
    *
    * @param logText 106.61.111.250|0.000|-|24/Apr/2018:03:14:21 +0800|GET /?bData=eyJrdGluZ1Rva2VuIjoiQzVRUzZUZUNnb3NZcURkOGVmcnZaZEZoVkpWWmZtNXNLdktzR3VxQVp5MFpGN242RFhpZXB4XC8wM0prdm1zN2RkeDk2Y2s5aG05N1FKM0h0SmZxOHpRPT0iLCJiZWhhdmlvcktleSI6IkRGU0o0MDAiLCJiZWhhdmlvckRhdGEiOnsiem9uZ0tleSI6IkZNNzAyIiwicHJvZ3JhbUlkIjoiMzEwMTMxIiwiYWxidW1JZCI6IjE2MjQ5IiwiYW5jaG9ySWQiOiIxMTUwNSIsInBsYXlUaW1lIjowLCJvbi1vZmYiOmZhbHNlfX0= HTTP/1.1|200|5|"Dalvik/2.1.0 (Linux; U; Android 7.0; WAS-AL00 Build/HUAWEIWAS-AL00)" sendfileon
    * @param ipRules
    */
  def analysisLog(logText: String, ipRules: Array[IPRule]) = {
    var logMap: mutable.Map[String, String] = null
    if (StringUtils.isNotBlank(logText)) {
      val fields = logText.split("[|]")
      if (fields.length == 8) {
        logMap = mutable.Map[String, String]()
        //解析ip
        handleIP(fields(0), ipRules, logMap)
        //处理请求时间
        logMap.put(LogConstants.LOG_COLUMNS_NAME_ACCESS_TIME, Utils.parseLogServerTimeToLong(fields(3)).toString)
        //处理请求方式
        if (logText.contains("GET")) {
          logMap.put(LogConstants.LOG_COLUMNS_NAME_REQUEST_TYPE, "GET")
        } else {
          logMap.put(LogConstants.LOG_COLUMNS_NAME_REQUEST_TYPE, "POST")
        }
        //处理请求数据
        handleRequestParams(fields(4), logMap)
        //处理behaviorData数据
        handleBehaviorData(logMap)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val jsonStr = "{\"ktingToken\":\"C5QS6TeCgosYqDd8efrvZdFhVJVZfm5sKvKsGuqAZy0ZF7n6DXiepx\\/03Jkvms7ddx96ck9hm97QJ3HtJfq8zQ==\",\"behaviorKey\":\"DFSJ400\",\"behaviorData\":{\"zongKey\":\"FM702\",\"programId\":\"310131\",\"albumId\":\"16249\",\"anchorId\":\"11505\",\"playTime\":0,\"on-off\":false}}"
    JSON.parseObject(jsonStr).entrySet().toArray.foreach(t2 => {
      val fields = t2.toString.split("=")
      println(fields.toBuffer)
    })
  }

}
