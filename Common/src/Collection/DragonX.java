package Collection;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class with Dragon type definition. It also implements the comparing of the dragon elements
 */

public class DragonX implements Comparable<DragonX>, Serializable {

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически


    private String name; //Поле не может быть null, Строка не может быть пустой


    private CoordinatesX coordinates; //Поле не может быть null

    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически


    private Long age; //Значение поля должно быть больше 0, Поле не может быть null


    private String description; //Поле может быть null


    private DragonTypeX type; //Поле может быть null


    private DragonCharacterX character; //Поле не может быть null

    private PersonX killer; //Поле может быть null

    public DragonX(
            long id,
            String name,
            CoordinatesX coordinates,
            LocalDate creationDate,
            Long age,
            String description,
            DragonTypeX type,
            DragonCharacterX character,
            PersonX killer
    ) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.description = description;
        this.type = type;
        this.character = character;
        this.killer = killer;
    }

    public DragonX() {
    }


    @Override
    public int compareTo(DragonX dragon) {
        if (!Objects.equals(this.age, dragon.getAge())) {
            return (int) (this.age - dragon.getAge());
        }

        if (!Objects.equals(this.creationDate, dragon.getCreationDate())) {
            return this.creationDate.compareTo(dragon.getCreationDate());
        }

        return (this.name.length()) - dragon.getName().length();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoordinatesX getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesX coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DragonTypeX getType() {
        return type;
    }

    public void setType(DragonTypeX type) {
        this.type = type;
    }

    public DragonCharacterX getCharacter() {
        return character;
    }

    public void setCharacter(DragonCharacterX character) {
        this.character = character;
    }

    public PersonX getKiller() {
        return killer;
    }

    public void setKiller(PersonX killer) {
        this.killer = killer;
    }

    public void displayInfo() {
        String info = returnInfo();
        System.out.print(info);
    }

    public String returnInfo() {
        String info = "";
        info += "ID: " + id + "\n";
        info += "name: " + name + "\n";
        info += "age " + age + "\n";
        info += "description: " + description + "\n";
        String chr = character == null? "null" : character.toString();
        info += "dragon character: " + chr + "\n";
        String t = type == null? "null" : type.toString();
        info += "dragon type: " + t + "\n";
        info += "Coordinates: (" + coordinates.getX() + ", " + coordinates.getY() + ")" + "\n";
        info += "Creation date: " + creationDate.toString() + "\n";
        info += "killer name: " + killer.getName() + "\n";
        info += "killer's location name: " + killer.getLocation().getName() + "\n\n";

        return info;
    }
}