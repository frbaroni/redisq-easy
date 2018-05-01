package tech.baroni.redisreliablepc;

import java.util.function.Consumer;

import ai.grakn.redisq.RedisqBuilder;
import ai.grakn.redisq.Document;
import ai.grakn.redisq.Queue;

import redis.clients.jedis.JedisPool;

public class QueueFactory {

  protected static <T extends Document> RedisqBuilder<T> builder(final JedisPool pool, final Class<T> clazz, final String queueName) {
    return new RedisqBuilder<T>()
      .setJedisPool(pool)
      .setName(queueName)
      .setDocumentClass(clazz);
  }

  public static <T extends Document> void consumer(final JedisPool pool, final Class<T> clazz, final String queueName, final Consumer<T> consumer) {
    final Queue<T> redisq = builder(pool, clazz, queueName)
      .setConsumer(consumer)
      .createRedisq();
    redisq.startConsumer();
  }

  public static <T extends Document> Queue<T> producer(final JedisPool pool, final Class<T> clazz, final String queueName) {
    final Queue<T> redisq = builder(pool, clazz, queueName)
      .createRedisq();
    return redisq;
  }
}
