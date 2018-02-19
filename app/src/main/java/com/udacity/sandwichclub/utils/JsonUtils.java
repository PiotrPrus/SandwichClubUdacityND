package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String KEY_JSON_OBJECT_SANDWICH_NAME = "name";
    private static final String KEY_JSON_STRING_MAIN_NAME = "mainName";
    private static final String KEY_JSON_ARRAY_ALT_NAMES = "alsoKnownAs";
    private static final String KEY_JSON_STRING_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String KEY_JSON_STRING_DESCRIPTION = "description";
    private static final String KEY_JSON_STRING_IMAGE = "image";
    private static final String KEY_JSON_ARRAY_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich parsedSandwich = null;

        try{
            JSONObject jsonResponse = new JSONObject(json);

            String sandwichMainName = "";
            List<String> sandwichAltNamesList = null;
            if (jsonResponse.has(KEY_JSON_OBJECT_SANDWICH_NAME)) {
                JSONObject sandwichName = jsonResponse.optJSONObject(KEY_JSON_OBJECT_SANDWICH_NAME);
                sandwichMainName = sandwichName.optString(KEY_JSON_STRING_MAIN_NAME);
                JSONArray sandwichAltNames = sandwichName.optJSONArray(KEY_JSON_ARRAY_ALT_NAMES);
                sandwichAltNamesList = new ArrayList<>();
                for (int i=0; i<sandwichAltNames.length(); i++){
                    sandwichAltNamesList.add(sandwichAltNames.getString(i));
                }
            }
            String sandwichPlaceOfOrigin = null;
            if (jsonResponse.has(KEY_JSON_STRING_PLACE_OF_ORIGIN)) {
                sandwichPlaceOfOrigin = jsonResponse.getString(KEY_JSON_STRING_PLACE_OF_ORIGIN);
            }
            String sandwichDescription = null;
            if (jsonResponse.has(KEY_JSON_STRING_DESCRIPTION)) {
                sandwichDescription = jsonResponse.getString(KEY_JSON_STRING_DESCRIPTION);
            }
            String sandwichImage = null;
            if (jsonResponse.has(KEY_JSON_STRING_IMAGE)) {
                sandwichImage = jsonResponse.getString(KEY_JSON_STRING_IMAGE);
            }
            List<String> sandwichIngList = null;
            if (jsonResponse.has(KEY_JSON_ARRAY_INGREDIENTS)) {
                JSONArray sandwichIng = jsonResponse.getJSONArray(KEY_JSON_ARRAY_INGREDIENTS);
                sandwichIngList = new ArrayList<>();
                for (int i=0; i<sandwichIng.length(); i++){
                    sandwichIngList.add(sandwichIng.getString(i));
                }
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
