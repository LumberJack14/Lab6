package Utils;

import Collection.DragonCharacterX;
import Collection.DragonTypeX;
import Exceptions.IncorrectDataException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class which is responsible for validation of user input during adding or updating dragons.
 */

public class InputHandler {

    public static Object validate(ValidationType type) {

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        while (true) {
            System.out.println(type.prompt);
            System.out.print(">>> ");
            switch (type) {
                case DRAGON_NAME: {
                    String name = scanner.nextLine();
                    if (name.isEmpty()) {
                        callError(type.errorMessage);
                        continue;
                    }

                    return name;
                }
                case DRAGON_AGE: {
                    try {
                        if (!scanner.hasNextLong()) {
                            System.out.println();
                            callError(type.errorMessage);
                            scanner.nextLine();
                            continue;
                        }
                        Long age = scanner.nextLong();
                        if (age <= 0) {
                            callError(type.errorMessage);
                            continue;
                        }

                        return age;
                    } catch (InputMismatchException | NumberFormatException exception) {
                        System.out.println(Colors.RED + "Error: " + exception.getMessage() + Colors.RESET);
                        continue;
                    }
                }
                case DRAGON_DESC: {
                    return scanner.nextLine();
                }
                case DRAGON_TYPE: {
                    String dragonType = scanner.nextLine();
                    if (dragonType.isEmpty()) {
                        return null;
                    }
                    if (Arrays.stream(DragonTypeX.values()).noneMatch(el -> el.name().equals(dragonType))) {
                        callError(type.errorMessage);
                        continue;
                    }

                    return dragonType;
                }
                case DRAGON_CHARACTER: {
                    String dragonCharacter = scanner.nextLine();
                    if (Arrays.stream(DragonCharacterX.values()).noneMatch(el -> el.name().equals(dragonCharacter))) {
                        callError(type.errorMessage);
                        continue;
                    }

                    return dragonCharacter;
                }
                case DRAGON_COORDINATE_X: {
                    try {
                        if (!scanner.hasNextDouble()) {
                            System.out.println();
                            callError(type.errorMessage);
                            scanner.nextLine();
                            continue;
                        }
                        Double X = scanner.nextDouble();
                        if (X <= -36) {
                            callError(type.errorMessage);
                            continue;
                        }

                        return X;
                    } catch (InputMismatchException | NumberFormatException exception) {
                        System.out.println(Colors.RED + "Error: " + exception.getMessage() + Colors.RESET);
                        continue;
                    }

                }
                case DRAGON_COORDINATE_Y: {
                    try {
                        if (!scanner.hasNextFloat()) {
                            System.out.println();
                            callError(type.errorMessage);
                            scanner.nextLine();
                            continue;
                        }
                        Float Y = scanner.nextFloat();
                        return Y;
                    } catch (InputMismatchException | NumberFormatException exception) {
                        System.out.println(Colors.RED + "Error: " + exception.getMessage() + Colors.RESET);
                        continue;
                    }
                }
                case KILLER_NAME: {
                    String name = scanner.nextLine();
                    if (name.isEmpty()) {
                        callError(type.errorMessage);
                        continue;
                    }

                    return name;
                }
                case KILLER_PASSPORT_ID: {
                    String passportID = scanner.nextLine();

                    if (passportID.length() > 33 || passportID.isEmpty()) {
                        callError(type.errorMessage);
                        continue;
                    }

                    return passportID;
                }
                case KILLER_WEIGHT: {
                    try {
                        if (!scanner.hasNextInt()) {
                            System.out.println();
                            callError(type.errorMessage);
                            scanner.nextLine();
                            continue;
                        }
                        Integer weight = scanner.nextInt();
                        if (weight <= 0) {
                            callError(type.errorMessage);
                            continue;
                        }

                        return weight;
                    } catch (InputMismatchException | NumberFormatException exception) {
                        System.out.println(Colors.RED + "Error: " + exception.getMessage() + Colors.RESET);
                        continue;
                    }
                }
                case KILLER_LOCATION_X: {
                    try {
                        if (!scanner.hasNextInt()) {
                            System.out.println();
                            callError(type.errorMessage);
                            scanner.nextLine();
                            continue;
                        }
                        Integer X = scanner.nextInt();
                        return X;
                    } catch (InputMismatchException | NumberFormatException exception) {
                        System.out.println(Colors.RED + "Error: " + exception.getMessage() + Colors.RESET);
                        continue;
                    }
                }
                case KILLER_LOCATION_Y: {
                    try {
                        if (!scanner.hasNextLong()) {
                            System.out.println();
                            callError(type.errorMessage);
                            scanner.nextLine();
                            continue;
                        }
                        Long Y = scanner.nextLong();
                        return Y;
                    } catch (InputMismatchException | NumberFormatException exception) {
                        System.out.println(Colors.RED + "Error: " + exception.getMessage() + Colors.RESET);
                        continue;
                    }
                }
                case KILLER_LOCATION_Z: {
                    try {
                        if (!scanner.hasNextFloat()) {
                            System.out.println();
                            callError(type.errorMessage);
                            scanner.nextLine();
                            continue;
                        }
                        Float Z = scanner.nextFloat();
                        return Z;
                    } catch (InputMismatchException | NumberFormatException exception) {
                        System.out.println(Colors.RED + "Error: " + exception.getMessage() + Colors.RESET);
                        continue;
                    }
                }
                case KILLER_LOCATION_NAME: {
                    String name = scanner.nextLine();
                    if (name.isEmpty()) {
                        callError(type.errorMessage);
                        continue;
                    }
                    return name;
                }
            }
        }


    }


