package com.bbzavrsni.zavrsni.model.dto;

import com.bbzavrsni.zavrsni.util.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {
    private final Long id;
    private final Long eventId;
    private final String creator;
    private final Long creatorId;
    private final String commentBody;
    private final String creationDate;
    private final Long parentCommentId;

    @Override
    public String toString() {
        return GsonUtil.toJson(this);
    }

}
