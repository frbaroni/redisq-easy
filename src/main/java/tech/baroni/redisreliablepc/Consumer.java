package tech.baroni.redisreliablepc;

import ai.grakn.redisq.Queue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
@Profile("consumer")
public class Consumer {

  private static final Logger log = LoggerFactory.getLogger(Consumer.class);

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.S");

  private static final String QUEUE_NAME = "RELIABLE_REDIS_PRODUCER_CONSUMER";

  public Consumer() {
    play();
  }

  public void play() {
    log.info("Initializing Consumer");
    QueueFactory.consumer(pool(), Message.class, QUEUE_NAME, m -> log.info("Consuming M: {}", m));
  }

  public JedisPool pool() {
    return new JedisPool(jedisPoolConfig(), "localhost");
  }

  @Bean
  public JedisPoolConfig jedisPoolConfig() {
    return new JedisPoolConfig();
  }
}
