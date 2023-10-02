package LocalCommands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Commands.Command;
import Commands.HelpCommand;
import Exceptions.TokenMismatchException;
import Utils.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class, that implements console command "execute_script"
 */

@CommandInfo(name = "execute_script", description = "print information about the available commands")
public class ExecuteScriptCommand implements Command {

    // TODO: solve potential problems with closing the stream
    @Override
    public void execute(List<String> tokens) {
        try {
            if (tokens.size() != 1) {
                throw new TokenMismatchException(1);
            }
        } catch (TokenMismatchException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(tokens.get(0))));
            String strCurrentLine;

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
            };
            commandManager.registerCommands(commands);

            while_loop:
            while ((strCurrentLine = bufferedReader.readLine()) != null) {
                ArrayList<String> lineTokens = new ArrayList<>(List.of(strCurrentLine.trim().split("\\s+")));
                if (lineTokens.get(0).equals("execute_script")) {
                    System.out.println(Colors.RED + "Using execute_script command inside an executing script is not allowed." +
                            " \nThis command will be skipped" + Colors.RESET);
                    continue;
                }

                System.out.println(Colors.YELLOW + "\nexecuting command: " + lineTokens.get(0) + Colors.RESET);

                if (lineTokens.get(0).equals("add")) {
                    DragonX dragon = DragonBuilder.customGenerateNewDragon(bufferedReader);
                    if (dragon == null) {
                        System.out.println(Colors.RED + "Process of generating new dragon was stopped" + Colors.RESET);
                    } else {
                        ClientConnectionManager connectionManager = new ClientConnectionManager();
                        tokens.add("add");
                        Response response = connectionManager.sendCommandToServer( lineTokens, dragon);
                        if (response != null) {
                            System.out.println(Colors.PURPLE + "SERVER RESPONSE:\n" + Colors.RESET);
                            System.out.println(response.getData());
                        }
                    }
                    continue;
                }

                if (lineTokens.get(0).equals("update")) {
                    lineTokens.remove(0);
                    long givenID;

                    try {
                        if (lineTokens.size() != 1) {
                            throw new TokenMismatchException(1);
                        }

                        givenID = Long.parseLong(lineTokens.get(0));
                    } catch (TokenMismatchException | NumberFormatException exception) {
                        System.out.println(exception.getMessage());
                        continue;
                    }

                    DragonX newDragon = DragonBuilder.customGenerateNewDragon(bufferedReader);
                    if (newDragon == null) {
                        System.out.println(Colors.RED + "Process of generating new dragon was stopped" + Colors.RESET);
                    } else {
                        ClientConnectionManager connectionManager = new ClientConnectionManager();
                        lineTokens.remove(0);
                        lineTokens.add("update");
                        lineTokens.add(String.valueOf(givenID));
                        Response response = connectionManager.sendCommandToServer( lineTokens, newDragon);
                        if (response != null) {
                            System.out.println(Colors.PURPLE + "SERVER RESPONSE:\n" + Colors.RESET);
                            System.out.println(response.getData());
                        }
                        continue;
                    }

                }

                commandManager.executeCommand(  lineTokens);
            }
        } catch (FileNotFoundException e) {
            System.out.println(Colors.RED + e.getMessage() + Colors.RESET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
