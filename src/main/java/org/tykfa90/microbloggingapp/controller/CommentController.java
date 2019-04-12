package org.tykfa90.microbloggingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tykfa90.microbloggingapp.dto.CommentDTO;
import org.tykfa90.microbloggingapp.model.Account;
import org.tykfa90.microbloggingapp.model.Comment;
import org.tykfa90.microbloggingapp.model.Entry;
import org.tykfa90.microbloggingapp.service.AccountService;
import org.tykfa90.microbloggingapp.service.CommentService;
import org.tykfa90.microbloggingapp.service.EntryService;

import java.security.Principal;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {

    private Logger LOG = Logger.getLogger(CommentController.class.getName());

    private CommentService commentService;
    private AccountService accountService;
    private EntryService entryService;

    public CommentController(CommentService commentService, AccountService accountService, EntryService entryService) {
        this.commentService = commentService;
        this.accountService = accountService;
        this.entryService = entryService;
    }

    //All comments
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Comment> getAllComments() {
        LOG.info("Displaying all comments");
        return commentService.getAll();
    }

    //All comments by author
    @GetMapping(path = "/author/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Comment> getAllCommentsByAuthor(@PathVariable String authorUsername) {
        LOG.info("Displaying all comments created by user with id: " + authorUsername);
        return commentService.getAllCommentsByAuthor(authorUsername);
    }

    //All comments by parent entry
    @GetMapping(path = "/{parentEntryId}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Comment> getAllByParentEntry(@PathVariable Long parentEntryId) {
        LOG.info("Displaying all comments by entry with id: " + parentEntryId);
        return commentService.getAllCommentsByParentEntry(parentEntryId);
    }

    //Add comment
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setAuthorUsername(commentDTO.getAuthorUsername());
        comment.setParentEntryId(commentDTO.getParentEntryId());
        comment.setText(commentDTO.getCommentText());
        commentService.saveComment(comment);
        LOG.info("Adding new comment");
    }

    //Remove comment by commentId - available for both comment AND parent entry author
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void removeComment(@RequestBody Long commentId, Principal principal) {
        String requestSenderAccountName = principal.getName();
        String commentAuthorAccountName = getByCommentId(commentId).getAuthorUsername();
        String entryAuthorAccountName = entryService.findEntryById(getByCommentId(commentId).getParentEntryId()).getEntryAuthor();

        if (requestSenderAccountName.equals(commentAuthorAccountName) || requestSenderAccountName.equals(entryAuthorAccountName)) {
            commentService.removeByCommentId(commentId);
            LOG.info("Deleting comment with id: " + commentId);
        } else {
            throw new RuntimeException("Unauthorized operation denied!");
        }
    }

    //Get comment by id
    private Comment getByCommentId(@RequestBody Long commentId) {
        return commentService.findByCommentId(commentId);
    }
}
