package com.github.one2story.ping_pong;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class PongVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    System.out.println("[PONG]: Running on event loop: " + Thread.currentThread().getName());
    vertx.eventBus().consumer("ping-pong", message -> {
      System.out.println("[PONG]: " + message.body() + " on thread: " + Thread.currentThread().getName());
      message.reply(new JsonObject().put("msg", "pong"));
    });
  }
}
