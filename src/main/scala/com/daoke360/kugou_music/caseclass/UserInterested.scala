package com.daoke360.kugou_music.caseclass

/**
  * Created by 黑桃K on 2018-5-15.
  * 刀客程序员淘宝旗舰店：https://daoke360.taobao.com/
  * 刀客程序员官网：http://www.daoke360.com
  * QQ:272488352
  */
case class UserInterested(var user_id: String, var interested_album_ids: String,
                          var interested_album_tags_id: String, var interested_album_tags_name: String,
                          var interested_anchor_ids: String, var interest_program_time_level: String)
