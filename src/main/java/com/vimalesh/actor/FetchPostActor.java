package com.vimalesh.actor;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.routing.RoundRobinPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/*@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)*/
public class FetchPostActor extends UntypedAbstractActor {

/*    @Autowired
    @Qualifier("createActorSystem")
    private ActorSystem system;*/
    public static Props props() {
        return Props.create(FetchPostActor.class)
                .withRouter(new RoundRobinPool(5));
    }

    @Override
    public void onReceive(Object message) throws Throwable {

        System.out.println(getSelf().path());
       // System.out.println(system);

        getSender().tell("hi", getSelf());

    }

}
