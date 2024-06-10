package com.unito.edu.craftstoreservice.repository;

import com.unito.edu.craftstoreservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    @Query( "SELECT com FROM Craftstore cr join cr.commentList com WHERE cr.id = :craftstoreId ORDER BY com.postDate DESC")
    List<Comment> findOrderedCommentsByCraftstoreId(@Param("craftstoreId") int craftstoreId);
}
