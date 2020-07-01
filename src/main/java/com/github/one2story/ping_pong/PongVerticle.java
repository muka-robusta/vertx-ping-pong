package com.github.one2story.ping_pong;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class PongVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    vertx.eventBus().consumer("ping-pong", message -> {
      System.out.println(message.body());
      message.reply(new JsonObject().put("msg", "pong"));
    });
  }
}
