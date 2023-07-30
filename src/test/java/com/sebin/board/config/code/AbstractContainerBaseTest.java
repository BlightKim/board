package com.sebin.board.config.code;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;

public class AbstractContainerBaseTest {
  static final String REDIS_IMAGE = "redis:7.0-alpine";
  static final GenericContainer REDIS_CONTAINER;

  static {
    REDIS_CONTAINER = new GenericContainer<>(REDIS_IMAGE)
        .withExposedPorts(6379)
        .withReuse(true);
    REDIS_CONTAINER.start();
  }

  @DynamicPropertySource
  public static void overrideProps(DynamicPropertyRegistry registry) {
    registry.add("spring.data.redis.host",REDIS_CONTAINER::getHost);
    registry.add("spring.data.redis.port", () -> "" + REDIS_CONTAINER.getMappedPort(6379));
  }
}
