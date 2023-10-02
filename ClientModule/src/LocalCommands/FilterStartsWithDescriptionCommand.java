package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;

import java.util.List;

/**
 * Class, that implements console command "filter_start_with_description"
 */

@CommandInfo(name = "filter_starts_with_description", description = "display elements whose description field value starts with the given substring")
public class FilterStartsWithDescriptionCommand implements Command {
    @Override
    public void execute(List<String> tokens) {
    }
}
