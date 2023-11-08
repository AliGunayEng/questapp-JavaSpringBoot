package com.reactproject.questapp.Repository;

import com.reactproject.questapp.Entity.Comment;
import com.reactproject.questapp.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {


    List<Comment> findByPostIdAndUserId(Long postId, Long userId);

    List<Comment> findByUserId(Long userId);

    List<Comment> findByPostId(Long postId);
}
