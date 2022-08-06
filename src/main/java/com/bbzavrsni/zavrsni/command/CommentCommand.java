package com.bbzavrsni.zavrsni.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentCommand {
    @NotNull(message = "creator must not be empty")
    private Long creator;
    @NotNull(message = "commentBody must not be empty")
    private String commentBody;
    @NotNull(message = "creationDate must not be empty")
    private LocalDateTime creationDate;
    @Nullable
    private Long parentComment;
}
