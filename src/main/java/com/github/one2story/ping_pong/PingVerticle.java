  package com.github.one2story.ping_pong;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

  public class PingVerticle extends AbstractVerticle {

  @Override
  public void start(){
    vertx.setPeriodic(1000, id -> {
      sendPing();
    });
  }

    private void sendPing() {
      vertx.eventBus().send("ping-pong", new JsonObject().put("msg", "ping"), ar -> {
        if(ar.succeeded()){
          System.out.println(ar.result().body());
        } else {
          System.err.println(ar.cause().getMessage());
        }
      });
    }

    public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new PingVerticle());
    vertx.deployVerticle(new PongVerticle());
  }
}
