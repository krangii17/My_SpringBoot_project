package com.timur.test.controller;

import com.timur.test.domain.Comment;
import com.timur.test.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
@Api(value = "Comment Controller", description = "Controller for comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @ApiOperation(value = "Send comment")
    @PostMapping("/sendComment")
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        commentService.saveComment(comment);
        return ResponseEntity.ok("Comment has been sent");
    }

    @ApiOperation(value = "Delete comment by id")
    @DeleteMapping("/deleteComment")
    public ResponseEntity<?> deleteComment(@RequestBody Long id) {
        boolean isExist = commentService.isExistCommentInDb(id);
        if (isExist) {
            commentService.deleteComment(id);
            return ResponseEntity.ok("Comment has been deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Change comment")
    @PutMapping("/changeComment")
    public ResponseEntity<?> changeComment(@RequestBody Long id,
                                       @RequestBody String comment) {
        boolean isExist = commentService.isExistCommentInDb(id);
        if (!isExist) {
            return ResponseEntity.notFound().build();
        }
        Optional<Comment> optionalComment = commentService.getComment(id);
        commentService.changeComment(optionalComment.get(), comment);
        return ResponseEntity.ok("Comment has been changed successfully");
    }
}
