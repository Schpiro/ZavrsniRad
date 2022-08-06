package com.bbzavrsni.zavrsni.model.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "creator_id")
    private User creator;
    private String commentBody;
    private LocalDateTime creationDate;
    @ManyToOne(targetEntity = Comment.class)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Comment(User creator, String commentBody, LocalDateTime creationDate, Comment parentComment, Event event) {
        this.creator = creator;
        this.commentBody = commentBody;
        this.creationDate = creationDate;
        this.parentComment = parentComment;
        this.event = event;
    }
}
