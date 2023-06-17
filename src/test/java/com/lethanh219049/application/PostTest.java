package com.lethanh219049.application;


import com.lethanh219049.application.entity.Post;
import com.lethanh219049.application.repository.PostRepository;
import com.lethanh219049.application.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
public class PostTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Test
    void findAllPostByStatus_Test(){

        Page<Post> posts = postService.getListPost(1);
        posts.forEach(System.out::println);
    }

    @Test
    void updatePost_test(){

    }
}
