package com.reactproject.questapp.Controller;

import com.reactproject.questapp.Entity.Comment;
import com.reactproject.questapp.Entity.Like;
import com.reactproject.questapp.Service.LikeService;
import com.reactproject.questapp.requests.CommentCreateRequest;
import com.reactproject.questapp.requests.LikeCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    @Autowired
    private LikeService likeService;

    @GetMapping
    public List<Like> getAllLikes(@RequestParam Optional <Long> postId,@RequestParam Optional <Long> userId){
        return likeService.getAllLikes(postId,userId);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@PathVariable Long likeId){
        return likeService.getOneLikeById(likeId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest request){
        return likeService.createOneLike(request);
    }
    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLikeById(likeId);
    }
}
