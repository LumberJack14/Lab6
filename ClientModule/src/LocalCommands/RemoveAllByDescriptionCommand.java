package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;

import java.util.List;

/**
 * Class, that implements console command "remove_all_by_description"
 */

@CommandInfo(name = "remove_all_by_description", description = "remove from the collection all elements whose description field value is equivalent to the given one")
public class RemoveAllByDescriptionCommand implements Command {
    @Override
    public void execute(List<String> tokens) {

    }
}
