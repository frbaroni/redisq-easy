package tech.baroni.redisreliablepc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RedisreliablepcApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisreliablepcApplication.class, args);
	}
}
