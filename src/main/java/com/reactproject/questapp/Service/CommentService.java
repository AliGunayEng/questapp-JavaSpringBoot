package com.reactproject.questapp.Service;

import com.reactproject.questapp.Entity.Comment;
import com.reactproject.questapp.Entity.Post;
import com.reactproject.questapp.Entity.User;
import com.reactproject.questapp.Repository.CommentRepository;
import com.reactproject.questapp.requests.CommentCreateRequest;
import com.reactproject.questapp.requests.CommentUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    public List<Comment> getAllComments(Optional<Long> postId, Optional<Long> userId) {
        if (postId.isPresent() && userId.isPresent())
            return commentRepository.findByPostIdAndUserId(postId.get(), userId.get());
        else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        } else
            return commentRepository.findAll();
    }


    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(CommentCreateRequest request) {
        User user = userService.getOneUser(request.getUserId());
        Post post = postService.getOnePost(request.getPostId());
        if (user != null && post != null) {
            Comment commentToSave = new Comment();
            commentToSave.setId(request.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(request.getText());

            return commentRepository.save(commentToSave);
        } else
            return null;
    }

    public Comment updateOneComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText((commentUpdateRequest.getText()));
            return commentRepository.save(commentToUpdate);

        } else
            return null;
    }

    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
