package org.oop.api.dao;

import org.oop.model.Article;
import org.oop.model.Comment;
import org.oop.model.User;

import java.util.List;

public interface ICommentDao {
    Comment createComment(Comment comment);

    Comment getCommentById(long id);

    List<Comment> getCommentsByArticle(Article article);

    boolean deleteComment(long id);
}
