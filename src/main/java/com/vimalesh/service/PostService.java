package com.vimalesh.service;

import com.vimalesh.dto.PostDTO;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Component
public class PostService {

    public static List<PostDTO> postList = new ArrayList();

    public CompletionStage<List<PostDTO>> getPosts() {
        return CompletableFuture.supplyAsync(() -> {
            return getPostList();
        });
    }

    public List<PostDTO> getPostList() {

        if (postList.isEmpty()) {

            PostDTO post = new PostDTO();
            post.setId(UUID.randomUUID());
            post.setDesc("first post");
            post.setCreatedOn(System.currentTimeMillis());
            post.setUrl("url");

            postList.add(post);

        }

        return postList;
    }
}
