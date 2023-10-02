package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;
import Exceptions.TokenMismatchException;

import java.util.List;

/**
 * Class, that implements console command "exit"
 */

@CommandInfo(name="exit", description = "exit the program (without saving to a file)")
public class ExitCommand implements Command {
    @Override
    public void execute(List<String> tokens) {

        try {
            if (!tokens.isEmpty()) {
                throw new TokenMismatchException(0);
            }
        } catch (TokenMismatchException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
