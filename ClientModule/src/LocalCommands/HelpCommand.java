package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;
import Exceptions.TokenMismatchException;

import java.util.List;

/**
 * Class, that implements console command "help"
 */

@CommandInfo(name = "help", description = "print information about the available commands")
public class HelpCommand implements Command {
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
                info : print information about the collection to standard output
                show : print to standard output all elements of the collection in string representation
                add {element} : add a new element to the collection
                update id {element} : update the value of the collection element, which id is equal to the given one
                remove_by_id id : remove an element from the collection by its id
                clear : clear the collection
                execute_script file_name : read and execute script from the specified file. The script contains commands in the same form in which they are entered by the user in interactive mode.
                exit : exit the program (without saving to a file)
                remove_first : remove the first element from the collection
                remove_lower {element} : Remove all elements from the collection that are smaller than the given one
                history : print the last 13 commands (without their arguments)
                remove_all_by_description description : remove from the collection all elements whose description field value is equivalent to the given one
                max_by_name : output any object from the collection whose name field value is the maximum
                filter_starts_with_description 'description' : display elements whose description field value starts with the given substring
                """);
    }
}
