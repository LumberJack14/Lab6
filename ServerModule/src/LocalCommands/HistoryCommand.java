package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;

import java.util.List;

/**
 * Class, that implements console command "history"
 */

@CommandInfo(name = "history", description = "print the last 13 commands (without their arguments)")
public class HistoryCommand implements Command {

    @Override
    public void execute(List<String> tokens) {

        if (tokens.isEmpty()) {
            System.out.println("No current history yet.");
            return;
        }

        System.out.println("History of commands:\n");
        for (String token : tokens) {
            System.out.println(token);
        }
    }
}
