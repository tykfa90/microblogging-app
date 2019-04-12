package org.tykfa90.microbloggingapp.dto;

public class EntryDTO {
    private Long accountId;
    private String entryText;

    public long getAccountId() {
        return accountId;
    }

    public String getEntryText() {
        return entryText;
    }
}
