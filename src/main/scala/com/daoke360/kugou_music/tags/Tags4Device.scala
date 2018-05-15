package com.daoke360.kugou_music.tags

import com.daoke360.kugou_music.bean.Logs
import com.daoke360.kugou_music.constants.TagsConstants
import org.apache.commons.lang.StringUtils

import scala.collection.mutable

/**
  * Created by 黑桃K on 2018-5-14.
  * 刀客程序员淘宝旗舰店：https://daoke360.taobao.com/
  * 刀客程序员官网：http://www.daoke360.com
  * QQ:272488352
  */
object Tags4Device extends Tags {
  /**
    * 打标签的方法
    *
    * @param args
    */
  override def makeTags(args: Any*) = {
    val deviceMap = mutable.Map[String, Int]()
    if (args.length > 0) {
      val logs = args(0).asInstanceOf[Logs]
      if (StringUtils.isNotBlank(logs.deviceId))
        deviceMap += (TagsConstants.DEVICE + logs.deviceId -> 1)
      if (StringUtils.isNotBlank(logs.osName))
        deviceMap += (TagsConstants.DEVICE + logs.osName -> 1)
      if (StringUtils.isNotBlank(logs.modelNum))
        deviceMap += (TagsConstants.DEVICE + logs.modelNum -> 1)
    }
    deviceMap
  }
}