    public static Object validateCustom(BufferedReader bufferedReader, ValidationType type) throws IOException, IncorrectDataException {

        switch (type) {
            case DRAGON_NAME: {
                String name = bufferedReader.readLine();
                if (name.isEmpty()) {
                    throw new IncorrectDataException(type.errorMessage);
                }

                return name;
            }
            case DRAGON_AGE: {
                Long age = Long.parseLong(bufferedReader.readLine());

                if (age <= 0) {
                    throw new IncorrectDataException(type.errorMessage);
                }

                return age;
            }
            case DRAGON_DESC: {
                return bufferedReader.readLine();
            }
            case DRAGON_TYPE: {
                String dragonType = bufferedReader.readLine();
                if (dragonType.isEmpty()) {
                    return null;
                }
                if (Arrays.stream(DragonTypeX.values()).noneMatch(el -> el.name().equals(dragonType))) {
                    throw new IncorrectDataException(type.errorMessage);
                }

                return dragonType;
            }
            case DRAGON_CHARACTER: {
                String dragonCharacter = bufferedReader.readLine();
                if (Arrays.stream(DragonCharacterX.values()).noneMatch(el -> el.name().equals(dragonCharacter))) {
                    throw new IncorrectDataException(type.errorMessage);
                }

                return dragonCharacter;
            }
            case DRAGON_COORDINATE_X: {
                Double X = Double.parseDouble(bufferedReader.readLine());
                if (X <= -36) {
                    throw new IncorrectDataException(type.errorMessage);
                }

                return X;

            }
            case DRAGON_COORDINATE_Y: {

                Float Y = Float.parseFloat(bufferedReader.readLine());
                return Y;

            }
            case KILLER_NAME: {
                String killerName = bufferedReader.readLine();
                if (killerName.isEmpty()) {
                    throw new IncorrectDataException(type.errorMessage);
                }

                return killerName;
            }
            case KILLER_PASSPORT_ID: {
                String passportID = bufferedReader.readLine();

                if (passportID.length() > 33|| passportID.isEmpty()) {
                    throw new IncorrectDataException(type.errorMessage);
                }

                return passportID;
            }
            case KILLER_WEIGHT: {

                Integer weight = Integer.parseInt(bufferedReader.readLine());
                if (weight <= 0) {
                    throw new IncorrectDataException(type.errorMessage);
                }

                return weight;

            }
            case KILLER_LOCATION_X: {

                Integer X = Integer.parseInt(bufferedReader.readLine());
                return X;

            }
            case KILLER_LOCATION_Y: {

                Long Y = Long.parseLong(bufferedReader.readLine());
                return Y;

            }
            case KILLER_LOCATION_Z: {

                    Float Z = Float.parseFloat(bufferedReader.readLine());
                    return Z;

            }
            case KILLER_LOCATION_NAME: {
                String locName = bufferedReader.readLine();
                if (locName.isEmpty()) {
                    throw new IncorrectDataException(type.errorMessage);
                }
                return locName;
            }
        }
        return null;
    }


    private static void callError(String errorMessage) {
        System.out.println(Colors.RED + "Error: " + errorMessage + Colors.RESET);
    }


}
