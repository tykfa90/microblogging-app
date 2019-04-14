package org.tykfa90.microbloggingapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.tykfa90.microbloggingapp.dto.CommentDTO;
import org.tykfa90.microbloggingapp.model.Comment;
import org.tykfa90.microbloggingapp.service.CommentService;
import org.tykfa90.microbloggingapp.service.EntryService;

import java.security.Principal;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {

    private Logger LOG = Logger.getLogger(CommentController.class.getName());

    private CommentService commentService;
    private EntryService entryService;

    public CommentController(CommentService commentService, EntryService entryService) {
        this.commentService = commentService;
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
    @GetMapping(path = "/author/{authorUsername:.+}")
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
    public void addComment(@RequestBody CommentDTO commentDTO, Principal principal) {
        Comment comment = new Comment();
        comment.setAuthorUsername(principal.getName());
        comment.setParentEntryId(commentDTO.getParentEntryId());
        comment.setText(commentDTO.getCommentText());
        commentService.saveComment(comment);
        LOG.info("Adding new comment");
    }

    //Remove comment by commentId - available for both comment AND parent entry author
    @DeleteMapping(path = "/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeComment(@PathVariable Long commentId, Principal principal) {
        String requestSenderAccountName = getSessionUsernameFromPrincipal(principal);
        String commentAuthorAccountName = getByCommentId(commentId).getAuthorUsername();
        String entryAuthorAccountName = entryService.findEntryById(getByCommentId(commentId).getParentEntryId()).getEntryAuthor();

        if (requestSenderAccountName.equals(commentAuthorAccountName) || requestSenderAccountName.equals(entryAuthorAccountName)) {
            commentService.removeByCommentId(commentId);
            LOG.info("Deleting comment with id: " + commentId);
        } else {
            throw new RuntimeException("Unauthorized operation denied!");
        }
    }


    @PatchMapping(path = "/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@PathVariable Long commentId, @RequestBody CommentDTO commentDTO, Principal principal) {
        String requestSenderAccountName = getSessionUsernameFromPrincipal(principal);
        String commentAuthorAccountName = getByCommentId(commentId).getAuthorUsername();
        if (requestSenderAccountName.equals(commentAuthorAccountName)) {
            Comment commentToUpdate = getByCommentId(commentId);
            commentToUpdate.setText(commentDTO.getCommentText());
            commentService.saveComment(commentToUpdate);
            LOG.info("Updating comment with id: " + commentId);
        }
    }

    //Supporting, reusable method for acquiring a comment by it's Id from repository
    private Comment getByCommentId(@RequestBody Long commentId) {
        return commentService.findByCommentId(commentId);
    }

    //Supporting method for acquiring username from active session
    private String getSessionUsernameFromPrincipal(Principal principal) {
        return principal.getName();
    }
}
