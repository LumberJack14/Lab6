package Utils;

import Collection.DragonX;

import java.io.Serializable;
import java.util.ArrayList;

public class CommandWrapper implements Serializable {
    private ArrayList<String> tokens;
    private DragonX dragon;

    public CommandWrapper(ArrayList<String> tokens, DragonX dragon) {
        this.tokens = tokens;
        this.dragon = dragon;
    }

    public ArrayList<String> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<String> tokens) {
        this.tokens = tokens;
    }

    public void setDragon(DragonX dragon) {
        this.dragon = dragon;
    }

    public DragonX getDragon() {
        return dragon;
    }
}
