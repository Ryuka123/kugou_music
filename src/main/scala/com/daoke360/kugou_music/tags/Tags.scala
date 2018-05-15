package com.daoke360.kugou_music.tags

import scala.collection.mutable

/**
  * Created by 黑桃K on 2018-5-14.
  * 刀客程序员淘宝旗舰店：https://daoke360.taobao.com/
  * 刀客程序员官网：http://www.daoke360.com
  * QQ:272488352
  */
trait Tags {

  /**
    * 打标签的方法
    *
    * @param args
    */
  def makeTags(args: Any*):mutable.Map[String,Int]
}
