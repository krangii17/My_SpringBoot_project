package com.timur.test.service;

import com.timur.test.domain.Comment;
import com.timur.test.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    CommentRepo commentRepo;

    public boolean isExistCommentInDb(Long id) {
        return commentRepo.existsById(id);
    }

    public void saveComment(Comment comment) {
        commentRepo.save(comment);
    }

    public void changeComment(Comment comment, String text) {
        comment.setText(text);
        commentRepo.save(comment);
    }

    public Optional<Comment> getComment(Long id) {
        Optional<Comment> comment = commentRepo.findById(id);
        return comment;
    }

    public void deleteComment(Long id) {
        commentRepo.deleteById(id);
    }
}
