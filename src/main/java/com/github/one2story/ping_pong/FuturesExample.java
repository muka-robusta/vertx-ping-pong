package com.github.one2story.ping_pong;

import io.vertx.core.*;

public class FuturesExample extends AbstractVerticle {
/*
  @Override
  public void start(final Future<Void> startFuture) throws Exception {
    System.out.println("Start");
    Future whenTimer1Fired = Future.future();
    vertx.setTimer(1000, id -> {
      System.out.println("Timer fired");
      startFuture.complete();
    });
  }
*/
  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    System.out.println("1 - Start");
    Promise<Void> whenTimer1Fired = Promise.promise();
    vertx.setTimer(1000, id -> {
      System.out.println("3 - Timer fired");
      whenTimer1Fired.complete();
    });

    Promise<Void> whenTimer2Fired = Promise.promise();
    vertx.setTimer(2000, id -> {
      System.out.println("4 - Second timer fired");
      whenTimer2Fired.complete();
    });
    CompositeFuture.all(whenTimer1Fired.future(), whenTimer2Fired.future()).onComplete(event -> {
      System.out.println("5 - Both timers fired");
      startPromise.complete();
    });
    System.out.println("2 - Timer execution called");
  }

/*
  @Override
  public void stop(final Future<Void> stopFuture) throws Exception {
    System.out.println("Stop");
  }
*/
  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {
    System.out.println("Stop");
  }

  public static void main(String[] args) {
    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new FuturesExample());

  }
}
