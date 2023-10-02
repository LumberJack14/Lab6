package Utils;

import java.io.Serializable;

public enum ResponseStatus implements Serializable {
    SUCCESS, COMMAND_NOT_FOUND, WRONG_TOKEN_NUMBER;
}
