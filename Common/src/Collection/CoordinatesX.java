package Collection;

import java.io.Serializable;

/**
 * Class with Coordinates type definition
 */

public class CoordinatesX implements Serializable {


    private Double x; //Значение поля должно быть больше -36, Поле не может быть null


    private Float y; //Поле не может быть null

    public CoordinatesX(Double x, Float y) {
        this.x = x;
        this.y = y;
    }

    public CoordinatesX() {
    }


    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public static boolean isValidX(Double x) {
        return x > -36d;
    }

    public Double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }
}