package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;

import java.util.List;

/**
 * Class, that implements console command "clear"
 */

@CommandInfo(name = "clear", description = "clear the collection")
public class ClearCommand implements Command {
    @Override
    public void execute(List<String> tokens) {

    }
}
