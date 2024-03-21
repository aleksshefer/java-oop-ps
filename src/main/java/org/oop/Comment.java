package org.oop;

public class Comment {
    private long id;
    private long articleId;
    private long userId;
    private String commentText;

    public Comment(long id, long articleId, long userId, String commentText) {
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
        this.commentText = commentText;
    }

    public Comment(long articleId, long userId, String commentText) {
        this.articleId = articleId;
        this.userId = userId;
        this.commentText = commentText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
