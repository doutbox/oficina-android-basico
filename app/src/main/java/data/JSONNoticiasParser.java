package data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by roger on 02/07/2017.
 */

public class JSONNoticiasParser {


        public static ArrayList<String> createArray(String data) {
            ArrayList<String> noticiasArrayList;
            JSONObject jsonObject;
            JSONArray jsonArray;
            try {
                jsonArray = new JSONArray(data);
                noticiasArrayList = new ArrayList<String>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    noticiasArrayList.add(jsonObject.getString("titulo"));
                }

                return noticiasArrayList;

            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
}
