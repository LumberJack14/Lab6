package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;

import java.util.List;

/**
 * Class, that implements console command "max_by_name"
 */

@CommandInfo(name = "max_by_name", description = "output any object from the collection whose name field value is the maximum")
public class MaxByNameCommand implements Command {
    @Override
    public void execute(List<String> tokens) {

    }
}
