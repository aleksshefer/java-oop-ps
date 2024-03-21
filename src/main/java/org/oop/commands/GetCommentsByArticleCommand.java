package org.oop.commands;

import org.oop.api.IArticleService;
import org.oop.api.ICommand;
import org.oop.api.ICommentService;
import org.oop.commands.menu.BaseCommand;
import org.oop.commands.menu.MainMenu;
import org.oop.di.Injector;
import org.oop.model.Article;
import org.oop.model.Comment;

import java.util.*;

public class GetCommentsByArticleCommand extends BaseCommand {
    private final ICommentService commentService;
    private final IArticleService articleService;

    public GetCommentsByArticleCommand() {
        this.articleService = Injector.getInstance().getService(IArticleService.class);
        this.commentService = Injector.getInstance().getService(ICommentService.class);
    }

    @Override
    public ICommand execute() {
        Map<Article, List<Comment>> commentsByArticles = new HashMap<>();
        String articleTitle = ioService.prompt("Введите заголовок статьи, у которой хотите посмотреть комментарии: ");
        List<Article> articles = articleService.getArticlesByTitle(articleTitle);
        for (Article article : articles) {
            commentsByArticles.put(article, commentService.getCommentsByArticle(article));
        }

        ioService.printComments(commentsByArticles);

        return new MainMenu();
    }

    @Override
    public String getDescription() {
        return "Посмотреть комментарии статьи";
    }
}
