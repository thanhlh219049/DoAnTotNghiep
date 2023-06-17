package com.lethanh219049.application.service;

import com.lethanh219049.application.entity.Comment;
import com.lethanh219049.application.model.request.CreateCommentPostRequest;
import com.lethanh219049.application.model.request.CreateCommentProductRequest;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Comment createCommentPost(CreateCommentPostRequest createCommentPostRequest,long userId);
    Comment createCommentProduct(CreateCommentProductRequest createCommentProductRequest, long userId);
}
