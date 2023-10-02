package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;
import Utils.ClientConnectionManager;
import Utils.Colors;
import Utils.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClientCommandManager {
    private static HashMap<String, Command> commandMap;
    private static ArrayList<String> commandsHistory;
    private static  List<String> localCommands = List.of("add", "execute_script", "update", "remove_lower", "help", "exit");

    public ClientCommandManager() {
        commandMap = new HashMap<>();
        commandsHistory = new ArrayList<>();
    }


    public void registerCommands(Command[] commands) {
        for (Command command : commands) {
            commandMap.put(command.getClass().getAnnotation(CommandInfo.class).name(), command);
        }
    }

    public void executeCommand(ArrayList<String> tokens) {
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

        if (localCommands.contains(command)) {
            tokens.remove(0);
            commandMap.get(command).execute(tokens);
        } else {
            ClientConnectionManager connectionManager = new ClientConnectionManager();
            Response response = connectionManager.sendCommandToServer(tokens, null);
            if (response != null) {
                System.out.println(Colors.PURPLE + "SERVER RESPONSE:\n" + Colors.RESET);
                System.out.println(response.getData());
            }
        }

        addCommandToHistory(command);

    }


    private void addCommandToHistory(String command) {
        if (commandsHistory.size() == 13) {
            commandsHistory.remove(0);
        }

        commandsHistory.add(command);
    }
}
