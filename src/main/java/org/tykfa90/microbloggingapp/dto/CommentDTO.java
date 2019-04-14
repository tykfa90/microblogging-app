package org.tykfa90.microbloggingapp.dto;

public class CommentDTO {
    private String commentText;
    private Long parentEntryId;

    public Long getParentEntryId() {
        return parentEntryId;
    }

    public String getCommentText() {
        return commentText;
    }


}
