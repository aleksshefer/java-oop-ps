package org.oop.api;

import org.oop.model.Article;
import org.oop.model.Comment;

import java.util.List;

public interface ICommentService {
    Comment createComment(long articleId, String commentText);

    List<Comment> getCommentsByArticle(Article article);

    Comment getCommentById(long id);

    boolean deleteComment(long id);
}
