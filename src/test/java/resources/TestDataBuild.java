package resources;

import POJO.AddPlaceBody;
import POJO.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlaceBody addPlacePayload(String name, String language, String address){

        AddPlaceBody ap = new AddPlaceBody();
        ap.setAccuracy(18);
        ap.setAddress(address);
        ap.setLanguage(language);
        ap.setPhone_number("(+91) 987 654 3210");
        ap.setWebsite("http://google.com");
        ap.setName(name);

        List<String> myTypeList = new ArrayList<String>();
        myTypeList.add("Crime Investigation");
        myTypeList.add("Puzzle");
        ap.setTypes(myTypeList);

        Location l = new Location();
        l.setLat(51.523772);
        l.setLng(-0.158538);
        ap.setLocation(l);

        return ap;

    }
}
