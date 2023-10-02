package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;

import java.util.List;

/**
 * Class, that implements console command "remove_first"
 */

@CommandInfo(name = "remove_first", description = "remove the first element from the collection")
public class RemoveFirstCommand implements Command {
    @Override
    public void execute(List<String> tokens) {

    }
}
