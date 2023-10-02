package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;

import java.util.List;

/**
 * Class, that implements console command "remove_by_id"
 */

@CommandInfo(name = "remove_by_id", description = "remove an element from the collection by its id")
public class RemoveById implements Command {
    @Override
    public void execute(List<String> tokens) {

    }
}
