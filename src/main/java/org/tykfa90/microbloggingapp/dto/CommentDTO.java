package org.tykfa90.microbloggingapp.dto;

public class CommentDTO {
    private String authorUsername;
    private String commentText;
    private Long parentEntryId;

    public Long getParentEntryId() {
        return parentEntryId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public String getCommentText() {
        return commentText;
    }


}
