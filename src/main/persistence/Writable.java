package persistence;

import org.json.JSONObject;
//Writer interface for json reader and listofquizset class

public interface Writable {
    //EFFECTS:returns this as JSON object
    JSONObject toJson();
}
