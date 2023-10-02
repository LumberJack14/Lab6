package Utils;

/**
 * Utility class with types of possible input from user and corresponding prompts depending on the parsing result.
 */

public enum ValidationType {
    DRAGON_NAME("Enter the dragon's name", "Dragon name cannot be empty"),
    DRAGON_AGE("Enter the dragon's age", "age should be more than 0 and should not be an empty string [Long type]"),
    DRAGON_DESC("Enter a description of the dragon", ""),
    DRAGON_TYPE("Enter the dragon's type. Available types:\n" + ValidationType.dragonTypes, "This field can either be a valid dragon type or an empty string"),
    DRAGON_CHARACTER("Enter the dragon's character. Available characters:\n" + ValidationType.dragonCharacters, "This field should be a valid dragon character from the above list"),
    DRAGON_COORDINATE_X("Enter the dragon's X coordinate", "X should be more than -36, it also cannot be an empty string [Double type]"),
    DRAGON_COORDINATE_Y("Enter the dragon's Y coordinate", "this field cannot be an empty string [Float type]"),
    KILLER_NAME("Enter the killer's name", "killer's name cannot be empty"),
    KILLER_PASSPORT_ID("Enter the killer's passport ID", "passport ID should not be more than 33 characters long and it should be unique"),
    KILLER_WEIGHT("Enter the killer's weight", "killer's weight (numeric) cannot be empty and should be more than 0"),
    KILLER_LOCATION_X("Enter the killer's X coordinate", "this field cannot be an empty string [Integer type]"),
    KILLER_LOCATION_Y("Enter the killer's Y coordinate", "this field cannot be an empty string [Long type]"),
    KILLER_LOCATION_Z("Enter the killer's Z coordinate", "this field cannot be an empty string [Float type]"),
    KILLER_LOCATION_NAME("Enter the killer's location name", "this field cannot be an empty string");


    public final String prompt;
    public final String errorMessage;

    private final static String dragonTypes = """
                WATER,
                UNDERGROUND,
                AIR,
                FIRE""";

    private final static String dragonCharacters = """
                CUNNING,
                WISE,
                EVIL,
                GOOD,
                CHAOTIC_EVIL""";

    private ValidationType(String prompt, String errorMessage ) {
        this.prompt = prompt;
        this.errorMessage = errorMessage;
    }
}
