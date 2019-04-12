package org.tykfa90.microbloggingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tykfa90.microbloggingapp.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Iterable<Comment> findAllByAuthorId(Long accountId);
    Iterable<Comment> findAllByParentEntryId(Long parentEntryId);
}
