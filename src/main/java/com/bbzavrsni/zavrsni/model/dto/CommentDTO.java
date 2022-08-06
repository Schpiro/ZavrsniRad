package com.bbzavrsni.zavrsni.model.dto;

import com.bbzavrsni.zavrsni.util.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentDTO {
    private final Long creatorId;
    private final String commentBody;
    private final LocalDateTime creationDate;
    private final Long parentCommentId;

    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }

}
