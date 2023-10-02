package Commands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;
import Utils.Colors;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

            CommandManager commandManager = new CommandManager();
            Command[] commands = {
                    new HelpCommand(),
                    new AddCommand(),
                    new ExitCommand(),
                    new HistoryCommand(),
                    new SaveCommand(),
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
                List<String> lineTokens = new ArrayList<>(List.of(strCurrentLine.trim().split("\\s+")));
                if (lineTokens.get(0).equals("execute_script")) {
                    System.out.println(Colors.RED + "Using execute_script command inside an executing script is not allowed." +
                            " \nThis command will be skipped" + Colors.RESET);
                    continue;
                }

                if (lineTokens.get(0).equals("add")) {
                    CollectionManager collectionManager = CollectionManager.getInstance();
                    DragonX dragon = collectionManager.customGenerateNewDragon(bufferedReader);
                    if (dragon == null) {
                        System.out.println(Colors.RED + "Process of generating new dragon was stopped" + Colors.RESET);
                    } else {
                        Vector<DragonX> collection = collectionManager.getCollection();
                        collection.add(dragon);
                        System.out.println(Colors.PURPLE + "New dragon was successfully added!" + Colors.RESET);
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

                    CollectionManager cm = CollectionManager.getInstance();
                    Vector<DragonX> collection = cm.getCollection();

                    boolean updated = false;
                    for (DragonX dragon: collection) {
                        if (dragon.getId() == givenID) {

                            System.out.println("Dragon, which will be updated:\n");
                            dragon.displayInfo();

                            DragonX newDragon = cm.customGenerateNewDragon(bufferedReader);
                            if (newDragon == null) {
                                continue while_loop;
                            }
                            newDragon.setId(givenID);
                            collection.remove(dragon);
                            collection.add(newDragon);

                            System.out.println("Dragon with ID '" + givenID + "' was successfully updated");
                            updated = true;
                            break;
                        }
                    }

                    if (!updated) {
                        System.out.println("Couldn't find a dragon with provided ID.");
                    }
                }

                System.out.println(Colors.PURPLE + "\nexecuting command: " + lineTokens.get(0) + Colors.RESET);
                commandManager.executeCommand(lineTokens);
            }
        } catch (FileNotFoundException e) {
            System.out.println(Colors.RED + e.getMessage() + Colors.RESET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
