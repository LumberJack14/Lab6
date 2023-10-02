package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;
import Utils.CommandWrapper;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerCommandManager {
    private static HashMap<String, Command> serverCommandMap = new HashMap<>();
    private static HashMap<String, ClientCommand> clientCommandMap = new HashMap<>();

    public void registerServerCommands(Command[] commands) {
        for (Command command : commands) {
            serverCommandMap.put(command.getClass().getAnnotation(CommandInfo.class).name(), command);
        }
    }

    public void registerClientCommands(ClientCommand[] commands) {
        for (ClientCommand command : commands) {
            clientCommandMap.put(command.getClass().getAnnotation(CommandInfo.class).name(), command);
        }
    }

    public void executeServerCommand(ArrayList<String> tokens) {
        String command = tokens.get(0);

        if (!serverCommandMap.containsKey(command)) {
            System.out.printf("No such command found: %s\nUse 'help' to see available commands", command);
            return;
        }

        tokens.remove(0);
        serverCommandMap.get(command).execute(tokens);

    }

    public String executeClientCommand(CommandWrapper cm) {
        ArrayList<String> tokens = cm.getTokens();
        String command = tokens.get(0);

        if (!clientCommandMap.containsKey(command)) {
            return String.format("No such command found: %s\nUse 'help' to see available commands", command);
        }

        System.out.println(LocalTime.now() + " --> " + command);
        tokens.remove(0);
        return clientCommandMap.get(command).executeClient(cm);
    }
}
