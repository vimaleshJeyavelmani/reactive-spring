package com.vimalesh.controller;

import akka.actor.ActorRef;
import akka.pattern.Patterns;
import com.vimalesh.dto.PostDTO;
import com.vimalesh.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    @Qualifier("createFetchActor")
    private ActorRef fetchPostActor;

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public CompletionStage<ResponseEntity<Object>> fetchAllPost() {

        CompletionStage<Object> akkRresponse = Patterns.ask(fetchPostActor, "hi", Duration.ofSeconds(10));
        return akkRresponse.handle((response, exception) -> {
            return new ResponseEntity<Object>("Hi", HttpStatus.OK);
        });
    }

    @RequestMapping(value = "/all/async", method = RequestMethod.GET)
    public CompletionStage<ResponseEntity<Object>> asyncFetchAllPost() {

        CompletionStage<List<PostDTO>> serviceResponse = postService.getPosts();
        return serviceResponse.handle((response, exception) -> {
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        });
    }
}
