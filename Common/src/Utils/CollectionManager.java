package Utils;

import Collection.*;
import Exceptions.IncorrectDataException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Vector;

public class CollectionManager {
    private static volatile CollectionManager instance;
    private Vector<DragonX> collection;
    private String path;

    private CollectionManager() {

    }

    public static CollectionManager getInstance() {
        CollectionManager result = instance;
        if (result == null) {
            synchronized (CollectionManager.class) {
                result = instance;
                if (result == null) {
                    instance = result = new CollectionManager();
                }
            }
        }

        return result;
    }

    public void initializeCollection(String path, File file) {
        Vector<DragonX> collection = ParseJSONFile.parse(path, file);
        System.out.println("Collection initialized.");
        setCollection(collection);
        this.path = path;
    }

    public Vector<DragonX> getCollection() {
        return collection;
    }

    public void setCollection(Vector<DragonX> collection) {
        this.collection = collection;
    }

    public DragonX generateNewDragon() {
        CollectionManager collectionManager = CollectionManager.getInstance();

        Vector<DragonX> collection = collectionManager.getCollection();

        String dragonName = (String) InputHandler.validate(ValidationType.DRAGON_NAME);
        Long dragonAge = (Long) InputHandler.validate(ValidationType.DRAGON_AGE);
        String dragonDesc = (String) InputHandler.validate(ValidationType.DRAGON_DESC);

        DragonTypeX dragonType;
        Object temp = InputHandler.validate(ValidationType.DRAGON_TYPE);
        if (temp == null) {
            dragonType = null;
        } else {
            dragonType = DragonTypeX.valueOf((String) temp);
        }
        DragonCharacterX dragonCharacter = DragonCharacterX.valueOf((String) InputHandler.validate(ValidationType.DRAGON_CHARACTER));
        Double dragonX = (Double) InputHandler.validate(ValidationType.DRAGON_COORDINATE_X);
        Float dragonY = (Float) InputHandler.validate(ValidationType.DRAGON_COORDINATE_Y);


        String killerName = (String) InputHandler.validate(ValidationType.KILLER_NAME);
        String killerPassportID = (String) InputHandler.validate(ValidationType.KILLER_PASSPORT_ID);
        Integer killerWeight = (Integer) InputHandler.validate(ValidationType.KILLER_WEIGHT);
        Integer killerX = (Integer) InputHandler.validate(ValidationType.KILLER_LOCATION_X);
        Long killerY = (Long) InputHandler.validate(ValidationType.KILLER_LOCATION_Y);
        Float killerZ = (Float) InputHandler.validate(ValidationType.KILLER_LOCATION_Z);
        String killerLocationName = (String) InputHandler.validate(ValidationType.KILLER_LOCATION_NAME);

        CoordinatesX coordinates = new CoordinatesX(dragonX, dragonY);
        LocationX killerLocation = new LocationX(killerX, killerY, killerZ, killerLocationName);
        PersonX killer = new PersonX(killerName, killerWeight, killerPassportID, killerLocation);

        long id = collection.size();
        LocalDate creationDate = LocalDate.now();

        DragonX dragon = new DragonX(id, dragonName, coordinates, creationDate, dragonAge, dragonDesc, dragonType, dragonCharacter, killer);

        return dragon;
    }


    public DragonX customGenerateNewDragon(BufferedReader bufferedReader) {
        CollectionManager collectionManager = CollectionManager.getInstance();

        Vector<DragonX> collection = collectionManager.getCollection();
        DragonX dragon = null;

        try {

            String dragonName = (String) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_NAME);
            Long dragonAge = (Long) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_AGE);
            String dragonDesc = (String) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_DESC);

            DragonTypeX dragonType;
            Object temp = InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_TYPE);
            if (temp == null) {
                dragonType = null;
            } else {
                dragonType = DragonTypeX.valueOf((String) temp);
            }
            DragonCharacterX dragonCharacter = DragonCharacterX.valueOf((String) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_CHARACTER));
            Double dragonX = (Double) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_COORDINATE_X);
            Float dragonY = (Float) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_COORDINATE_Y);


            String killerName = (String) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_NAME);
            String killerPassportID = (String) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_PASSPORT_ID);
            Integer killerWeight = (Integer) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_WEIGHT);
            Integer killerX = (Integer) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_LOCATION_X);
            Long killerY = (Long) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_LOCATION_Y);
            Float killerZ = (Float) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_LOCATION_Z);
            String killerLocationName = (String) InputHandler.validateCustom(bufferedReader, ValidationType.KILLER_LOCATION_NAME);

            CoordinatesX coordinates = new CoordinatesX(dragonX, dragonY);
            LocationX killerLocation = new LocationX(killerX, killerY, killerZ, killerLocationName);
            PersonX killer = new PersonX(killerName, killerWeight, killerPassportID, killerLocation);

            long id = collection.size();
            LocalDate creationDate = LocalDate.now();

            dragon = new DragonX(id, dragonName, coordinates, creationDate, dragonAge, dragonDesc, dragonType, dragonCharacter, killer);
        } catch (IncorrectDataException exception) {
            System.out.println("Entry data in the script doesn't fit the requirements. Raised Error: \n" + exception.getMessage());
        } catch (IOException exception) {
            System.out.println("Error: exception while reading script (IOException):\n" + exception.getMessage());
        }

        return dragon;
    }

    public String getPath() {
        return path;
    }

}
