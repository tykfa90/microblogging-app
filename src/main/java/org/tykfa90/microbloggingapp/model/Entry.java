package org.tykfa90.microbloggingapp.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entries")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    private String entryText;
    @Column(updatable = false)
    private String entryAuthor;

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public String getEntryText() {
        return entryText;
    }

    public String getEntryAuthor() {
        return entryAuthor;
    }

    public void setEntryText(String entryText) {
        this.entryText = entryText;
    }

    public void setEntryAuthor(String entryAuthor) {
        this.entryAuthor = entryAuthor;
    }
}
