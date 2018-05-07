package com.daoke360.kugou_music.caseclass

import com.daoke360.kugou_music.constants.GlobalConstants

/**
  * Created by 黑桃K on 2018-5-7.
  * 刀客程序员淘宝旗舰店：https://daoke360.taobao.com/
  * 刀客程序员官网：http://www.daoke360.com
  * QQ:272488352
  */
case class RegionInfo(var country:String=GlobalConstants.DEFAULT_VALUE,var province:String=GlobalConstants.DEFAULT_VALUE,var city:String=GlobalConstants.DEFAULT_VALUE) {
}
