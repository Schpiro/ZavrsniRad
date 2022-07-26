package com.bbzavrsni.zavrsni.repository.interfaces;

import com.bbzavrsni.zavrsni.model.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
