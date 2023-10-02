package Commands;

import Annotations.CommandInfo;
import Utils.Colors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class that registers available commands and holds the command history
 */

public class CommandManager {
    HashMap<String, Command> commandMap;
    ArrayList<String> commandsHistory;

    public CommandManager() {
        this.commandMap = new HashMap<>();
        this.commandsHistory = new ArrayList<>();
    }


    public void registerCommands(Command[] commands) {
        for (Command command : commands) {
            this.commandMap.put(command.getClass().getAnnotation(CommandInfo.class).name(), command);
        }
    }

    public void executeCommand(List<String> tokens) {
        String command = tokens.get(0);

        if (!commandMap.containsKey(command)) {
            System.out.printf("No such command found: %s\nUse 'help' to see available commands", command);
            return;
        }

        if (command.equals("history")) {
            if (tokens.subList(1, tokens.size()).size() > 0) {
                System.out.println(Colors.RED + "Redundant tokens found. Type 'help' to learn about commands" + Colors.RESET);
                return;
            }
            commandMap.get(command).execute(commandsHistory);
            addCommandToHistory(command);
            return;
        }

        tokens.remove(0);
        commandMap.get(command).execute(tokens);

        addCommandToHistory(command);
    }


    private void addCommandToHistory(String command) {
        if (commandsHistory.size() == 13) {
            commandsHistory.remove(0);
        }

        commandsHistory.add(command);
    }
}
