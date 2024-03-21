package org.oop.dao;

import org.oop.api.dao.ICommentDao;
import org.oop.model.Article;
import org.oop.model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDao extends Dao implements ICommentDao {
    @Override
    public Comment createComment(Comment comment) {
        String query = "INSERT INTO comments (article_id, user_id, comment_text) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, comment.getArticleId());
            preparedStatement.setLong(2, comment.getUserId());
            preparedStatement.setString(3, comment.getCommentText());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating comment failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    comment.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating comment failed, no ID obtained.");
                }
            }

            return comment;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Comment getCommentById(long id) {
        String query = "SELECT id, article_id, user_id, comment_text FROM comments WHERE id = ?";
        Comment comment = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet != null) {
                    resultSet.next();
                    comment = new Comment(
                            resultSet.getLong("id"),
                            resultSet.getLong("article_id"),
                            resultSet.getLong("user_id"),
                            resultSet.getString("comment_text")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }

    @Override
    public List<Comment> getCommentsByArticle(Article article) {
        List<Comment> comments = new ArrayList<>();
        String query = "SELECT id, article_id, user_id, comment_text FROM comments WHERE article_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, article.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    comments.add(new Comment(
                            resultSet.getLong("id"),
                            resultSet.getLong("article_id"),
                            resultSet.getLong("user_id"),
                            resultSet.getString("comment_text")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public boolean deleteComment(long commentId) {
        String query = "DELETE FROM comments WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, commentId);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
