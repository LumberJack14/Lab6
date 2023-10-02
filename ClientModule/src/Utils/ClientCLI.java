package Utils;

import Commands.Command;
import LocalCommands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ClientCLI {
    public void run() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ClientCommandManager commandManager = new ClientCommandManager();
        Command[] commands = {
                new HelpCommand(),
                new AddCommand(),
                new ExitCommand(),
                new HistoryCommand(),
                new InfoCommand(),
                new ShowCommand(),
                new UpdateCommand(),
                new RemoveById(),
                new ClearCommand(),
                new FilterStartsWithDescriptionCommand(),
                new MaxByNameCommand(),
                new ExecuteScriptCommand(),
                new RemoveFirstCommand(),
                new RemoveLowerCommand(),
                new RemoveAllByDescriptionCommand(),
        };
        commandManager.registerCommands(commands);

        while (true) {
            System.out.print("\nEnter a command \n>>> ");
            try {
                ArrayList<String> tokens = new ArrayList<>(List.of(bufferedReader.readLine().trim().split("\\s+")));
                commandManager.executeCommand(tokens);

            } catch (IOException e) {
                System.out.println("Error reading the string: " + e.getMessage());
            }
        }
    }
}
