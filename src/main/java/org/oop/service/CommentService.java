package org.oop.service;

import org.oop.api.IAuthService;
import org.oop.api.ICommentService;
import org.oop.api.dao.IArticleDao;
import org.oop.api.dao.ICommentDao;
import org.oop.di.Injector;
import org.oop.model.Article;
import org.oop.model.Comment;

import java.util.List;

public class CommentService implements ICommentService {
    private final ICommentDao commentDao;
    private final IAuthService authService;

    public CommentService() {
        this.commentDao = Injector.getInstance().getService(ICommentDao.class);
        this.authService = Injector.getInstance().getService(IAuthService.class);
    }

    @Override
    public Comment createComment(long articleId, String commentText) {
        long userId = authService.getCurrentUserId();
        Comment newComment = new Comment(articleId, userId, commentText);
        return commentDao.createComment(newComment);
    }

    @Override
    public List<Comment> getCommentsByArticle(Article article) {
        return commentDao.getCommentsByArticle(article);
    }

    @Override
    public Comment getCommentById(long id) {
        return commentDao.getCommentById(id);
    }

    @Override
    public boolean deleteComment(long id) {
        return commentDao.deleteComment(id);
    }
}
