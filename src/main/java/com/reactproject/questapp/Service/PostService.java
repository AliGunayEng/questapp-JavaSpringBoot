package com.reactproject.questapp.Service;

import com.reactproject.questapp.Entity.Post;
import com.reactproject.questapp.Entity.User;
import com.reactproject.questapp.Repository.PostRepository;
import com.reactproject.questapp.requests.PostCreateRequest;
import com.reactproject.questapp.requests.PostUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent())
            return postRepository.findByUserId(userId.get());
        return postRepository.findAll();
    }

    public Post getOnePost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }



    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user = userService.getOneUser(newPostRequest.getUserId());
        if(user==null)
            return null;
        Post toSave=new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updatePost) {
        Optional<Post> post=postRepository.findById(postId);
        if(post.isPresent()){
            Post toUpdate =post.get();
            toUpdate.setText((updatePost.getText()));
            toUpdate.setTitle(updatePost.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }


    public void deleteOnePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
