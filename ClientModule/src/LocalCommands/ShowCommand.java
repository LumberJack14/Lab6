package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;

import java.util.List;

/**
 * Class, that implements console command "show"
 */

@CommandInfo(name = "show", description = "print to standard output all elements of the collection in string representation")
public class ShowCommand implements Command {

    @Override
    public void execute(List<String> tokens) {

    }
}
