package org.tykfa90.microbloggingapp.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "entries")
public class Entry {
    @Id
    @GeneratedValue
    private Long id;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    private String entryText;

    private Long accountId;

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public String getEntryText() {
        return entryText;
    }

    public Long getAccount() {
        return accountId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    public void setAccountId(Long id) {
        this.accountId = id;
    }
}
