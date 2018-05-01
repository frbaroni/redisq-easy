package tech.baroni.redisreliablepc;

import ai.grakn.redisq.Queue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import tech.baroni.redisreliablepc.QueueFactory;

@Component
@Profile("producer")
public class Producer {

  private static final Logger log = LoggerFactory.getLogger(Producer.class);

  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.S");

  private static final String QUEUE_NAME = "RELIABLE_REDIS_PRODUCER_CONSUMER";

  @Scheduled(fixedRate = 2000)
  public void play() throws InterruptedException {

    final String now = dateFormat.format(new Date());
    final String destination = "fernando@baroni.tech";
    final String id = now + "_" + destination;

    log.info("Producing message at {}", id);
    Queue<Message> queue = QueueFactory.producer(pool(), Message.class, QUEUE_NAME);
    try {
      final Message message = Message.builder().id(id).destination(destination).content("Hello World at " + now).build();
      queue.push(message);
    } finally {
      queue.close();
    }
  }

  public JedisPool pool() {
    return new JedisPool(jedisPoolConfig(), "localhost");
  }

  @Bean
  public JedisPoolConfig jedisPoolConfig() {
    return new JedisPoolConfig();
  }
}
