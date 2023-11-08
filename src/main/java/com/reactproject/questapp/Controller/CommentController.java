package com.reactproject.questapp.Controller;

import com.reactproject.questapp.Entity.Comment;
import com.reactproject.questapp.Service.CommentService;
import com.reactproject.questapp.Service.UserService;
import com.reactproject.questapp.requests.CommentCreateRequest;
import com.reactproject.questapp.requests.CommentUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;


    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> postId,@RequestParam Optional<Long> userId){
        return commentService.getAllComments(postId,userId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest request){
        return commentService.createComment(request);
    }
    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest){
        return commentService.updateOneComment(commentId,commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneComment(commentId);
    }
}
