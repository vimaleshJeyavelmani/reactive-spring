package com.vimalesh.config;

import akka.actor.Actor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinPool;
import com.vimalesh.actor.FetchPostActor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfig {

    @Bean
    public ActorSystem createActorSystem() {
        return ActorSystem.create("reactive-spring");
    }

    @Bean
    public ActorRef createFetchActor() {
        return createActorSystem().actorOf(FetchPostActor.props(), "fetch-post-actor");
    }

}
