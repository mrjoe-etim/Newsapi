package sample.meepo.nekocode.joe.news;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class json_class {

    private static final String TAG = "json_class";

    public static ArrayList<news_model> Jsonparser(String json_data) throws JSONException {
        news_model model = null ;

        if(TextUtils.isEmpty(json_data)){
            return null;
        }
        ArrayList<news_model> models = new ArrayList<>();

        JSONObject jsonArray = new JSONObject(json_data);
        JSONArray first = jsonArray.getJSONArray("articles");
        Log.d(TAG, "Jsonparser: getting Array" + jsonArray );
        for(int i =0 ; i<first.length() ; i++ ){

            JSONObject second  = first.getJSONObject(i);
            JSONObject third   = second.getJSONObject("source");
            String name        = third.getString("name");
            Log.d(TAG, "Jsonparser: getting name " + name);
            String Author      = second.getString("author");
            Log.d(TAG, "Jsonparser: getting Author" + Author);
            String title       = second.getString("title");
            Log.d(TAG, "Jsonparser: getting title" + title);
            String description = second.getString("description");
            Log.d(TAG, "Jsonparser: getting description" + description);
            String url         = second.getString("url");
            Log.d(TAG, "Jsonparser: getting url" + url);
            String image_url   = second.getString("urlToImage");
            Log.d(TAG, "Jsonparser: getting img_url" + image_url);
            String published_at= second.getString("publishedAt");
            Log.d(TAG, "Jsonparser: getting published_at" + published_at);

           model = new news_model(name,Author,title,image_url,description,url,published_at);

            models.add(model);
        }


        return models;
    }

}
