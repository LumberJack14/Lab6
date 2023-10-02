package Utils;

import Collection.DragonX;
import Collection.LocationX;
import Collection.PersonX;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Vector;

/**
 * Class that does parsing from Dragon object to JSON
 */

public class DragonToJSON {
    public static JSONObject parse(DragonX dragon) {
        JSONObject json = new JSONObject();

        json.put("id", dragon.getId());

        json.put("name", dragon.getName());

        JSONObject jsonCoordinates = new JSONObject();

        jsonCoordinates.put("x", dragon.getCoordinates().getX());
        jsonCoordinates.put("y", dragon.getCoordinates().getY());
        json.put("coordinates", jsonCoordinates);

        json.put("creationDate", dragon.getCreationDate().toString());

        json.put("age", dragon.getAge());

        json.put("description", dragon.getDescription());

        if (dragon.getType() == null) {
            json.put("type", "");
        } else {
            json.put("type", dragon.getType().toString());
        }

        json.put("character", dragon.getCharacter().toString());

        JSONObject jsonPerson = new JSONObject();
        PersonX killer = dragon.getKiller();
        jsonPerson.put("name", killer.getName());
        jsonPerson.put("weight", killer.getWeight());
        jsonPerson.put("passportID", killer.getPassportID());

        JSONObject killerLocatoin = new JSONObject();

        LocationX location = killer.getLocation();
        killerLocatoin.put("x", location.getX());
        killerLocatoin.put("y", location.getY());
        killerLocatoin.put("z", location.getZ());
        killerLocatoin.put("name", location.getName());

        jsonPerson.put("location", killerLocatoin);

        json.put("killer", jsonPerson);

        return json;
    }

    public static JSONObject createArrayInsideObject(Vector<DragonX> collection) {
        JSONArray res = new JSONArray();

        for (DragonX dragon : collection) {
            res.add(parse(dragon));
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dragons", res);

        return jsonObject;
    }
}
