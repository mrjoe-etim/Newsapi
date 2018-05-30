package sample.meepo.nekocode.joe.news;


import android.net.Uri;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.R.attr.mode;
import static sample.meepo.nekocode.joe.news.json_class.Jsonparser;

public class networkClass {


    String url = "https://newsapi.org/v2/everything?q=bitcoin&apiKey=fd4bc23eda7c4538a3af211a959ccfd3";
    private static String BASE_URL = "https://newsapi.org/v2/everything";
    private static String PARAM1   =  "q";
    private static String PARAM2   =  "apiKey";
    private static String PARAM3   = "fd4bc23eda7c4538a3af211a959ccfd3";

    //The fromString takes in the selected spinner elements and append it as a query parameter
    public static Uri fromString(String category){

        Uri buildUri = Uri.parse(BASE_URL).buildUpon().appendQueryParameter(PARAM1,category)
                .appendQueryParameter(PARAM2,PARAM3).build();

        return buildUri;

    }

    //this method takes a uri and returns a URL for making HttpRequest
    public static URL create_url(Uri uri){

        URL urll  = null;

        try {
            urll = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return urll;
    }


    private static final String TAG = "networkClass";

    //this method takes a Url and retun the String for Json parsing after making connection with the ApI
    public static String MakeHttpRequest(URL url) throws IOException{
        Log.d(TAG, "MakeHttpRequest: Connecting to the server");
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        String JsonResponse = null;

        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            Log.d(TAG, "MakeHttpRequest: Connected");

            if(httpURLConnection.getResponseCode() == 200){
                Log.d(TAG, "MakeHttpRequest: Response code" + httpURLConnection.getResponseCode());

                inputStream = httpURLConnection.getInputStream();
                JsonResponse = ReadFromStream(inputStream);

                Log.d(TAG, "MakeHttpRequest: Gotten JsonString");

            }else{
                Log.d(TAG, "MakeHttpRequest: Error response code" + httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
     return JsonResponse;
    }


    private static String ReadFromStream(InputStream inputstream) throws IOException {

        Log.d(TAG, "ReadFromStream: Reading Stream");

        InputStreamReader inputstreamReader = null;
        BufferedReader    bufferedReader    = null;
        StringBuilder stringBuilder  = new StringBuilder();

        if(inputstream!=null){
            inputstreamReader = new InputStreamReader(inputstream);
            bufferedReader  = new BufferedReader(inputstreamReader);
            String line = bufferedReader.readLine();
            while (line!=null){
                stringBuilder.append(line);
                line = bufferedReader.readLine();
                Log.d(TAG, "ReadFromStream: Appending line");

            }
        }

        return stringBuilder.toString();

    }

    //THIS method returns arraylist to be populated on the GridView
    public static ArrayList<news_model> JsonReturn(Uri url_string) throws IOException {
        ArrayList<news_model> models = null;
        news_model model = null;
        String jsonData = null;

        URL url = create_url(url_string);

        jsonData =  MakeHttpRequest(url);

        json_class jsonClass = new json_class();
        try {


        models = jsonClass.Jsonparser(jsonData);

            Log.d(TAG, "JsonReturn: returning models" + models);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return models;
    }
}
