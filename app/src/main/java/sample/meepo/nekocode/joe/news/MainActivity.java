package sample.meepo.nekocode.joe.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Handler;

import static sample.meepo.nekocode.joe.news.networkClass.JsonReturn;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private GridView mGridView;
    private GridAdapter mGridAdapter;
    private Spinner mSpinner;
    public  String category;
    private asyntask asyn;
    private   Uri uri;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] Categories = new String[]{"entertainment","general","health","science","sports","technology","business"};

          category = Categories[0];
        init();
        mContext = MainActivity.this;

        asyn = new asyntask();
        uri = networkClass.fromString(category);
        asyn.execute(uri);

        UniversalImageLoader uni = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(uni.getConfig());

        mSpinner = (Spinner) findViewById(R.id.Spinner);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item
                ,Categories);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setBackgroundColor(R.color.colorPrimary);
        mSpinner.setAdapter(arrayAdapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                category = Categories[i];

                android.os.Handler handler = new android.os.Handler();
                Runnable refresh = new Runnable() {
                    @Override
                    public void run() {
                        asyntask aa = new asyntask();
                        uri = networkClass.fromString(category);
                        aa.execute(uri);
                    }
                };
                handler.postDelayed(refresh,60);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void init(){
        mGridView = (GridView)findViewById(R.id.gridView);

    }



    private class asyntask extends AsyncTask<Uri,Void,ArrayList<news_model>>{

        @Override
        protected ArrayList<news_model> doInBackground(Uri... strings) {
            ArrayList<news_model> models = null ;
            try {
                models =  networkClass.JsonReturn(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return models;
        }

        @Override
        protected void onPostExecute(final ArrayList<news_model> models) {
            super.onPostExecute(models);
            int GridWidth = getResources().getDisplayMetrics().widthPixels;
            int ImageWidth = GridWidth/3;
            mGridView.setColumnWidth(ImageWidth);
            mGridAdapter = new GridAdapter(mContext,R.layout.custom_layout,models);
            mGridView.setAdapter(mGridAdapter);
            mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(),Full_detail.class);

                    String ImageUrls   = models.get(i).getImageUrl();
                    String title       = models.get(i).getTitle();
                    String name        = models.get(i).getName();
                    String author      = models.get(i).getAuthor();
                    String webURl      = models.get(i).getWebUrl();
                    String description = models.get(i).getDescription();

                    intent.putExtra("description",description);
                    intent.putExtra("webURL",webURl);
                    intent.putExtra("author",author);
                    intent.putExtra("name",name);
                    intent.putExtra("title",title);
                    intent.putExtra("ImageUrls",ImageUrls);

                    startActivity(intent);
                }
            });

        }
    }
}
