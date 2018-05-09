package com.daoke360.kugou_music.utils

import redis.clients.jedis.{JedisPool, JedisPoolConfig}

/**
  * Created by 黑桃K on 2018-5-9.
  * 刀客程序员淘宝旗舰店：https://daoke360.taobao.com/
  * 刀客程序员官网：http://www.daoke360.com
  * QQ:272488352
  */
object JedisUtils {
  val config = new JedisPoolConfig()
  val jedisPool = new JedisPool(config, "mini1", 6379, 0)

  def getConnection() = {
    jedisPool.getResource
  }

  /**
    * 向 redis中写入hash数据类型的数据
    *
    * @param key
    * @param field
    * @param value
    */
  def hset(key: String, field: String, value: String) = {
    val client = getConnection()
    client.hset(key, field, value)
    client.close()
  }


}
