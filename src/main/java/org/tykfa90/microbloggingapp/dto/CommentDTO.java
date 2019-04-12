package org.tykfa90.microbloggingapp.dto;

public class CommentDTO {
    private Long accountId;
    private String commentText;
    private Long parentEntryId;

    public Long getParentEntryId() {
        return parentEntryId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getCommentText() {
        return commentText;
    }
}
