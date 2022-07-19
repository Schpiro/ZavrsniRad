package com.bbzavrsni.zavrsni.model.pojo;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator_id")
    private User creator;
    private String commentBody;
    private LocalDateTime creationDate;
    @ManyToOne(targetEntity = Comment.class)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;
}
