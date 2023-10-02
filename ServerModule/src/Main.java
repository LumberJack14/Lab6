import Utils.CollectionManager;
import Utils.ServerCLI;

import java.io.File;


public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Missing path to collection. Provide it as a command line argument.");
            return;
        }
        String path = args[0];

        CollectionManager.getInstance().initializeCollection(path, new File(path));
        ServerCLI cli = new ServerCLI();
        cli.run();
    }
}
