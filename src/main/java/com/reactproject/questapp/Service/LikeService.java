package com.reactproject.questapp.Service;

import com.reactproject.questapp.Entity.Comment;
import com.reactproject.questapp.Entity.Like;
import com.reactproject.questapp.Entity.Post;
import com.reactproject.questapp.Entity.User;
import com.reactproject.questapp.Repository.CommentRepository;
import com.reactproject.questapp.Repository.LikeRepository;
import com.reactproject.questapp.requests.LikeCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    public List<Like> getAllLikes(Optional<Long> postId, Optional<Long> userId) {
        if (postId.isPresent() && userId.isPresent())
            return likeRepository.findByPostIdAndUserId(postId.get(), userId.get());
        else if (userId.isPresent()) {
            return likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return likeRepository.findByPostId(postId.get());
        } else
            return likeRepository.findAll();
    }

    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest request) {
        User user = userService.getOneUser(request.getUserId());
        Post post = postService.getOnePost(request.getPostId());
        if (user != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        } else
            return null;
    }


    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
