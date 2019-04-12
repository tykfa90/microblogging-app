package org.tykfa90.microbloggingapp.service;

import org.springframework.stereotype.Service;
import org.tykfa90.microbloggingapp.model.Comment;
import org.tykfa90.microbloggingapp.repository.CommentRepository;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Iterable<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Iterable<Comment> getAllCommentsByAccountId(Long accountId) {
        return commentRepository.findAllByAuthorId(accountId);
    }

    public Iterable<Comment> getAllCommentsByParentEntry(Long parentEntryId) {
        return commentRepository.findAllByParentEntryId(parentEntryId);
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void removeByCommentId(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public Comment findByCommentId(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("No comment with such id found!"));
    }
}
