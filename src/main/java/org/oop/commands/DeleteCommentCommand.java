package org.oop.commands;

import org.oop.api.IAuthService;
import org.oop.api.ICommand;
import org.oop.api.ICommentService;
import org.oop.commands.menu.BaseCommand;
import org.oop.commands.menu.MainMenu;
import org.oop.di.Injector;
import org.oop.model.Comment;

public class DeleteCommentCommand extends BaseCommand {
    private final ICommentService commentService;
    private final IAuthService authService;

    public DeleteCommentCommand() {
        this.commentService = Injector.getInstance().getService(ICommentService.class);
        this.authService = Injector.getInstance().getService(IAuthService.class);
    }

    @Override
    public ICommand execute() {
        long commentIdToDelete = Long.parseLong(ioService.prompt("Введите ID комментария, который хотите удалить: "));
        Comment commentToDelete = commentService.getCommentById(commentIdToDelete);

        if (commentToDelete == null) {
            ioService.printLine("Комментария с таким ID нет.");
            return new MainMenu();
        }

        if (authService.getCurrentUserId() != commentToDelete.getUserId()) {
            ioService.printLine("Вы не являетесь автором этого комментария и нем модете удалить его.");
            return new MainMenu();
        }

        if (commentService.deleteComment(commentIdToDelete)) {
            ioService.printLine("Комментарий с ID " + commentIdToDelete + " был удален.");
        } else {
            ioService.printLine("Комментарий с ID " + commentIdToDelete + " не удалось удалить.");
        }

        return new MainMenu();
    }

    @Override
    public String getDescription() {
        return "Удалить комментарий";
    }
}
