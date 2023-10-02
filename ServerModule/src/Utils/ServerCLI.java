package Utils;

import LocalCommands.*;
import Commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ServerCLI {
    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ServerCommandManager commandManager = new ServerCommandManager();
        Command[] serverCommands = {
                new ServerHelpCommand(),
                new ExitCommand(),
                new SaveCommand(),
                new StartCommand(),
        };
        commandManager.registerServerCommands(serverCommands);

        ClientCommand[] clientCommands = {
            new InfoCommand()
        };
        commandManager.registerClientCommands(clientCommands);

        while (true) {
            System.out.print("\nEnter a command \n>>> ");
            try {
                ArrayList<String> tokens = new ArrayList<>(List.of(bufferedReader.readLine().trim().split("\\s+")));
                commandManager.executeServerCommand(tokens);

            } catch (IOException e) {
                System.out.println("Error reading the string: " + e.getMessage());
            }
        }
    }
}
