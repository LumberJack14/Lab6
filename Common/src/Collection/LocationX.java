package Collection;

import java.io.Serializable;

/**
 * Class with Location type definition
 */

public class LocationX implements Serializable {


    private Integer x; //Поле не может быть null


    private Long y; //Поле не может быть null


    private Float z; //Поле не может быть null


    private String name; //Поле не может быть null

    public LocationX(Integer x, Long y, Float z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public LocationX() {}

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public void setZ(Float z) {
        this.z = z;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public Float getZ() {
        return z;
    }

    public String getName() {
        return name;
    }
}