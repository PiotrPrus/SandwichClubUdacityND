package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich parsedSandwich = null;

        try{
            JSONObject jsonResponse = new JSONObject(json);

            JSONObject sandwichName = jsonResponse.getJSONObject("name");
            String sandwichMainName = sandwichName.getString("mainName");
            JSONArray sandwichAltNames = sandwichName.getJSONArray("alsoKnownAs");
            List<String> sandwichAltNamesList = new ArrayList<>();
            for (int i=0; i<sandwichAltNames.length(); i++){
                sandwichAltNamesList.add(sandwichAltNames.getString(i));
            }
            String sandwichPlaceOfOrigin = jsonResponse.getString("placeOfOrigin");
            String sandwichDescription = jsonResponse.getString("description");
            String sandwichImage = jsonResponse.getString("image");
            JSONArray sandwichIng = jsonResponse.getJSONArray("ingredients");
            List<String> sandwichIngList = new ArrayList<>();
            for (int i=0; i<sandwichIng.length(); i++){
                sandwichIngList.add(sandwichIng.getString(i));
            }
            parsedSandwich =
                    new Sandwich(sandwichMainName, sandwichAltNamesList, sandwichPlaceOfOrigin,
                            sandwichDescription, sandwichImage, sandwichIngList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return parsedSandwich;
    }
}
