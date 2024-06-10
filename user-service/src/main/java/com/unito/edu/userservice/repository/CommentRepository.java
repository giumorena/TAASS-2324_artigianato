package com.unito.edu.userservice.repository;

import com.unito.edu.userservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {

    @Query( "SELECT com FROM User u join u.commentList com WHERE u.id = :userId ORDER BY com.postDate DESC")
    List<Comment> findOrderedCommentsByUserId(@Param("userId") int userId);
}
