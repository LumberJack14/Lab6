package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;

import java.util.List;

/**
 * Class, that implements console command "info"
 */

@CommandInfo(name = "info", description = "print information about the collection to standard output")
public class InfoCommand implements Command {
    @Override
    public void execute(List<String> tokens) {

    }
}
