package com.huyang.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * jedis工具类
 * @Project: ids
 * @Package com.huyang.common.utils
 * @author HuYang
 * @date 2017年4月29日 下午7:08:31
 * @version V1.0 Copyright (c) 2017, 790247179@qq.com All Rights Reserved
 */
public class JedisClient {

	@Autowired
	private ShardedJedisPool shardedJedisPool;

	public String get(String key) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	public String set(String key, String value) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		String string = jedis.set(key, value);
		jedis.close();
		return string;
	}

	public String hget(String hkey, String key) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		String string = jedis.hget(hkey, key);
		jedis.close();
		return string;
	}

	public long hset(String hkey, String key, String value) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		Long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	public long incr(String key) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	public long expire(String key, int second) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	public long ttl(String key) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	public long del(String key) {
		ShardedJedis jedis = shardedJedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();

		return result;
	}

	public long hdel(String hkey, String key) {

		ShardedJedis jedis = shardedJedisPool.getResource();
		Long result = jedis.hdel(hkey, key);
		jedis.close();

		return result;
	}

}
