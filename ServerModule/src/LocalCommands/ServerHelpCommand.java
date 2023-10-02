package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;
import Exceptions.TokenMismatchException;

import java.util.List;

@CommandInfo(name = "help", description = "print information about the available commands")
public class ServerHelpCommand implements Command {
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

        System.out.println("""
                help : print information about the available commands
                save : save the collection to a file
                exit : exit the program (without saving to a file)
                start : open the server to receive connections
                """);
    }
}
