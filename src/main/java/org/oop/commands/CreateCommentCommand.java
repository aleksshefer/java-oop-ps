package org.oop.commands;

import org.oop.api.IArticleService;
import org.oop.api.ICommand;
import org.oop.api.ICommentService;
import org.oop.commands.menu.BaseCommand;
import org.oop.commands.menu.MainMenu;
import org.oop.di.Injector;

public class CreateCommentCommand extends BaseCommand {
    private final ICommentService commentService;
    private final IArticleService articleService;

    public CreateCommentCommand() {
        this.articleService = Injector.getInstance().getService(IArticleService.class);
        this.commentService = Injector.getInstance().getService(ICommentService.class);
    }

    @Override
    public ICommand execute() {
        long articleId = Long.parseLong(ioService.prompt("Введите ID статьи, которую хотите прокомментировать: "));
        String commentText = ioService.prompt("Введите комментарий: ");

        commentService.createComment(articleId, commentText);

        ioService.printLine("Статья '" + articleService.getArticleById(articleId).getTitle() + "' была успешно прокомментирована.");

        return new MainMenu();
    }

    @Override
    public String getDescription() {
        return "Прокомментировать статью";
    }
}