package LocalCommands;

import Commands.Command;
import Utils.CommandWrapper;

import java.util.List;

/**
 * Basic interface for all the commands of the application
 */
public interface ClientCommand extends Command {
    String executeClient(CommandWrapper cm);
    void execute(List<String> tokens);
}
