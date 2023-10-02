package Commands;

import java.util.List;

/**
 * Basic interface for all the commands of the application
 */
public interface Command {
    void execute(List<String> tokens);
}
