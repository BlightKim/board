package com.sebin.board.config.code;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
@Testcontainers
public class TestContainerConfig implements BeforeAllCallback {

  private static final int REDIS_PORT = 6379;
  private static GenericContainer redis;

  @Override
  public void beforeAll(ExtensionContext context) {
    redis = new GenericContainer(DockerImageName.parse("redis:6-alpine"))
        .withExposedPorts(REDIS_PORT);
    redis.start();
    System.setProperty("spring.data.redis.host", redis.getHost());
    System.setProperty("spring.data.redis.port", String.valueOf(redis.getMappedPort(REDIS_PORT
    )));
  }
}