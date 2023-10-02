package Utils;

import Collection.*;
import Exceptions.IncorrectDataException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

public class DragonBuilder {
    public static DragonX generateNewDragon() {
        DragonX dragon = new DragonX();

        String dragonName = (String) InputHandler.validate(ValidationType.DRAGON_NAME);
        dragon.setName(dragonName);
        Long dragonAge = (Long) InputHandler.validate(ValidationType.DRAGON_AGE);
        dragon.setAge(dragonAge);
        String dragonDesc = (String) InputHandler.validate(ValidationType.DRAGON_DESC);
        dragon.setDescription(dragonDesc);
        DragonTypeX dragonType;
        Object temp = InputHandler.validate(ValidationType.DRAGON_TYPE);
        if (temp == null) {
            dragonType = null;
        } else {
            dragonType = DragonTypeX.valueOf((String) temp);
        }
        dragon.setType(dragonType);
        DragonCharacterX dragonCharacter = DragonCharacterX.valueOf((String) InputHandler.validate(ValidationType.DRAGON_CHARACTER));
        dragon.setCharacter(dragonCharacter);
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
        dragon.setCoordinates(coordinates);
        LocationX killerLocation = new LocationX(killerX, killerY, killerZ, killerLocationName);
        PersonX killer = new PersonX(killerName, killerWeight, killerPassportID, killerLocation);
        dragon.setKiller(killer);

        return dragon;
    }

    public static DragonX customGenerateNewDragon(BufferedReader bufferedReader) {
        CollectionManager collectionManager = CollectionManager.getInstance();

        Vector<DragonX> collection = collectionManager.getCollection();
        DragonX dragon = new DragonX();

        try {
            String dragonName = (String) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_NAME);
            dragon.setName(dragonName);
            Long dragonAge = (Long) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_AGE);
            dragon.setAge(dragonAge);
            String dragonDesc = (String) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_DESC);
            dragon.setDescription(dragonDesc);

            DragonTypeX dragonType;
            Object temp = InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_TYPE);
            if (temp == null) {
                dragonType = null;
            } else {
                dragonType = DragonTypeX.valueOf((String) temp);
            }
            dragon.setType(dragonType);
            DragonCharacterX dragonCharacter = DragonCharacterX.valueOf((String) InputHandler.validateCustom(bufferedReader, ValidationType.DRAGON_CHARACTER));
            dragon.setCharacter(dragonCharacter);
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
            dragon.setCoordinates(coordinates);
            LocationX killerLocation = new LocationX(killerX, killerY, killerZ, killerLocationName);
            PersonX killer = new PersonX(killerName, killerWeight, killerPassportID, killerLocation);
            dragon.setKiller(killer);

        } catch (IncorrectDataException exception) {
            System.out.println("Entry data in the script doesn't fit the requirements. Raised Error: \n" + exception.getMessage());
            return null;
        } catch (IOException exception) {
            System.out.println("Error: exception while reading script (IOException):\n" + exception.getMessage());
            return null;
        }

        return dragon;
    }
}
