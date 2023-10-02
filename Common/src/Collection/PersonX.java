package Collection;

import java.io.Serializable;

/**
 * Class with person type definition
 */

public class PersonX implements Serializable {


    private String name; //Поле не может быть null, Строка не может быть пустой

    private Integer weight; //Поле не может быть null, Значение поля должно быть больше 0

    private String passportID; //Длина строки не должна быть больше 33, Строка не может быть пустой, Значение этого поля должно быть уникальным

    private LocationX location; //Поле может быть null


    public PersonX(String name, Integer weight, String passportID, LocationX location) {
        this.name = name;
        this.weight = weight;
        this.passportID = passportID;
        this.location = location;
    }

    public PersonX() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public LocationX getLocation() {
        return location;
    }

    public void setLocation(LocationX location) {
        this.location = location;
    }

    public void showInfo() {
        System.out.println("killer name: " + this.name);
        System.out.println("killer weight: " + this.weight);
        System.out.println("killer passportID: " + this.passportID);
        System.out.printf("killer location: %s, координаты: (%d, %d, %f)", this.location.getName(), this.location.getX(), this.location.getY(), this.location.getZ());
    }

}