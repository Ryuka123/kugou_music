package com.daoke360.kugou_music.utils

import java.text.SimpleDateFormat
import java.util.{Base64, Locale}
import java.util.regex.Pattern

/**
  * Created by 黑桃K on 2018-5-7.
  * 刀客程序员淘宝旗舰店：https://daoke360.taobao.com/
  * 刀客程序员官网：http://www.daoke360.com
  * QQ:272488352
  */
object Utils {

  /**
    * 验证ip是否符合ip格式
    *
    * @param ip
    */
  def validateIP(ip: String) = {
    val reg = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))"
    val pattern = Pattern.compile(reg)
    pattern.matcher(ip).matches()
  }

  /**
    * 将日志服务器时间转换成时间戳
    *
    * @param accessTime 24/Apr/2018:03:14:21 +0800
    */
  def parseLogServerTimeToLong(accessTime: String) = {
    val simpleDateFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss +0800", Locale.ENGLISH)
    val date = simpleDateFormat.parse(accessTime)
    date.getTime
  }

  /**
    * base64解码
    *
    * @param base64EndcodeString
    */
  def base64Decode(base64EndcodeString: String) = {
    Base64.getDecoder.decode(base64EndcodeString)
  }
/*
  def main(args: Array[String]): Unit = {
    val str = base64Decode("eyJrdGluZ1Rva2VuIjoiQzVRUzZUZUNnb3NZcURkOGVmcnZaZEZoVkpWWmZtNXNLdktzR3VxQVp5MFpGN242RFhpZXB4XC8wM0prdm1zN2RkeDk2Y2s5aG05N1FKM0h0SmZxOHpRPT0iLCJiZWhhdmlvcktleSI6IkRGU0o0MDAiLCJiZWhhdmlvckRhdGEiOnsiem9uZ0tleSI6IkZNNzAyIiwicHJvZ3JhbUlkIjoiMzEwMTMxIiwiYWxidW1JZCI6IjE2MjQ5IiwiYW5jaG9ySWQiOiIxMTUwNSIsInBsYXlUaW1lIjowLCJvbi1vZmYiOmZhbHNlfX0=")
    println(new String(str))
  }*/
}
