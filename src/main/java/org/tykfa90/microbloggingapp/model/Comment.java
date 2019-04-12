package org.tykfa90.microbloggingapp.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    @Column(updatable = false)
    private Long parentEntryId;
    @Column(updatable = false)
    private Long authorId;
    @CreationTimestamp
    private LocalDateTime dateCreated;

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Long getParentEntryId() {
        return parentEntryId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setParentEntryId(Long parentEntryId) {
        this.parentEntryId = parentEntryId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
